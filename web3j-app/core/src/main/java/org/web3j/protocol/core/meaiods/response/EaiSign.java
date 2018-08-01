package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;

/**
 * eai_sign.
 */
public class EaiSign extends Response<String> {
    public String getSignature() {
        return getResult();
    }
}
