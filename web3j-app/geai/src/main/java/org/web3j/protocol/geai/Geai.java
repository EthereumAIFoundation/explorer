package org.web3j.protocol.geai;

import org.web3j.protocol.Web3jService;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.BooleanResponse;
import org.web3j.protocol.admin.methods.response.PersonalSign;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.MinerStartResponse;
import org.web3j.protocol.geai.response.PersonalEcRecover;
import org.web3j.protocol.geai.response.PersonalImportRawKey;

/**
 * JSON-RPC Request object building factory for Geai. 
 */
public interface Geai extends Admin {
    static Geai build(Web3jService web3jService) {
        return new JsonRpc2_0Geai(web3jService);
    }
        
    Request<?, PersonalImportRawKey> personalImportRawKey(String keydata, String password);
    
    Request<?, BooleanResponse> personalLockAccount(String accountId);
    
    Request<?, PersonalSign> personalSign(String message, String accountId, String password);
    
    Request<?, PersonalEcRecover> personalEcRecover(String message, String signiture);

    Request<?, MinerStartResponse> minerStart(int threadCount);

    Request<?, BooleanResponse> minerStop();

}
