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
public class TokenTransfersList {

	List<TokenTransfers> tokenTransferList = new ArrayList<>();
	
	public void setTokenTransferList(List<TTokenTx> tokenTx){
		for(TTokenTx tx : tokenTx){
			TokenTransfers transfer = new TokenTransfers();
			transfer.setTokenTransfer(tx);
			tokenTransferList.add(transfer);
		}
	}
}
