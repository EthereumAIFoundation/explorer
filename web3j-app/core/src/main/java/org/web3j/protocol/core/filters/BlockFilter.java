package org.web3j.protocol.core.filters;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EaiFilter;
import org.web3j.protocol.core.methods.response.EaiLog;

/**
 * Handler for working with block filter requests.
 */
public class BlockFilter extends Filter<String> {

    public BlockFilter(Web3j web3j, Callback<String> callback) {
        super(web3j, callback);
    }

    @Override
    EaiFilter sendRequest() throws IOException {
        return web3j.eaiNewBlockFilter().send();
    }

    @Override
    void process(List<EaiLog.LogResult> logResults) {
        for (EaiLog.LogResult logResult : logResults) {
            if (logResult instanceof EaiLog.Hash) {
                String blockHash = ((EaiLog.Hash) logResult).get();
                callback.onEvent(blockHash);
            } else {
                throw new FilterException(
                        "Unexpected result type: " + logResult.get() + ", required Hash");
            }
        }
    }

    /**
     * Since the block filter does not support historic filters, the filterId is ignored
     * and an empty optional is returned.
     * @param filterId
     * Id of the filter for which the historic log should be retrieved
     * @return
     * Optional.empty()
     */
    @Override
    protected Optional<Request<?, EaiLog>> getFilterLogs(BigInteger filterId) {
        return Optional.empty();
    }
}

