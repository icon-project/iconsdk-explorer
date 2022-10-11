package com.dfg.icon.core.v3.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class RpcNoParamReq {
    @JsonProperty("jsonrpc")
    String jsonrpc;

    @JsonProperty("method")
    String method;

    @JsonProperty("id")
    Integer id;

    public RpcNoParamReq() {
        jsonrpc = "2.0";
        id = 1234;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}