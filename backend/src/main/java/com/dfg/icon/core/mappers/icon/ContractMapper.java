package com.dfg.icon.core.mappers.icon;

import java.util.List;

import com.dfg.icon.web.v3.dto.*;
import org.apache.ibatis.annotations.Param;

import com.dfg.icon.core.dao.icon.TContract;
import com.dfg.icon.core.dao.icon.TContractHistory;

/** 컨트렉트 관련 mapper
 * @author hslee
 *
 */
public interface ContractMapper {
	
	/** 
	 * 컨트렉트 리스트 조회  
	 */
	List<ContractList> selectContractList(PageReq req);
	
	/** 
	 * 컨트렉트 리스트 총 갯수 조회   
	 */
	int selectContractListCount(PageReq req);

	/** 
	 * 컨트렉트 정보조회    
	 */
	ContractInfo selectContractInfo(@Param("addr") String addr);
	List<ContractVersion> selectContractVersion(TContractHistory history);
	
	/**
	 * 컨트렉트 상세 정보 조회 
	 */
	ContractDetail selectContractDetail(PageReq req);
	
	/** 
	 *   IRC 포멧이 아닌 컨트렉트 tx 리스트 조회 
	 */
	List<TokenTransfer> selectNoIrcContractTxList(@Param("addr") String addr, @Param("page") int page, @Param("count") int count);
	
		/** 
	 *    컨트렉트 Token Transfer 리스트 조회 
	 */
	List<ContractTx> selectContractTokenTxList(PageReq req);
	
	/** 
	 *  컨트렉트 Token Transfer 갯수 조회
	 */
	int selectContractTokenTxCount(PageReq  req);

	/** 하나의 블록에 생성된 Contract를 db에 저장 
	 * @param list
	 * @return
	 */
	int insertContractArray(List<TContract> list);
	
	/** 하나의 블록에 생성된 Contract 이력 정보를 DB에 저장 
	 * @param list
	 * @return
	 */
	int insertContractHistoryArray(List<TContractHistory> list);
	

	/**
	 * 취소대상의 createTx 조회
	 *
	 * @param history
	 * @return
	 */
	List<TContractHistory> selectHistoryForCancel(TContractHistory history);

	List<ContractPendingInfo> selectRequireContractList(PageReq req);
	int selectRequireContractListCount();

	int updateHistoryToCancel(TContractHistory history);
	int updateContractTxToCancel(TContractHistory history);
}
