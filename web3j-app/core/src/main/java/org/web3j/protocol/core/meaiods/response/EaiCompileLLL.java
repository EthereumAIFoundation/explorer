package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;

/**
 * eai_compileLLL.
 */
public class EaiCompileLLL extends Response<String> {
    public String getCompiledSourceCode() {
        return getResult();
    }
}
