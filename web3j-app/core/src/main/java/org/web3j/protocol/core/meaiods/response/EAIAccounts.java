package org.web3j.protocol.core.methods.response;

import java.util.List;

import org.web3j.protocol.core.Response;

/**
 * eai_accounts.
 */
public class EaiAccounts extends Response<List<String>> {
    public List<String> getAccounts() {
        return getResult();
    }
}
