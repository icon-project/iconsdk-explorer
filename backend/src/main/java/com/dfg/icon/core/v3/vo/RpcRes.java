package com.dfg.icon.core.v3.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.dfg.icon.core.v3.vo.RpcResult;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RpcRes {
    @JsonProperty("jsonrpc")
    String jsonRpc;

    @JsonProperty("result")
    RpcResult result;

    @JsonProperty("id")
    Integer id;

    @JsonProperty("error")
    RpcError error;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}