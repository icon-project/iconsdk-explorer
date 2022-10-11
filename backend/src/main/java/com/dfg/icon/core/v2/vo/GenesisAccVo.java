package com.dfg.icon.core.v2.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class GenesisAccVo {
    @JsonProperty("name")
    String name;

    @JsonProperty("address")
    String address;

    @JsonProperty("balance")
    String balance;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
