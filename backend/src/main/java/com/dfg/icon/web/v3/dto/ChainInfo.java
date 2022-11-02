package com.dfg.icon.web.v3.dto;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

@Data
public class ChainInfo {
	
	String chainName;

    String host;

    String api;
    
    String channel;
    
    String version;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
