package com.dfg.icon.web.v3.dto;

import com.dfg.icon.core.dao.icon.TContract;

import lombok.Data;

/**
 * 컨트랙트 리스트를 위한 정보 dto
 * @author bsm
 *
 */
@Data
public class ContractList {

	String address;
	String contractName;
	String compiler;
	String balance;
	String txCount;
	String verifiedDate;
	String ircVersion;	
	String status;
	
	
}
