package com.dfg.icon.core.v3.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class TxFactoryVo {
	
	@JsonProperty("version")
    String version;
	
    @JsonProperty("from")
    String from;

    @JsonProperty("to")
    String to;

    @JsonProperty("value")
    String value;

    @JsonProperty("timestamp")
    Long timestamp;

    @JsonProperty("nonce")
    String nonce;

    @JsonProperty("tx_hash")
    String txHash;

    @JsonProperty("signature")
    String signature;

    /*
    	@JsonProperty("method")
    	String method;
    */

    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
