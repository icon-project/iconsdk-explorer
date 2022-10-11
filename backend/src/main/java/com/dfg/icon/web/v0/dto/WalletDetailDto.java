package com.dfg.icon.web.v0.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;

@Data
public class WalletDetailDto {

    String address;		//주소

    String balance;		//잔액	

    int txCount;	//트랜잭션 갯수

    String nodeType;	//노드타입

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
