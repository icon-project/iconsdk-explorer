package com.dfg.icon.core.v2.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

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
