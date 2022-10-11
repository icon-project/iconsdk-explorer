package com.dfg.icon.core.v3.vo;

import lombok.Data;

@Data
public class SelectTxReq {
    Integer page;

    String contractAddr;

    Integer txType;
}
