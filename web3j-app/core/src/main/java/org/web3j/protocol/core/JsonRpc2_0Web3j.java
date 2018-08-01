package org.web3j.protocol.core;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ScheduledExecutorService;

import org.web3j.protocol.core.methods.response.TxPoolContent;
import rx.Observable;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.methods.request.ShhFilter;
import org.web3j.protocol.core.methods.request.ShhPost;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.DbGetHex;
import org.web3j.protocol.core.methods.response.DbGetString;
import org.web3j.protocol.core.methods.response.DbPutHex;
import org.web3j.protocol.core.methods.response.DbPutString;
import org.web3j.protocol.core.methods.response.EaiAccounts;
import org.web3j.protocol.core.methods.response.EaiBlock;
import org.web3j.protocol.core.methods.response.EaiBlockNumber;
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
import org.web3j.protocol.core.methods.response.EaiGetWork;
import org.web3j.protocol.core.methods.response.EaiHashrate;
import org.web3j.protocol.core.methods.response.EaiLog;
import org.web3j.protocol.core.methods.response.EaiMining;
import org.web3j.protocol.core.methods.response.EaiProtocolVersion;
import org.web3j.protocol.core.methods.response.EaiSign;
import org.web3j.protocol.core.methods.response.EaiSubmitHashrate;
import org.web3j.protocol.core.methods.response.EaiSubmitWork;
import org.web3j.protocol.core.methods.response.EaiSyncing;
import org.web3j.protocol.core.methods.response.EaiTransaction;
import org.web3j.protocol.core.methods.response.EaiUninstallFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.NetListening;
import org.web3j.protocol.core.methods.response.NetPeerCount;
import org.web3j.protocol.core.methods.response.NetVersion;
import org.web3j.protocol.core.methods.response.ShhAddToGroup;
import org.web3j.protocol.core.methods.response.ShhHasIdentity;
import org.web3j.protocol.core.methods.response.ShhMessages;
import org.web3j.protocol.core.methods.response.ShhNewFilter;
import org.web3j.protocol.core.methods.response.ShhNewGroup;
import org.web3j.protocol.core.methods.response.ShhNewIdentity;
import org.web3j.protocol.core.methods.response.ShhUninstallFilter;
import org.web3j.protocol.core.methods.response.ShhVersion;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.core.methods.response.Web3Sha3;
import org.web3j.protocol.rx.JsonRpc2_0Rx;
import org.web3j.utils.Async;
import org.web3j.utils.Numeric;

/**
 * JSON-RPC 2.0 factory implementation.
 */
public class JsonRpc2_0Web3j implements Web3j {

    public static final int DEFAULT_BLOCK_TIME = 15 * 1000;

    protected final Web3jService web3jService;
    private final JsonRpc2_0Rx web3jRx;
    private final long blockTime;
    private final ScheduledExecutorService scheduledExecutorService;

    public JsonRpc2_0Web3j(Web3jService web3jService) {
        this(web3jService, DEFAULT_BLOCK_TIME, Async.defaultExecutorService());
    }

    public JsonRpc2_0Web3j(
            Web3jService web3jService, long pollingInterval,
            ScheduledExecutorService scheduledExecutorService) {
        this.web3jService = web3jService;
        this.web3jRx = new JsonRpc2_0Rx(this, scheduledExecutorService);
        this.blockTime = pollingInterval;
        this.scheduledExecutorService = scheduledExecutorService;
    }

    @Override
    public Request<?, EaiAccounts> debugGetModifiedAccountsByNumber(int start, int end) {
        return new Request<>(
            "debug_getModifiedAccountsByNumber",
            Arrays.asList(start, end),
            web3jService,
            EaiAccounts.class);
    }

    @Override
    public Request<?, TxPoolContent> txPoolContent() {
        return new Request<>(
            "txpool_content",
            Collections.<String>emptyList(),
            web3jService,
            TxPoolContent.class);
    }

    @Override
    public Request<?, Web3ClientVersion> web3ClientVersion() {
        return new Request<>(
                "web3_clientVersion",
                Collections.<String>emptyList(),
                web3jService,
                Web3ClientVersion.class);
    }

