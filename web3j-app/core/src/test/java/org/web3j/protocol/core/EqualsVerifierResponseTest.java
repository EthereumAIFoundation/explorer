package org.web3j.protocol.core;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

import org.web3j.protocol.core.methods.response.AbiDefinition;
import org.web3j.protocol.core.methods.response.EaiBlock;
import org.web3j.protocol.core.methods.response.EaiCompileSolidity;
import org.web3j.protocol.core.methods.response.EaiLog;
import org.web3j.protocol.core.methods.response.EaiSyncing;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.ShhMessages;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

public class EqualsVerifierResponseTest {

    @Test
    public void testBlock() {
        EqualsVerifier.forClass(EaiBlock.Block.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    public void testTransaction() {
        EqualsVerifier.forClass(Transaction.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    public void testTransactionReceipt() {
        EqualsVerifier.forClass(TransactionReceipt.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    public void testLog() {
        EqualsVerifier.forClass(Log.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    public void testSshMessage() {
        EqualsVerifier.forClass(ShhMessages.SshMessage.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    public void testSolidityInfo() {
        EqualsVerifier.forClass(EaiCompileSolidity.SolidityInfo.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    public void testSyncing() {
        EqualsVerifier.forClass(EaiSyncing.Syncing.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    public void testAbiDefinition() {
        EqualsVerifier.forClass(AbiDefinition.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    public void testAbiDefinitionNamedType() {
        EqualsVerifier.forClass(AbiDefinition.NamedType.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    public void testHash() {
        EqualsVerifier.forClass(EaiLog.Hash.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    public void testCode() {
        EqualsVerifier.forClass(EaiCompileSolidity.Code.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    public void testTransactionHash() {
        EqualsVerifier.forClass(EaiBlock.TransactionHash.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    public void testCompiledSolidityCode() {
        EqualsVerifier.forClass(EaiCompileSolidity.Code.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    public void testDocumentation() {
        EqualsVerifier.forClass(EaiCompileSolidity.Documentation.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    public void testError() {
        EqualsVerifier.forClass(Response.Error.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }
}
