package com.dfg.icon.core.v2.adapter;


import com.dfg.icon.core.v2.vo.GenesisRpcRes;
import com.dfg.icon.core.v2.vo.RpcRes;

public interface V2BlockChainAdapter {
    /**
     * ์์ก ์กฐํ
     */
    RpcRes getBalance(String name, String address) throws Exception;
}
