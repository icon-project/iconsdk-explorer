package com.dfg.icon.web.v0.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;

@Data
public class TxInBlock {
	
	String txHash;
	
	String fromAddr;
	
	String toAddr;
    
    String amount;
    
    String fee;
    
    Integer state;
    
    String dataType;

    String txType;
    
    String targetContractAddr;
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
