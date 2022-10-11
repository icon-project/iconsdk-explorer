package com.dfg.icon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

import com.dfg.icon.core.dao.icon.TAddressTotal;
import com.dfg.icon.core.dao.icon.TAddressTotalExample;
import com.dfg.icon.core.mappers.icon.TAddressTotalMapper;
import com.dfg.icon.core.mappers.icon.TransactionV3Mapper;
import com.dfg.icon.core.v0.service.V0MainService;
import com.dfg.icon.core.v3.adapter.V3BlockChainAdapter;
import com.dfg.icon.core.v3.vo.DecimalType;
import com.dfg.icon.core.v3.vo.RpcBalanceRes;
import com.dfg.icon.core.v3.vo.TokenAddressGroupKey;
import com.dfg.icon.util.*;
import com.dfg.icon.web.v3.dto.PageReq;
import org.junit.Test;

import com.dfg.icon.core.exception.IconCode;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ApplicationTests {

	@Autowired
	V3BlockChainAdapter blockChainAdapter;

	@Autowired
	TAddressTotalMapper tAddressTotalMapper;

	@Autowired
	TransactionV3Mapper transactionV3Mapper;

	@Test
	public void contextLoads() throws Exception {
	}
}
