package com.dfg.icon.web.v3.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;

/**
 * 토큰 정보를 조회 하기 위한 dto
 * @author bsm
 *
 */
@Data
public class TokenInfo {

	String contractAddr;
	String name;
	String symbol;
	String holderAddr;
	Integer holderCount;
	Integer transferCount;
	String totalSupply;
	Integer decimals;
	String linkUrl;
	float changeVal;
	BigDecimal volume;
	BigDecimal marketCap;
	

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
