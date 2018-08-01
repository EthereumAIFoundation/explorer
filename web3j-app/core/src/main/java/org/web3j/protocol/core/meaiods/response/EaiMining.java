package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;

/**
 * eai_mining.
 */
public class EaiMining extends Response<Boolean> {
    public boolean isMining() {
        return getResult();
    }
}
