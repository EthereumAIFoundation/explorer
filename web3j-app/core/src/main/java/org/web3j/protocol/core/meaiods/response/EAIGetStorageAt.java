package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;

/**
 * eai_getStorageAt.
 */
public class EaiGetStorageAt extends Response<String> {
    public String getData() {
        return getResult();
    }
}
