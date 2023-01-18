package com.dfg.icon.core.v3.service;

import com.dfg.icon.web.v3.dto.CommonListRes;
import com.dfg.icon.web.v3.dto.CommonRes;
import com.dfg.icon.web.v3.dto.PageReq;

public interface V3BtpHeaderService {

	CommonListRes getBtpHeaderList(PageReq req, String networkId);

	CommonRes getBtpHeader(int height, String networkId);

	CommonRes getBtpMessage(String url, int height, String networkId, int sequenceNumber) throws Exception;

}
