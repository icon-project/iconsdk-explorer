package com.dfg.icon.web.v3.dto;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class ReportReq {

    @NotBlank
    String reported;

    @NotBlank
    String reporter;

    String refUrl;

    Integer reportType;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}