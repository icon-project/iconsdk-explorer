package com.dfg.icon.core.v3.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class BtpNetworkVo {
    @JsonProperty("networkId")
    String networkId;

    @JsonProperty("networkTypeId")
    String networkTypeId;

    @JsonProperty("networkName")
    String networkName;

    @JsonProperty("networkTypeName")
    String networkTypeName;

    @JsonProperty("url")
    String url;

    @JsonProperty("startHeight")
    int startHeight;

    @JsonProperty("open")
    String open;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}

