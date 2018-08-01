package org.web3j.protocol.core.methods.response;

import org.web3j.protocol.core.Response;

/**
 * eai_protocolVersion.
 */
public class EaiProtocolVersion extends Response<String> {
    public String getProtocolVersion() {
        return getResult();
    }
}
