package com.dfg.icon.core.v3.vo;

import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.util.HexUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * version2 transaction Factory
 * @author hslee
 *
 */
public class TxResultV3Factory implements TxResultFactory{
	
	private static final Logger logger = LoggerFactory.getLogger(TxResultV3Factory.class);
	

	TxResult txResult = null;
	TxResultFailure txResultFailure = null;

	JSONObject jsonData = null;
	JsonObject dataObject = null;
	long jsonTime = 0;
	
	
	/* (non-Javadoc)
	 * @see com.dfg.icon.core.v3.vo.TxFactory#init(com.dfg.icon.core.v3.vo.BlockVo, org.json.JSONObject)
	 */

	@Override
	public void init(JsonObject txResultInfo) throws Exception {
		txResult = new TxResult();
		txResult.setStatus( HexUtil.changeHexToInteger(txResultInfo.get("status").getAsString())  );
		// Feesms 공통으로 처리 

//		txResult.setCumulativeStepUsed(String.valueOf(HexUtil.changeHexToInteger(txResultInfo.get("cumulativeStepUsed").getAsString())));
		txResult.setStepUsed(String.valueOf(HexUtil.applyDecimal(txResultInfo.get("stepUsed").getAsString(), 0)));
		txResult.setStepPrice(String.valueOf(HexUtil.applyDecimal(txResultInfo.get("stepPrice").getAsString(), 0)));
		if(txResultInfo.get("stepUsedDetails") != null && !txResultInfo.get("stepUsedDetails").isJsonNull()) {
			txResult.setStepUsedDetails(txResultInfo.get("stepUsedDetails").toString());
		}

		txResult.setVersion(IconCode.TXNVERSION_3.getCode());
		if(txResult.getStatus().intValue() == 0) {
			txResultFailure = new TxResultFailure();
			JsonObject failure = txResultInfo.get("failure").getAsJsonObject();
			txResultFailure.setCode(failure.get("code").getAsString());
			txResultFailure.setMessage(failure.get("message").getAsString());
			txResult.setFailure(txResultFailure);
			return;
		}
		txResult.setTxHash(txResultInfo.get("txHash").getAsString());
		txResult.setTxIndex(HexUtil.changeHexToInteger(txResultInfo.get("txIndex").getAsString()));
		txResult.setBlockHeight(HexUtil.changeHexToInteger(txResultInfo.get("blockHeight").getAsString()));
		if(txResultInfo.get("blockHash") == null || txResultInfo.get("blockHash").isJsonNull()) {
			txResult.setBlockHash("");
		}else {
			txResult.setBlockHash(txResultInfo.get("blockHash").getAsString());
		}

		
		txResult.setTo(txResultInfo.get("to").getAsString());

		// if score
		if(txResultInfo.get("scoreAddress") == null || txResultInfo.get("scoreAddress").isJsonNull()) {
			txResult.setScoreAddress("-");
		}else {
			txResult.setScoreAddress(txResultInfo.get("scoreAddress").getAsString());
		}

		JsonArray el = txResultInfo.get("eventLogs").getAsJsonArray();

		for(int i=0; i<el.size(); i++) {
			JsonObject elo = el.get(i).getAsJsonObject();
			EventLog log = new EventLog();
			if(elo.get("scoreAddress") != null && !elo.get("scoreAddress").isJsonNull()) {
				log.setScoreAddress(elo.get("scoreAddress").getAsString());
			}
			if(elo.get("indexed") != null && !elo.get("indexed").isJsonNull()) {
				JsonArray idxArray = elo.get("indexed").getAsJsonArray();
				for(int j=0; j<idxArray.size(); j++) {
					if(idxArray.get(j) == null || idxArray.get(j).isJsonNull()) {
						log.getIndexed().add(null);
					} else {
						log.getIndexed().add(idxArray.get(j).getAsString());
					}
				}
			}
			if(elo.get("data") != null && !elo.get("data").isJsonNull()) {
				JsonArray dataArray = elo.get("data").getAsJsonArray();
				for(int j=0; j<dataArray.size(); j++) {
					try {
						if(dataArray.get(j) != null && !dataArray.get(j).isJsonNull()) {
							log.getData().add(dataArray.get(j).getAsString());
						} else {
							log.getData().add(null);
						}
					} catch(Exception e) {
						logger.error("[TxResultInit] data obj json parsing error = [{}]", e.getMessage());
						log.getData().add(null);
					}
				}
			}
			txResult.getEventLogList().add(log);
		}
	}

