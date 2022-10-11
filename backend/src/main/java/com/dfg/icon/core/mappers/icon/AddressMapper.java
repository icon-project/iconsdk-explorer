package com.dfg.icon.core.mappers.icon;

import java.util.List;

import com.dfg.icon.core.dao.icon.*;
import com.dfg.icon.core.v3.vo.IcxSupplyVo;
import org.apache.ibatis.annotations.Param;

import com.dfg.icon.web.v0.dto.WalletInfoDto;
import com.dfg.icon.web.v0.dto.block.Address;
import com.dfg.icon.web.v3.dto.AddressDto;
import com.dfg.icon.web.v3.dto.AddressInfo;
import com.dfg.icon.web.v3.dto.PageReq;

public interface AddressMapper {

	
	/**
	 * 전체 주소 정보 조회
	 */
	List<Address> selectAddrList(@Param("page") int page);
	
	List<AddressDto> selectAddrListV3(PageReq req);
	
	Integer selectCountAddrlist();

	/**
	 * 기관주소 잔액 합계
	 */
	IcxSupplyVo sumFCAIcx();
	
	/**
	 * 주소에 관한 모든 지갑 정보 
	 */
	WalletInfoDto selectWalletInfo(@Param("address") String address);
	AddressInfo selectAddressInfo(PageReq req);

	/**
	 * address 개수 제한 삭제
	 */
	Integer deleteAddressByBalanceLessThan(@Param("balance") String balance);
	
	/** 선택한 주소의 txcount
	 * @return
	 */
	Integer selectxCountAddrTx(@Param("address") String address);


	int upsertTxCountByTransactionRange(@Param("height1") Integer height1, @Param("height2") Integer height2);
	int upsertTokenTxCountByTokenTransactionRange(@Param("height1") Integer height1, @Param("height2") Integer height2);


	/**
	 * 업데이트 된 address_total로 address 갱신
	 */
	int updateAddressByTotal();
	int initUpdateAddressTotal();


	int upsertTokenAddressArray(List<TTokenAddress> list);
	int upsertAddressArray(List<TAddressTotal> list);
	String selectSumIcx();

	Integer selectBalanceAddressCount();

	int updateReportedCountAdd(@Param("address") String address);
	int updateReportCountAdd(@Param("address") String address);

	List<String> selectWhiteAddressList();
}

