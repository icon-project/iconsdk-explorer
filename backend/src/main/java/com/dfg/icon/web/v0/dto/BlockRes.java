package com.dfg.icon.web.v0.dto;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.dfg.icon.web.v0.dto.block.Block;

import lombok.Data;

@Data
public class BlockRes {
	
	Block blockDetail;
	List<TxInBlock> txInBlock;
	
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