	/**
	 * 해당 TX 의 status(1:success, 0:failure) 반환
	 */
	@Override
	public Boolean getStatus() throws Exception {
		if(txResult.getStatus() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * status가 0인 경우 존재하는 failure 반환
	 */
	@Override
	public TxResultFailure getFailure() throws Exception {
		if(txResult.getStatus().intValue() == 0) {
			return txResultFailure;
		}
		return null;
	}

	/**
	 * txHash 반환
	 */
	@Override
	public String getTxHash() throws Exception {
		return txResult.getTxHash();
	}


	/** txIndex 반환
	 */
	@Override
	public Integer getTxIndex() throws Exception {
		return txResult.getTxIndex();
	}


	/** blockHeight 반환
	 */
	@Override
	public Integer getBlockHeight() throws Exception {
		return txResult.getBlockHeight();
	}

	/** blockHash 반한
	 */
	@Override
	public String getBlockHash() throws Exception {
		return txResult.getBlockHash();
	}

	/** tx 수행하기까지 소비된 step 누적량
	 */
	@Override
	public String getCumulativeStepUsed() throws Exception {
		return txResult.getCumulativeStepUsed();
	}

	/** tx 수행하는데 소비된 step 양
	 */
	@Override
	public String getStepUsed() throws Exception {
		return txResult.getStepUsed();
	}

	@Override
	public String getStepPrice() throws Exception {
		return txResult.getStepPrice();
	}

	@Override
	public String getStepUsedDetails() throws Exception {
		return txResult.getStepUsedDetails();
	}

	@Override
	public String getFee() throws Exception {
		if(txResult.getStepUsed() != null && txResult.getStepPrice() != null) {
			return HexUtil.decimalMultiDecimal(
					txResult.getStepUsed(),
					HexUtil.applyDecimal(txResult.getStepPrice(), DecimalType.ICX.getValue()),
					DecimalType.ICX.getValue());
		} else {
			return "0";
		}
	}

	/** score를 생성했을 경우 해당 score 주소
	 */
	@Override
	public String getScoreAddress() throws Exception {
		return txResult.getScoreAddress();
	}

	@Override
	public String getTo() throws Exception {
		return txResult.getTo();
	}

	@Override
	public List<EventLog> getEventLogs() throws Exception {
		return txResult.getEventLogList();
	}

	@Override
	public String getVersion() {
		return txResult.getVersion();
	}

	@Override
	public long getJsonTime() {
		return jsonTime;
	}
	@Override
	public void setJsonTime(long time) {
		jsonTime = time;
	}

//	/* (non-Javadoc)
//	 * @see com.dfg.icon.core.v3.vo.TxFactory#getDataJsonValueByKey(java.lang.String[])
//	 */
//	@Override
//	public String getDataJsonValueByKey(String... key) throws Exception {
//		JsonObject resultObject = dataObject;
//		for(String k : key ) {
//			resultObject = resultObject.get(k).getAsJsonObject();
//		}
//		return resultObject.getAsString();
//	}
//
//	/* (non-Javadoc)
//	 * @see com.dfg.icon.core.v3.vo.TxFactory#getEtc(java.lang.String)
//	 */
//	@Override
//	public String getEtc(String key) throws Exception {
//		return jsonData.getString(key);
//	}
}
