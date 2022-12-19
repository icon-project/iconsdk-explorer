package com.dfg.icon.core.mappers.icon;

import com.dfg.icon.core.dao.icon.TContract;
import com.dfg.icon.core.dao.icon.TTokenTx;
import com.dfg.icon.core.dao.icon.TTxResultLog;
import com.dfg.icon.core.dao.icon.TTxResultLogWithBLOBs;
import com.dfg.icon.web.v3.dto.*;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TokenMapper {

	List<TokenInfo> selectTokenInfoList(PageReq req);

	Integer selectTokenInfoTotalCount(PageReq req);

	List<TokenTransfers> selectTokenTransferList(PageReq req);
	List<TokenTransfers> selectTokenTransferListFromView(PageReq req);


	/** tokenTransfer 리스트 개수
	 * @param req
	 * @return
	 */
	Integer selectTotalCountTransfers(PageReq req);
	
	/** 토큰 tx count 합계 
	 * @return
	 */
	Integer selectTotalTxCount(String ircVersion);
	
	/** 특정 주소에 관한 tokenTxCount
	 * @return
	 */
	Integer selectAddrTokenTxCount(PageReq req);
	
	/** 토큰 holders
	 * @param req
	 * @return
	 */
	List<TokenHolder> selectTokenHolders(PageReq req);
	
	/**토큰 전체 holders 갯수 
	 * @return
	 */
	Integer selectTotalCountHoderList(PageReq req);
	
	/**
	 * 주소가 소유한 토큰들 조회
	 */
	List<TokenAddress> selectQuantityByAddress(PageReq req);
	
	
	Integer selectTokenAddrCount(PageReq req);


	List<TokenTxDetail> selectTokenTxByAddressTxData(PageReq req);

	/** 토큰 관련 TX 정보 입력 
	 * @param list
	 * @return
	 */
	int insertTokenTxArray(List<TTokenTx> list);
	int insertTxResultLogArray(List<TTxResultLogWithBLOBs> list);
	int updateTokenInfoHolderCountAndTxCountRange(@Param("height1")  Integer height1, @Param("height2")  Integer height2);

}

