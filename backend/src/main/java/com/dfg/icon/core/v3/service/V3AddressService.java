package com.dfg.icon.core.v3.service;

import com.dfg.icon.core.dao.icon.TAddressTotal;
import com.dfg.icon.web.v3.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface V3AddressService {

	
	/** 전체 주소 리스트 조회 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	CommonListRes getAddressList(PageReq req) throws Exception;

	/** 주소와 관련된 상세 정보 조회
	 * @param req
	 * @return
	 * @throws Exception
	 */
	CommonRes selectAddressInfo(PageReq req) throws Exception;
	
	/** 특정 주소의 트랜잭션 조회
	 * @param req
	 * @return
	 * @throws Exception
	 */
	CommonListRes getAddressTxList(PageReq req) throws Exception;
	
	/** 지갑용 특정 주소  TxList 조회 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	CommonListRes getAddressTxListForWallet(PageReq req) throws Exception;
	
	/** 특정 주소의 토큰 트랜잭션 조회
	 * @param req
	 * @return
	 * @throws Exception
	 */
	CommonListRes selectAddressTokenTx(PageReq req) throws Exception;

	/** 페이징 용 특정 주소의 총 트랜잭션 개수
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Integer getTotalTxCount(PageReq req) throws Exception;

	/** 페이징 용 특정 주소의 총 토큰 트랜잭션 개수
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Integer getTotalTokenTxCount(PageReq req) throws Exception;

	/**
	 * 실시간 잔액보유 주소개수 조회
	 * @return
	 * @throws Exception
	 */
	CommonRes getBalanceAddressCount() throws Exception;

	TAddressTotal initGenesisTotal() throws Exception;
	TAddressTotal initTreasuryTotal() throws Exception;

	Set<String> getWhiteAddressList();
}
