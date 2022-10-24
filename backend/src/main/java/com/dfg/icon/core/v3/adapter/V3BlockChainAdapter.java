package com.dfg.icon.core.v3.adapter;

import com.dfg.icon.core.v3.vo.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public interface V3BlockChainAdapter {
    /**
     * 높이로 블록 상세정보 조회 
     * @param height
     * @return
     * @throws Exception
     */
    BlockFactory getBlockFactoryByHeight(String url,Integer height) throws Exception;

    Integer getHeightByBlock(String url,Integer height) throws Exception;
    
    /**
     * txHash 로  TransAction Result 조회 
     * @param txHash
     * @return
     * @throws Exception
     */
    TxResultFactory getTransactionResult(String url,String txHash) throws Exception;

    GenesisRpcRes getBlockInfoByGenesis(String url) throws Exception;

    /**
     * 잔액 조회
     */
    RpcBalanceRes getBalance(String url,String address) throws Exception;
    RpcBalanceRes getTotalSupply(String url) throws Exception;
    
    /** address로 ScoreApi 조회 
     * @param address
     * @return
     * @throws Exception
     */
    JsonArray getIcxScoreApi(String url,String address)throws Exception;
    
    /**
     * icx_call
     */
    String getIcxCall(String url, String toAddress, String method, String... tokenAddress);

    JsonObject getScoreStatus(String url,String address);

    RpcStakeRes getStake(String url,String address);

}
