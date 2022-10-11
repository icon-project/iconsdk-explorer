package com.dfg.icon.core.v3.vo;

import java.util.ArrayList;
import java.util.List;

import com.dfg.icon.core.dao.icon.*;
import org.json.JSONObject;

import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.util.CommonUtil;
import com.dfg.icon.util.FactoryUtil;
import com.dfg.icon.util.HexUtil;
import com.google.gson.JsonObject;

/** version2 transaction Factory
 * @author hslee
 *
 */
public class TxV2Factory implements TxFactory{

	TTransactionTotalWithBLOBs tx = null;
	List<TTransactionView> txView = null;
	JSONObject jsonData = null;
	TTxDataWithBLOBs txData = null;
	JsonObject dataObject = null;

	@Override
	public void init(TBlockTotal block, JsonObject txInfo) throws Exception{
		this.txView = new ArrayList<>();
		this.tx = new TTransactionTotalWithBLOBs();
		tx.setTxHash(HexType.toDBformatString(txInfo.get("tx_hash").getAsString()));
		tx.setHash(block.getHash());
		tx.setHeight(block.getHeight());
		if(txInfo.get("timestamp") == null) {
			tx.setCreateDate(block.getCreateDate());
		} else {
			tx.setCreateDate(FactoryUtil.changeTimeStampFormat(txInfo.get("timestamp").getAsString()));
		}
		tx.setFromAddr(CommonUtil.checkAddress(txInfo.get("from").getAsString()));
		tx.setToAddr(CommonUtil.checkAddress(txInfo.get("to").getAsString()).toLowerCase());
		tx.setAmount(HexUtil.toDecimalString(txInfo.get("value").getAsString(), DecimalType.ICX.getValue()));
		tx.setFee(HexUtil.toDecimalString(txInfo.get("fee").getAsString(), DecimalType.ICX.getValue()));
		tx.setStepUsedTx(tx.getFee());
		tx.setStepPrice("1");
		tx.setVersion(IconCode.TXNVERSION_2.getCode());
		tx.setTxType(Byte.parseByte(IconCode.TX_TYPE_ICX.getCode()));
		tx.setDataType("icx");
		// v2블록의 v2트랜잭션이라면 기본 성공.
		// v3블록의 v2트랜잭션이라면 txResult 구간에서 덮어씀.
		tx.setState(Boolean.parseBoolean(IconCode.TX_RESULT_SUCCESS.getCode()));
		txData = new TTxDataWithBLOBs();
		txData.setTxHash(tx.getTxHash());
		if(txInfo.get("data") != null) {
			String data = txInfo.get("data").getAsString();
			txData.setDataBody(data);
//			dataObject = txInfo.get("data").getAsJsonObject();
		}else {
			txData.setDataBody("");
		}
//
//		TTransactionView fromView = new TTransactionView();
//		fromView.setTxHash(tx.getTxHash());
//		fromView.setAddress(tx.getFromAddr());
//		fromView.setHeight(tx.getHeight());
//		fromView.setCreateDate(tx.getCreateDate());
//		fromView.setState(tx.getState());
//		fromView.setTxType(tx.getTxType());
//		txView.add(fromView);
//		if(!tx.getFromAddr().equals(tx.getToAddr())) {
//			TTransactionView toView = new TTransactionView();
//			toView.setTxHash(tx.getTxHash());
//			toView.setAddress(tx.getToAddr());
//			toView.setHeight(tx.getHeight());
//			toView.setCreateDate(tx.getCreateDate());
//			toView.setState(tx.getState());
//			toView.setTxType(tx.getTxType());
//			txView.add(toView);
//		}
	}

	@Override
	public String getFee() {
		return tx.getFee();
	}

	@Override
	public TTransactionTotalWithBLOBs getTransaction() {
		return tx;
	}

	@Override
	public List<TTransactionView> getTransactionView() {
		return txView;
	}

	@Override
	public String getVersion() {
		return IconCode.TXNVERSION_2.getMessage();
	}

	@Override
	public String getValue() {
		return tx.getAmount();
	}

	@Override
	public TTxDataWithBLOBs getData() throws Exception {
		return txData;
	}

	@Override
	public String getEtc(String key) throws Exception {
		return jsonData.getString(key);
	}

	@Override
	public String getDataJsonValueByKey(String... key) throws Exception {
		JsonObject resultObject = dataObject;
		for(String k : key ) {
			resultObject = resultObject.get(k).getAsJsonObject();
		}
		return resultObject.getAsString();
	}

	@Override
	public String getContentData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
