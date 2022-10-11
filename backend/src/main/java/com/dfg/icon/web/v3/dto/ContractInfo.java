package com.dfg.icon.web.v3.dto;

import com.dfg.icon.core.dao.icon.TContract;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/** 컨트렉트 정보 dto 
 * @author hslee
 *
 */
@Data
public class ContractInfo {

	String balance;
	String address;
	String usdBalance;
	String txCount;
	String ircVersion;
	String creator;
	String createTx;
	String status;
	String symbol;
	String tokenName;
	String contractVersion;
	String newVersion;
	Integer reportedCount;
	Integer reportCount;

	List<ContractVersion> versionList;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
	
}
