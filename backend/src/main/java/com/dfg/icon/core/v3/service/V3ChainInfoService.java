package com.dfg.icon.core.v3.service;

import com.dfg.icon.web.v3.dto.CommonListRes;
import com.dfg.icon.web.v3.dto.CommonRes;

public interface V3ChainInfoService {
    CommonListRes selectChainInfoList();

    CommonRes selectChainByName(String name);

    String chainHost(String name);
}