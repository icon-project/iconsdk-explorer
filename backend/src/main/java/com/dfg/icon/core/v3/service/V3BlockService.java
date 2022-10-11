package com.dfg.icon.core.v3.service;

import java.util.List;

import com.dfg.icon.web.v0.dto.TxInBlock;
import com.dfg.icon.web.v0.dto.block.Block;
import com.dfg.icon.web.v3.dto.CommonListRes;
import com.dfg.icon.web.v3.dto.PageReq;

public interface V3BlockService {
	

	/** recent block 20
	 * @param req
	 * @return
	 * @throws Exception
	 */
	CommonListRes selectRecentBlock(PageReq req) throws Exception;

	/** get block
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Block selectBlockDetail(PageReq req) throws Exception;
	
	/** get tx in block
	 * @param req
	 * @return
	 * @throws Exception
	 */
	List<TxInBlock>  selectTxInBlock(PageReq req ) throws Exception;

	/** get block by hash
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Block selectBlockDetailByHash(PageReq req) throws Exception;
	
	/** get tx by hash
	 * @param req
	 * @return
	 * @throws Exception
	 */
	List<TxInBlock> selectTxInBlockByHash(PageReq req ) throws Exception;
	
}
