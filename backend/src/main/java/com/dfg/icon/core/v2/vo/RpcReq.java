package com.dfg.icon.core.v2.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class RpcReq {
    @JsonProperty("jsonprc")
    String jsonrpc;

    @JsonProperty("method")
    String method;

    @JsonProperty("id")
    Integer id;

    @JsonProperty("params")
    ParamsVo params;

    public RpcReq() {
        jsonrpc = "2.0";
    }

    public void setMethodLastBlock() {
        method = "icx_getLastBlock";
        id = 1234;
        this.params = new ParamsVo();
    }

    public void setMethodGetBlockByHeight(Integer height) {
        method = "icx_getBlockByHeight";
        id = 1234;
        ParamsVo vo = new ParamsVo();
        vo.setHeight(height);
        this.params = vo;

    }

    public void setMethodGetGenesisBlock() {
        method = "icx_getBlockByHeight";
        ParamsVo vo = new ParamsVo();
        vo.setHeight(0);
        id = 1234;
        this.params = vo;
    }

    public void setMethodGetTransactionResult(String txHash) {
        method = "icx_getTransactionResult";
        ParamsVo vo = new ParamsVo();
        vo.setTxHash(txHash);
        id = 1234;
        this.params = vo;
    }

    public void setMethodGetBalance(String address) {
        method = "icx_getBalance";
        id = 1234;
        ParamsVo vo = new ParamsVo();
        vo.setAddress(address);
        this.params = vo;

    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}