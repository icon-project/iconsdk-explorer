package com.dfg.icon.web.v0.dto.main;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.dfg.icon.core.dao.icon.TMainChart;
import com.dfg.icon.core.dao.icon.TMainTx;
import com.dfg.icon.core.exception.IconCode;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MainRes {
	@ApiModelProperty(value="mainInfo")
	@JsonProperty()
	
	String result;
    String description;
	
    MainInfo tmainInfo;
	List<TMainTx> tmainTx;
	List<MainBlock> tmainBlock;

    
	
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
    
    public void setCode(IconCode code){
    	this.result 		= code.getCode();
    	this.description 	= code.getMessage();
    }
    
    public void setError(){
    	this.result 		= IconCode.ERROR.getCode();
    	this.description 	= IconCode.ERROR.getMessage();
    	this.tmainInfo 		= null;
    	this.tmainTx 		= null;
    	this.tmainBlock		= null;
    }
    
}
