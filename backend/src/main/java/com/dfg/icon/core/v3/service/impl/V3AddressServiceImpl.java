package com.dfg.icon.core.v3.service.impl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dfg.icon.core.dao.icon.*;
import com.dfg.icon.core.mappers.icon.*;
import com.dfg.icon.core.v2.vo.GenesisAccVo;
import com.dfg.icon.core.v3.adapter.V3BlockChainAdapter;
import com.dfg.icon.core.v3.vo.GenesisRpcRes;
import com.dfg.icon.util.DateUtil;
import com.dfg.icon.web.v3.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.v3.service.V3AddressService;
import com.dfg.icon.core.v3.vo.DecimalType;
import com.dfg.icon.util.HexUtil;

/**
 * @author bsm
 * 2018-01-22
 */
@Service
public class V3AddressServiceImpl implements V3AddressService{

	@Autowired
	AddressMapper addressMapper;

	@Autowired
	TokenMapper tokenMapper;

	@Autowired
	TTransactionViewMapper tTransactionViewMapper;

	@Autowired
	TransactionV3Mapper transactionV3Mapper;

	@Autowired
	TAddressTotalMapper tAddressTotalMapper;

	@Autowired
	TTokenTxMapper tTokenTxMapper;

	@Autowired
	TMainInfoMapper tMainInfoMapper;

	@Autowired
	MainMapper mainMapper;

	@Autowired
	V3BlockChainAdapter blockChainAdapter;

	/* 주소 리스트 페이징
	 * @see com.dfg.icon.core.v3.service.V3AddressService#getAddressList(com.dfg.icon.web.v3.dto.PageReq)
	 */
	@Override
	public CommonListRes getAddressList(PageReq req) throws Exception {

		CommonListRes res = new CommonListRes();
		//페이징
		int page = (req.getPage() - 1) * req.getCounting();

		req.setPage(page);

		// 총 주소 정보 조회 
		List<AddressDto> addr = addressMapper.selectAddrListV3(req);

		//전체 주소 갯수 조회 
		Integer totalAddr = addressMapper.selectCountAddrlist();

		res.setData(addr);
		res.setListSize(totalAddr);
		if(addr != null || addr.size() >0){
			res.setCode(IconCode.SUCCESS);
		} else {
			res.setCode(IconCode.NO_DATA);
		}
		return res;
	}

	/* 주소 상세 정보
	 * @see com.dfg.icon.core.v3.service.V3AddressService#selectAddressInfo(com.dfg.icon.web.v3.dto.PageReq)
	 */
	@Override
	public CommonRes selectAddressInfo(PageReq req) throws Exception {

		CommonRes res = new CommonRes();

		String selRate = mainMapper.selectRate();
		if(selRate == null) {
			selRate = "0";
		}

		AddressInfo result = addressMapper.selectAddressInfo(req);
		List<TokenAddress> tokenAddrList = tokenMapper.selectQuantityByAddress(req);
		Integer tokenTxCount = tokenMapper.selectTokenAddrCount(req);

		if(result == null){
			if(tokenAddrList==null || tokenAddrList.size() == 0){
				res.setCode(IconCode.NO_DATA);
				return res;
			}
			result = new AddressInfo();
			result.setAddress(req.getAddress());
			result.setBalance("0");
			result.setIcxUsd("0");
			result.setNodeType("-");
			result.setTxCount(0);
		}
		result.setTokenList(tokenAddrList);
		result.setTokenTxCount(tokenTxCount);

		// internal Tx count
		req.setAddress2(req.getAddress());
		req.setAddress(null);
		Integer internalTxCount = transactionV3Mapper.selectInternalTxCountByAddress(req);
		result.setInternalTxCount(internalTxCount);

		res.setCode(IconCode.SUCCESS);
		res.setData(result);

		return res;
	}

	/* 트랜잭션 리스트 조회 by address
	 * @see com.dfg.icon.core.v3.service.V3AddressService#getAddressTxList(com.dfg.icon.web.v3.dto.PageReq)
	 */
	public CommonListRes getAddressTxList(PageReq req) throws Exception {

	    //페이징
		req.setPage((req.getPage() - 1) * req.getCounting());
		CommonListRes cRes = new CommonListRes();
		int totalTxCount = getTxCount(req.getAddress(), req.getTxType()); //그주소에 해당하는 txList 갯수 가져오기

		if(req.getPage() > totalTxCount) {
			cRes.setCode(IconCode.NO_DATA);
		}else{
			// --------------------
			// 2019.08.21 맨 끝 페이지 조회 속도 개선
			if(!req.checkReverse(totalTxCount)) {
				cRes.setCode(IconCode.NO_DATA);
				return cRes;
			}
			// --------------------
			List<TxDetailDto> addressDataList = transactionV3Mapper.selectTotalTxListFromView(req);
			cRes.setData(addressDataList);
			cRes.setListSize(totalTxCount);
			cRes.setTotalSize(totalTxCount);
			cRes.setCode(IconCode.SUCCESS);
		}
        return cRes;
	}


	public CommonListRes getAddressTxListForWallet(PageReq req) throws Exception {
		//페이징
		req.setPage((req.getPage() - 1) * req.getCounting());
		CommonListRes cRes = new CommonListRes();
		int totalTxCount = getTxCount(req.getAddress(), req.getTxType()); //그주소에 해당하는 txList 갯수 가져오기

		// --------------------
		// 2019.08.21 맨 끝 페이지 조회 속도 개선
		if(!req.checkReverse(totalTxCount)) {
			cRes.setCode(IconCode.NO_DATA);
			return cRes;
		}
		// --------------------
		List<TxDetailDto> addressTxList = transactionV3Mapper.selectTotalTxListFromViewForWallet(req);
		cRes.setData(addressTxList);
		if (addressTxList.size() == 0) {
			cRes.setListSize(0);
			cRes.setCode(IconCode.NO_DATA);
		} else {
//			cRes.setListSize(transactionV3Mapper.selectTotalTxCountForWallet(req));
			cRes.setListSize(totalTxCount);
			cRes.setCode(IconCode.SUCCESS);
		}
		cRes.setTotalSize(cRes.getListSize());
		
		return cRes;
	}


