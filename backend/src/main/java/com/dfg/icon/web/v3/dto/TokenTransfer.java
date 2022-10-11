package com.dfg.icon.web.v3.dto;

import java.util.Date;

import com.dfg.icon.core.dao.icon.TTokenTx;
import com.dfg.icon.util.HexUtil;

import lombok.Data;

/**
 * @author tokenTransfer dto
 *
 */
@Data
public class TokenTransfer {

	String txHash;
	String symbol;
	Date age;
	String fromAddr;
	String toAddr;
	String quantity;
	String fee;

	public void setTokenTransfer(TTokenTx tokenTx){
		txHash = tokenTx.getTxHash();
		age = tokenTx.getAge();
		fromAddr = tokenTx.getFromAddr();
		toAddr = tokenTx.getToAddr();
		quantity = HexUtil.decimalPlusDecimal(String.valueOf(tokenTx.getQuantity()), "0", 18);
		fee = tokenTx.getFee();
	}
}