    @Override
    public Request<?, Web3Sha3> web3Sha3(String data) {
        return new Request<>(
                "web3_sha3",
                Arrays.asList(data),
                web3jService,
                Web3Sha3.class);
    }

    @Override
    public Request<?, NetVersion> netVersion() {
        return new Request<>(
                "net_version",
                Collections.<String>emptyList(),
                web3jService,
                NetVersion.class);
    }

    @Override
    public Request<?, NetListening> netListening() {
        return new Request<>(
                "net_listening",
                Collections.<String>emptyList(),
                web3jService,
                NetListening.class);
    }

    @Override
    public Request<?, NetPeerCount> netPeerCount() {
        return new Request<>(
                "net_peerCount",
                Collections.<String>emptyList(),
                web3jService,
                NetPeerCount.class);
    }

    @Override
    public Request<?, EaiProtocolVersion> eaiProtocolVersion() {
        return new Request<>(
                "eai_protocolVersion",
                Collections.<String>emptyList(),
                web3jService,
                EaiProtocolVersion.class);
    }

    @Override
    public Request<?, EaiCoinbase> eaiCoinbase() {
        return new Request<>(
                "eai_coinbase",
                Collections.<String>emptyList(),
                web3jService,
                EaiCoinbase.class);
    }

    @Override
    public Request<?, EaiSyncing> eaiSyncing() {
        return new Request<>(
                "eai_syncing",
                Collections.<String>emptyList(),
                web3jService,
                EaiSyncing.class);
    }

    @Override
    public Request<?, EaiMining> eaiMining() {
        return new Request<>(
                "eai_mining",
                Collections.<String>emptyList(),
                web3jService,
                EaiMining.class);
    }

    @Override
    public Request<?, EaiHashrate> eaiHashrate() {
        return new Request<>(
                "eai_hashrate",
                Collections.<String>emptyList(),
                web3jService,
                EaiHashrate.class);
    }

    @Override
    public Request<?, EaiGasPrice> eaiGasPrice() {
        return new Request<>(
                "eai_gasPrice",
                Collections.<String>emptyList(),
                web3jService,
                EaiGasPrice.class);
    }

    @Override
    public Request<?, EaiAccounts> eaiAccounts() {
        return new Request<>(
                "eai_accounts",
                Collections.<String>emptyList(),
                web3jService,
                EaiAccounts.class);
    }

    @Override
    public Request<?, EaiBlockNumber> eaiBlockNumber() {
        return new Request<>(
                "eai_blockNumber",
                Collections.<String>emptyList(),
                web3jService,
                EaiBlockNumber.class);
    }

    @Override
    public Request<?, EaiGetBalance> eaiGetBalance(
            String address, DefaultBlockParameter defaultBlockParameter) {
        return new Request<>(
                "eai_getBalance",
                Arrays.asList(address, defaultBlockParameter.getValue()),
                web3jService,
                EaiGetBalance.class);
    }

    @Override
    public Request<?, EaiGetStorageAt> eaiGetStorageAt(
            String address, BigInteger position, DefaultBlockParameter defaultBlockParameter) {
        return new Request<>(
                "eai_getStorageAt",
                Arrays.asList(
                        address,
                        Numeric.encodeQuantity(position),
                        defaultBlockParameter.getValue()),
                web3jService,
                EaiGetStorageAt.class);
    }

    @Override
    public Request<?, EaiGetTransactionCount> eaiGetTransactionCount(
            String address, DefaultBlockParameter defaultBlockParameter) {
        return new Request<>(
                "eai_getTransactionCount",
                Arrays.asList(address, defaultBlockParameter.getValue()),
                web3jService,
                EaiGetTransactionCount.class);
    }

    @Override
    public Request<?, EaiGetBlockTransactionCountByHash> eaiGetBlockTransactionCountByHash(
            String blockHash) {
        return new Request<>(
                "eai_getBlockTransactionCountByHash",
                Arrays.asList(blockHash),
                web3jService,
                EaiGetBlockTransactionCountByHash.class);
    }

    @Override
    public Request<?, EaiGetBlockTransactionCountByNumber> eaiGetBlockTransactionCountByNumber(
            DefaultBlockParameter defaultBlockParameter) {
        return new Request<>(
                "eai_getBlockTransactionCountByNumber",
                Arrays.asList(defaultBlockParameter.getValue()),
                web3jService,
                EaiGetBlockTransactionCountByNumber.class);
    }

