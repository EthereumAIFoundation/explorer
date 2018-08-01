package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;

/**
 * eai_sendRawTransaction.
 */
public class EaiSendRawTransaction extends Response<String> {
    public String getTransactionHash() {
        return getResult();
    }
}
