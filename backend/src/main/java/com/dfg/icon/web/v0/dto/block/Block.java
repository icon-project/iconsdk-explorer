package com.dfg.icon.web.v0.dto.block;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

@Data
public class Block {

    Integer height;
    
    String lastBlock;

    Date createDate;

    String peerId;

    Integer txCount;

    String hash;
    
    String prevHash;

    Integer blockSize;

    String amount;
    
    String fee;

    String message;

   

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
