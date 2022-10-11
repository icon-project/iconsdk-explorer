package com.dfg.icon.web.v3.dto;

import com.dfg.icon.core.dao.icon.TTokenAddress;

import lombok.Data;

/**
 * 토큰 holder를 조회하기 위한 dto
 * @author bsm
 *
 */
@Data
public class TokenHolder {

	String contractAddr;
	String symbol;
	String address;
	String quantity;
	Integer txCount;
	String percentage;
	Integer rank;
	
}
