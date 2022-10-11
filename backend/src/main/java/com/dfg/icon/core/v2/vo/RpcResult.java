package com.dfg.icon.core.v2.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class RpcResult {
    @JsonProperty("response_code")
    Integer responseCode;

    /**
     * getBlockByHeight용
     */
    @JsonProperty("block")
    BlockVo block;

    /**
     * getBalance 용
     */
    @JsonProperty("response")
    String response;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
