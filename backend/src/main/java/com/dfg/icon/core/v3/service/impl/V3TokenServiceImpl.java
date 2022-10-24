package com.dfg.icon.core.v3.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.dfg.icon.core.common.service.ResourceService;
import com.dfg.icon.core.v3.adapter.V3BlockChainAdapter;
import com.dfg.icon.util.HexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfg.icon.core.dao.icon.TContract;
import com.dfg.icon.core.dao.icon.TContractKey;
import com.dfg.icon.core.dao.icon.TTokenTxViewExample;
import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.mappers.icon.ContractMapper;
import com.dfg.icon.core.mappers.icon.TContractMapper;
import com.dfg.icon.core.mappers.icon.TTokenAddressMapper;
import com.dfg.icon.core.mappers.icon.TTokenTxMapper;
import com.dfg.icon.core.mappers.icon.TTokenTxViewMapper;
import com.dfg.icon.core.mappers.icon.TTxResultLogMapper;
import com.dfg.icon.core.mappers.icon.TokenMapper;
import com.dfg.icon.core.v3.service.V3TokenService;
import com.dfg.icon.web.v3.dto.CommonListRes;
import com.dfg.icon.web.v3.dto.CommonRes;
import com.dfg.icon.web.v3.dto.PageReq;
import com.dfg.icon.web.v3.dto.TokenHolder;
import com.dfg.icon.web.v3.dto.TokenInfo;
import com.dfg.icon.web.v3.dto.TokenSummary;
import com.dfg.icon.web.v3.dto.TokenTransfers;

@Service
public class V3TokenServiceImpl implements V3TokenService {

	@Autowired
	TContractMapper tContractMapper;

	@Autowired
	TTokenTxMapper tTokenTxMapper;

	@Autowired
	TTokenTxViewMapper tTokenTxViewMapper;

	@Autowired
	TokenMapper tokenMapper;

	@Autowired
	TTokenAddressMapper tTokenAddressMapper;

	@Autowired
	TTxResultLogMapper tTxResultLogMapper;

	@Autowired
	ContractMapper contractMapper;

	@Autowired
	V3BlockChainAdapter blockChainAdapter;

	@Autowired
	ResourceService resourceService;

	/*
	 * 토큰 요약 정보 조회
	 * 
	 * @see
	 * com.dfg.icon.core.v3.service.V3TokenService#selectTokenSummary(java.lang.
	 * String)
	 */
	@Override
	public CommonRes selectTokenSummary(String url, String contractAddr) throws Exception {
		CommonRes res = new CommonRes();
		TContract key = new TContract();
		key.setContractAddr(contractAddr);
		TContract tokenInfo = tContractMapper.selectByPrimaryKey(key);

		if (tokenInfo == null) {
			res.setData("");
			res.setCode(IconCode.ERROR);
		} else {
			TokenSummary summary = new TokenSummary();
			summary.setTokenSummary(tokenInfo);

			// total Supply의 mint burn 반영하기 위해 엔진에 물어보는 것으로 변경
//			String totalSupply = tokenInfo.getTotalSupply();
			String totalSupply = blockChainAdapter.getIcxCall(url, contractAddr, "totalSupply");
			if(totalSupply == null) {
				totalSupply = tokenInfo.getTotalSupply();
			}
			totalSupply = HexUtil.applyDecimal(totalSupply, tokenInfo.getDecimals());
			summary.setTotalSupply(totalSupply);
			// ---------------------

			String symbol = tokenInfo.getSymbol().toLowerCase();
			String tradeUsdName = (new StringBuffer()).append(symbol).append("usd").toString();
			String tradeIcxName = (new StringBuffer()).append(symbol).append("icx").toString();

			List<String> tradeName = new ArrayList<>();
			tradeName.add(tradeUsdName);
			tradeName.add(tradeIcxName);

			/*
			 * summary.setHolderCount(holderCount); summary.setTxCount(txCount);
			 */

			res.setData(summary);
			res.setCode(IconCode.SUCCESS);
		}

		return res;
	}

	/*
	 * 모든 토큰 리스트 조회
	 * 
	 * @see
	 * com.dfg.icon.core.v3.service.V3TokenService#selectTokenList(com.dfg.icon.web.
	 * v3.dto.PageReq)
	 */
	@Override
	public CommonListRes selectTokenList(PageReq req) throws Exception {
		req.setPage((req.getPage() - 1) * req.getCounting());

		CommonListRes res = new CommonListRes();

		List<TokenInfo> tokenInfoList = tokenMapper.selectTokenInfoList(req);
		for (TokenInfo tokenInfo : tokenInfoList) {

			List<String> tokenCurrencyPairs = new ArrayList<>();
			for (QuoteCurrency quoteCurrency : QuoteCurrency.values()) {
				if(tokenInfo.getSymbol() != null) {
					tokenCurrencyPairs.add(tokenInfo.getSymbol().toLowerCase() + quoteCurrency.name().toLowerCase());
				}
			}
			
			// symbol이 null인 경우 skip
			if(tokenCurrencyPairs.size() == 0) continue;
		}

		res.setData(tokenInfoList);
		res.setListSize(tokenMapper.selectTokenInfoTotalCount(req));
		res.setTotalSize(res.getListSize());

		if (tokenInfoList == null || tokenInfoList.size() == 0) {
			res.setCode(IconCode.NO_DATA);
		} else {
			res.setCode(IconCode.SUCCESS);
		}

		return res;
	}

	private enum QuoteCurrency {
		USD, BTC, ETH, ICX
	};

