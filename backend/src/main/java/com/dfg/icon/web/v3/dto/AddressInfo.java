package com.dfg.icon.web.v3.dto;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;

/**
 * 주소 페이지에서 조회하는 주소상세 정보 dto
 * @author LYJ
 */
@Data
public class AddressInfo {
	
	String address;

    String balance;
    
    Integer txCount;

    Integer tokenTxCount;
    
    String nodeType;

    /**
     * 해당 주소가 소유한 토큰 리스트
     */
    List<TokenAddress> tokenList;

    Integer internalTxCount;

    Integer reportedCount;

    Integer reportCount;

//    /**
//     * 해당 주소의 상위10개 트랜잭션
//     */
//    List<TxDetailDto> txList;
//    /**
//     * 해당 주소의 상위10개 토큰 트랜잭션
//     */
//    List<TokenTxDetail> tokenTxList;

	
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
