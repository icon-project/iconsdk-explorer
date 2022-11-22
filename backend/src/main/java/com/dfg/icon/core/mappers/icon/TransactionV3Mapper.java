package com.dfg.icon.core.mappers.icon;

import java.util.List;

import com.dfg.icon.core.dao.icon.*;
import com.dfg.icon.core.v3.vo.TxCount;
import com.dfg.icon.web.v3.dto.*;
import org.apache.ibatis.annotations.Param;

public interface TransactionV3Mapper {


	List<TxDetailDto> selectTxBtpList(@Param("height") int height, @Param("networkId") String networkId);
	
	/** 
	 * Tx 메인 페이징 조회
	 */
	List<TxDetailDto> selectRecentTxList(PageReq req);

	// desc 최신순 조회
	List<TxDetailDto> selectTotalTxListFromView(PageReq req);

	/**
	 * (지갑용)
	 */
	List<TxDetailDto> selectTotalTxListFromViewForWallet(PageReq req);

	/**
	 * 전체 페이징 조회 (for Contract)
	 */
	List<TxDetailDto> selectContractTxList(PageReq req);

//	List<TxChallengeDto> selectContractTxCountForChallenge(PageReq req);

	
	/** TxDetail에서 토큰송금 내역 조회
	 */
	List<TxDetailTokenTx> selectTokenTx(@Param("txHash") String txHash);

	/** TxEventLog 리스트 조회
	 */
	List<TxResultLog> selectEventLogList(PageReq req);
	
	
	/** TxEventLog 전체 리스트 개수
	 * @param req
	 * @return
	 */
	Integer selectTotalCountEventLog(PageReq req);
	
	/**
	 * 페이징 용 전체 트랜잭션 개수
	 */
	Integer selectTotalTxCount(PageReq req);
	
	/** 페이징 용 전체 트랜잭션 갯수 (지갑용)
	 * @param req
	 * @return
	 */
	Integer selectTotalTxCountForWallet(PageReq req);

	/** 
	 * 해당 Tx에 관한 tx 정보
	 */
	TxDetail selectTxDetail(@Param("txHash") String txHash);

	/**
	 * 해당 tx의 internal tx 조회
	 */
	List<InternalTx> selectInternalTx(@Param("txHash") String txHash);
	List<InternalTx> selectInternalTxPaging(PageReq req);
	List<InternalTx> selectInternalTxPagingByAddress(PageReq req);
	Integer selectInternalTxCount(PageReq req);
	Integer selectInternalTxCountByAddress(PageReq req);


	Integer selectTxListCountForDownload(PageReq req);
	List<TTransactionTotal> selectTxListForDownload(PageReq req);
	List<TxCount> selectTxCountForDownload(PageReq req);

	int updateReportedCountAdd(@Param("txHash") String txHash);

	TAddressTotal getFeeSum(PageReq req);
}

