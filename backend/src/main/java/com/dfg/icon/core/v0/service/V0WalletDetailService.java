package com.dfg.icon.core.v0.service;

import java.util.List;

import com.dfg.icon.web.v0.dto.CommonRes;
import com.dfg.icon.web.v3.dto.PageReq;
import com.dfg.icon.web.v0.dto.WalletInfoDto;
import com.dfg.icon.web.v0.dto.WalletTxReq;
import com.dfg.icon.web.v0.dto.block.Transaction;
import com.dfg.icon.web.v3.dto.TxDetail;

public interface V0WalletDetailService {


	/** 주소 리스트 조회
	 * @return
	 * @throws Exception
	 */
	CommonRes selectAddrList(PageReq req) throws Exception;

	/** 주소와 관련된 상세 정보 조회
	 * @param req
	 * @return
	 * @throws Exception
	 */
	WalletInfoDto selectWalletDetail(WalletTxReq req) throws Exception;

	/** 주소와 관련된 트랜잭션 정보 조회
	 * @param req
	 * @return
	 * @throws Exception
	 */
	 List<TxDetail> selectWalletTx(WalletTxReq req) throws Exception;
}
