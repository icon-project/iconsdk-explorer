package com.dfg.icon.web.v3.dto;

import com.dfg.icon.core.dao.icon.TContract;

import lombok.Data;

/** 빠른 검색 결과 
 * @author hslee
 *
 */
@Data
public class SearchQuickInfo {

	String name;
	String address;
	String ircVersion;
	Long   txCount;
	
	public SearchQuickInfo(String[] data) {
		this.name 		= data[0];
		this.address 	= data[1];
		this.ircVersion = data[2];
	}
	

	public SearchQuickInfo(String name , String address  , String ircVersion , Long txCount ) {
		this.name 		= name;
		this.address 	= address;
		this.ircVersion = ircVersion;
		this.txCount 	= txCount;
	}
}