	/* 선택한 주소에 관한 토큰 트랜잭션 리스트
	 * @see com.dfg.icon.core.v3.service.V3AddressService#selectAddressTokenTx(com.dfg.icon.web.v3.dto.PageReq)
	 */
	@Override
	public CommonListRes selectAddressTokenTx(PageReq req) throws Exception {

		CommonListRes cRes = new CommonListRes();
		req.setPage((req.getPage() - 1) * req.getCounting());

		Integer totalTxCount = tokenMapper.selectAddrTokenTxCount(req);
		if(req.getPage() > totalTxCount) {
			cRes.setCode(IconCode.NO_DATA);
		}else{
			List<TokenTxDetail> addressTokenTxList  = tokenMapper.selectTokenTxByAddressTxData(req);
			cRes.setData(addressTokenTxList);
			if (addressTokenTxList == null || addressTokenTxList.size() == 0) {
				cRes.setListSize(0);
				cRes.setCode(IconCode.NO_DATA);
			} else {
				cRes.setListSize(totalTxCount);
				cRes.setCode(IconCode.SUCCESS);
			}
		}


		return cRes;
	}


	/* 주소에 관한 전체 트랜잭션 갯수 
	 * @see com.dfg.icon.core.v3.service.V3AddressService#getTotalTxCount(com.dfg.icon.web.v3.dto.PageReq)
	 */
	@Override
	public Integer getTotalTxCount(PageReq req) throws Exception {
		return transactionV3Mapper.selectTotalTxCount(req);
	}

	/* 주소에 관한 전체 토큰 트랜잭션 갯수 
	 * @see com.dfg.icon.core.v3.service.V3AddressService#getTotalTokenTxCount(com.dfg.icon.web.v3.dto.PageReq)
	 */
	@Override
	public Integer getTotalTokenTxCount(PageReq req) throws Exception {
		TTokenTxExample example = new TTokenTxExample();
		example.or().andFromAddrEqualTo(req.getAddress());
		example.or().andToAddrEqualTo(req.getAddress());
		Long totalData = tTokenTxMapper.countByExample(example);
		return totalData.intValue();
	}

	// 실시간 주소 개수 수집용
	@Override
	public CommonRes getBalanceAddressCount() throws Exception {
		TAddressTotalExample example = new TAddressTotalExample();
		AddressCount addressCount = new AddressCount();
		addressCount.setTotalCount((int) tAddressTotalMapper.countByExample(example));
		addressCount.setBalanceCount(addressMapper.selectBalanceAddressCount());
		addressCount.setSelectDate(DateUtil.getNowDate());

		CommonRes res = new CommonRes();
		res.setData(addressCount);
		res.setCode(IconCode.SUCCESS);
		return res;
	}

	/* (non-Javadoc)
	 * @see com.dfg.icon.core.v3.service.V3AddressService#initGenesisTotal()
	 */
	@Override
	public TAddressTotal initGenesisTotal(String url) throws Exception {
		TAddressTotal genesis = new TAddressTotal();
		GenesisRpcRes rpcRes = blockChainAdapter.getBlockInfoByGenesis(url);
		for(GenesisAccVo vo : rpcRes.getResult().getConfrimedTransactionList().get(0).getAccounts()) {
			if(vo.getName().equals("god")) {
				genesis.setAddress(vo.getAddress());
				genesis.setBalance(HexUtil.toDecimalString(vo.getBalance(), DecimalType.ICX.getValue()));
				genesis.setNodeType("Genesis");
				genesis.setTxCount(1);
				break;
			}
		}
		return genesis;
	}

	/* (non-Javadoc)
	 * @see com.dfg.icon.core.v3.service.V3AddressService#initTreasuryTotal()
	 */
	@Override
	public TAddressTotal initTreasuryTotal(String url) throws Exception {
		TAddressTotal treasury = new TAddressTotal();
		GenesisRpcRes rpcRes = blockChainAdapter.getBlockInfoByGenesis(url);
		for(GenesisAccVo vo : rpcRes.getResult().getConfrimedTransactionList().get(0).getAccounts()) {
			if(vo.getName().equals("treasury") || vo.getName().equals("fee_treasury")) {
				treasury.setAddress(vo.getAddress());
				treasury.setBalance(HexUtil.toDecimalString(vo.getBalance(), DecimalType.ICX.getValue()));
				treasury.setNodeType("Treasury");
				treasury.setTxCount(0);
				treasury.setBalanceOrder(new BigDecimal(treasury.getBalance()));
				break;
			}
		}

		return treasury;
	}

	/** 특정 주소에 관한 txCount 조회
	 * @param address
	 * @return
	 */
	private Integer getTxCount(String address, Byte txType) {
		if(txType == null) {
			Integer txCount = addressMapper.selectxCountAddrTx(address);
			if(txCount == null){txCount = 0;}
			return txCount;
		} else {
			TTransactionViewExample example = new TTransactionViewExample();
			example.createCriteria().andTxTypeEqualTo(txType).andAddressEqualTo(address);
			return (int)tTransactionViewMapper.countByExample(example);
		}
	}

	@Override
	public Set<String> getWhiteAddressList() {
		List<String> list = addressMapper.selectWhiteAddressList();
		Set<String> res = new HashSet<>();
		res.addAll(list);
		return res;
	}
}