    @Override
    public Request<?, EaiGetUncleCountByBlockHash> eaiGetUncleCountByBlockHash(String blockHash) {
        return new Request<>(
                "eai_getUncleCountByBlockHash",
                Arrays.asList(blockHash),
                web3jService,
                EaiGetUncleCountByBlockHash.class);
    }

    @Override
    public Request<?, EaiGetUncleCountByBlockNumber> eaiGetUncleCountByBlockNumber(
            DefaultBlockParameter defaultBlockParameter) {
        return new Request<>(
                "eai_getUncleCountByBlockNumber",
                Arrays.asList(defaultBlockParameter.getValue()),
                web3jService,
                EaiGetUncleCountByBlockNumber.class);
    }

    @Override
    public Request<?, EaiGetCode> eaiGetCode(
            String address, DefaultBlockParameter defaultBlockParameter) {
        return new Request<>(
                "eai_getCode",
                Arrays.asList(address, defaultBlockParameter.getValue()),
                web3jService,
                EaiGetCode.class);
    }

    @Override
    public Request<?, EaiSign> eaiSign(String address, String sha3HashOfDataToSign) {
        return new Request<>(
                "eai_sign",
                Arrays.asList(address, sha3HashOfDataToSign),
                web3jService,
                EaiSign.class);
    }

    @Override
    public Request<?, org.web3j.protocol.core.methods.response.EaiSendTransaction>
            eaiSendTransaction(
            Transaction transaction) {
        return new Request<>(
                "eai_sendTransaction",
                Arrays.asList(transaction),
                web3jService,
                org.web3j.protocol.core.methods.response.EaiSendTransaction.class);
    }

    @Override
    public Request<?, org.web3j.protocol.core.methods.response.EaiSendTransaction>
            eaiSendRawTransaction(
            String signedTransactionData) {
        return new Request<>(
                "eai_sendRawTransaction",
                Arrays.asList(signedTransactionData),
                web3jService,
                org.web3j.protocol.core.methods.response.EaiSendTransaction.class);
    }

    @Override
    public Request<?, org.web3j.protocol.core.methods.response.EaiCall> eaiCall(
            Transaction transaction, DefaultBlockParameter defaultBlockParameter) {
        return new Request<>(
                "eai_call",
                Arrays.asList(transaction, defaultBlockParameter),
                web3jService,
                org.web3j.protocol.core.methods.response.EaiCall.class);
    }

    @Override
    public Request<?, EaiEstimateGas> eaiEstimateGas(Transaction transaction) {
        return new Request<>(
                "eai_estimateGas",
                Arrays.asList(transaction),
                web3jService,
                EaiEstimateGas.class);
    }

    @Override
    public Request<?, EaiBlock> eaiGetBlockByHash(
            String blockHash, boolean returnFullTransactionObjects) {
        return new Request<>(
                "eai_getBlockByHash",
                Arrays.asList(
                        blockHash,
                        returnFullTransactionObjects),
                web3jService,
                EaiBlock.class);
    }

    @Override
    public Request<?, EaiBlock> eaiGetBlockByNumber(
            DefaultBlockParameter defaultBlockParameter,
            boolean returnFullTransactionObjects) {
        return new Request<>(
                "eai_getBlockByNumber",
                Arrays.asList(
                        defaultBlockParameter.getValue(),
                        returnFullTransactionObjects),
                web3jService,
                EaiBlock.class);
    }

    @Override
    public Request<?, EaiTransaction> eaiGetTransactionByHash(String transactionHash) {
        return new Request<>(
                "eai_getTransactionByHash",
                Arrays.asList(transactionHash),
                web3jService,
                EaiTransaction.class);
    }

    @Override
    public Request<?, EaiTransaction> eaiGetTransactionByBlockHashAndIndex(
            String blockHash, BigInteger transactionIndex) {
        return new Request<>(
                "eai_getTransactionByBlockHashAndIndex",
                Arrays.asList(
                        blockHash,
                        Numeric.encodeQuantity(transactionIndex)),
                web3jService,
                EaiTransaction.class);
    }

