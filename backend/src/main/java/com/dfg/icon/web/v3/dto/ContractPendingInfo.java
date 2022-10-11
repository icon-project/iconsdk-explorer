package com.dfg.icon.web.v3.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created by LYJ on 2018-11-14.
 * 승인 리젝이 필요한 컨트랙트 정보 조회 DTO
 */
@Data
public class ContractPendingInfo {
    String contractAddr;

    Integer version;

    String contractName;

    String ircVersion;

    String createTx;

    Date createDate;
}
