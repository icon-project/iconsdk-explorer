package com.dfg.icon.core.v3.vo;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dfg.icon.core.dao.icon.*;
import com.google.gson.JsonObject;

/** 블록 정보 Factory
 * @author hslee
 *
 */
public interface BlockFactory {

	/**
	 * loop chain에서 가져온 데이터를 초기화
	 */
	public void init(JsonObject resultObject, int BlockSize) throws Exception;

	/**
	 * deploy된 contract 리스트 조회
	 */
	public List<TContract> getContractList() throws Exception;

	/**
	 * contract hisotry
	 */
	public List<TContractHistory> getContractHistoryList() throws Exception;

	public List<TContractHistory> getCanceledHistoryList() throws Exception;

	/**
	 * 토큰 트랜잭션 리스트 조회
	 */
	public List<TTokenTx> getTokenTxList() throws Exception;

	public List<TTokenTxView> getTokenTxViewList() throws Exception;

	/**
	 * 잔액을 업데이트해야 할 토큰 주소 그룹
	 */
	public List<TokenAddressGroupKey> getTokenAddressGroup() throws Exception;

	/**
	 * 이벤트 로그 리스트 조회
	 */
	public List<TTxResultLogWithBLOBs> getTxResultLogList() throws Exception;

	/** Block 정보 조회
	 * @return
	 * @throws Exception
	 */
	public TBlockTotal getBlockInfo() throws Exception;

	public String getVersion() throws Exception;

	/**
	 * 트랜잭션 리스트 조회
	 */
	public List<TTransactionTotalWithBLOBs> getTransactionList()throws Exception;

	public List<TTransactionView> getTransactionViewList() throws Exception;

	/**
	 * 트랜잭션 데이터 리스트 조회
	 */
	public List<TTxDataWithBLOBs> getTransactionDataList()throws Exception;

	/**
	 * 잔액을 업데이트해야 할 주소 그룹
	 */
	public Set<String> getAddressGroup() throws Exception;


	/**
	 * tokenTxCount, holderCount를 업데이트 해야 할 스코어 주소 그룹
	 */
	public Set<String> getTokenInfoGroup() throws Exception;

	public Set<String> getStakeGroup();

	/**
	 * Internal Tx 리스트
	 * @return
	 * @throws Exception
	 */
	public List<TInternalTx> getInternalTransactionList() throws Exception;

	public List<TInternalTxView> getInternalTxViewList() throws Exception;

	public List<TFeeDetails> getFeeDetailList();

	/**
	 * contract deploy tx가 있는 경우 중간 insert를 구분하기 위한 flag
	 * @return
	 */
	public boolean isBreak();
	public boolean isDelegation();
	public void setBreak(boolean isBreak);
	public void setDelegation(boolean isDelegation);

}
