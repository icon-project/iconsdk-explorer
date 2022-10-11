package com.dfg.icon.core.v3.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;

/**
 * Created by chanwo on 2020-07-06.
 */
@Data
public class RpcUnstakeResult {
	String unstake;

	String unstakeBlockHeight;

	String remainingBlocks;

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
