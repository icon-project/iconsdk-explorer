package com.dfg.icon.web.v3.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;

/** 
 * 주소 전체 리스트 조회를 위한 정보 dto
 * @author bsm
 */
@Data
public class AddressDto {
    String address;

    String balance;

    
    double percentage;
    
    Integer txCount;
    
    String nodeType;
    

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
