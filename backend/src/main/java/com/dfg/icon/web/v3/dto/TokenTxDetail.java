package com.dfg.icon.web.v3.dto;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 토큰 트랜잭션 dto
 * @author LYJ
 */
@Data
public class TokenTxDetail {
    String txHash;
   
    String contractAddr;

    String contractName;

    String contractSymbol;

    String unit;

    Date createDate;

    String fromAddr;

    String toAddr;

    String quantity;

    String ircVersion;

    Integer state;
 
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
