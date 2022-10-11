package com.dfg.icon.core.v3.vo;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.dfg.icon.core.v2.vo.GenesisAccVo;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GenesisTransactionVo {
    @JsonProperty("accounts")
    List<GenesisAccVo> accounts;

    @JsonProperty("message")
    String message;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
