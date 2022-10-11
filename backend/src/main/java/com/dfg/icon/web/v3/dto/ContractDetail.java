package com.dfg.icon.web.v3.dto;

import lombok.Data;

/** 컨트랙트 상세 조회를 위한 정보 dto 
 * @author bsm
 */
@Data
public class ContractDetail {

	Integer state;
	String txHash;
	String verifiedTx;
	String verifier;
	String submitter;
	String checksum;
	String comment;
}
