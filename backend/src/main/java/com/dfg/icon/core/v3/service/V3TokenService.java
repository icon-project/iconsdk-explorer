package com.dfg.icon.core.v3.service;

import com.dfg.icon.core.dao.icon.*;
import com.dfg.icon.web.v3.dto.CommonListRes;
import com.dfg.icon.web.v3.dto.CommonRes;
import com.dfg.icon.web.v3.dto.PageReq;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface V3TokenService {

	/** 토큰 정보 summary 조회
	 * @param contractAddr
	 * @return
	 * @throws Exception
	 */
	CommonRes selectTokenSummary(String url, String contractAddr) throws Exception;
	
	/** 토큰 리스트 조회
	 * @param req
	 * @return
	 * @throws Exception
	 */
	CommonListRes selectTokenList(PageReq req) throws Exception;
	
	/** 토큰  Holders 조회
	 * @param req
	 * @return
	 * @throws Exception
	 */
	CommonListRes selectTokenHolders(String url, PageReq req) throws Exception;
	
	/** 토큰 transferList 정보 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	CommonListRes selectTokenTransfers(PageReq req) throws Exception;

	TContract getTokenInfoByAddress(String contractAddr);
}
