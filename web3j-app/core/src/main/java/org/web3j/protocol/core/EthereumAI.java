package org.web3j.protocol.core;

import java.math.BigInteger;

import org.web3j.protocol.core.methods.request.ShhFilter;
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
import org.web3j.protocol.core.methods.response.TxPoolContent;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.core.methods.response.Web3Sha3;

/**
 * Core EthereumAI JSON-RPC API.
 */
public interface EthereumAI {
    Request<?, EaiAccounts> debugGetModifiedAccountsByNumber(int start, int end);

    Request<?, TxPoolContent> txPoolContent();

    Request<?, Web3ClientVersion> web3ClientVersion();

    Request<?, Web3Sha3> web3Sha3(String data);

    Request<?, NetVersion> netVersion();

    Request<?, NetListening> netListening();

    Request<?, NetPeerCount> netPeerCount();

    Request<?, EaiProtocolVersion> eaiProtocolVersion();

    Request<?, EaiCoinbase> eaiCoinbase();

    Request<?, EaiSyncing> eaiSyncing();

    Request<?, EaiMining> eaiMining();

    Request<?, EaiHashrate> eaiHashrate();

    Request<?, EaiGasPrice> eaiGasPrice();

    Request<?, EaiAccounts> eaiAccounts();

    Request<?, EaiBlockNumber> eaiBlockNumber();

    Request<?, EaiGetBalance> eaiGetBalance(
            String address, DefaultBlockParameter defaultBlockParameter);

    Request<?, EaiGetStorageAt> eaiGetStorageAt(
            String address, BigInteger position,
            DefaultBlockParameter defaultBlockParameter);

    Request<?, EaiGetTransactionCount> eaiGetTransactionCount(
            String address, DefaultBlockParameter defaultBlockParameter);

    Request<?, EaiGetBlockTransactionCountByHash> eaiGetBlockTransactionCountByHash(
            String blockHash);

    Request<?, EaiGetBlockTransactionCountByNumber> eaiGetBlockTransactionCountByNumber(
            DefaultBlockParameter defaultBlockParameter);

    Request<?, EaiGetUncleCountByBlockHash> eaiGetUncleCountByBlockHash(String blockHash);

    Request<?, EaiGetUncleCountByBlockNumber> eaiGetUncleCountByBlockNumber(
            DefaultBlockParameter defaultBlockParameter);

    Request<?, EaiGetCode> eaiGetCode(String address, DefaultBlockParameter defaultBlockParameter);

    Request<?, EaiSign> eaiSign(String address, String sha3HashOfDataToSign);

    Request<?, org.web3j.protocol.core.methods.response.EaiSendTransaction> eaiSendTransaction(
            org.web3j.protocol.core.methods.request.Transaction transaction);

    Request<?, org.web3j.protocol.core.methods.response.EaiSendTransaction> eaiSendRawTransaction(
            String signedTransactionData);

    Request<?, org.web3j.protocol.core.methods.response.EaiCall> eaiCall(
            org.web3j.protocol.core.methods.request.Transaction transaction,
            DefaultBlockParameter defaultBlockParameter);

    Request<?, EaiEstimateGas> eaiEstimateGas(
            org.web3j.protocol.core.methods.request.Transaction transaction);

    Request<?, EaiBlock> eaiGetBlockByHash(String blockHash, boolean returnFullTransactionObjects);

    Request<?, EaiBlock> eaiGetBlockByNumber(
            DefaultBlockParameter defaultBlockParameter,
            boolean returnFullTransactionObjects);

    Request<?, EaiTransaction> eaiGetTransactionByHash(String transactionHash);

    Request<?, EaiTransaction> eaiGetTransactionByBlockHashAndIndex(
            String blockHash, BigInteger transactionIndex);

    Request<?, EaiTransaction> eaiGetTransactionByBlockNumberAndIndex(
            DefaultBlockParameter defaultBlockParameter, BigInteger transactionIndex);

    Request<?, EaiGetTransactionReceipt> eaiGetTransactionReceipt(String transactionHash);

    Request<?, EaiBlock> eaiGetUncleByBlockHashAndIndex(
            String blockHash, BigInteger transactionIndex);

    Request<?, EaiBlock> eaiGetUncleByBlockNumberAndIndex(
            DefaultBlockParameter defaultBlockParameter, BigInteger transactionIndex);

    Request<?, EaiGetCompilers> eaiGetCompilers();

    Request<?, EaiCompileLLL> eaiCompileLLL(String sourceCode);

    Request<?, EaiCompileSolidity> eaiCompileSolidity(String sourceCode);

    Request<?, EaiCompileSerpent> eaiCompileSerpent(String sourceCode);

    Request<?, EaiFilter> eaiNewFilter(org.web3j.protocol.core.methods.request.EaiFilter eaiFilter);

    Request<?, EaiFilter> eaiNewBlockFilter();

    Request<?, EaiFilter> eaiNewPendingTransactionFilter();

    Request<?, EaiUninstallFilter> eaiUninstallFilter(BigInteger filterId);

    Request<?, EaiLog> eaiGetFilterChanges(BigInteger filterId);

    Request<?, EaiLog> eaiGetFilterLogs(BigInteger filterId);

    Request<?, EaiLog> eaiGetLogs(org.web3j.protocol.core.methods.request.EaiFilter eaiFilter);

    Request<?, EaiGetWork> eaiGetWork();

    Request<?, EaiSubmitWork> eaiSubmitWork(String nonce, String headerPowHash, String mixDigest);

    Request<?, EaiSubmitHashrate> eaiSubmitHashrate(String hashrate, String clientId);

    Request<?, DbPutString> dbPutString(String databaseName, String keyName, String stringToStore);

    Request<?, DbGetString> dbGetString(String databaseName, String keyName);

    Request<?, DbPutHex> dbPutHex(String databaseName, String keyName, String dataToStore);

    Request<?, DbGetHex> dbGetHex(String databaseName, String keyName);

    Request<?, org.web3j.protocol.core.methods.response.ShhPost> shhPost(
            org.web3j.protocol.core.methods.request.ShhPost shhPost);

    Request<?, ShhVersion> shhVersion();

    Request<?, ShhNewIdentity> shhNewIdentity();

    Request<?, ShhHasIdentity> shhHasIdentity(String identityAddress);

    Request<?, ShhNewGroup> shhNewGroup();

    Request<?, ShhAddToGroup> shhAddToGroup(String identityAddress);

    Request<?, ShhNewFilter> shhNewFilter(ShhFilter shhFilter);

    Request<?, ShhUninstallFilter> shhUninstallFilter(BigInteger filterId);

    Request<?, ShhMessages> shhGetFilterChanges(BigInteger filterId);

    Request<?, ShhMessages> shhGetMessages(BigInteger filterId);
}
