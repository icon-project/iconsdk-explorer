package com.dfg.icon.core.v3.adapter.impl;

import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.exception.IconException;
import com.dfg.icon.core.v3.adapter.V3BlockChainAdapter;
import com.dfg.icon.core.v3.service.V3ChainInfoService;
import com.dfg.icon.core.v3.vo.*;
import com.dfg.icon.util.HexUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Service
public class V3BlockChainClient implements V3BlockChainAdapter {
	private static final Logger logger = LoggerFactory.getLogger(V3BlockChainClient.class);
	private static final Logger sLogger = LoggerFactory.getLogger("ScheduleLogger");

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

	/* (non-Javadoc)
	 * height 에 해당하는 블록 정보를 루프체인에서 조회  ( height 가 null 이면  LastBlock )
	 * @see com.dfg.icon.core.v2.adapter.V2BlockChainAdapter#getBlockInfoByHeight(java.lang.Integer)
	 */
	@Override
	public BlockFactory getBlockFactoryByHeight(String url,Integer height) throws Exception {
		logger.info("--------------------");
		logger.info("[V3 BlockChain] getBlockByHeight. url = {}, height = {}", url, height);
		long startTime = System.currentTimeMillis();
		try{
			RpcReq request = new RpcReq();
			request.setMethodGetBlockByHeight(height);
			HttpEntity requestEntity = new HttpEntity(request.toString(), defaultHeader);

			String jsonStr = restTemplate.postForObject(url, requestEntity, String.class);
			long jsonTime = System.currentTimeMillis() - startTime;
			long middleTime = System.currentTimeMillis();
			JsonObject resultObject = getRootObject(jsonStr).get("result").getAsJsonObject();

			BlockFactory blockFactory = null;
			blockFactory = new BlockV3Factory();
			blockFactory.init(resultObject, jsonStr.length());
			sLogger.info("[Speed] getBlockByHeight bl : {}, txCnt : {}, ntk : {}s, init : {}s, total : {}s",
					height, blockFactory.getBlockInfo().getTxCount(),
					jsonTime / 1000.0f,
					(System.currentTimeMillis() - middleTime) / 1000.0f,
					(System.currentTimeMillis() - startTime) / 1000.0f);
			return blockFactory;
		} catch (HttpClientErrorException re) {
			sLogger.info("getBlockByHeight http fail = {}", height);
			logger.error("HttpClientError getBlockByHeight by {}. errMsg = [{}]", height, re.getResponseBodyAsString(), re);
			throw new IconException(IconCode.TRANSACTION_ERROR, re.getMessage());
		} catch (Exception re) {
			sLogger.info("getBlockByHeight http fail = {}", height);
			logger.error("Exception getBlockByHeight by {}. errMsg = [{}]", height, re.getMessage(),re);
			throw new IconException(IconCode.TRANSACTION_ERROR, re.getMessage());
		}
	}

	@Override
	public Integer getHeightByBlock(String url,Integer height) throws Exception {
		logger.info("--------------------");

		try{
			HttpEntity requestEntity = null;

			if(height == null ) {
				logger.info("[V3 BlockChain] getHeightByBlock. url = {}, height = lastBlock", url);
				RpcNoParamReq request = new RpcNoParamReq();
				request.setMethod("icx_getLastBlock");
				requestEntity = new HttpEntity(request.toString(), defaultHeader);
			}else {
				logger.info("[V3 BlockChain] getHeightByBlock. url = {}, height = {}", url, height);
				RpcReq request = new RpcReq();
				request.setMethodGetBlockByHeight(height);
				requestEntity = new HttpEntity(request.toString(), defaultHeader);
			}

			String jsonStr = restTemplate.postForObject(url, requestEntity, String.class);

			// block 체크
			JsonObject resultObject = getRootObject(jsonStr).get("result").getAsJsonObject();

			String strHeight = resultObject.get("height").getAsString();
			Integer blockHeight = HexUtil.changeStringToInteger(strHeight);
			logger.info("[V3 BlockChain] getHeightByBlock V3 height = {}", blockHeight);
			return blockHeight;
		} catch (HttpClientErrorException re) {
			logger.error("HttpClientError getHeightByBlock. errMsg = [{}]", re.getResponseBodyAsString());
			throw new IconException(IconCode.TRANSACTION_ERROR, re.getMessage());
		} catch (Exception re) {
			re.printStackTrace();
			logger.error("Exception getHeightByBlock. errMsg = [{}]", re.getMessage());
			throw new IconException(IconCode.TRANSACTION_ERROR, re.getMessage());
		}
	}

