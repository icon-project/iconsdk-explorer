package com.dfg.icon.web.v3.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.dfg.icon.core.exception.IconCode;

import lombok.Data;

/** 기본 리스트 응답 DTO
 * @author hslee
 *
 */
@Data
public class CommonListRes {
	Object data;
	int listSize;
	int totalSize;
	String result;
	String description;

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
		this.data 			= null;
	}
}
