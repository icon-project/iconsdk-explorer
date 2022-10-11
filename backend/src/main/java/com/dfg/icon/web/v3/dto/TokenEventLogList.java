package com.dfg.icon.web.v3.dto;

import java.util.ArrayList;
import java.util.List;

import com.dfg.icon.core.dao.icon.TTokenTx;
import com.dfg.icon.core.dao.icon.TTxResultLog;

import com.dfg.icon.core.dao.icon.TTxResultLogWithBLOBs;
import lombok.Data;

/** 토큰 이벤트 조회를 담은 list dto 
 * @author bsm
 *
 */
@Data
public class TokenEventLogList {

	List<TokenEventLog> tokenEventLog = new ArrayList<>();

	public void setTokenEventLogList(List<TTxResultLogWithBLOBs> txResultLog){
		for(TTxResultLogWithBLOBs txLog : txResultLog){
			TokenEventLog eventLog = new TokenEventLog();
			eventLog.setTokenEventLog(txLog);
			tokenEventLog.add(eventLog);
		}
	}
}
