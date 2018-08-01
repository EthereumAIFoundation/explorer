package org.web3j.protocol.core;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigInteger;
import java.util.List;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EaiAccounts;
import org.web3j.protocol.core.methods.response.EaiBlock;
import org.web3j.protocol.core.methods.response.EaiBlock.TransactionObject;
import org.web3j.protocol.core.methods.response.EaiBlock.TransactionResult;
import org.web3j.protocol.core.methods.response.EaiBlockNumber;
import org.web3j.protocol.core.methods.response.EaiCall;
import org.web3j.protocol.core.methods.response.EaiCoinbase;
import org.web3j.protocol.core.methods.response.EaiCompileLLL;
import org.web3j.protocol.core.methods.response.EaiCompileSerpent;
import org.web3j.protocol.core.methods.response.EaiCompileSolidity;
import org.web3j.protocol.core.methods.response.EaiEstimateGas;
import org.web3j.protocol.core.methods.response.EaiFilter;
import org.web3j.protocol.core.methods.response.EaiGasPrice;
import org.web3j.protocol.core.methods.response.EaiGetBalance;
import org.web3j.protocol.core.methods.response.EaiGetBlockTransactionCountByHash;
import org.web3j.protocol.core.methods.response.EaiGetBlockTransactionCountByNumber;
import org.web3j.protocol.core.methods.response.EaiGetCode;
import org.web3j.protocol.core.methods.response.EaiGetCompilers;
import org.web3j.protocol.core.methods.response.EaiGetStorageAt;
import org.web3j.protocol.core.methods.response.EaiGetTransactionCount;
import org.web3j.protocol.core.methods.response.EaiGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EaiGetUncleCountByBlockHash;
import org.web3j.protocol.core.methods.response.EaiGetUncleCountByBlockNumber;
import org.web3j.protocol.core.methods.response.EaiHashrate;
import org.web3j.protocol.core.methods.response.EaiLog;
import org.web3j.protocol.core.methods.response.EaiMining;
import org.web3j.protocol.core.methods.response.EaiProtocolVersion;
import org.web3j.protocol.core.methods.response.EaiSendTransaction;
import org.web3j.protocol.core.methods.response.EaiSyncing;
import org.web3j.protocol.core.methods.response.EaiTransaction;
import org.web3j.protocol.core.methods.response.EaiUninstallFilter;
import org.web3j.protocol.core.methods.response.NetListening;
import org.web3j.protocol.core.methods.response.NetPeerCount;
import org.web3j.protocol.core.methods.response.NetVersion;
import org.web3j.protocol.core.methods.response.ShhNewGroup;
import org.web3j.protocol.core.methods.response.ShhNewIdentity;
import org.web3j.protocol.core.methods.response.ShhVersion;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.TxPoolContent;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.core.methods.response.Web3Sha3;
import org.web3j.protocol.ipc.UnixIpcService;

/**
 * JSON-RPC 2.0 Integration Tests.
 */
public class CoreIT {

    private Web3j web3j;

    private IntegrationTestConfig config = new TestnetConfig();

    public CoreIT() { }

    @Before
    public void setUp() {
        //this.web3j = Web3j.build(new HttpService());
        this.web3j = Web3j.build(new UnixIpcService("/Users/bing/test/data/geai.ipc"));
    }

    @Test
    public void testEaiGetBalance1() throws Exception {
        EaiGetBalance eaiGetBalance =
            web3j.eaiGetBalance("0x328c2504e15b05572df94f0df385cf3c4b130879", DefaultBlockParameter.valueOf("latest")).send();
        System.out.println(eaiGetBalance.getBalance());
    }

