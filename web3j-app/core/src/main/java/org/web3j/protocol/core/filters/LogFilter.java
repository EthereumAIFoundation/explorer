package org.web3j.protocol.core.filters;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EaiFilter;
import org.web3j.protocol.core.methods.response.EaiLog;
import org.web3j.protocol.core.methods.response.Log;

/**
 * Log filter handler.
 */
public class LogFilter extends Filter<Log> {

    private final org.web3j.protocol.core.methods.request.EaiFilter eaiFilter;

    public LogFilter(
            Web3j web3j, Callback<Log> callback,
            org.web3j.protocol.core.methods.request.EaiFilter eaiFilter) {
        super(web3j, callback);
        this.eaiFilter = eaiFilter;
    }


    @Override
    EaiFilter sendRequest() throws IOException {
        return web3j.eaiNewFilter(eaiFilter).send();
    }

    @Override
    void process(List<EaiLog.LogResult> logResults) {
        for (EaiLog.LogResult logResult : logResults) {
            if (logResult instanceof EaiLog.LogObject) {
                Log log = ((EaiLog.LogObject) logResult).get();
                callback.onEvent(log);
            } else {
                throw new FilterException(
                        "Unexpected result type: " + logResult.get() + " required LogObject");
            }
        }
    }

    @Override
    protected Optional<Request<?, EaiLog>> getFilterLogs(BigInteger filterId) {
        return Optional.of(web3j.eaiGetFilterLogs(filterId));
    }
}
