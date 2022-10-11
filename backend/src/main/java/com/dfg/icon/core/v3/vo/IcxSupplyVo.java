package com.dfg.icon.core.v3.vo;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by LYJ on 2018-08-01.
 *
 * address_total에서 nodeType이 -인 icx 잔액 합계를 구하는 용도의 vo
 */
@Data
public class IcxSupplyVo {
    String balance;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