    @Override
    public Request<?, EaiTransaction> eaiGetTransactionByBlockNumberAndIndex(
            DefaultBlockParameter defaultBlockParameter, BigInteger transactionIndex) {
        return new Request<>(
                "eai_getTransactionByBlockNumberAndIndex",
                Arrays.asList(
                        defaultBlockParameter.getValue(),
                        Numeric.encodeQuantity(transactionIndex)),
                web3jService,
                EaiTransaction.class);
    }

    @Override
    public Request<?, EaiGetTransactionReceipt> eaiGetTransactionReceipt(String transactionHash) {
        return new Request<>(
                "eai_getTransactionReceipt",
                Arrays.asList(transactionHash),
                web3jService,
                EaiGetTransactionReceipt.class);
    }

    @Override
    public Request<?, EaiBlock> eaiGetUncleByBlockHashAndIndex(
            String blockHash, BigInteger transactionIndex) {
        return new Request<>(
                "eai_getUncleByBlockHashAndIndex",
                Arrays.asList(
                        blockHash,
                        Numeric.encodeQuantity(transactionIndex)),
                web3jService,
                EaiBlock.class);
    }

    @Override
    public Request<?, EaiBlock> eaiGetUncleByBlockNumberAndIndex(
            DefaultBlockParameter defaultBlockParameter, BigInteger uncleIndex) {
        return new Request<>(
                "eai_getUncleByBlockNumberAndIndex",
                Arrays.asList(
                        defaultBlockParameter.getValue(),
                        Numeric.encodeQuantity(uncleIndex)),
                web3jService,
                EaiBlock.class);
    }

    @Override
    public Request<?, EaiGetCompilers> eaiGetCompilers() {
        return new Request<>(
                "eai_getCompilers",
                Collections.<String>emptyList(),
                web3jService,
                EaiGetCompilers.class);
    }

    @Override
    public Request<?, EaiCompileLLL> eaiCompileLLL(String sourceCode) {
        return new Request<>(
                "eai_compileLLL",
                Arrays.asList(sourceCode),
                web3jService,
                EaiCompileLLL.class);
    }

    @Override
    public Request<?, EaiCompileSolidity> eaiCompileSolidity(String sourceCode) {
        return new Request<>(
                "eai_compileSolidity",
                Arrays.asList(sourceCode),
                web3jService,
                EaiCompileSolidity.class);
    }

    @Override
    public Request<?, EaiCompileSerpent> eaiCompileSerpent(String sourceCode) {
        return new Request<>(
                "eai_compileSerpent",
                Arrays.asList(sourceCode),
                web3jService,
                EaiCompileSerpent.class);
    }

    @Override
    public Request<?, EaiFilter> eaiNewFilter(
            org.web3j.protocol.core.methods.request.EaiFilter eaiFilter) {
        return new Request<>(
                "eai_newFilter",
                Arrays.asList(eaiFilter),
                web3jService,
                EaiFilter.class);
    }

    @Override
    public Request<?, EaiFilter> eaiNewBlockFilter() {
        return new Request<>(
                "eai_newBlockFilter",
                Collections.<String>emptyList(),
                web3jService,
                EaiFilter.class);
    }

    @Override
    public Request<?, EaiFilter> eaiNewPendingTransactionFilter() {
        return new Request<>(
                "eai_newPendingTransactionFilter",
                Collections.<String>emptyList(),
                web3jService,
                EaiFilter.class);
    }

    @Override
    public Request<?, EaiUninstallFilter> eaiUninstallFilter(BigInteger filterId) {
        return new Request<>(
                "eai_uninstallFilter",
                Arrays.asList(Numeric.toHexStringWithPrefixSafe(filterId)),
                web3jService,
                EaiUninstallFilter.class);
    }

    @Override
    public Request<?, EaiLog> eaiGetFilterChanges(BigInteger filterId) {
        return new Request<>(
                "eai_getFilterChanges",
                Arrays.asList(Numeric.toHexStringWithPrefixSafe(filterId)),
                web3jService,
                EaiLog.class);
    }

