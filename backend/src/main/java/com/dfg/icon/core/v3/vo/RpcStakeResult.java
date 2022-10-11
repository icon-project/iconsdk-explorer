package com.dfg.icon.core.v3.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Updated by chanwo on 2020-07-06. - Add UnstakeList
 */
@Data
public class RpcStakeResult {
    String stake;

    // 기존 API 호환되기 위해 기존 필드 유지
    String unstake;

    String unstakeBlockHeight;

    String remainingBlocks;

    // 변경된 API 필드
    List<RpcUnstakeResult> unstakes;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
