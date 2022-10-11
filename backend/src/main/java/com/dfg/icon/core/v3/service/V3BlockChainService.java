package com.dfg.icon.core.v3.service;

import java.util.Date;

public interface V3BlockChainService {


    void blockLimit(Integer limit);
    void transactionLimit(Integer limit);
    void addressLimit(Integer limit);
    void updateMain() throws Exception;
    void updateChart() throws Exception;
//    void updatePrepTerm() throws Exception;
    String getBalance(String address, Integer decimals) throws Exception;
    String getTokenBalance(String address, String contractAddr, Integer decimals) throws Exception;
    void collectBalanceAddress() throws Exception;
    /**
     * 블럭 단위로 트랜잭션을 처리해서 데이터 저장 
     * 
     * @param height
     * @return
     * @throws Exception
     */
    void blockChainSyncUpdateAllinOne(Integer height, Integer lastHeight, Date updateDate) throws Exception;





}
