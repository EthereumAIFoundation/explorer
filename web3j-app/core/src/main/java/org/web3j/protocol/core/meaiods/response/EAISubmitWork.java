package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;

/**
 * eai_submitWork.
 */
public class EaiSubmitWork extends Response<Boolean> {

    public boolean solutionValid() {
        return getResult();
    }
}
