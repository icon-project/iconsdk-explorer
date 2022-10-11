package com.dfg.icon.web.v3.dto;

import com.dfg.icon.core.dao.icon.TInternalTx;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 인터널 tx
 * @author LYJ
 */
@Data
public class InternalTx {
	String txHash;

	Integer txIndex;

	Integer height;

	String contractAddr;

	Date createDate;

	String fromAddr;

	String toAddr;

	String amount;

	Integer state;

	public InternalTx() {}

	public InternalTx(TInternalTx tx) {
		this.txHash = tx.getParentTxHash();
		this.txIndex = tx.getTxIndex();
		this.height = tx.getHeight();
		this.contractAddr = tx.getContractAddr();
		this.createDate = tx.getCreateDate();
		this.fromAddr = tx.getFromAddr();
		this.toAddr = tx.getToAddr();
		this.amount = tx.getAmount();
		this.state = 1;
	}

	public void setInternalTx(TInternalTx tx) {
		this.txHash = tx.getParentTxHash();
		this.txIndex = tx.getTxIndex();
		this.height = tx.getHeight();
		this.contractAddr = tx.getContractAddr();
		this.createDate = tx.getCreateDate();
		this.fromAddr = tx.getFromAddr();
		this.toAddr = tx.getToAddr();
		this.amount = tx.getAmount();
		this.state = 1;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
