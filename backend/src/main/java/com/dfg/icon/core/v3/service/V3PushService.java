package com.dfg.icon.core.v3.service;

import com.dfg.icon.core.dao.icon.TAddressTotal;
import com.dfg.icon.web.v3.dto.*;

public interface V3PushService {


	CommonRes isRegistered(PushKeyReq req) throws Exception;
	CommonRes registerPush(PushReq req) throws Exception;
	CommonRes withdrawPush(PushKeyReq req) throws Exception;
}
