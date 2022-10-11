package com.dfg.icon.core.v3.vo;

import com.dfg.icon.util.HexUtil;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * Created by lyj01702 on 2019-05-16.
 */
@Data
public class TStake {

    String address;

    String stake;

    String unstake;

    Integer unstakeBlockHeight;

    public TStake(){}

    public TStake(String address, StakeResult result) {
        this.address = address;
        this.stake = result.getStake();
        this.unstake = result.getUnstake();
        this.unstakeBlockHeight = Integer.parseInt(HexUtil.toDecimalString(result.getUnstakeBlockHeight(), 0));
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
