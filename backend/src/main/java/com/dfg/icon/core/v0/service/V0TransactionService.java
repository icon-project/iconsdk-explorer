package com.dfg.icon.core.v0.service;

import com.dfg.icon.web.v0.dto.CommonRes;
import com.dfg.icon.web.v3.dto.PageReq;

public interface V0TransactionService {

	/** 최신 트랜잭션 정보 조회 (20개씩)
	 * @param req
	 * @return
	 * @throws Exception
	 */
	CommonRes selectRecentTx(PageReq req) throws Exception;

	/** 트랜잭션 상세 정보
	 * @param req
	 * @return
	 * @throws Exception
	 */
	CommonRes selectTxDetail(PageReq req) throws Exception;
}
