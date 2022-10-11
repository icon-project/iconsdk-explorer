package com.dfg.icon.core.v3.vo;

import com.dfg.icon.core.dao.icon.*;
import org.json.JSONObject;

import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.util.CommonUtil;
import com.dfg.icon.util.FactoryUtil;
import com.dfg.icon.util.HexUtil;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * version2 transaction Factory
 * @author hslee
 *
 */
public class TxV3Factory implements TxFactory{

	TTransactionTotalWithBLOBs tx = null;
	List<TTransactionView> txView = null;
	JSONObject jsonData = null;
	TTxDataWithBLOBs txData = null;
	JsonObject dataObject = null;
	String dataContentObject = null;


	/* (non-Javadoc)
	 * @see com.dfg.icon.core.v3.vo.TxFactory#init(com.dfg.icon.core.v3.vo.BlockVo, org.json.JSONObject)
	 */

	@Override
	public void init(TBlockTotal block, JsonObject txInfo) throws Exception {
		txView = new ArrayList<>();
		tx = new TTransactionTotalWithBLOBs();
		tx.setTxHash(HexType.toDBformatString(txInfo.get("txHash").getAsString()));
		tx.setHash(block.getHash());
		tx.setHeight(block.getHeight());
		tx.setCreateDate(FactoryUtil.changeStringTimeStampFormat(txInfo.get("timestamp").getAsString()));
		if(txInfo.get("dataType") == null) {
			tx.setDataType(IconCode.TXDATATYPE_ICX.getCode());
		} else {
			tx.setDataType(txInfo.get("dataType").getAsString());
		}
		if(txInfo.get("from") != null && !txInfo.get("from").isJsonNull()) {
			tx.setFromAddr(CommonUtil.checkAddress(txInfo.get("from").getAsString()));
		}
		if(txInfo.get("to") != null && !txInfo.get("to").isJsonNull()) {
			tx.setToAddr(CommonUtil.checkAddress(txInfo.get("to").getAsString()));
		}
		if(txInfo.get("stepLimit") != null && !txInfo.get("stepLimit").isJsonNull()) {
			tx.setStepLimit(HexUtil.applyDecimal(txInfo.get("stepLimit").getAsString(), 0));
		}

		if (txInfo.get("value") == null || txInfo.get("value").isJsonNull()) {
			tx.setAmount("0");
		}else {
			tx.setAmount(HexUtil.toDecimalString(txInfo.get("value").getAsString(), DecimalType.ICX.getValue()));
		}
		tx.setFee("");
		tx.setVersion(IconCode.TXNVERSION_3.getCode());

		txData = new TTxDataWithBLOBs();
		txData.setTxHash(tx.getTxHash());
		if(txInfo.get("data") != null) {

			// score deploy or update
			/*
			if(IconCode.TXDATATYPE_DEPLOY.getCode().equals(tx.getDataType())) {
				JsonObject data = txInfo.get("data").getAsJsonObject();
				dataContentObject = data.get("content").getAsString();
				//data.remove("content");
				txData.setDataBody(data.toString());
				dataObject = data;
			}else {
				String data = txInfo.get("data").toString();
				txData.setDataBody(data);
				dataObject = txInfo.get("data").getAsJsonObject();
			}
			*/
			// deploy/update 모두 그냥 data 담아서 blockfactory로 넘김.
			// zip 저장 작업은 blockfactory를 가지고 해야하기때문에 txfactory는 접근 불능.
			// zip 저장 때 content 제거하여 db등록
			String data = txInfo.get("data").toString();
			txData.setDataBody(data);
//			if(!tx.getDataType().equals("message")) {
//				dataObject = txInfo.get("data").getAsJsonObject();
//			}
		}else {
			txData.setDataBody("");
		}
	}

	/* (non-Javadoc)
	 * @see com.dfg.icon.core.v3.vo.TxFactory#getStep()
	 */
	@Override
	public String getFee() {
		return tx.getFee();
	}

	/* (non-Javadoc)
	 * @see com.dfg.icon.core.v3.vo.TxFactory#getTransaction()
	 */
	@Override
	public TTransactionTotalWithBLOBs getTransaction() {
		return tx;
	}

	@Override
	public List<TTransactionView> getTransactionView() {
		return txView;
	}

	/* (non-Javadoc)
	 * @see com.dfg.icon.core.v3.vo.TxFactory#getVersion()
	 */
	@Override
	public String getVersion() {
		return IconCode.TXNVERSION_3.getMessage();
	}

	/* (non-Javadoc)
	 * @see com.dfg.icon.core.v3.vo.TxFactory#getValue()
	 */
	@Override
	public String getValue() throws Exception {
		return tx.getAmount();
	}

	/* (non-Javadoc)
	 * @see com.dfg.icon.core.v3.vo.TxFactory#getData()
	 */
	@Override
	public TTxDataWithBLOBs getData() throws Exception {
		return txData;
	}


	/* (non-Javadoc)
	 * @see com.dfg.icon.core.v3.vo.TxFactory#getContentData()
	 */
	@Override
	public String getContentData() throws Exception {
		return dataContentObject.toString();
	}

	/* (non-Javadoc)
	 * @see com.dfg.icon.core.v3.vo.TxFactory#getDataJsonValueByKey(java.lang.String[])
	 */
	@Override
	public String getDataJsonValueByKey(String... key) throws Exception {
		JsonObject resultObject = dataObject;
		for(String k : key ) {
			resultObject = resultObject.get(k).getAsJsonObject();
		}
		return resultObject.getAsString();
	}

	/* (non-Javadoc)
	 * @see com.dfg.icon.core.v3.vo.TxFactory#getEtc(java.lang.String)
	 */
	@Override
	public String getEtc(String key) throws Exception {
		return jsonData.getString(key);
	}


}
