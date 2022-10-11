package com.dfg.icon.web.v3.dto;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 트랜잭션 상세 dto
 * @author LYJ
 */
@Data
public class TxDetail {
	String txHash;

	String status;

	String errorCode;

	String errorMsg;

	String dataString;

	Boolean isSafe;

	Integer reportedCount;

	int height;

	Date createDate;

	String fromAddr;

	String toAddr;

	String txType;
	
	String dataType;

	String amount;

	String fee;

	int state;

	String stepLimit;

	String stepUsedByTxn;

	String stepPrice;

	String stepUsedDetails;

	Integer confirmation;
	
	String targetContractAddr;

	String contractVersion;
	
	List<TxDetailTokenTx> tokenTxList ;

	List<InternalTx> internalTxList;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
