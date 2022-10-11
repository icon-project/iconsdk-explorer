package com.dfg.icon.core.mappers.icon;

import java.util.Date;
import java.util.List;

import com.dfg.icon.core.dao.icon.*;
import com.dfg.icon.web.v3.dto.PageReq;
import org.apache.ibatis.annotations.Param;

import com.dfg.icon.web.v3.dto.TxDetail;

public interface TransactionMapper {

	
	/** 
	 * Tx 메인 페이징 조회
	 */
	List<TxDetail> selectRecentTxList(PageReq req);

	/**
	 * 전체 페이징 조회 (for address)
	 */
	List<TxDetail> selectTotalTxList(PageReq req);

	/**
	 * 현재 조회한 트랜잭션의 블록 높이 조회
	 */
	Integer selectTxHeight(@Param("txHash") String txHash);
	
	/** 
	 * 선택한 Tx에 관한 모든 Tx 정보 
	 */
	TxDetail selectTxDetail(@Param("txHash") String txHash);
	
	int insertTxArray(List<TTransactionTotalWithBLOBs> list);
	int insertTxTotalArray(List<TTransactionTotalWithBLOBs> list);

	int insertTxViewArray(List<TTransactionView> list);
	int insertTxTokenViewArray(List<TTokenTxView> list);

	int insertInternalTx(List<TInternalTx> list);
	int insertInternalTxView(List<TInternalTxView> list);
	int insertFeeDetails(List<TFeeDetails> list);

	/** txData 값을 입력
	 * @param list
	 * @return
	 */
	int insertTxDataArray(List<TTxDataWithBLOBs> list);

	String selectCreateDateByLimit(@Param("limit") int limit );
	TTransaction selectTransactionByLimit(@Param("limit") int limit);

	int deleteCreateDateByLimit(@Param("height") String height);
	int deleteTransactionByLimit(TTransaction limitTx);

	int updateTxCancel(TContractHistory history);
	int updateTxTotalCancel(TContractHistory history);
	int updateTxViewCancel(TContractHistory history);
}

