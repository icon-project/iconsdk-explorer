package com.dfg.icon.core.v3.vo;

import lombok.Data;

import java.util.Date;

/**
 * Created by LYJ on 2018-08-14.
 */
@Data
public class TokenTxView {
    String txHash;

    Integer txIndex;

    String contractAddr;

    String address;

    Integer blockHeight;

    Date age;
}
