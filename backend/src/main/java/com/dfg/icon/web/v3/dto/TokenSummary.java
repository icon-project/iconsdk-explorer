package com.dfg.icon.web.v3.dto;

import com.dfg.icon.core.dao.icon.TContract;

import lombok.Data;

/**
 * @author 토큰 요약 정보를 조회하기 위한 dto
 *
 */
@Data
public class TokenSummary {

	Double totalSupplyUsd;
	Double priceUsd;
	Double price;
	String symbol;
	String tokenName;
	String totalSupply;
	String contract;
	Integer decimals;
	String holderAddr;
	Integer transfers;
	Integer holders;

	public void setTokenSummary(TContract tokenInfo){
		symbol = tokenInfo.getSymbol();
		tokenName = tokenInfo.getName();
		totalSupply = tokenInfo.getTotalSupply();
		contract = tokenInfo.getContractAddr();
		decimals = tokenInfo.getDecimals();
		holderAddr = tokenInfo.getHolderAddr();
		transfers = tokenInfo.getTransferCount();
		holders = tokenInfo.getHolderCount();
	}
}
