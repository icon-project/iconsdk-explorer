package com.dfg.icon.web.v3.dto;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class BtpNetwork {
    String networkId;
    String networkTypeId;
    String networkName;
    String networkTypeName;
    int startHeight;
    String url;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