    @Override
    public Request<?, EaiLog> eaiGetFilterLogs(BigInteger filterId) {
        return new Request<>(
                "eai_getFilterLogs",
                Arrays.asList(Numeric.toHexStringWithPrefixSafe(filterId)),
                web3jService,
                EaiLog.class);
    }

    @Override
    public Request<?, EaiLog> eaiGetLogs(
            org.web3j.protocol.core.methods.request.EaiFilter eaiFilter) {
        return new Request<>(
                "eai_getLogs",
                Arrays.asList(eaiFilter),
                web3jService,
                EaiLog.class);
    }

    @Override
    public Request<?, EaiGetWork> eaiGetWork() {
        return new Request<>(
                "eai_getWork",
                Collections.<String>emptyList(),
                web3jService,
                EaiGetWork.class);
    }

    @Override
    public Request<?, EaiSubmitWork> eaiSubmitWork(
            String nonce, String headerPowHash, String mixDigest) {
        return new Request<>(
                "eai_submitWork",
                Arrays.asList(nonce, headerPowHash, mixDigest),
                web3jService,
                EaiSubmitWork.class);
    }

    @Override
    public Request<?, EaiSubmitHashrate> eaiSubmitHashrate(String hashrate, String clientId) {
        return new Request<>(
                "eai_submitHashrate",
                Arrays.asList(hashrate, clientId),
                web3jService,
                EaiSubmitHashrate.class);
    }

    @Override
    public Request<?, DbPutString> dbPutString(
            String databaseName, String keyName, String stringToStore) {
        return new Request<>(
                "db_putString",
                Arrays.asList(databaseName, keyName, stringToStore),
                web3jService,
                DbPutString.class);
    }

    @Override
    public Request<?, DbGetString> dbGetString(String databaseName, String keyName) {
        return new Request<>(
                "db_getString",
                Arrays.asList(databaseName, keyName),
                web3jService,
                DbGetString.class);
    }

    @Override
    public Request<?, DbPutHex> dbPutHex(String databaseName, String keyName, String dataToStore) {
        return new Request<>(
                "db_putHex",
                Arrays.asList(databaseName, keyName, dataToStore),
                web3jService,
                DbPutHex.class);
    }

    @Override
    public Request<?, DbGetHex> dbGetHex(String databaseName, String keyName) {
        return new Request<>(
                "db_getHex",
                Arrays.asList(databaseName, keyName),
                web3jService,
                DbGetHex.class);
    }

    @Override
    public Request<?, org.web3j.protocol.core.methods.response.ShhPost> shhPost(ShhPost shhPost) {
        return new Request<>(
                "shh_post",
                Arrays.asList(shhPost),
                web3jService,
                org.web3j.protocol.core.methods.response.ShhPost.class);
    }

    @Override
    public Request<?, ShhVersion> shhVersion() {
        return new Request<>(
                "shh_version",
                Collections.<String>emptyList(),
                web3jService,
                ShhVersion.class);
    }

    @Override
    public Request<?, ShhNewIdentity> shhNewIdentity() {
        return new Request<>(
                "shh_newIdentity",
                Collections.<String>emptyList(),
                web3jService,
                ShhNewIdentity.class);
    }

    @Override
    public Request<?, ShhHasIdentity> shhHasIdentity(String identityAddress) {
        return new Request<>(
                "shh_hasIdentity",
                Arrays.asList(identityAddress),
                web3jService,
                ShhHasIdentity.class);
    }

    @Override
    public Request<?, ShhNewGroup> shhNewGroup() {
        return new Request<>(
                "shh_newGroup",
                Collections.<String>emptyList(),
                web3jService,
                ShhNewGroup.class);
    }

    @Override
    public Request<?, ShhAddToGroup> shhAddToGroup(String identityAddress) {
        return new Request<>(
                "shh_addToGroup",
                Arrays.asList(identityAddress),
                web3jService,
                ShhAddToGroup.class);
    }

    @Override
    public Request<?, ShhNewFilter> shhNewFilter(ShhFilter shhFilter) {
        return new Request<>(
                "shh_newFilter",
                Arrays.asList(shhFilter),
                web3jService,
                ShhNewFilter.class);
    }

