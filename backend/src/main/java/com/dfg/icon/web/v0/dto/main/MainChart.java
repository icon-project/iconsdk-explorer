package com.dfg.icon.web.v0.dto.main;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.dfg.icon.core.dao.icon.TMainChart;

import lombok.Data;

@Data
public class MainChart {
    Date targetDate;

    Integer txCount;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    public void setMainChart(TMainChart tMainChart) {
    	this.targetDate = tMainChart.getTargetDate();
        this.txCount = tMainChart.getTxCount();
    }
}
