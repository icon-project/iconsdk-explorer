package com.dfg.icon.core.v3.vo;

import com.dfg.icon.core.dao.icon.*;

import com.google.gson.JsonObject;

import java.util.List;

public interface TxFactory {

	/**
	 * 해당 TX를 초기화
	 * @return
	 */
	// 신규 사용
	public void init(TBlockTotal block ,JsonObject txInfo ) throws Exception;

	/**
	 * 해당 TX 의 Step 값을 반환
	 * @return
	 */
	public String getFee() throws Exception;

	/**
	 * 표준 TX 테이블 형태에 맞추어서 반환
	 * @return
	 */
	public TTransactionTotalWithBLOBs getTransaction() throws Exception;

	public List<TTransactionView> getTransactionView() throws Exception;


	/** TX 의 버젼을 조회
	 * @return
	 */
	public String getVersion() throws Exception;


	/** TX 의 버젼을 값을 조회
	 * @return
	 */
	public String getValue() throws Exception;

	/** 데이터 필드를 조회
	 * @return
	 */
	public TTxDataWithBLOBs getData() throws Exception;

	/** 컨텐츠 데이터  필드를 조회 (score file)
	 * @return
	 */
	public String getContentData() throws Exception;


	/** 이외에 데이터를 key를 이용해서 조회
	 * @return
	 */
	public String getEtc(String key) throws Exception;


	/** 데이터가 json일 경우 key에 해당 하는 값을 조회
	 * @return
	 */
	public String getDataJsonValueByKey(String... key) throws Exception ;
}
