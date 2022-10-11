package com.dfg.icon.web.v3.dto;

import java.util.Date;

import lombok.Data;

/** 컨트렉트 정보 dto 
 * @author hslee
 *
 */
@Data
public class TxEventLogList {

	String txHash;
	String contractAddr;
	Integer height;
	Integer txIndex;
	Date age;
	String method;
	String eventLog;
	
}
