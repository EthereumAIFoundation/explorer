package org.web3j.protocol.core.filters;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.web3j.protocol.Web3j;

import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.Response;
import org.web3j.protocol.core.methods.response.EaiFilter;
import org.web3j.protocol.core.methods.response.EaiLog;
import org.web3j.protocol.core.methods.response.EaiUninstallFilter;


/**
 * Class for creating managed filter requests with callbacks.
 */
public abstract class Filter<T> {

    private static final Logger log = LoggerFactory.getLogger(Filter.class);

    final Web3j web3j;
    final Callback<T> callback;

    private volatile BigInteger filterId;

    private ScheduledFuture<?> schedule;

    public Filter(Web3j web3j, Callback<T> callback) {
        this.web3j = web3j;
        this.callback = callback;
    }

    public void run(ScheduledExecutorService scheduledExecutorService, long blockTime) {
        try {
            EaiFilter eaiFilter = sendRequest();
            if (eaiFilter.hasError()) {
                throwException(eaiFilter.getError());
            }

            filterId = eaiFilter.getFilterId();
            // this runs in the caller thread as if any exceptions are encountered, we shouldn't
            // proceed with creating the scheduled task below
            getInitialFilterLogs();

            /*
            We want the filter to be resilient against client issues. On numerous occasions
            users have reported socket timeout exceptions when connected over HTTP to Geai and
            Parity clients. For examples, refer to
            https://github.com/web3j/web3j/issues/144 and
            https://github.com/ethereumai/go-ethereumai/issues/15243.

            Hence we consume errors and log them as errors, allowing our polling for changes to
            resume. The downside of this approach is that users will not be notified of
            downstream connection issues. But given the intermittent nature of the connection
            issues, this seems like a reasonable compromise.

            The alternative approach would be to have another thread that blocks waiting on
            schedule.get(), catching any Exceptions thrown, and passing them back up to the
            caller. However, the user would then be required to recreate subscriptions manually
            which isn't ideal given the aforementioned issues.
            */
            schedule = scheduledExecutorService.scheduleAtFixedRate(
                    () -> {
                        try {
                            this.pollFilter(eaiFilter);
                        } catch (Throwable e) {
                            // All exceptions must be caught, otherwise our job terminates without
                            // any notification
                            log.error("Error sending request", e);
                        }
                    },
                    0, blockTime, TimeUnit.MILLISECONDS);
        } catch (IOException e) {
            throwException(e);
        }
    }

    private void getInitialFilterLogs() {
        try {
            Optional<Request<?, EaiLog>> maybeRequest = this.getFilterLogs(this.filterId);
            EaiLog eaiLog = null;
            if (maybeRequest.isPresent()) {
                eaiLog = maybeRequest.get().send();
            } else {
                eaiLog = new EaiLog();
                eaiLog.setResult(Collections.emptyList());
            }
            process(eaiLog.getLogs());

        } catch (IOException e) {
            throwException(e);
        }
    }

    private void pollFilter(EaiFilter eaiFilter) {
        EaiLog eaiLog = null;
        try {
            eaiLog = web3j.eaiGetFilterChanges(filterId).send();
        } catch (IOException e) {
            throwException(e);
        }
        if (eaiLog.hasError()) {
            throwException(eaiLog.getError());
        } else {
            process(eaiLog.getLogs());
        }
    }

    abstract EaiFilter sendRequest() throws IOException;

    abstract void process(List<EaiLog.LogResult> logResults);

    public void cancel() {
        schedule.cancel(false);

        try {
            EaiUninstallFilter eaiUninstallFilter = web3j.eaiUninstallFilter(filterId).send();
            if (eaiUninstallFilter.hasError()) {
                throwException(eaiUninstallFilter.getError());
            }

            if (!eaiUninstallFilter.isUninstalled()) {
                throw new FilterException("Filter with id '" + filterId + "' failed to uninstall");
            }
        } catch (IOException e) {
            throwException(e);
        }
    }

    /**
     * Retrieves historic filters for the filter with the given id.
     * Getting historic logs is not supported by all filters.
     * If not the method should return an empty EaiLog object
     *
     * @param filterId Id of the filter for which the historic log should be retrieved
     * @return Historic logs, or an empty optional if the filter cannot retrieve historic logs
     */
    protected abstract Optional<Request<?, EaiLog>> getFilterLogs(BigInteger filterId);

    void throwException(Response.Error error) {
        throw new FilterException("Invalid request: "
                + (error == null ? "Unknown Error" : error.getMessage()));
    }

    void throwException(Throwable cause) {
        throw new FilterException("Error sending request", cause);
    }
}