    @Test
    public void testEaiGetBlockByNumber() throws Exception {
        EaiBlock eaiBlock = web3j.eaiGetBlockByNumber(
            DefaultBlockParameter.valueOf(BigInteger.valueOf(2391)), true).send();

        EaiBlock.Block block = eaiBlock.getBlock();
        assertNotNull(eaiBlock.getBlock());

        for (TransactionResult tx : block.getTransactions()) {
            System.out.println(((TransactionObject) tx).getCreates());
            EaiTransaction eaiTransaction = web3j.eaiGetTransactionByHash(((TransactionObject) tx).getHash()).send();
            System.out.println(eaiTransaction.getTransaction().get().getCreates());
        }
        System.out.println(new ObjectMapper().writeValueAsString(block));
    }

    @Test
    public void testDebugGetModifiedAccountsByNumber() throws Exception {
        EaiAccounts accounts = web3j.debugGetModifiedAccountsByNumber(0, 390).send();
        List<String> addrs = accounts.getAccounts();
        System.out.println("EthereumAI accounts: " + addrs);
        assertFalse(addrs.isEmpty());
    }

    @Test
    public void testTxPoolContent() throws Exception {
        TxPoolContent txPoolContent = web3j.txPoolContent().send();
        List<Transaction> content = txPoolContent.getPendingTransactions();
        assertFalse(content.isEmpty());
    }

    @Test
    public void testWeb3ClientVersion() throws Exception {
        Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
        System.out.println("EthereumAI client version: " + clientVersion);
        assertFalse(clientVersion.isEmpty());
    }

    @Test
    public void testWeb3Sha3() throws Exception {
        Web3Sha3 web3Sha3 = web3j.web3Sha3("0x68656c6c6f20776f726c64").send();
        assertThat(web3Sha3.getResult(),
                is("0x47173285a8d7341e5e972fc677286384f802f8ef42a5ec5f03bbfa254cb01fad"));
    }

    @Test
    public void testNetVersion() throws Exception {
        NetVersion netVersion = web3j.netVersion().send();
        assertFalse(netVersion.getNetVersion().isEmpty());
    }

    @Test
    public void testNetListening() throws Exception {
        NetListening netListening = web3j.netListening().send();
        assertTrue(netListening.isListening());
    }

    @Test
    public void testNetPeerCount() throws Exception {
        NetPeerCount netPeerCount = web3j.netPeerCount().send();
        assertTrue(netPeerCount.getQuantity().signum() == 1);
    }

    @Test
    public void testEaiProtocolVersion() throws Exception {
        EaiProtocolVersion eaiProtocolVersion = web3j.eaiProtocolVersion().send();
        assertFalse(eaiProtocolVersion.getProtocolVersion().isEmpty());
    }

    @Test
    public void testEaiSyncing() throws Exception {
        EaiSyncing eaiSyncing = web3j.eaiSyncing().send();
        assertNotNull(eaiSyncing.getResult());
    }

    @Test
    public void testEaiCoinbase() throws Exception {
        EaiCoinbase eaiCoinbase = web3j.eaiCoinbase().send();
        assertNotNull(eaiCoinbase.getAddress());
    }

    @Test
    public void testEaiMining() throws Exception {
        EaiMining eaiMining = web3j.eaiMining().send();
        assertNotNull(eaiMining.getResult());
    }

    @Test
    public void testEaiHashrate() throws Exception {
        EaiHashrate eaiHashrate = web3j.eaiHashrate().send();
        assertThat(eaiHashrate.getHashrate(), is(BigInteger.ZERO));
    }

    @Test
    public void testEaiGasPrice() throws Exception {
        EaiGasPrice eaiGasPrice = web3j.eaiGasPrice().send();
        assertTrue(eaiGasPrice.getGasPrice().signum() == 1);
    }

    @Test
    public void testEaiAccounts() throws Exception {
        EaiAccounts eaiAccounts = web3j.eaiAccounts().send();
        assertNotNull(eaiAccounts.getAccounts());
    }

    @Test
    public void testEaiBlockNumber() throws Exception {
        EaiBlockNumber eaiBlockNumber = web3j.eaiBlockNumber().send();
        assertTrue(eaiBlockNumber.getBlockNumber().signum() == 1);
    }