	public TxResultFactory getTransactionResult(String url,String txHash) throws Exception {
		RpcReq request = new RpcReq();
		request.setMethodGetTransactionResult(txHash);
		long startTime = System.currentTimeMillis();

		try{
			HttpEntity requestEntity = new HttpEntity(request.toString(), defaultHeader);

			// getJsonString
			String jsonStr = restTemplate.postForObject(url, requestEntity, String.class);

			JsonObject rootObject =  getRootObject(jsonStr);
			if(rootObject.get("result") != null) {
				JsonObject resultObject = rootObject.get("result").getAsJsonObject();
				TxResultFactory txResultFactory = null;

				txResultFactory = new TxResultV3Factory();
				txResultFactory.setJsonTime(System.currentTimeMillis()-startTime);
				txResultFactory.init(resultObject);
				return txResultFactory;
			} else {
				JsonObject errorObject = rootObject.get("error").getAsJsonObject();
				TxResultV2Factory txResultFactory = new TxResultV2Factory();
				txResultFactory.init(errorObject);
				return txResultFactory;
			}

		} catch (Exception re) {
			sLogger.error("getTransactionResult fail = {}, retry", txHash, re);
			try {
				HttpEntity requestEntity = new HttpEntity(request.toString(), defaultHeader);
				String jsonStr = restTemplate.postForObject(url, requestEntity, String.class);

				JsonObject rootObject =  getRootObject(jsonStr);
				if(rootObject.get("result") != null) {
					JsonObject resultObject = rootObject.get("result").getAsJsonObject();
					TxResultFactory txResultFactory = null;

					if(resultObject.get("code") != null) {
						txResultFactory = new TxResultV2Factory();
					}else {
						txResultFactory = new TxResultV3Factory();
					}
					txResultFactory.setJsonTime(System.currentTimeMillis()-startTime);
					txResultFactory.init(resultObject);
					return txResultFactory;
				} else {
					JsonObject errorObject = rootObject.get("error").getAsJsonObject();
					TxResultV2Factory txResultFactory = new TxResultV2Factory();
					txResultFactory.init(errorObject);
					return txResultFactory;
				}
			} catch (Exception re2) {
				sLogger.error("reGetTransactionResult fail = {}", txHash, re2);
				throw new IconException(IconCode.TRANSACTION_ERROR, re.getMessage());
			}
		}
	}

	public GenesisRpcRes getBlockInfoByGenesis(String url) throws Exception {
		logger.info("--------------------");
		logger.info("[V3 BlockChain] getGenesis. url = {}", url);

		RpcReq request = new RpcReq();
		request.setMethodGetGenesisBlock();
		HttpEntity requestEntity = new HttpEntity(request.toString(), defaultHeader);
		try{
			String jsonStr = restTemplate.postForObject(url, requestEntity, String.class);

			// block 체크
			JsonObject resultObject = getRootObject(jsonStr).get("result").getAsJsonObject();

			logger.info("[V3 BlockChain] getGenesisV3");
			GenesisRpcRes response = restTemplate.postForObject(url, requestEntity, GenesisRpcRes.class);
			return response;
		} catch (HttpClientErrorException re) {
			logger.error("HttpClientError getBlockInfoByGenesis. errMsg = [{}]", re.getResponseBodyAsString());
			throw new IconException(IconCode.BLOCK_ERROR, re.getMessage());
		} catch (Exception re) {
			re.printStackTrace();
			logger.error("Exception getBlockInfoByGenesis. errMsg = [{}]", re.getMessage());
			throw new IconException(IconCode.BLOCK_ERROR, re.getMessage());
		}
	}

	@Override
	public RpcBalanceRes getBalance(String url,String address) throws Exception {

		RpcReq request = new RpcReq();
		request.setMethodGetBalance(address);

		try{
			HttpEntity requestEntity = new HttpEntity(request.toString(), defaultHeader);
			RpcBalanceRes response = restTemplate.postForObject(url, requestEntity, RpcBalanceRes.class);
			return response;
		} catch (Exception re) {
			if((re instanceof HttpClientErrorException) && ((HttpClientErrorException)re).getStatusCode() == HttpStatus.BAD_REQUEST) {
				HttpClientErrorException hcee = (HttpClientErrorException) re;
				sLogger.error("HttpClientError getBalance. errMsg = [{}]", hcee.getResponseBodyAsString());
				sLogger.error("Might be malformed Error address = {}, set balance = 0", address);
				RpcBalanceRes res = new RpcBalanceRes();
				res.setResult("0");
				return res;
			} else {
				sLogger.error("Exception getBalance addr = {}, retry", address);
				try {
					HttpEntity requestEntity = new HttpEntity(request.toString(), defaultHeader);
					RpcBalanceRes response = restTemplate.postForObject(url, requestEntity, RpcBalanceRes.class);
					return response;
				} catch(Exception re2) {
					sLogger.error("ReException getBalance addr = {}", address);
					throw new IconException(IconCode.BALANCE_ERROR, re.getMessage());
				}
			}
		}
	}