	/*
	 * 토큰 transfers List 조회
	 * 
	 * @see
	 * com.dfg.icon.core.v3.service.V3TokenService#selectTokenTransfers(com.dfg.icon
	 * .web.v3.dto.PageReq)
	 */
	@Override
	public CommonListRes selectTokenTransfers(PageReq req) throws Exception {
		req.setPage((req.getPage() - 1) * req.getCounting());
		CommonListRes res = new CommonListRes();
		Integer totalTxCount = 0;
		boolean isView = false;

		if(req.getAddress() == null && req.getAddress2() == null) {
			// 모두 미지정. 전체 토큰tx 개수 조회
			totalTxCount = tokenMapper.selectTotalTxCount();
			if(totalTxCount == null) {
				totalTxCount = 0;
			}
		} else if(req.getAddress() != null && req.getAddress2() == null) {
			// 토큰만 지정
			TContract tokenInfo = getTokenInfoByAddress(req.getAddress());
			if (tokenInfo != null) {
				totalTxCount = tokenInfo.getTransferCount();
			} else {
				totalTxCount = 0;
			}
		} else if(req.getAddress() == null && req.getAddress2() != null) {
			// 주소만 지정
			TTokenTxViewExample txViewExample = new TTokenTxViewExample();
			txViewExample.createCriteria().andAddressEqualTo(req.getAddress2());
			totalTxCount = (int) tTokenTxViewMapper.countByExample(txViewExample);
			isView = true;
		} else {
			// 토큰 주소 모두 지정.
			TTokenTxViewExample txViewExample = new TTokenTxViewExample();
			txViewExample.createCriteria().andContractAddrEqualTo(req.getAddress())
					.andAddressEqualTo(req.getAddress2());
			totalTxCount = (int) tTokenTxViewMapper.countByExample(txViewExample);
			isView = true;
		}

		if (totalTxCount == null || totalTxCount == 0 || req.getPage() > totalTxCount.intValue()) {
			res.setListSize(0);
			res.setTotalSize(0);
			res.setCode(IconCode.NO_DATA);
		} else {
			if(isView) {
				// --------------------
				// 2019.08.21 맨 끝 페이지 조회 속도 개선
				if(!req.checkReverse(totalTxCount)) {
					res.setCode(IconCode.NO_DATA);
					return res;
				}
				// --------------------
				List<TokenTransfers> tokenTxList = tokenMapper.selectTokenTransferListFromView(req);
				res.setData(tokenTxList);
				res.setListSize(totalTxCount);
			} else {
				if(req.getAddress() == null && resourceService.getLimitTx() < totalTxCount) {
					// 아무 지정 없는 전체 tokenTx조회인 경우, txLimit개수만큼만 조회되도록 제한.
					if( req.getPage() >= resourceService.getLimitTx() ) {
						res.setCode(IconCode.NO_DATA);
						res.setTotalSize(totalTxCount);
						res.setListSize(resourceService.getLimitTx());
						return res;
					}
					// --------------------
					// 2019.08.21 맨 끝 페이지 조회 속도 개선
					if(!req.checkReverse(totalTxCount)) {
						res.setCode(IconCode.NO_DATA);
						return res;
					}
					// --------------------
					List<TokenTransfers> tokenTxList = tokenMapper.selectTokenTransferList(req);
					res.setData(tokenTxList);
					res.setListSize(resourceService.getLimitTx());
				} else {
					// --------------------
					// 2019.08.21 맨 끝 페이지 조회 속도 개선
					if(!req.checkReverse(totalTxCount)) {
						res.setCode(IconCode.NO_DATA);
						return res;
					}
					// --------------------
					List<TokenTransfers> tokenTxList = tokenMapper.selectTokenTransferList(req);
					res.setData(tokenTxList);
					res.setListSize(totalTxCount);
				}

			}
			res.setCode(IconCode.SUCCESS);
			res.setTotalSize(totalTxCount);

		}
		return res;
	}

	/*
	 * 토큰 Holders 전체 조회
	 * 
	 * @see
	 * com.dfg.icon.core.v3.service.V3TokenService#selectTokenHolders(com.dfg.icon.
	 * web.v3.dto.PageReq)
	 */
	@Override
	public CommonListRes selectTokenHolders(String url, PageReq req) throws Exception {

		req.setPage((req.getPage() - 1) * req.getCounting());
		CommonListRes res = new CommonListRes();

		// getToken TotalSupply
		TContract tokenInfo = getTokenInfoByAddress(req.getAddress());
		if (tokenInfo != null) {
//			req.setIcxSupply(tokenInfo.getTotalSupply());
			// LYJ 190514 : totalSupply를 노드에서 직접 부르는것으로 수정.
			String totalSupply = blockChainAdapter.getIcxCall(url, req.getAddress(), "totalSupply");
			totalSupply = HexUtil.applyDecimal(totalSupply, tokenInfo.getDecimals());
			req.setIcxSupply(totalSupply);
		}

		List<TokenHolder> addrInfo = tokenMapper.selectTokenHolders(req);
		res.setData(addrInfo);

		if (addrInfo == null) {
			res.setListSize(0);
			res.setTotalSize(0);
			res.setCode(IconCode.NO_DATA);
		} else {
			Integer listSize = tokenMapper.selectTotalCountHoderList(req);
			res.setListSize(listSize);
			res.setTotalSize(listSize);
			res.setData(addrInfo);
			res.setCode(IconCode.SUCCESS);
		}

		return res;
	}

	@Override
	public TContract getTokenInfoByAddress(String contractAddr) {
		TContractKey key = new TContractKey();
		key.setContractAddr(contractAddr);
		return tContractMapper.selectByPrimaryKey(key);
	}
}
