package org.web3j.tx;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import org.web3j.crypto.SampleKeys;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EaiGasPrice;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.utils.Convert;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransferTest extends ManagedTransactionTester {

    private TransactionReceipt transactionReceipt;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        transactionReceipt = prepareTransfer();
    }

    @Test
    public void testSendFunds() throws Exception {
        assertThat(Transfer.sendFunds(web3j, SampleKeys.CREDENTIALS, ADDRESS,
                BigDecimal.TEN, Convert.Unit.ETHERAI).send(),
                is(transactionReceipt));
    }

    @Test
    public void testSendFundsAsync() throws  Exception {
        assertThat(Transfer.sendFunds(web3j, SampleKeys.CREDENTIALS, ADDRESS,
                BigDecimal.TEN, Convert.Unit.ETHERAI).send(),
                is(transactionReceipt));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testTransferInvalidValue() throws Exception {
        Transfer.sendFunds(web3j, SampleKeys.CREDENTIALS, ADDRESS,
                new BigDecimal(0.1), Convert.Unit.WEI).send();
    }

    @SuppressWarnings("unchecked")
    private TransactionReceipt prepareTransfer() throws IOException {
        TransactionReceipt transactionReceipt = new TransactionReceipt();
        transactionReceipt.setTransactionHash(TRANSACTION_HASH);
        transactionReceipt.setStatus("0x1");
        prepareTransaction(transactionReceipt);

        EaiGasPrice eaiGasPrice = new EaiGasPrice();
        eaiGasPrice.setResult("0x1");

        Request<?, EaiGasPrice> gasPriceRequest = mock(Request.class);
        when(gasPriceRequest.send()).thenReturn(eaiGasPrice);
        when(web3j.eaiGasPrice()).thenReturn((Request) gasPriceRequest);

        return transactionReceipt;
    }
}
