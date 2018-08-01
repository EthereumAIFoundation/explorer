package org.web3j.protocol.core.methods.response;

import java.util.List;

import org.web3j.protocol.core.Response;

/**
 * eai_getWork.
 */
public class EaiGetWork extends Response<List<String>> {

    public String getCurrentBlockHeaderPowHash() {
        return getResult().get(0);
    }

    public String getSeedHashForDag() {
        return getResult().get(1);
    }

    public String getBoundaryCondition() {
        return getResult().get(2);
    }
}
