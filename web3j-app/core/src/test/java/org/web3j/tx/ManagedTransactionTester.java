package org.web3j.tx;

import java.io.IOException;

import org.junit.Before;

import org.web3j.crypto.SampleKeys;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EaiGetTransactionCount;
import org.web3j.protocol.core.methods.response.EaiGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EaiSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public abstract class ManagedTransactionTester {

    static final String ADDRESS = "0x3d6cb163f7c72d20b0fcd6baae5889329d138a4a";
    static final String TRANSACTION_HASH = "0xHASH";
    protected Web3j web3j;

    @Before
    public void setUp() throws Exception {
        web3j = mock(Web3j.class);
    }

    void prepareTransaction(TransactionReceipt transactionReceipt) throws IOException {
        prepareNonceRequest();
        prepareTransactionRequest();
        prepareTransactionReceipt(transactionReceipt);
    }

    @SuppressWarnings("unchecked")
    void prepareNonceRequest() throws IOException {
        EaiGetTransactionCount eaiGetTransactionCount = new EaiGetTransactionCount();
        eaiGetTransactionCount.setResult("0x1");

        Request<?, EaiGetTransactionCount> transactionCountRequest = mock(Request.class);
        when(transactionCountRequest.send())
                .thenReturn(eaiGetTransactionCount);
        when(web3j.eaiGetTransactionCount(SampleKeys.ADDRESS, DefaultBlockParameterName.PENDING))
                .thenReturn((Request) transactionCountRequest);
    }

    @SuppressWarnings("unchecked")
    void prepareTransactionRequest() throws IOException {
        EaiSendTransaction eaiSendTransaction = new EaiSendTransaction();
        eaiSendTransaction.setResult(TRANSACTION_HASH);

        Request<?, EaiSendTransaction> rawTransactionRequest = mock(Request.class);
        when(rawTransactionRequest.send()).thenReturn(eaiSendTransaction);
        when(web3j.eaiSendRawTransaction(any(String.class)))
                .thenReturn((Request) rawTransactionRequest);
    }

    @SuppressWarnings("unchecked")
    void prepareTransactionReceipt(TransactionReceipt transactionReceipt) throws IOException {
        EaiGetTransactionReceipt eaiGetTransactionReceipt = new EaiGetTransactionReceipt();
        eaiGetTransactionReceipt.setResult(transactionReceipt);

        Request<?, EaiGetTransactionReceipt> getTransactionReceiptRequest = mock(Request.class);
        when(getTransactionReceiptRequest.send())
                .thenReturn(eaiGetTransactionReceipt);
        when(web3j.eaiGetTransactionReceipt(TRANSACTION_HASH))
                .thenReturn((Request) getTransactionReceiptRequest);
    }
}
