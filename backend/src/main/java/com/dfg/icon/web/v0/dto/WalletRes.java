package com.dfg.icon.web.v0.dto;

import java.util.List;

import com.dfg.icon.web.v3.dto.TxDetail;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.dfg.icon.web.v0.dto.block.Transaction;

import lombok.Data;

@Data
public class WalletRes {
	
	WalletInfoDto walletDetail;
	List<TxDetail> walletTx;
	
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
    
}
