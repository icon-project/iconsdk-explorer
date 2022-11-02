package com.dfg.icon.core.mappers.icon;

import com.dfg.icon.core.dao.icon.TChainInfo;
import com.dfg.icon.core.dao.icon.TContract;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChainInfoMapper {

	int chainInfoByName(@Param("chainName") String chainName);
	List<TChainInfo> selectAll(@Param("txHash") String txHash);
	
}