	@Override
	public RpcBalanceRes getTotalSupply(String url) throws Exception {

		RpcNoParamReq request = new RpcNoParamReq();
		request.setMethod("icx_getTotalSupply");

		try{
			HttpEntity requestEntity = new HttpEntity(request.toString(), defaultHeader);
			RpcBalanceRes response = restTemplate.postForObject(url, requestEntity, RpcBalanceRes.class);
			return response;
		} catch (Exception re) {
			sLogger.error("Exception getTotalSupply retry");
			try {
				HttpEntity requestEntity = new HttpEntity(request.toString(), defaultHeader);
				RpcBalanceRes response = restTemplate.postForObject(url, requestEntity, RpcBalanceRes.class);
				return response;
			} catch (Exception re2) {
				sLogger.error("ReException getTotalSupply");
				return null;
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.dfg.icon.core.v3.adapter.V3BlockChainAdapter#getIcxScoreApi(java.lang.String)
	 */
	public JsonArray getIcxScoreApi(String url, String address) throws Exception{

		try {
			RpcReq request = new RpcReq();
			request.setMethodScoreApi(address);
			HttpEntity requestEntity = new HttpEntity(request.toString(), defaultHeader);
			String jsonStr = restTemplate.postForObject(url, requestEntity, String.class);

			JsonObject rootObject = getRootObject(jsonStr);
			if (rootObject.get("result") == null) {
				logger.error("result is null");
				throw new Exception();
			}
			return rootObject.get("result").getAsJsonArray();
		} catch (HttpClientErrorException re) {
			logger.error("HttpClientError getIcxScoreApi. errMsg = [{}]", re.getResponseBodyAsString());
			throw new IconException(IconCode.TRANSACTION_ERROR, re.getMessage());
		} catch (Exception re) {
			re.printStackTrace();
			logger.error("Exception getIcxScoreApi. errMsg = [{}]", re.getMessage());
			throw new IconException(IconCode.TRANSACTION_ERROR, re.getMessage());
		}
	}

	@Override
	public String getIcxCall(String url, String toAddress, String method, String... tokenAddress) {
		RpcReq request = new RpcReq();
		request.setMethodIcxCall(toAddress, method, tokenAddress);

		try{
			HttpEntity requestEntity = new HttpEntity(request.toString(), defaultHeader);
			String jsonStr = restTemplate.postForObject(url, requestEntity, String.class);

			JsonObject rootObject =  getRootObject(jsonStr);
			if(rootObject == null || rootObject.get("result") == null || rootObject.get("result").isJsonNull()) {
				return null;
			}
			return rootObject.get("result").getAsString();
		} catch (HttpClientErrorException re) {
			logger.error("HttpClientError getIcxCall. errMsg = [{}]", re.getResponseBodyAsString());
		} catch (Exception re) {
			re.printStackTrace();
			logger.error("Exception getIcxCall. errMsg = [{}]", re.getMessage());
		}
		return null;
	}

	@Override
	public JsonObject getScoreStatus(String url, String address) {
		RpcReq request = new RpcReq();
		request.setMethodScoreStatus(address);

		try {
			HttpEntity<String> requestEntity = new HttpEntity<>(request.toString(), defaultHeader);
			String jsonStr = restTemplate.postForObject(url, requestEntity, String.class);

			JsonObject rootObject = getRootObject(jsonStr);
			if (rootObject == null || rootObject.get("result") == null || rootObject.get("result").isJsonNull()) {
				return null;
			}
			return rootObject.get("result").getAsJsonObject();
		} catch (HttpClientErrorException re) {
			logger.error("HttpClientError getIcxCall. errMsg = [{}]", re.getResponseBodyAsString());
		} catch (Exception re) {
			logger.error("Exception getScoreStatus. errMsg = [{}]", re.getMessage());
		}
		return null;
	}

	@Override
	public RpcStakeRes getStake(String url, String address) {
		logger.info("--------------------");

		try{
			HttpEntity requestEntity = null;

			logger.info("[V3 BlockChain] getPRepList. url = {}", url);
			RpcReq request = new RpcReq();
			request.setMethodGetStake(address);
			requestEntity = new HttpEntity(request.toString(), defaultHeader);

			RpcStakeRes res = restTemplate.postForObject(url, requestEntity, RpcStakeRes.class);
			return res;

		} catch (Exception re) {
			logger.error("Exception getPRepList. errMsg = [{}]", re.getMessage());
			return null;
		}
	}

	private ResponseEntity<String> sendGet(String url) {
		try {
			HttpEntity requestEntity = new HttpEntity(defaultHeader);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
			return response;
		} catch (Exception e) {
			logger.error("SendGet Error {} = [{}]", url, e.getMessage());
			return null;
		}
	}

	private JsonObject getRootObject(String jsonStr) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(jsonStr);
		JsonObject rootObject =  element.getAsJsonObject();
		return rootObject;
	}

}
