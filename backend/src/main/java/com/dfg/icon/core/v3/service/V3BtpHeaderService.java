package com.dfg.icon.core.v3.service;

import com.dfg.icon.web.v3.dto.CommonListRes;
import com.dfg.icon.web.v3.dto.CommonRes;
import com.dfg.icon.web.v3.dto.PageReq;

public interface V3BtpHeaderService {

	CommonListRes getBtpHeaderList(PageReq req);

	CommonListRes getBtpHeaderListByNetworkId(String networkId, PageReq req);

	CommonRes getBtpHeader(int height, String networkId);

	CommonRes getBtpMessage(int height, String networkId);

}
