package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;

/**
 * eai_sendTransaction.
 */
public class EaiSendTransaction extends Response<String> {
    public String getTransactionHash() {
        return getResult();
    }
}
