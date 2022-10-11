package com.dfg.icon.web.v3.dto;

import java.util.ArrayList;
import java.util.List;

import com.dfg.icon.core.dao.icon.TTokenTx;

import lombok.Data;

/** tokenTransferList dto 
 * @author bsm
 *
 */
@Data
public class TokenTransferList {

	List<TokenTransfer> tokenTransferList = new ArrayList<>();
	String tokenSymbol;
	
	public void setTokenTransferList(List<TTokenTx> tokenTx){
		for(TTokenTx tx : tokenTx){
			TokenTransfer transfer = new TokenTransfer();
			transfer.setTokenTransfer(tx);
			tokenTransferList.add(transfer);
		}
	}
}
