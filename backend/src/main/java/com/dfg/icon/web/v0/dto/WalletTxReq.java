package com.dfg.icon.web.v0.dto;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class WalletTxReq {
    String address; 	//송금자, 수금자 지갑 주소
    String startDate;	//시작 날짜
    String endDate;		//끝 날짜
    Integer page;		//보려하는 페이지 수

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
