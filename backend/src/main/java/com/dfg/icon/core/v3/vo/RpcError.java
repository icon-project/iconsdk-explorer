package com.dfg.icon.core.v3.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by LYJ on 2018-06-27.
 */
public class RpcError {
    @JsonProperty("code")
    Integer code;

    @JsonProperty("message")
    String message;
}
