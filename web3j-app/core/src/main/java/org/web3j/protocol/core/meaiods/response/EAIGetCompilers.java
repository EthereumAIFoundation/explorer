package org.web3j.protocol.core.methods.response;

import java.util.List;

import org.web3j.protocol.core.Response;

/**
 * eai_getCompilers.
 */
public class EaiGetCompilers extends Response<List<String>> {
    public List<String> getCompilers() {
        return getResult();
    }
}
