package com.dfg.icon.core.v3.service;

import java.util.Date;
import java.util.List;

import com.dfg.icon.core.dao.icon.TContract;
import com.dfg.icon.core.dao.icon.TContractHistory;
import com.dfg.icon.web.v3.dto.*;

/**
 * 컨트렉트 관련 Service
 * @author hslee
 *
 */
public interface V3ContractService {

	/** 해당 조건의 컨트렉트 리스트를 페이징 조회
	 * @param req
	 * @return
	 * @throws Exception
	 */
	List<ContractList> selectContractList(PageReq req) throws Exception;

	/** 해당 조건의 컨트렉트 총 개수 조회
	 * @param req
	 * @return
	 * @throws Exception
	 */
	int selectContractListCount(PageReq req) throws Exception;

	/** 컨트렉트 정보를 조회 
	 * @param addr
	 * @return
	 * @throws Exception
	 */
	ContractInfo selectContractInfo(String addr) throws Exception;
	
	
	/** 컨트렉트 detail 상세정보 조회
	 * @param req
	 * @return
	 * @throws Exception
	 */
	ContractDetail selectContractDetail(PageReq req) throws Exception;
	
	
	/** 컨트랙트의 트랜잭션 조회 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	CommonListRes selectContractTxList(PageReq req) throws Exception;

//	CommonRes selectContractTxCountForTxChallengeGroupByAddress(PageReq req) throws Exception;

	CommonListRes selectContractTokenTxList(PageReq req) throws Exception;
	
	/** 컨트렉트 트랜잭션 리스트를 조회 
	 * @param addr
	 * @param req
	 * @return
	 * @throws Exception
	 */
	List<TokenTransfer> selectContractTransactionList(String addr, PageReq req) throws Exception;

	/** 컨트렉트 주소로 IRC 타입을 조회 
	 * @param contractAddr
	 * @return
	 */
	String getIrcTypeByAddress(String contractAddr);

	/** 주소로 컨트렉트 조회
	 * @param contractAddr
	 * @return
	 */
	TContract getContractByAddress(String contractAddr);

	/** 설치/업뎃 txHash로 히스토리 조회
	 * @param txHash
	 * @return
	 */
	TContractHistory getContractHistoryByCreateTx(String txHash);
	
	/** 컨트렉트 히스토리  조회 
	 * @param addr
	 * @param page
	 * @param count
	 * @return
	 * @throws Exception
	 */
	List<TContractHistory> selectContractHistoryList(String addr, int page , int count) throws Exception;
	
	/** 히스토리 카운터 조회  
	 * @param addr
	 * @return
	 * @throws Exception
	 */
	int selectContractHistoryListCount(String addr) throws Exception;
	
	/** contract event Log 리스트 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	CommonListRes selectTokenEventLogList(PageReq req) throws Exception;

	List<ContractPendingInfo> selectRequireContractList(PageReq req) throws Exception;
	int selectRequireContractListCount() throws Exception;


	TContract getContractForInsert(String contractAddr, String name, Integer version, String ircVersion, Byte state);
	TContractHistory getContractHistoryForInsert(String contractAddr, Integer version, String compiler, String createTx, Date createDate, String verifiedTx, Date verifiedDate, String creator, Byte state);

	int getContractLastVersionForUpdate(String contractAddr);

	void initContractInfo();
}
