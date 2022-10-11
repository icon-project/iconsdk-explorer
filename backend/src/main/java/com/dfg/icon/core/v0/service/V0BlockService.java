package com.dfg.icon.core.v0.service;

import java.util.List;

import com.dfg.icon.web.v0.dto.BlockByHashReq;
import com.dfg.icon.web.v0.dto.CommonRes;
import com.dfg.icon.web.v3.dto.PageReq;
import com.dfg.icon.web.v0.dto.TxInBlock;
import com.dfg.icon.web.v0.dto.block.Block;

public interface V0BlockService {
	
	/**
	 * recent block 20
	 */
	CommonRes selectRecentBlock(PageReq req) throws Exception;
	
	/**
	 * get block
	 */
	Block selectBlockDetail(PageReq req) throws Exception;
	
	/**
	 * get tx in block
	 */
	List<TxInBlock>  selectTxInBlock(PageReq req ) throws Exception;

	/**
	 * get block by hash
	 */
	Block selectBlockDetailByHash(PageReq req) throws Exception;
	
	/**
	 * get tx by hash
	 */
	List<TxInBlock> selectTxInBlockByHash(PageReq req ) throws Exception;
	
}
