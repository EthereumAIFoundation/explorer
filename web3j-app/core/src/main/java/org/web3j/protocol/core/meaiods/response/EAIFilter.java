package org.web3j.protocol.core.methods.response;

import java.math.BigInteger;

import org.web3j.protocol.core.Response;
import org.web3j.utils.Numeric;

/**
 * eai_newFilter.
 */
public class EaiFilter extends Response<String> {
    public BigInteger getFilterId() {
        return Numeric.decodeQuantity(getResult());
    }
}
