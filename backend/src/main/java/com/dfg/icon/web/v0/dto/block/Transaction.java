package com.dfg.icon.web.v0.dto.block;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

@Data
public class Transaction {
    String txHash;
   
    int height;

    Date createDate;

    String fromAddr;

    String toAddr;

    String amount;
    
    String fee;
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
