package com.dfg.icon.web.v3.dto;

import java.util.Date;

import com.dfg.icon.core.dao.icon.TTokenTx;
import com.dfg.icon.core.dao.icon.TTxResultLog;
import com.dfg.icon.core.dao.icon.TTxResultLogWithBLOBs;
import com.dfg.icon.util.HexUtil;

import lombok.Data;

/**
 * 토큰 이벤트 로그 조회를 위한 dto 
 * @author bsm
 *
 */
@Data
public class TokenEventLog {

	Integer txIndex;
	String txHash;
	String contractAddr;
	Integer height;
	Date age;
	String method;
	String eventLog;

	public void setTokenEventLog(TTxResultLogWithBLOBs txResultLog){
		txIndex = txResultLog.getTxIndex();
		txHash = txResultLog.getTxHash();
		contractAddr = txResultLog.getContractAddr();
		height = txResultLog.getHeight();
		age = txResultLog.getAge();
		method = txResultLog.getMethod();
		eventLog = txResultLog.getEventLog();
	}
}
