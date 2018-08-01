package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;

/**
 * eai_call.
 */
public class EaiCall extends Response<String> {
    public String getValue() {
        return getResult();
    }
}
