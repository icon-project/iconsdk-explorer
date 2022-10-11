package com.dfg.icon.core.v3.vo;

import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.util.HexUtil;
import com.google.gson.JsonObject;
import org.json.JSONObject;

import java.util.List;

/**
 * version2 transaction Factory
 * @author hslee
 *
 */
public class TxResultV2Factory implements TxResultFactory{

	TxResult txResult = null;
	TxResultFailure txResultFailure = null;

	JSONObject jsonData = null;
	JsonObject dataObject = null;
	
	
	/* (non-Javadoc)
	 * @see com.dfg.icon.core.v3.vo.TxFactory#init(com.dfg.icon.core.v3.vo.BlockVo, org.json.JSONObject)
	 */

	@Override
	public void init(JsonObject txResultInfo) throws Exception {
		txResult = new TxResult();
		int code = txResultInfo.get("code").getAsInt();
		if(code == 0) {
			txResult.setStatus(1);
			txResult.setStepPrice("10000000000");
			txResult.setStepUsed("1000000");
		} else {
			txResult.setStatus(0);
			txResultFailure = new TxResultFailure();
			txResultFailure.setCode(txResultInfo.get("code").getAsString());
			txResultFailure.setMessage(txResultInfo.get("message").getAsString());
			txResult.setFailure(txResultFailure);
		}

		txResult.setVersion(IconCode.TXNVERSION_2.getCode());
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
		return null;
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
		return 0;
	}
	public void setJsonTime(long time){}
}
