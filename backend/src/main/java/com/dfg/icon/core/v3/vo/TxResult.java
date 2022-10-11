package com.dfg.icon.core.v3.vo;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * icx_getTransactionResult의 result vo
 */
@Data
public class TxResult {
    /**
     * 0x0, 0x1
     */
    Integer status;

    /**
     * status가 0x0인 경우만
     */
    TxResultFailure failure;

    /**
     * 트랜잭션 해시
     */
    String txHash;

    /**
     * 블록 내의 트랜잭션 인덱스
     */
    Integer txIndex;

    /**
     * 트랜잭션이 포함된 플록의 높이
     */
    Integer blockHeight;

    /**
     * 트랜잭션이 포함된 블록의 해시
     */
    String blockHash;

    /**
     * 블록 내에서 해당 트랜잭션을 수행하기까지 소비된 step 누적량
     */
    String cumulativeStepUsed;

    /**
     * 해당 트랜잭션을 수행하는 데에 소비된 step양
     */
    String stepUsed;
    
    /**
     *  step 가격 
     */
    String stepPrice;

    /**
     *  fee 2.0의 상세
     */
    String stepUsedDetails;

    /**
     * 트랜잭션이 score 생성시 score 주소(cx로 시작)
     */
    String scoreAddress;

    /**
     * to address
     */
    String to;

    /**
     * tx version
     */
    String version;

    /**
     * eventLog
     */
    List<EventLog> eventLogList = new ArrayList<>();

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
