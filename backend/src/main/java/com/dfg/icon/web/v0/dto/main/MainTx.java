package com.dfg.icon.web.v0.dto.main;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.dfg.icon.core.dao.icon.TMainTx;

import lombok.Data;

@Data
public class MainTx {

    String txHash;

    String amount;

    String fee;

    String scoreYn;

    Date createDate;
    
    Integer state;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    public void setMainTx(TMainTx tMainTx) {
        this.txHash = tMainTx.getTxHash();
        this.amount = tMainTx.getAmount();
        this.fee = tMainTx.getFee();
        this.scoreYn = tMainTx.getScoreYn();
        this.state = tMainTx.getState()==true?1:0;
       // this.createDate = tMainTx.getCreateDate();
    }
}
