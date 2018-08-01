package org.web3j.protocol.core;

import java.math.BigInteger;
import java.util.Arrays;

import org.junit.Test;

import org.web3j.protocol.RequestTester;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.request.EaiFilter;
import org.web3j.protocol.core.methods.request.ShhFilter;
import org.web3j.protocol.core.methods.request.ShhPost;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

public class RequestTest extends RequestTester {

    private Web3j web3j;

    @Override
    protected void initWeb3Client(HttpService httpService) {
        web3j = Web3j.build(httpService);
    }

    @Test
    public void testWeb3ClientVersion() throws Exception {
        web3j.web3ClientVersion().send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"web3_clientVersion\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testWeb3Sha3() throws Exception {
        web3j.web3Sha3("0x68656c6c6f20776f726c64").send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"web3_sha3\","
                        + "\"params\":[\"0x68656c6c6f20776f726c64\"],\"id\":1}");
    }

    @Test
    public void testNetVersion() throws Exception {
        web3j.netVersion().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"net_version\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testNetListening() throws Exception {
        web3j.netListening().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"net_listening\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testNetPeerCount() throws Exception {
        web3j.netPeerCount().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"net_peerCount\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testEaiProtocolVersion() throws Exception {
        web3j.eaiProtocolVersion().send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"eai_protocolVersion\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testEaiSyncing() throws Exception {
        web3j.eaiSyncing().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_syncing\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testEaiCoinbase() throws Exception {
        web3j.eaiCoinbase().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_coinbase\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testEaiMining() throws Exception {
        web3j.eaiMining().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_mining\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testEaiHashrate() throws Exception {
        web3j.eaiHashrate().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_hashrate\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testEaiGasPrice() throws Exception {
        web3j.eaiGasPrice().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_gasPrice\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testEaiAccounts() throws Exception {
        web3j.eaiAccounts().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_accounts\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testEaiBlockNumber() throws Exception {
        web3j.eaiBlockNumber().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_blockNumber\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testEaiGetBalance() throws Exception {
        web3j.eaiGetBalance("0x407d73d8a49eeb85d32cf465507dd71d507100c1",
                DefaultBlockParameterName.LATEST).send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"eai_getBalance\","
                        + "\"params\":[\"0x407d73d8a49eeb85d32cf465507dd71d507100c1\",\"latest\"],"
                        + "\"id\":1}");
    }

    @Test
    public void testEaiGetStorageAt() throws Exception {
        web3j.eaiGetStorageAt("0x295a70b2de5e3953354a6a8344e616ed314d7251", BigInteger.ZERO,
                DefaultBlockParameterName.LATEST).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_getStorageAt\","
                + "\"params\":[\"0x295a70b2de5e3953354a6a8344e616ed314d7251\",\"0x0\",\"latest\"],"
                + "\"id\":1}");
    }

    @Test
    public void testEaiGetTransactionCount() throws Exception {
        web3j.eaiGetTransactionCount("0x407d73d8a49eeb85d32cf465507dd71d507100c1",
                DefaultBlockParameterName.LATEST).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_getTransactionCount\","
                + "\"params\":[\"0x407d73d8a49eeb85d32cf465507dd71d507100c1\",\"latest\"],"
                + "\"id\":1}");
    }

    @Test
    public void testEaiGetBlockTransactionCountByHash() throws Exception {
        web3j.eaiGetBlockTransactionCountByHash(
                "0xb903239f8543d04b5dc1ba6579132b143087c68db1b2168786408fcbce568238").send();

        //CHECKSTYLE:OFF
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_getBlockTransactionCountByHash\",\"params\":[\"0xb903239f8543d04b5dc1ba6579132b143087c68db1b2168786408fcbce568238\"],\"id\":1}");
        //CHECKSTYLE:ON
    }

    @Test
    public void testEaiGetBlockTransactionCountByNumber() throws Exception {
        web3j.eaiGetBlockTransactionCountByNumber(
                DefaultBlockParameter.valueOf(Numeric.toBigInt("0xe8"))).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_getBlockTransactionCountByNumber\","
                + "\"params\":[\"0xe8\"],\"id\":1}");
    }

    @Test
    public void testEaiGetUncleCountByBlockHash() throws Exception {
        web3j.eaiGetUncleCountByBlockHash(
                "0xb903239f8543d04b5dc1ba6579132b143087c68db1b2168786408fcbce568238").send();

        //CHECKSTYLE:OFF
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_getUncleCountByBlockHash\",\"params\":[\"0xb903239f8543d04b5dc1ba6579132b143087c68db1b2168786408fcbce568238\"],\"id\":1}");
        //CHECKSTYLE:ON
    }

    @Test
    public void testEaiGetUncleCountByBlockNumber() throws Exception {
        web3j.eaiGetUncleCountByBlockNumber(
                DefaultBlockParameter.valueOf(Numeric.toBigInt("0xe8"))).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_getUncleCountByBlockNumber\","
                + "\"params\":[\"0xe8\"],\"id\":1}");
    }

    @Test
    public void testEaiGetCode() throws Exception {
        web3j.eaiGetCode("0xa94f5374fce5edbc8e2a8697c15331677e6ebf0b",
                DefaultBlockParameter.valueOf(Numeric.toBigInt("0x2"))).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_getCode\","
                + "\"params\":[\"0xa94f5374fce5edbc8e2a8697c15331677e6ebf0b\",\"0x2\"],\"id\":1}");
    }

    @Test
    public void testEaiSign() throws Exception {
        web3j.eaiSign("0x8a3106a3e50576d4b6794a0e74d3bb5f8c9acaab",
                "0xc5d2460186f7233c927e7db2dcc703c0e500b653ca82273b7bfad8045d85a470").send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_sign\","
                + "\"params\":[\"0x8a3106a3e50576d4b6794a0e74d3bb5f8c9acaab\","
                + "\"0xc5d2460186f7233c927e7db2dcc703c0e500b653ca82273b7bfad8045d85a470\"],"
                + "\"id\":1}");
    }

    @Test
    public void testEaiSendTransaction() throws Exception {
        web3j.eaiSendTransaction(new Transaction(
                "0xb60e8dd61c5d32be8058bb8eb970870f07233155",
                BigInteger.ONE,
                Numeric.toBigInt("0x9184e72a000"),
                Numeric.toBigInt("0x76c0"),
                "0xb60e8dd61c5d32be8058bb8eb970870f07233155",
                Numeric.toBigInt("0x9184e72a"),
                "0xd46e8dd67c5d32be8d46e8dd67c5d32be8058bb8eb"
                        + "970870f072445675058bb8eb970870f072445675")).send();

        //CHECKSTYLE:OFF
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_sendTransaction\",\"params\":[{\"from\":\"0xb60e8dd61c5d32be8058bb8eb970870f07233155\",\"to\":\"0xb60e8dd61c5d32be8058bb8eb970870f07233155\",\"gas\":\"0x76c0\",\"gasPrice\":\"0x9184e72a000\",\"value\":\"0x9184e72a\",\"data\":\"0xd46e8dd67c5d32be8d46e8dd67c5d32be8058bb8eb970870f072445675058bb8eb970870f072445675\",\"nonce\":\"0x1\"}],\"id\":1}");
        //CHECKSTYLE:ON
    }

    @Test
    public void testEaiSendRawTransaction() throws Exception {
        web3j.eaiSendRawTransaction(
                "0xd46e8dd67c5d32be8d46e8dd67c5d32be8058bb8eb970870f"
                        + "072445675058bb8eb970870f072445675").send();

        //CHECKSTYLE:OFF
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_sendRawTransaction\",\"params\":[\"0xd46e8dd67c5d32be8d46e8dd67c5d32be8058bb8eb970870f072445675058bb8eb970870f072445675\"],\"id\":1}");
        //CHECKSTYLE:ON
    }


    @Test
    public void testEaiCall() throws Exception {
        web3j.eaiCall(Transaction.createEaiCallTransaction(
                "0xa70e8dd61c5d32be8058bb8eb970870f07233155",
                "0xb60e8dd61c5d32be8058bb8eb970870f07233155",
                        "0x0"),
                DefaultBlockParameter.valueOf("latest")).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_call\","
                + "\"params\":[{\"from\":\"0xa70e8dd61c5d32be8058bb8eb970870f07233155\","
                + "\"to\":\"0xb60e8dd61c5d32be8058bb8eb970870f07233155\",\"data\":\"0x0\"},"
                + "\"latest\"],\"id\":1}");
    }

    @Test
    public void testEaiEstimateGas() throws Exception {
        web3j.eaiEstimateGas(
                Transaction.createEaiCallTransaction(
                        "0xa70e8dd61c5d32be8058bb8eb970870f07233155",
                        "0x52b93c80364dc2dd4444c146d73b9836bbbb2b3f", "0x0")).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_estimateGas\","
                + "\"params\":[{\"from\":\"0xa70e8dd61c5d32be8058bb8eb970870f07233155\","
                + "\"to\":\"0x52b93c80364dc2dd4444c146d73b9836bbbb2b3f\",\"data\":\"0x0\"}],"
                + "\"id\":1}");
    }

    @Test
    public void testEaiEstimateGasContractCreation() throws Exception {
        web3j.eaiEstimateGas(
                Transaction.createContractTransaction(
                        "0x52b93c80364dc2dd4444c146d73b9836bbbb2b3f", BigInteger.ONE,
                        BigInteger.TEN, "")).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_estimateGas\","
                + "\"params\":[{\"from\":\"0x52b93c80364dc2dd4444c146d73b9836bbbb2b3f\","
                + "\"gasPrice\":\"0xa\",\"data\":\"0x\",\"nonce\":\"0x1\"}],\"id\":1}");
    }

    @Test
    public void testEaiGetBlockByHash() throws Exception {
        web3j.eaiGetBlockByHash(
                "0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331", true).send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"eai_getBlockByHash\",\"params\":["
                        + "\"0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331\""
                        + ",true],\"id\":1}");
    }

    @Test
    public void testEaiGetBlockByNumber() throws Exception {
        web3j.eaiGetBlockByNumber(
                DefaultBlockParameter.valueOf(Numeric.toBigInt("0x1b4")), true).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_getBlockByNumber\","
                + "\"params\":[\"0x1b4\",true],\"id\":1}");
    }

    @Test
    public void testEaiGetTransactionByHash() throws Exception {
        web3j.eaiGetTransactionByHash(
                "0xb903239f8543d04b5dc1ba6579132b143087c68db1b2168786408fcbce568238").send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_getTransactionByHash\",\"params\":["
                + "\"0xb903239f8543d04b5dc1ba6579132b143087c68db1b2168786408fcbce568238\"],"
                + "\"id\":1}");
    }

    @Test
    public void testEaiGetTransactionByBlockHashAndIndex() throws Exception {
        web3j.eaiGetTransactionByBlockHashAndIndex(
                "0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331",
                BigInteger.ZERO).send();

        //CHECKSTYLE:OFF
        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_getTransactionByBlockHashAndIndex\",\"params\":[\"0xe670ec64341771606e55d6b4ca35a1a6b75ee3d5145a99d05921026d1527331\",\"0x0\"],\"id\":1}");
        //CHECKSTYLE:ON
    }

    @Test
    public void testEaiGetTransactionByBlockNumberAndIndex() throws Exception {
        web3j.eaiGetTransactionByBlockNumberAndIndex(
                DefaultBlockParameter.valueOf(Numeric.toBigInt("0x29c")), BigInteger.ZERO).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_getTransactionByBlockNumberAndIndex\","
                + "\"params\":[\"0x29c\",\"0x0\"],\"id\":1}");
    }

    @Test
    public void testEaiGetTransactionReceipt() throws Exception {
        web3j.eaiGetTransactionReceipt(
                "0xb903239f8543d04b5dc1ba6579132b143087c68db1b2168786408fcbce568238").send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_getTransactionReceipt\",\"params\":["
                + "\"0xb903239f8543d04b5dc1ba6579132b143087c68db1b2168786408fcbce568238\"],"
                + "\"id\":1}");
    }

    @Test
    public void testEaiGetUncleByBlockHashAndIndex() throws Exception {
        web3j.eaiGetUncleByBlockHashAndIndex(
                "0xc6ef2fc5426d6ad6fd9e2a26abeab0aa2411b7ab17f30a99d3cb96aed1d1055b",
                BigInteger.ZERO).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_getUncleByBlockHashAndIndex\","
                + "\"params\":["
                + "\"0xc6ef2fc5426d6ad6fd9e2a26abeab0aa2411b7ab17f30a99d3cb96aed1d1055b\",\"0x0\"],"
                + "\"id\":1}");
    }

    @Test
    public void testEaiGetUncleByBlockNumberAndIndex() throws Exception {
        web3j.eaiGetUncleByBlockNumberAndIndex(
                DefaultBlockParameter.valueOf(Numeric.toBigInt("0x29c")), BigInteger.ZERO).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_getUncleByBlockNumberAndIndex\","
                + "\"params\":[\"0x29c\",\"0x0\"],\"id\":1}");
    }

    @Test
    public void testEaiGetCompilers() throws Exception {
        web3j.eaiGetCompilers().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_getCompilers\","
                + "\"params\":[],\"id\":1}");
    }

    @Test
    public void testEaiCompileSolidity() throws Exception {
        web3j.eaiCompileSolidity(
                "contract test { function multiply(uint a) returns(uint d) {   return a * 7;   } }")
                .send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_compileSolidity\","
                + "\"params\":[\"contract test { function multiply(uint a) returns(uint d) {"
                + "   return a * 7;   } }\"],\"id\":1}");
    }

    @Test
    public void testEaiCompileLLL() throws Exception {
        web3j.eaiCompileLLL("(returnlll (suicide (caller)))").send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_compileLLL\","
                + "\"params\":[\"(returnlll (suicide (caller)))\"],\"id\":1}");
    }

    @Test
    public void testEaiCompileSerpent() throws Exception {
        web3j.eaiCompileSerpent("/* some serpent */").send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_compileSerpent\","
                + "\"params\":[\"/* some serpent */\"],\"id\":1}");
    }

    @Test
    public void testEaiNewFilter() throws Exception {
        EaiFilter eaiFilter = new EaiFilter()
                .addSingleTopic("0x12341234");

        web3j.eaiNewFilter(eaiFilter).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_newFilter\","
                + "\"params\":[{\"topics\":[\"0x12341234\"]}],\"id\":1}");
    }

    @Test
    public void testEaiNewBlockFilter() throws Exception {
        web3j.eaiNewBlockFilter().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_newBlockFilter\","
                + "\"params\":[],\"id\":1}");
    }

    @Test
    public void testEaiNewPendingTransactionFilter() throws Exception {
        web3j.eaiNewPendingTransactionFilter().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_newPendingTransactionFilter\","
                + "\"params\":[],\"id\":1}");
    }

    @Test
    public void testEaiUninstallFilter() throws Exception {
        web3j.eaiUninstallFilter(Numeric.toBigInt("0xb")).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_uninstallFilter\","
                + "\"params\":[\"0x0b\"],\"id\":1}");
    }

    @Test
    public void testEaiGetFilterChanges() throws Exception {
        web3j.eaiGetFilterChanges(Numeric.toBigInt("0x16")).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_getFilterChanges\","
                + "\"params\":[\"0x16\"],\"id\":1}");
    }

    @Test
    public void testEaiGetFilterLogs() throws Exception {
        web3j.eaiGetFilterLogs(Numeric.toBigInt("0x16")).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_getFilterLogs\","
                + "\"params\":[\"0x16\"],\"id\":1}");
    }

    @Test
    public void testEaiGetLogs() throws Exception {
        web3j.eaiGetLogs(new EaiFilter().addSingleTopic(
                "0x000000000000000000000000a94f5374fce5edbc8e2a8697c15331677e6ebf0b"))
                .send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_getLogs\","
                + "\"params\":[{\"topics\":["
                + "\"0x000000000000000000000000a94f5374fce5edbc8e2a8697c15331677e6ebf0b\"]}],"
                + "\"id\":1}");
    }

    @Test
    public void testEaiGetLogsWithNumericBlockRange() throws Exception {
        web3j.eaiGetLogs(new EaiFilter(
                DefaultBlockParameter.valueOf(Numeric.toBigInt("0xe8")),
                DefaultBlockParameter.valueOf("latest"), ""))
                .send();

        verifyResult(
                "{\"jsonrpc\":\"2.0\",\"method\":\"eai_getLogs\","
                        + "\"params\":[{\"topics\":[],\"fromBlock\":\"0xe8\","
                        + "\"toBlock\":\"latest\",\"address\":[\"\"]}],\"id\":1}");
    }

    @Test
    public void testEaiGetWork() throws Exception {
        web3j.eaiGetWork().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_getWork\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testEaiSubmitWork() throws Exception {
        web3j.eaiSubmitWork("0x0000000000000001",
                "0x1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdef",
                "0xD1FE5700000000000000000000000000D1FE5700000000000000000000000000").send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_submitWork\","
                + "\"params\":[\"0x0000000000000001\","
                + "\"0x1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdef\","
                + "\"0xD1FE5700000000000000000000000000D1FE5700000000000000000000000000\"],"
                + "\"id\":1}");
    }

    @Test
    public void testEaiSubmitHashRate() throws Exception {
        web3j.eaiSubmitHashrate(
                "0x0000000000000000000000000000000000000000000000000000000000500000",
                "0x59daa26581d0acd1fce254fb7e85952f4c09d0915afd33d3886cd914bc7d283c").send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"eai_submitHashrate\","
                + "\"params\":["
                + "\"0x0000000000000000000000000000000000000000000000000000000000500000\","
                + "\"0x59daa26581d0acd1fce254fb7e85952f4c09d0915afd33d3886cd914bc7d283c\"],"
                + "\"id\":1}");
    }

    @Test
    public void testDbPutString() throws Exception {
        web3j.dbPutString("testDB", "myKey", "myString").send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"db_putString\","
                + "\"params\":[\"testDB\",\"myKey\",\"myString\"],\"id\":1}");
    }

    @Test
    public void testDbGetString() throws Exception {
        web3j.dbGetString("testDB", "myKey").send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"db_getString\","
                + "\"params\":[\"testDB\",\"myKey\"],\"id\":1}");
    }

    @Test
    public void testDbPutHex() throws Exception {
        web3j.dbPutHex("testDB", "myKey", "0x68656c6c6f20776f726c64").send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"db_putHex\","
                + "\"params\":[\"testDB\",\"myKey\",\"0x68656c6c6f20776f726c64\"],\"id\":1}");
    }

    @Test
    public void testDbGetHex() throws Exception {
        web3j.dbGetHex("testDB", "myKey").send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"db_getHex\","
                + "\"params\":[\"testDB\",\"myKey\"],\"id\":1}");
    }

    @Test
    public void testShhVersion() throws Exception {
        web3j.shhVersion().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"shh_version\","
                + "\"params\":[],\"id\":1}");
    }

    @Test
    public void testShhPost() throws Exception {
        //CHECKSTYLE:OFF
        web3j.shhPost(new ShhPost(
                "0x04f96a5e25610293e42a73908e93ccc8c4d4dc0edcfa9fa872f50cb214e08ebf61a03e245533f97284d442460f2998cd41858798ddfd4d661997d3940272b717b1",
                "0x3e245533f97284d442460f2998cd41858798ddf04f96a5e25610293e42a73908e93ccc8c4d4dc0edcfa9fa872f50cb214e08ebf61a0d4d661997d3940272b717b1",
                Arrays.asList("0x776869737065722d636861742d636c69656e74", "0x4d5a695276454c39425154466b61693532"),
                "0x7b2274797065223a226d6",
                Numeric.toBigInt("0x64"),
                Numeric.toBigInt("0x64"))).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"shh_post\",\"params\":[{\"from\":\"0x04f96a5e25610293e42a73908e93ccc8c4d4dc0edcfa9fa872f50cb214e08ebf61a03e245533f97284d442460f2998cd41858798ddfd4d661997d3940272b717b1\",\"to\":\"0x3e245533f97284d442460f2998cd41858798ddf04f96a5e25610293e42a73908e93ccc8c4d4dc0edcfa9fa872f50cb214e08ebf61a0d4d661997d3940272b717b1\",\"topics\":[\"0x776869737065722d636861742d636c69656e74\",\"0x4d5a695276454c39425154466b61693532\"],\"payload\":\"0x7b2274797065223a226d6\",\"priority\":\"0x64\",\"ttl\":\"0x64\"}],\"id\":1}");
        //CHECKSTYLE:ON
    }

    @Test
    public void testShhNewIdentity() throws Exception {
        web3j.shhNewIdentity().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"shh_newIdentity\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testShhHasIdentity() throws Exception {
        //CHECKSTYLE:OFF
        web3j.shhHasIdentity("0x04f96a5e25610293e42a73908e93ccc8c4d4dc0edcfa9fa872f50cb214e08ebf61a03e245533f97284d442460f2998cd41858798ddfd4d661997d3940272b717b1").send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"shh_hasIdentity\",\"params\":[\"0x04f96a5e25610293e42a73908e93ccc8c4d4dc0edcfa9fa872f50cb214e08ebf61a03e245533f97284d442460f2998cd41858798ddfd4d661997d3940272b717b1\"],\"id\":1}");
        //CHECKSTYLE:ON
    }

    @Test
    public void testShhNewGroup() throws Exception {
        web3j.shhNewGroup().send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"shh_newGroup\",\"params\":[],\"id\":1}");
    }

    @Test
    public void testShhAddToGroup() throws Exception {
        //CHECKSTYLE:OFF
        web3j.shhAddToGroup("0x04f96a5e25610293e42a73908e93ccc8c4d4dc0edcfa9fa872f50cb214e08ebf61a03e245533f97284d442460f2998cd41858798ddfd4d661997d3940272b717b1").send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"shh_addToGroup\",\"params\":[\"0x04f96a5e25610293e42a73908e93ccc8c4d4dc0edcfa9fa872f50cb214e08ebf61a03e245533f97284d442460f2998cd41858798ddfd4d661997d3940272b717b1\"],\"id\":1}");
        //CHECKSTYLE:ON
    }

    @Test
    public void testShhNewFilter() throws Exception {
        //CHECKSTYLE:OFF
        web3j.shhNewFilter(
                new ShhFilter("0x04f96a5e25610293e42a73908e93ccc8c4d4dc0edcfa9fa872f50cb214e08ebf61a03e245533f97284d442460f2998cd41858798ddfd4d661997d3940272b717b1")
                        .addSingleTopic("0x12341234bf4b564f")).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"shh_newFilter\",\"params\":[{\"topics\":[\"0x12341234bf4b564f\"],\"to\":\"0x04f96a5e25610293e42a73908e93ccc8c4d4dc0edcfa9fa872f50cb214e08ebf61a03e245533f97284d442460f2998cd41858798ddfd4d661997d3940272b717b1\"}],\"id\":1}");
        //CHECKSTYLE:ON
    }

    @Test
    public void testShhUninstallFilter() throws Exception {
        web3j.shhUninstallFilter(Numeric.toBigInt("0x7")).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"shh_uninstallFilter\","
                + "\"params\":[\"0x07\"],\"id\":1}");
    }

    @Test
    public void testShhGetFilterChanges() throws Exception {
        web3j.shhGetFilterChanges(Numeric.toBigInt("0x7")).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"shh_getFilterChanges\","
                + "\"params\":[\"0x07\"],\"id\":1}");
    }

    @Test
    public void testShhGetMessages() throws Exception {
        web3j.shhGetMessages(Numeric.toBigInt("0x7")).send();

        verifyResult("{\"jsonrpc\":\"2.0\",\"method\":\"shh_getMessages\","
                + "\"params\":[\"0x07\"],\"id\":1}");
    }
}
