package com.dfg.icon.web.v0.dto.block;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;

@Data
public class Address {
    String address;

    String balance;
    
    double percentage;
    
    Integer txCount;
    
    String nodeType;
    

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