    @Test
    public void testEaiGetBalance() throws Exception {
        EaiGetBalance eaiGetBalance = web3j.eaiGetBalance(
                config.validAccount(), DefaultBlockParameter.valueOf("latest")).send();
        assertTrue(eaiGetBalance.getBalance().signum() == 1);
    }

    @Test
    public void testEaiGetStorageAt() throws Exception {
        EaiGetStorageAt eaiGetStorageAt = web3j.eaiGetStorageAt(
                config.validContractAddress(),
                BigInteger.valueOf(0),
                DefaultBlockParameter.valueOf("latest")).send();
        assertThat(eaiGetStorageAt.getData(), is(config.validContractAddressPositionZero()));
    }

    @Test
    public void testEaiGetTransactionCount() throws Exception {
        EaiGetTransactionCount eaiGetTransactionCount = web3j.eaiGetTransactionCount(
                config.validAccount(),
                DefaultBlockParameter.valueOf("latest")).send();
        assertTrue(eaiGetTransactionCount.getTransactionCount().signum() == 1);
    }

    @Test
    public void testEaiGetBlockTransactionCountByHash() throws Exception {
        EaiGetBlockTransactionCountByHash eaiGetBlockTransactionCountByHash =
                web3j.eaiGetBlockTransactionCountByHash(
                        config.validBlockHash()).send();
        assertThat(eaiGetBlockTransactionCountByHash.getTransactionCount(),
                equalTo(config.validBlockTransactionCount()));
    }

    @Test
    public void testEaiGetBlockTransactionCountByNumber() throws Exception {
        EaiGetBlockTransactionCountByNumber eaiGetBlockTransactionCountByNumber =
                web3j.eaiGetBlockTransactionCountByNumber(
                        DefaultBlockParameter.valueOf(config.validBlock())).send();
        assertThat(eaiGetBlockTransactionCountByNumber.getTransactionCount(),
                equalTo(config.validBlockTransactionCount()));
    }

    @Test
    public void testEaiGetUncleCountByBlockHash() throws Exception {
        EaiGetUncleCountByBlockHash eaiGetUncleCountByBlockHash =
                web3j.eaiGetUncleCountByBlockHash(config.validBlockHash()).send();
        assertThat(eaiGetUncleCountByBlockHash.getUncleCount(),
                equalTo(config.validBlockUncleCount()));
    }

    @Test
    public void testEaiGetUncleCountByBlockNumber() throws Exception {
        EaiGetUncleCountByBlockNumber eaiGetUncleCountByBlockNumber =
                web3j.eaiGetUncleCountByBlockNumber(
                        DefaultBlockParameter.valueOf("latest")).send();
        assertThat(eaiGetUncleCountByBlockNumber.getUncleCount(),
                equalTo(config.validBlockUncleCount()));
    }

    @Test
    public void testEaiGetCode() throws Exception {
        EaiGetCode eaiGetCode = web3j.eaiGetCode(config.validContractAddress(),
                DefaultBlockParameter.valueOf(config.validBlock())).send();
        assertThat(eaiGetCode.getCode(), is(config.validContractCode()));
    }

    @Ignore  // TODO: Once account unlock functionality is available
    @Test
    public void testEaiSign() throws Exception {
        // EaiSign eaiSign = web3j.eaiSign();
    }

    @Ignore  // TODO: Once account unlock functionality is available
    @Test
    public void testEaiSendTransaction() throws Exception {
        EaiSendTransaction eaiSendTransaction = web3j.eaiSendTransaction(
                config.buildTransaction()).send();
        assertFalse(eaiSendTransaction.getTransactionHash().isEmpty());
    }

    @Ignore  // TODO: Once account unlock functionality is available
    @Test
    public void testEaiSendRawTransaction() throws Exception {

    }

