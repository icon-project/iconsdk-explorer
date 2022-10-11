package com.dfg.icon.core.v2.adapter;


import com.dfg.icon.core.v2.vo.GenesisRpcRes;
import com.dfg.icon.core.v2.vo.RpcRes;

public interface V2BlockChainAdapter {

    /**
     * 최신 블록정보 조회
     */
    RpcRes getLastBlock() throws Exception;

    /**
     * 높이로 블록 상세정보 조회
     * @param height
     * @return
     * @throws Exception
     */
    RpcRes getBlockInfoByHeight(Integer height) throws Exception;

    /**
     * 높이로 블록 상세정보 조회
     */
    GenesisRpcRes getBlockInfoByGenesis() throws Exception;

    /**
     * 잔액 조회
     */
    RpcRes getBalance(String address) throws Exception;
}
