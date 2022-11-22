package com.dfg.icon.core.v3.service;

import com.dfg.icon.web.v3.dto.CommonListRes;
import com.dfg.icon.web.v3.dto.CommonRes;
import com.dfg.icon.web.v3.dto.PageReq;

public interface V3TransactionService {
	
	/** 최신 트랜잭션 정보 조회 (default 20)
	 * @param req
	 * @return
	 * @throws Exception
	 */
	CommonListRes selectRecentTx(PageReq req) throws Exception;
	
	/** 트랜잭션 상세 정보
	 * @param txHash
	 * @return
	 * @throws Exception
	 */
	CommonRes selectTxDetail(String txHash) throws Exception;

	/**  Internal Tx 리스트 조회
	 * @param req
	 * @return
	 * @throws Exception
	 */
	CommonListRes selectInternalTxList(PageReq req) throws Exception;
	
	/** 트랜잭션 eventLog 리스트 조회 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	CommonListRes selectTxEventLogList(PageReq req) throws Exception;

	String selectTxDataImage(String txHash) throws Exception;
	Long selectCount(String txType) throws Exception;

	CommonListRes selectTxBtp(String networkId, int height) throws Exception;

}
