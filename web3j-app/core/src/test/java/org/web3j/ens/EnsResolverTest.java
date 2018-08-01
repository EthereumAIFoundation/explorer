package org.web3j.ens;

import java.io.IOException;
import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

import org.web3j.abi.TypeDecoder;
import org.web3j.abi.TypeEncoder;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.JsonRpc2_0Web3j;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EaiBlock;
import org.web3j.protocol.core.methods.response.EaiCall;
import org.web3j.protocol.core.methods.response.EaiSyncing;
import org.web3j.protocol.core.methods.response.NetVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ChainId;
import org.web3j.utils.Numeric;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.web3j.ens.EnsResolver.DEFAULT_SYNC_THRESHOLD;
import static org.web3j.ens.EnsResolver.isValidEnsName;

public class EnsResolverTest {

    private Web3j web3j;
    private Web3jService web3jService;
    private EnsResolver ensResolver;

    @Before
    public void setUp() {
        web3jService = mock(Web3jService.class);
        web3j = Web3j.build(web3jService);
        ensResolver = new EnsResolver(web3j);
    }

    @Test
    public void testResolve() throws Exception {
        configureSyncing(false);
        configureLatestBlock(System.currentTimeMillis() / 1000);  // block timestamp is in seconds

        NetVersion netVersion = new NetVersion();
        netVersion.setResult(Byte.toString(ChainId.MAINNET));

        String resolverAddress =
                "0x0000000000000000000000004c641fb9bad9b60ef180c31f56051ce826d21a9a";
        String contractAddress =
                "0x00000000000000000000000019e03255f667bdfd50a32722df860b1eeaf4d635";

        EaiCall resolverAddressResponse = new EaiCall();
        resolverAddressResponse.setResult(resolverAddress);

        EaiCall contractAddressResponse = new EaiCall();
        contractAddressResponse.setResult(contractAddress);

        when(web3jService.send(any(Request.class), eq(NetVersion.class)))
                .thenReturn(netVersion);
        when(web3jService.send(any(Request.class), eq(EaiCall.class)))
                .thenReturn(resolverAddressResponse);
        when(web3jService.send(any(Request.class), eq(EaiCall.class)))
                .thenReturn(contractAddressResponse);

        assertThat(ensResolver.resolve("web3j.eai"),
                is("0x19e03255f667bdfd50a32722df860b1eeaf4d635"));
    }

    @Test
    public void testReverseResolve() throws Exception {
        configureSyncing(false);
        configureLatestBlock(System.currentTimeMillis() / 1000);  // block timestamp is in seconds

        NetVersion netVersion = new NetVersion();
        netVersion.setResult(Byte.toString(ChainId.MAINNET));

        String resolverAddress =
                "0x0000000000000000000000004c641fb9bad9b60ef180c31f56051ce826d21a9a";
        String contractName =
                "0x0000000000000000000000000000000000000000000000000000000000000020"
                + TypeEncoder.encode(new Utf8String("web3j.eai"));
        System.err.println(contractName);

        EaiCall resolverAddressResponse = new EaiCall();
        resolverAddressResponse.setResult(resolverAddress);

        EaiCall contractNameResponse = new EaiCall();
        contractNameResponse.setResult(contractName);

        when(web3jService.send(any(Request.class), eq(NetVersion.class)))
                .thenReturn(netVersion);
        when(web3jService.send(any(Request.class), eq(EaiCall.class)))
                .thenReturn(resolverAddressResponse);
        when(web3jService.send(any(Request.class), eq(EaiCall.class)))
                .thenReturn(contractNameResponse);

        assertThat(ensResolver.reverseResolve("0x19e03255f667bdfd50a32722df860b1eeaf4d635"),
                is("web3j.eai"));
    }

    @Test
    public void testIsSyncedSyncing() throws Exception {
        configureSyncing(true);

        assertFalse(ensResolver.isSynced());
    }

    @Test
    public void testIsSyncedFullySynced() throws Exception {
        configureSyncing(false);
        configureLatestBlock(System.currentTimeMillis() / 1000);  // block timestamp is in seconds

        assertTrue(ensResolver.isSynced());
    }

    @Test
    public void testIsSyncedBelowThreshold() throws Exception {
        configureSyncing(false);
        configureLatestBlock((System.currentTimeMillis() / 1000) - DEFAULT_SYNC_THRESHOLD);

        assertFalse(ensResolver.isSynced());
    }

    private void configureSyncing(boolean isSyncing) throws IOException {
        EaiSyncing eaiSyncing = new EaiSyncing();
        EaiSyncing.Result result = new EaiSyncing.Result();
        result.setSyncing(isSyncing);
        eaiSyncing.setResult(result);

        when(web3jService.send(any(Request.class), eq(EaiSyncing.class)))
                .thenReturn(eaiSyncing);
    }

    private void configureLatestBlock(long timestamp) throws IOException {
        EaiBlock.Block block = new EaiBlock.Block();
        block.setTimestamp(Numeric.encodeQuantity(BigInteger.valueOf(timestamp)));
        EaiBlock eaiBlock = new EaiBlock();
        eaiBlock.setResult(block);

        when(web3jService.send(any(Request.class), eq(EaiBlock.class)))
                .thenReturn(eaiBlock);
    }

    @Test
    public void testIsEnsName() {
        assertTrue(isValidEnsName("eai"));
        assertTrue(isValidEnsName("web3.eai"));
        assertTrue(isValidEnsName("0x19e03255f667bdfd50a32722df860b1eeaf4d635.eai"));

        assertFalse(isValidEnsName("0x19e03255f667bdfd50a32722df860b1eeaf4d635"));
        assertFalse(isValidEnsName("19e03255f667bdfd50a32722df860b1eeaf4d635"));

        assertTrue(isValidEnsName(""));
    }
}