    @Test
    public void testEaiCall() throws Exception {
        EaiCall eaiCall = web3j.eaiCall(config.buildTransaction(),
                DefaultBlockParameter.valueOf("latest")).send();

        assertThat(DefaultBlockParameterName.LATEST.getValue(), is("latest"));
        assertThat(eaiCall.getValue(), is("0x"));
    }

    @Test
    public void testEaiEstimateGas() throws Exception {
        EaiEstimateGas eaiEstimateGas = web3j.eaiEstimateGas(config.buildTransaction())
                .send();
        assertTrue(eaiEstimateGas.getAmountUsed().signum() == 1);
    }

    @Test
    public void testEaiGetBlockByHashReturnHashObjects() throws Exception {
        EaiBlock eaiBlock = web3j.eaiGetBlockByHash(config.validBlockHash(), false)
                .send();

        EaiBlock.Block block = eaiBlock.getBlock();
        assertNotNull(eaiBlock.getBlock());
        assertThat(block.getNumber(), equalTo(config.validBlock()));
        assertThat(block.getTransactions().size(),
                is(config.validBlockTransactionCount().intValue()));
    }

    @Test
    public void testEaiGetBlockByHashReturnFullTransactionObjects() throws Exception {
        EaiBlock eaiBlock = web3j.eaiGetBlockByHash(config.validBlockHash(), true)
                .send();

        EaiBlock.Block block = eaiBlock.getBlock();
        assertNotNull(eaiBlock.getBlock());
        assertThat(block.getNumber(), equalTo(config.validBlock()));
        assertThat(block.getTransactions().size(),
                equalTo(config.validBlockTransactionCount().intValue()));
    }

    @Test
    public void testEaiGetBlockByNumberReturnHashObjects() throws Exception {
        EaiBlock eaiBlock = web3j.eaiGetBlockByNumber(
                DefaultBlockParameter.valueOf(config.validBlock()), false).send();

        EaiBlock.Block block = eaiBlock.getBlock();
        assertNotNull(eaiBlock.getBlock());
        assertThat(block.getNumber(), equalTo(config.validBlock()));
        assertThat(block.getTransactions().size(),
                equalTo(config.validBlockTransactionCount().intValue()));
    }

    @Test
    public void testEaiGetBlockByNumberReturnTransactionObjects() throws Exception {
        EaiBlock eaiBlock = web3j.eaiGetBlockByNumber(
                DefaultBlockParameter.valueOf(config.validBlock()), true).send();

        EaiBlock.Block block = eaiBlock.getBlock();
        assertNotNull(eaiBlock.getBlock());
        assertThat(block.getNumber(), equalTo(config.validBlock()));
        assertThat(block.getTransactions().size(),
                equalTo(config.validBlockTransactionCount().intValue()));
    }

    @Test
    public void testEaiGetTransactionByHash() throws Exception {
        EaiTransaction eaiTransaction = web3j.eaiGetTransactionByHash(
                config.validTransactionHash()).send();
        assertTrue(eaiTransaction.getTransaction().isPresent());
        Transaction transaction = eaiTransaction.getTransaction().get();
        assertThat(transaction.getBlockHash(), is(config.validBlockHash()));
    }

    @Test
    public void testEaiGetTransactionByBlockHashAndIndex() throws Exception {
        BigInteger index = BigInteger.ONE;

        EaiTransaction eaiTransaction = web3j.eaiGetTransactionByBlockHashAndIndex(
                config.validBlockHash(), index).send();
        assertTrue(eaiTransaction.getTransaction().isPresent());
        Transaction transaction = eaiTransaction.getTransaction().get();
        assertThat(transaction.getBlockHash(), is(config.validBlockHash()));
        assertThat(transaction.getTransactionIndex(), equalTo(index));
    }