    @Override
    public Request<?, ShhUninstallFilter> shhUninstallFilter(BigInteger filterId) {
        return new Request<>(
                "shh_uninstallFilter",
                Arrays.asList(Numeric.toHexStringWithPrefixSafe(filterId)),
                web3jService,
                ShhUninstallFilter.class);
    }

    @Override
    public Request<?, ShhMessages> shhGetFilterChanges(BigInteger filterId) {
        return new Request<>(
                "shh_getFilterChanges",
                Arrays.asList(Numeric.toHexStringWithPrefixSafe(filterId)),
                web3jService,
                ShhMessages.class);
    }

    @Override
    public Request<?, ShhMessages> shhGetMessages(BigInteger filterId) {
        return new Request<>(
                "shh_getMessages",
                Arrays.asList(Numeric.toHexStringWithPrefixSafe(filterId)),
                web3jService,
                ShhMessages.class);
    }

    @Override
    public Observable<String> eaiBlockHashObservable() {
        return web3jRx.eaiBlockHashObservable(blockTime);
    }

    @Override
    public Observable<String> eaiPendingTransactionHashObservable() {
        return web3jRx.eaiPendingTransactionHashObservable(blockTime);
    }

    @Override
    public Observable<Log> eaiLogObservable(
            org.web3j.protocol.core.methods.request.EaiFilter eaiFilter) {
        return web3jRx.eaiLogObservable(eaiFilter, blockTime);
    }

    @Override
    public Observable<org.web3j.protocol.core.methods.response.Transaction>
            transactionObservable() {
        return web3jRx.transactionObservable(blockTime);
    }

    @Override
    public Observable<org.web3j.protocol.core.methods.response.Transaction>
            pendingTransactionObservable() {
        return web3jRx.pendingTransactionObservable(blockTime);
    }

    @Override
    public Observable<EaiBlock> blockObservable(boolean fullTransactionObjects) {
        return web3jRx.blockObservable(fullTransactionObjects, blockTime);
    }

    @Override
    public Observable<EaiBlock> replayBlocksObservable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock,
            boolean fullTransactionObjects) {
        return web3jRx.replayBlocksObservable(startBlock, endBlock, fullTransactionObjects);
    }

    @Override
    public Observable<EaiBlock> replayBlocksObservable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock,
            boolean fullTransactionObjects, boolean ascending) {
        return web3jRx.replayBlocksObservable(startBlock, endBlock,
                fullTransactionObjects, ascending);
    }

    @Override
    public Observable<org.web3j.protocol.core.methods.response.Transaction>
            replayTransactionsObservable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        return web3jRx.replayTransactionsObservable(startBlock, endBlock);
    }

    @Override
    public Observable<EaiBlock> catchUpToLatestBlockObservable(
            DefaultBlockParameter startBlock, boolean fullTransactionObjects,
            Observable<EaiBlock> onCompleteObservable) {
        return web3jRx.catchUpToLatestBlockObservable(
                startBlock, fullTransactionObjects, onCompleteObservable);
    }

    @Override
    public Observable<EaiBlock> catchUpToLatestBlockObservable(
            DefaultBlockParameter startBlock, boolean fullTransactionObjects) {
        return web3jRx.catchUpToLatestBlockObservable(startBlock, fullTransactionObjects);
    }

    @Override
    public Observable<org.web3j.protocol.core.methods.response.Transaction>
            catchUpToLatestTransactionObservable(DefaultBlockParameter startBlock) {
        return web3jRx.catchUpToLatestTransactionObservable(startBlock);
    }

    @Override
    public Observable<EaiBlock> catchUpToLatestAndSubscribeToNewBlocksObservable(
            DefaultBlockParameter startBlock, boolean fullTransactionObjects) {
        return web3jRx.catchUpToLatestAndSubscribeToNewBlocksObservable(
                startBlock, fullTransactionObjects, blockTime);
    }

    @Override
    public Observable<org.web3j.protocol.core.methods.response.Transaction>
            catchUpToLatestAndSubscribeToNewTransactionsObservable(
            DefaultBlockParameter startBlock) {
        return web3jRx.catchUpToLatestAndSubscribeToNewTransactionsObservable(
                startBlock, blockTime);
    }

    @Override
    public void shutdown() {
        scheduledExecutorService.shutdown();
    }
}
