package com.dfg.icon.core.v3.service;

import com.dfg.icon.core.dao.icon.TAddressTotal;
import com.dfg.icon.web.v3.dto.CommonListRes;
import com.dfg.icon.web.v3.dto.CommonRes;
import com.dfg.icon.web.v3.dto.PageReq;

import java.util.Set;

public interface V3BtpNetworkService {

	CommonListRes getBtpNetworkList(PageReq req);

	CommonRes getBtpNetwork(String networkId);

}
