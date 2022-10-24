package com.dfg.icon.core.v2.service.impl;

import com.dfg.icon.core.v2.adapter.V2BlockChainAdapter;
import com.dfg.icon.core.v2.service.V2BlockChainService;
import com.dfg.icon.core.v2.vo.*;
import com.dfg.icon.core.v3.vo.DecimalType;
import com.dfg.icon.util.HexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class V2BlockChainServiceImpl implements V2BlockChainService {

	@Autowired
	V2BlockChainAdapter blockChainAdapter;

	@Override
	public String getBalance(String url, String address) throws Exception{
		RpcRes res = blockChainAdapter.getBalance(url, address);
		return HexUtil.toDecimalString(res.getResult().getResponse(), DecimalType.ICX.getValue());
	}
}