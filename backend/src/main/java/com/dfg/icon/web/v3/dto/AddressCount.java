package com.dfg.icon.web.v3.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created by lyj01702 on 2019-04-08.
 */
@Data
public class AddressCount {
    private Integer balanceCount;
    private Integer totalCount;
    private Integer increaseBalanceCount;
    private Integer increaseTotalCount;
    private Date selectDate;
}
