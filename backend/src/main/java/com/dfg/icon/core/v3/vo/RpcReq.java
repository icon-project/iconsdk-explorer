package com.dfg.icon.core.v3.vo;

import com.dfg.icon.core.exception.IconCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class RpcReq {
    @JsonProperty("jsonrpc")
    String jsonrpc;

    @JsonProperty("method")
    String method;

    @JsonProperty("id")
    Integer id;

    @JsonProperty("params")
    Object params;

    public RpcReq() {
        jsonrpc = "2.0";
    }

    public void setMethodGetBtpNetworkInfo(String networkId){
        method = "btp_getNetworkInfo";
        id = 1234;
        ParamsBtpIdVo vo = new ParamsBtpIdVo();
        vo.setId(networkId);
        this.params = vo;
    }

    public void setMethodGetBtpHeader(String networkId,Integer height){
        method = "btp_getHeader";
        id = 1234;
        ParamsBtpBlockVo vo = new ParamsBtpBlockVo();
        vo.setHeight("0x" + Integer.toHexString(height));
        vo.setNetworkID(networkId);
        this.params = vo;
    }


    public void setMethodGetBtpMessage(String networkId,Integer height){
        method = "btp_getMessages";
        id = 1234;
        ParamsBtpBlockVo vo = new ParamsBtpBlockVo();
        vo.setHeight("0x" + Integer.toHexString(height));
        vo.setNetworkID(networkId);
        this.params = vo;
    }

    public void setMethodLastBlock() {
        method = "icx_getLastBlock";
        id = 1234;
        this.params = new Object();
    }

    public void setMethodGetBlockByHeight(Integer height) {
        method = "icx_getBlockByHeight";
        id = 1234;
        ParamsHeightVo vo = new ParamsHeightVo();
        vo.setHeight("0x" + Integer.toHexString(height));
        this.params = vo;
    }

    public void setMethodGetGenesisBlock() {
        method = "icx_getBlockByHeight";
        ParamsHeightVo vo = new ParamsHeightVo();
        vo.setHeight("0x0");
        id = 1234;
        this.params = vo;
    }

    public void setMethodGetTransactionResult(String txHash) {
        method = "icx_getTransactionResult";
        ParamsTxHashVo vo = new ParamsTxHashVo();
        vo.setTxHash(txHash);
        id = 1234;
        this.params = vo;
    }

    public void setMethodGetBalance(String address) {
        method = "icx_getBalance";
        id = 1234;
        ParamsAddressVo vo = new ParamsAddressVo();
        vo.setAddress(address);
        this.params = vo;

    }
    
    public void setMethodScoreApi(String address) {
        method = "icx_getScoreApi";
        id = 1234;
        ParamsAddressVo vo = new ParamsAddressVo();
        vo.setAddress(address);
        this.params = vo;
    }

    public void setMethodIcxCall(String contractAddr, String inputMethod ,String... tokenAddr ) {
        method = "icx_call";
        id = 1234;
        
        ParamIcxCallVo vo = new ParamIcxCallVo();
        vo.setTo(contractAddr);
        vo.setDataType("call");
        
        ParamIcxCallDataVo dataVo = new ParamIcxCallDataVo(); 
        dataVo.setMethod(inputMethod);
        if("balanceOf".equals(inputMethod)) {
            // score external
            // 토큰 잔액조회시 params address가 필요함.
            ParamsOwnerVo paramsOwnerVo = new ParamsOwnerVo();
            paramsOwnerVo.set_owner(tokenAddr[0]);
            dataVo.setParams(paramsOwnerVo);
        } else {
            ParamEmptyVo  emptyVo = new ParamEmptyVo();
            dataVo.setParams( emptyVo);
        }
        vo.setData(dataVo);
        this.params = vo;
    }

    public void setMethodScoreStatus(String address) {
        method = "icx_call";
        id = 1234;

        ParamIcxCallVo vo = new ParamIcxCallVo();
        vo.setTo(IconCode.SCORE_INSTALL_ADDR.getCode());
        vo.setDataType("call");

        ParamIcxCallDataVo dataVo = new ParamIcxCallDataVo();
        dataVo.setMethod("getScoreStatus");
        ParamsAddressVo addressVo = new ParamsAddressVo();
        addressVo.setAddress(address);
        dataVo.setParams(addressVo);

        vo.setData(dataVo);
        this.params = vo;
    }

    public void setMethodGetPRepInfo(String address) {
        method = "icx_call";
        id = 1234;

        ParamIcxCallVo vo = new ParamIcxCallVo();
        vo.setTo(IconCode.SCORE_INSTALL_ADDR.getCode());
        vo.setDataType("call");

        ParamIcxCallDataVo dataVo = new ParamIcxCallDataVo();
        dataVo.setMethod("getPRep");
        ParamsAddressVo addressVo = new ParamsAddressVo();
        addressVo.setAddress(address);
        dataVo.setParams(addressVo);

        vo.setData(dataVo);
        this.params = vo;
    }

    public void setMethodGetStake(String address) {
        method = "icx_call";
        id = 1234;

        ParamIcxCallVo vo = new ParamIcxCallVo();
        vo.setTo(IconCode.SCORE_INSTALL_ADDR.getCode());
        vo.setDataType("call");

        ParamIcxCallDataVo dataVo = new ParamIcxCallDataVo();
        dataVo.setMethod("getStake");
        ParamsAddressVo paramVo = new ParamsAddressVo();
        paramVo.setAddress(address);
        dataVo.setParams(paramVo);
        vo.setData(dataVo);
        this.params = vo;
    }

    public void setMethodGetPRep(String dataMethod, String start, String end) {
        method = "icx_call";
        id = 1234;

        ParamIcxCallVo vo = new ParamIcxCallVo();
        vo.setTo(IconCode.SCORE_INSTALL_ADDR.getCode());
        vo.setDataType("call");

        ParamIcxCallMethodVo dataVo = new ParamIcxCallMethodVo();
        dataVo.setMethod(dataMethod);
        vo.setData(dataVo);

        this.params = vo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
//TODO refactoring
//        ObjectMapper objectMapper = new ObjectMapper();
//        String str = null;
//        try {
//            str = objectMapper.writeValueAsString(this);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return str;
    }
}