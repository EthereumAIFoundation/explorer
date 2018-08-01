package org.web3j.protocol.core.methods.response;

import java.math.BigInteger;

import org.web3j.protocol.core.Response;
import org.web3j.utils.Numeric;

/**
 * eai_blockNumber.
 */
public class EaiBlockNumber extends Response<String> {
    public BigInteger getBlockNumber() {
        return Numeric.decodeQuantity(getResult());
    }
}
