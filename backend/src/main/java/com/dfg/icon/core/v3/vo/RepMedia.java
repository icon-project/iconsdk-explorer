package com.dfg.icon.core.v3.vo;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by lyj01702 on 2019-07-30.
 */
@Data
public class RepMedia {
    String steemit;

    String twitter;

    String youtube;

    String facebook;

    String github;

    String reddit;

    String keybase;

    String telegram;

    String wechat;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
