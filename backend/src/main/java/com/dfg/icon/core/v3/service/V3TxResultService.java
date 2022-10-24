package com.dfg.icon.core.v3.service;

import com.dfg.icon.core.v3.vo.BlockFactory;


public interface V3TxResultService {

    BlockFactory initTxResult(String url, BlockFactory bf) throws Exception;
	
}
