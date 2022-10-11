package com.dfg.icon.web.v0.dto.main;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.dfg.icon.core.dao.icon.TMainInfo;

import lombok.Data;

@Data
public class MainInfo {
    
    Double marketCap;
    
    String icxSupply;

    String icxCirculationy;

    Integer transactionCount;

    String publicTreasury;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
