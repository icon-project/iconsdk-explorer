package com.dfg.icon.core.mappers.icon;

import com.dfg.icon.core.dao.icon.TBtpHeader;
import com.dfg.icon.core.dao.icon.TBtpNetwork;
import com.dfg.icon.core.dao.icon.TTokenTx;
import com.dfg.icon.core.dao.icon.TTxResultLogWithBLOBs;
import com.dfg.icon.web.v0.dto.block.Address;
import com.dfg.icon.web.v3.dto.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BtpMapper {

	List<BtpNetwork> selectBtpNetworkList(@Param("page") int page, @Param("counting") int counting);

	Integer selectCountBtpNetworkList();

	List<BtpHeader> selectBtpHeaderList(@Param("page") int page, @Param("counting") int counting, @Param("networkId") String networkId);

	Integer selectCountBtpHeaderList(@Param("networkId") String networkId);

	int insertBtpNetworkArray(List<TBtpNetwork> list);

	int insertBtpHeaderArray(List<TBtpHeader> list);


}