    @Test
    public void testEaiGetTransactionByBlockNumberAndIndex() throws Exception {
        BigInteger index = BigInteger.ONE;

        EaiTransaction eaiTransaction = web3j.eaiGetTransactionByBlockNumberAndIndex(
                DefaultBlockParameter.valueOf(config.validBlock()), index).send();
        assertTrue(eaiTransaction.getTransaction().isPresent());
        Transaction transaction = eaiTransaction.getTransaction().get();
        assertThat(transaction.getBlockHash(), is(config.validBlockHash()));
        assertThat(transaction.getTransactionIndex(), equalTo(index));
    }

    @Test
    public void testEaiGetTransactionReceipt() throws Exception {
        EaiGetTransactionReceipt eaiGetTransactionReceipt = web3j.eaiGetTransactionReceipt(
                config.validTransactionHash()).send();
        assertTrue(eaiGetTransactionReceipt.getTransactionReceipt().isPresent());
        TransactionReceipt transactionReceipt =
                eaiGetTransactionReceipt.getTransactionReceipt().get();
        assertThat(transactionReceipt.getTransactionHash(), is(config.validTransactionHash()));
    }

    @Test
    public void testEaiGetUncleByBlockHashAndIndex() throws Exception {
        EaiBlock eaiBlock = web3j.eaiGetUncleByBlockHashAndIndex(
                config.validUncleBlockHash(), BigInteger.ZERO).send();
        assertNotNull(eaiBlock.getBlock());
    }

    @Test
    public void testEaiGetUncleByBlockNumberAndIndex() throws Exception {
        EaiBlock eaiBlock = web3j.eaiGetUncleByBlockNumberAndIndex(
                DefaultBlockParameter.valueOf(config.validUncleBlock()), BigInteger.ZERO)
                .send();
        assertNotNull(eaiBlock.getBlock());
    }

    @Test
    public void testEaiGetCompilers() throws Exception {
        EaiGetCompilers eaiGetCompilers = web3j.eaiGetCompilers().send();
        assertNotNull(eaiGetCompilers.getCompilers());
    }

    @Ignore  // The method eai_compileLLL does not exist/is not available
    @Test
    public void testEaiCompileLLL() throws Exception {
        EaiCompileLLL eaiCompileLLL = web3j.eaiCompileLLL(
                "(returnlll (suicide (caller)))").send();
        assertFalse(eaiCompileLLL.getCompiledSourceCode().isEmpty());
    }

    @Test
    public void testEaiCompileSolidity() throws Exception {
        String sourceCode = "pragma solidity ^0.4.0;"
                + "\ncontract test { function multiply(uint a) returns(uint d) {"
                + "   return a * 7;   } }"
                + "\ncontract test2 { function multiply2(uint a) returns(uint d) {"
                + "   return a * 7;   } }";
        EaiCompileSolidity eaiCompileSolidity = web3j.eaiCompileSolidity(sourceCode)
                .send();
        assertNotNull(eaiCompileSolidity.getCompiledSolidity());
        assertThat(
                eaiCompileSolidity.getCompiledSolidity().get("test2").getInfo().getSource(),
                is(sourceCode));
    }

    @Ignore  // The method eai_compileSerpent does not exist/is not available
    @Test
    public void testEaiCompileSerpent() throws Exception {
        EaiCompileSerpent eaiCompileSerpent = web3j.eaiCompileSerpent(
                "/* some serpent */").send();
        assertFalse(eaiCompileSerpent.getCompiledSourceCode().isEmpty());
    }

