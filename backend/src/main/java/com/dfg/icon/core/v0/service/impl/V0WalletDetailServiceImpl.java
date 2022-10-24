package com.dfg.icon.core.v0.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.dfg.icon.core.dao.icon.TAddressExample;
import com.dfg.icon.core.mappers.icon.*;
import com.dfg.icon.web.v3.dto.TxDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfg.icon.core.dao.icon.TMainInfo;
import com.dfg.icon.core.dao.icon.TMainInfoExample;
import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.v0.service.V0WalletDetailService;
import com.dfg.icon.web.v0.dto.CommonRes;
import com.dfg.icon.web.v3.dto.PageReq;
import com.dfg.icon.web.v0.dto.WalletInfoDto;
import com.dfg.icon.web.v0.dto.WalletTxReq;
import com.dfg.icon.web.v0.dto.block.Address;
import com.dfg.icon.web.v0.dto.block.Transaction;

/**
 * @author bsm
 * 2018-01-22
 */
@Service
public class V0WalletDetailServiceImpl implements V0WalletDetailService{

	@Autowired
	AddressMapper addressMapper;
	
	@Autowired
	TAddressMapper taddressMapper;

	@Autowired
	TransactionMapper transactionMapper;
	
	@Autowired
	TMainInfoMapper tMainInfoMapper;
	
	@Autowired
	MainMapper mainMapper;
	
	/*
	 * 모든 주소 리스트 
	 */
	@Override
	public CommonRes selectAddrList(PageReq req) throws Exception {

		CommonRes res = new CommonRes();
		//페이징 
		int page = (req.getPage()-1) * 20;

		// 총 주소 정보 조회 
		List<Address> addr = addressMapper.selectAddrList(page);
		//전체 주소 갯수 조회
		String totalAddr = String.valueOf(taddressMapper.countByExample(new TAddressExample()));

		//총 보유 잔액 
		TMainInfoExample mainInfo = new TMainInfoExample();
		List<TMainInfo> info = tMainInfoMapper.selectByExample(mainInfo);

		if(!info.isEmpty() && !addr.isEmpty() ) {
			res.setData(addr);
			res.setTotalData(totalAddr);
			res.setCode(IconCode.SUCCESS);
		}else{
			res.setTotalData(totalAddr);
			res.setCode(IconCode.NO_DATA);
		}
		return res;
	}
	
	/*
	 * 선택한 주소에 관한 wallet 정보 
	 */
	@Override
	public WalletInfoDto selectWalletDetail(WalletTxReq req) throws Exception {

		//주소로 주소 상세 내역 조회 하기
		WalletInfoDto result = addressMapper.selectWalletInfo(req.getAddress());

		if(result == null){
			return null;
		} else {
			//TODO Block explorer (confirmation of necessity)
			result.setBalance(result.getBalance());
		}

		return result;
	}

	/*
	 * 선택한 주소에 관한 트랜잭션 리스트 
	 */
	@Override
	public List<TxDetail> selectWalletTx(WalletTxReq req) throws Exception {
		
		//송금자 수금자 주소 
		String fromAddr 	= req.getAddress();
		String toAddr 		= req.getAddress();
		String startDate	= req.getStartDate() +  " 00:00:00";  
		String endDate		= req.getEndDate() + " 23:59:59";

		//보려하는 페이지
		PageReq pageReq = new PageReq(10);
		pageReq.setPage((req.getPage()-1)*pageReq.getCounting());
		
		//날짜 입력할 경우
		List<TxDetail> result = transactionMapper.selectTotalTxList(pageReq);
		if(result==null){
			return null;
		}
		
		return result;
	}

	

}
