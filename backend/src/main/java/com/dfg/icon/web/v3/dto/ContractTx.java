package com.dfg.icon.web.v3.dto;

import java.util.Date;

import com.dfg.icon.core.dao.icon.TTokenTx;

import lombok.Data;

/**
 * Contract Transaction 
 * @author hslee
 *
 */
@Data
public class ContractTx {

	String 	txHash;
	String 	name;
	String 	symbol;
	String 	fromAddr;
	String 	toAddr;
	String 	tradeTokenAddr;
	String 	quantity;
	Date 	age;
	int		state;
	
}
