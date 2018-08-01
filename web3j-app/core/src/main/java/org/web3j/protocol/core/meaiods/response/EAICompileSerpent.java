package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;

/**
 * eai_compileSerpent.
 */
public class EaiCompileSerpent extends Response<String> {
    public String getCompiledSourceCode() {
        return getResult();
    }
}
