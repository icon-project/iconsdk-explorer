package com.dfg.icon.core.v3.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class RpcBalanceRes {
    @JsonProperty("jsonrpc")
    String jsonRpc;

    @JsonProperty("result")
    String result;

    @JsonProperty("id")
    Integer id;

    @JsonProperty("error")
    RpcError error;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}