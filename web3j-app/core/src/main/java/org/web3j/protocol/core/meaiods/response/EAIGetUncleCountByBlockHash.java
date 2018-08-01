package org.web3j.protocol.core.methods.response;

import java.math.BigInteger;

import org.web3j.protocol.core.Response;
import org.web3j.utils.Numeric;

/**
 * eai_getUncleCountByBlockHash.
 */
public class EaiGetUncleCountByBlockHash extends Response<String> {
    public BigInteger getUncleCount() {
        return Numeric.decodeQuantity(getResult());
    }
}
