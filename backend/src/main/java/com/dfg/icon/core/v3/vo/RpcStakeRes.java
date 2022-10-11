package com.dfg.icon.core.v3.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by lyj01702 on 2019-07-15.
 */
@Data
public class RpcStakeRes {
    @JsonProperty("jsonrpc")
    String jsonrpc;

    @JsonProperty("result")
    RpcStakeResult result;

    @JsonProperty("id")
    Integer id;

    @JsonProperty("error")
    RpcError error;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
