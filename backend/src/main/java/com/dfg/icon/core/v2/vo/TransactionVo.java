package com.dfg.icon.core.v2.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class TransactionVo {
    // after v3
	@JsonProperty("version")
    String version;
	
	@JsonProperty("from")
    String from;

    @JsonProperty("to")
    String to;

    @JsonProperty("value")
    String value;
    
    // after v3  
    @JsonProperty("stepLimit")
    String stepLimit;

    //only v2 
    @JsonProperty("fee")
    String fee;

    @JsonProperty("timestamp")
    Long timestamp;

    @JsonProperty("nonce")
    String nonce;
    
    @JsonProperty("signature")
    String signature;
    
    @JsonProperty("tx_hash")
    String txHash;
    
    // after v3  
    @JsonProperty("dataType")
    String dataType;

    //only v2 
    @JsonProperty("method")
    String method;
    
    // after v3  
    @JsonProperty("data")
    String data;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
