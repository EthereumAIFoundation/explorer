package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;

/**
 * eai_submitHashrate.
 */
public class EaiSubmitHashrate extends Response<Boolean> {

    public boolean submissionSuccessful() {
        return getResult();
    }
}
