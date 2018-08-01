package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;

/**
 * eai_coinbase.
 */
public class EaiCoinbase extends Response<String> {
    public String getAddress() {
        return getResult();
    }
}
