package com.dfg.icon.core.v3.vo;

import com.google.gson.JsonObject;

import java.util.List;

public interface TxResultFactory {
	
	/**
	 * TxResult init setting
	 */
	// 신규 사용 
	 void init(JsonObject txResultInfo) throws Exception;
	
	/**
	 * 해당 TX 의 status(1:success, 0:failure) 반환
	 */
	 Boolean getStatus() throws Exception;

	/**
	 * status가 0인 경우 존재하는 failure 반환
	 */
	 TxResultFailure getFailure() throws Exception;

	/**
	 * txHash 반환
	 */
	 String getTxHash() throws Exception;
	
	
	/** txIndex 반환
	 */
	 Integer getTxIndex() throws Exception;
	
	
	/** blockHeight 반환
	 */
	 Integer getBlockHeight() throws Exception;
	
	/** blockHash 반한
	 */
	 String getBlockHash() throws Exception;

	/** tx 수행하기까지 소비된 step 누적량
	 */
	 String getCumulativeStepUsed() throws Exception;

	/** tx 수행하는데 소비된 step 양
	 */
	 String getStepUsed() throws Exception;

	/**
	 * 스탭당 가격
	 */
	 String getStepPrice() throws Exception;

	/**
	 * fee 2.0
	 */
	String getStepUsedDetails() throws Exception;

	/**
	 * 수수료 계산 후 반환
	 */
	 String getFee() throws Exception;

	/** score를 생성했을 경우 해당 score 주소
	 */
	 String getScoreAddress() throws Exception;

	 String getTo() throws Exception;

	List<EventLog> getEventLogs() throws Exception;

	/**
	 * 업데이트 이전의 v2와 이후의 v2 구분을 위한 version.
	 * 업데이트 이후의 v2는 v3와 같으므로 v3 set.
	 * @return
	 */
	String getVersion();

	long getJsonTime();
	void setJsonTime(long time);
}