    @Test
    public void testFiltersByFilterId() throws Exception {
        org.web3j.protocol.core.methods.request.EaiFilter eaiFilter =
                new org.web3j.protocol.core.methods.request.EaiFilter(
                DefaultBlockParameterName.EARLIEST,
                DefaultBlockParameterName.LATEST,
                config.validContractAddress());

        String eventSignature = config.encodedEvent();
        eaiFilter.addSingleTopic(eventSignature);

        // eai_newFilter
        EaiFilter eaiNewFilter = web3j.eaiNewFilter(eaiFilter).send();
        BigInteger filterId = eaiNewFilter.getFilterId();

        // eai_getFilterLogs
        EaiLog eaiFilterLogs = web3j.eaiGetFilterLogs(filterId).send();
        List<EaiLog.LogResult> filterLogs = eaiFilterLogs.getLogs();
        assertFalse(filterLogs.isEmpty());

        // eai_getFilterChanges - nothing will have changed in this interval
        EaiLog eaiLog = web3j.eaiGetFilterChanges(filterId).send();
        assertTrue(eaiLog.getLogs().isEmpty());

        // eai_uninstallFilter
        EaiUninstallFilter eaiUninstallFilter = web3j.eaiUninstallFilter(filterId).send();
        assertTrue(eaiUninstallFilter.isUninstalled());
    }

    @Test
    public void testEaiNewBlockFilter() throws Exception {
        EaiFilter eaiNewBlockFilter = web3j.eaiNewBlockFilter().send();
        assertNotNull(eaiNewBlockFilter.getFilterId());
    }

    @Test
    public void testEaiNewPendingTransactionFilter() throws Exception {
        EaiFilter eaiNewPendingTransactionFilter =
                web3j.eaiNewPendingTransactionFilter().send();
        assertNotNull(eaiNewPendingTransactionFilter.getFilterId());
    }

    @Test
    public void testEaiGetLogs() throws Exception {
        org.web3j.protocol.core.methods.request.EaiFilter eaiFilter =
                new org.web3j.protocol.core.methods.request.EaiFilter(
                DefaultBlockParameterName.EARLIEST,
                DefaultBlockParameterName.LATEST,
                config.validContractAddress()
        );

        eaiFilter.addSingleTopic(config.encodedEvent());

        EaiLog eaiLog = web3j.eaiGetLogs(eaiFilter).send();
        List<EaiLog.LogResult> logs = eaiLog.getLogs();
        assertFalse(logs.isEmpty());
    }

    // @Test
    // public void testEaiGetWork() throws Exception {
    //     EaiGetWork eaiGetWork = requestFactory.eaiGetWork();
    //     assertNotNull(eaiGetWork.getResult());
    // }

    @Test
    public void testEaiSubmitWork() throws Exception {

    }

    @Test
    public void testEaiSubmitHashrate() throws Exception {
    
    }

    @Test
    public void testDbPutString() throws Exception {
    
    }

    @Test
    public void testDbGetString() throws Exception {
    
    }

    @Test
    public void testDbPutHex() throws Exception {
    
    }

    @Test
    public void testDbGetHex() throws Exception {
    
    }

    @Test
    public void testShhPost() throws Exception {
    
    }

    @Ignore // The method shh_version does not exist/is not available
    @Test
    public void testShhVersion() throws Exception {
        ShhVersion shhVersion = web3j.shhVersion().send();
        assertNotNull(shhVersion.getVersion());
    }

    @Ignore  // The method shh_newIdentity does not exist/is not available
    @Test
    public void testShhNewIdentity() throws Exception {
        ShhNewIdentity shhNewIdentity = web3j.shhNewIdentity().send();
        assertNotNull(shhNewIdentity.getAddress());
    }

    @Test
    public void testShhHasIdentity() throws Exception {
    
    }

    @Ignore  // The method shh_newIdentity does not exist/is not available
    @Test
    public void testShhNewGroup() throws Exception {
        ShhNewGroup shhNewGroup = web3j.shhNewGroup().send();
        assertNotNull(shhNewGroup.getAddress());
    }

    @Ignore  // The method shh_addToGroup does not exist/is not available
    @Test
    public void testShhAddToGroup() throws Exception {

    }

    @Test
    public void testShhNewFilter() throws Exception {
    
    }

    @Test
    public void testShhUninstallFilter() throws Exception {
    
    }

    @Test
    public void testShhGetFilterChanges() throws Exception {
    
    }

    @Test
    public void testShhGetMessages() throws Exception {
    
    }
}
