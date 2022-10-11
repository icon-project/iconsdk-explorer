package com.dfg.icon.web.v3.dto;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/** 트랜잭션 상세에서 토큰송금 내역 관련 dto
 * @author LYJ
 *
 */
@Data
public class TxDetailTokenTx {

	String quantity;
	
	String symbol;
	
	String tokenName;
	
	String fromAddr;

	String toAddr;
	
	Integer state;
		
	String targetContractAddr;



	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
