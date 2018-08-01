package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;

/**
 * eai_getCode.
 */
public class EaiGetCode extends Response<String> {
    public String getCode() {
        return getResult();
    }
}
