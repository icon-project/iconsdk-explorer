package com.dfg.icon.web.v0.dto;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;

@Data
public class BlockDto {

	String hash;

	Integer height;

    Date createDate;

    Integer txCount;

    String prevHash;

    String amount;

    String fee;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
