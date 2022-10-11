package com.dfg.icon.core.v2.service.impl;

import com.dfg.icon.core.common.service.ScheduleService;
import com.dfg.icon.core.dao.icon.*;
import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.exception.IconException;
import com.dfg.icon.core.mappers.icon.*;
import com.dfg.icon.core.v0.service.V0MainService;
import com.dfg.icon.core.v2.adapter.V2BlockChainAdapter;
import com.dfg.icon.core.v2.service.V2BlockChainService;
import com.dfg.icon.core.v2.vo.*;
import com.dfg.icon.core.v3.vo.DecimalType;
import com.dfg.icon.util.HexUtil;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class V2BlockChainServiceImpl implements V2BlockChainService {

	@Autowired
	V2BlockChainAdapter blockChainAdapter;

	@Override
	public String getBalance(String address) throws Exception{
		RpcRes res = blockChainAdapter.getBalance(address);
		return HexUtil.toDecimalString(res.getResult().getResponse(), DecimalType.ICX.getValue());
	}
}