package com.dfg.icon.web.v3.dto;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 트랜잭션 상세 dto
 * @author LYJ
 */
@Data
public class TxDetailDto {
	String txHash;

	int height;

	Date createDate;

	String fromAddr;

	String toAddr;

	String txType;
	
	String dataType;

	String amount;

	String fee;

	int state;

	String errorMsg;
	
	String targetContractAddr;

	Integer id;

//	Integer confirmation;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
