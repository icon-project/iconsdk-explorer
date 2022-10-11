package com.dfg.icon.core.v2.adapter.impl;

import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.exception.IconException;
import com.dfg.icon.core.v2.adapter.V2BlockChainAdapter;
import com.dfg.icon.core.v2.vo.GenesisRpcRes;
import com.dfg.icon.core.v2.vo.RpcReq;
import com.dfg.icon.core.v2.vo.RpcRes;
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

    @Value("${block.host}")
    String BLOCK_CHAIN_HOST;

	@Value("${block.api.channel}")
	String BLOCK_CHAIN_API;

	String API_URL;

    @Autowired
	private RestTemplate restTemplate;

	HttpHeaders defaultHeader = null;

	@PostConstruct
	public void init() {
		if(BLOCK_CHAIN_HOST.contains("api/")) {
			API_URL = BLOCK_CHAIN_HOST;
		} else {
			API_URL = (new StringBuffer()).append(BLOCK_CHAIN_HOST).append("/").append(BLOCK_CHAIN_API).toString();
		}
		logger.info("V2BlockChain URL = {}", API_URL);

		defaultHeader = new HttpHeaders();
//		defaultHeader.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		defaultHeader.setContentType(MediaType.APPLICATION_JSON);

	}

	@Override
    public RpcRes getLastBlock() throws Exception {
		String url = API_URL;

		logger.info("--------------------");
		logger.info("[V2 BlockChain] getLastBlock. url = {}", url);

		try{
			RpcReq request = new RpcReq();
			request.setMethodLastBlock();
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

    /* (non-Javadoc)
     * height 에 해당하는 블록 정보를 루프체인에서 조회
     * @see com.dfg.icon.core.v2.adapter.V2BlockChainAdapter#getBlockInfoByHeight(java.lang.Integer)
     */
    public RpcRes getBlockInfoByHeight(Integer height) throws Exception {
    	String url = API_URL;
    		logger.info("--------------------");
        logger.info("[V2 BlockChain] getBlockByHeight. url = {}, height = {}", url, height);

		RpcReq request = new RpcReq();
		request.setMethodGetBlockByHeight(height);

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

	public GenesisRpcRes getBlockInfoByGenesis() throws Exception {
		String url = API_URL;
		logger.info("--------------------");
		logger.info("[V2 BlockChain] getGenesis. url = {}", url);

		RpcReq request = new RpcReq();
		request.setMethodGetGenesisBlock();

		try{
//			HttpEntity<RpcReq> requestEntity = new HttpEntity<>(request, defaultHeader);
			HttpEntity requestEntity = new HttpEntity(request.toString(), defaultHeader);
			GenesisRpcRes response = restTemplate.postForObject(url, requestEntity, GenesisRpcRes.class);
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

	public RpcRes getBalance(String address) throws Exception {
		String url = API_URL;
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
