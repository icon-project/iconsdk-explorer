package com.dfg.icon.web.v3.dto;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;

/** 최근 블록 정보를 조회 하기 위한 블록 dto 
 * @author bsm
 *
 */
@Data
public class RecentBlock {
	
	Integer height;

    Date createDate;

    Integer txCount;
    
    String hash;
    
    String amount;
    
    String fee;

    String peerId;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
