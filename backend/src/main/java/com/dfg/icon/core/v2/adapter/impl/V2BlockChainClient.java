package com.dfg.icon.core.v2.adapter.impl;

import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.exception.IconException;
import com.dfg.icon.core.v2.adapter.V2BlockChainAdapter;
import com.dfg.icon.core.v2.vo.GenesisRpcRes;
import com.dfg.icon.core.v2.vo.RpcReq;
import com.dfg.icon.core.v2.vo.RpcRes;
import com.dfg.icon.core.v3.service.V3ChainInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Service
public class V2BlockChainClient implements V2BlockChainAdapter {
    private static final Logger logger = LoggerFactory.getLogger(V2BlockChainClient.class);

    @Autowired
	private RestTemplate restTemplate;

	@Autowired
    private V3ChainInfoService chainInfoService;


	HttpHeaders defaultHeader = null;

	@PostConstruct
	public void init() {
		defaultHeader = new HttpHeaders();
		defaultHeader.setContentType(MediaType.APPLICATION_JSON);

	}


	public RpcRes getBalance(String url, String address) throws Exception {

		logger.info("--------------------");
		logger.info("[V2 BlockChain] getBalance. url = {}, address = {}", url, address);

		RpcReq request = new RpcReq();
		request.setMethodGetBalance(address);

		try{
			HttpEntity requestEntity = new HttpEntity(request.toString(), defaultHeader);
			RpcRes response = restTemplate.postForObject(url, requestEntity, RpcRes.class);
			return response;
		} catch (HttpClientErrorException re) {
			logger.error("BlockChain API rest-template call, responseBody = [{}]", re.getResponseBodyAsString());
			throw new IconException(IconCode.EXCHANGE_ACCESS_ERROR, re.getMessage());
		} catch (Exception re) {
			re.printStackTrace();
			logger.error("A error occurred during BlockChain API rest-template call, errorMsg[{}]", re.getMessage());
			throw new IconException(IconCode.EXCHANGE_ACCESS_ERROR, re.getMessage());
		}
	}
}
