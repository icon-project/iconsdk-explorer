package com.dfg.icon.core.v3.vo;

import lombok.Data;

import java.util.Date;

/**
 * Created by LYJ on 2018-08-14.
 */
@Data
public class TransactionView {
    String txHash;

    String address;

    Integer height;

    Date createDate;

    Integer state;

    Integer txType;
}
