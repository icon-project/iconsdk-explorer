package com.dfg.icon.web.v3.dto;

import java.util.Date;

import com.dfg.icon.core.dao.icon.TTokenTx;
import com.dfg.icon.util.HexUtil;

import lombok.Data;

/**
 * tokenTransfers dto 
 * @author bsm
 *
 */
@Data
public class TokenTransfers {

	String contractAddr;
	String symbol;
	String txHash;
	Date age;
	String fromAddr;
	String toAddr;
	String quantity;
	String fee;
	String txType;
	Integer state;
	String tokenName;
	String targetContractAddr;
	String tokenId;

	public void setTokenTransfer(TTokenTx tokenTx){
		contractAddr = tokenTx.getContractAddr();
		txHash = tokenTx.getTxHash();
		age = tokenTx.getAge();
		fromAddr = tokenTx.getFromAddr();
		toAddr = tokenTx.getToAddr();
		quantity = tokenTx.getQuantity();
		fee = tokenTx.getFee();
		tokenId = tokenTx.getTokenId();
		state = 1;
	}
}
