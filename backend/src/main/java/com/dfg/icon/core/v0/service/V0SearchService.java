package com.dfg.icon.core.v0.service;

import com.dfg.icon.web.v0.dto.SearchReq;
import com.dfg.icon.web.v0.dto.SimpleRes;

public interface V0SearchService {

	/** 검색 api
	 * @param req
	 * @return
	 * @throws Exception
	 */
	SimpleRes search(SearchReq req) throws Exception;


}
