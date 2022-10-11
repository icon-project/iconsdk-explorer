package com.dfg.icon.web.v0.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;

@Data
public class WalletInfoDto {

	String address;

    String balance;

//    double icxUsd;
    String icxUsd;

    Integer txCount;

    String nodeType;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
