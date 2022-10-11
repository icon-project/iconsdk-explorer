package com.dfg.icon.core.v3.vo;

import java.util.*;

import com.dfg.icon.core.dao.icon.*;
import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.util.HexUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.dfg.icon.util.FactoryUtil;

/**
 * v2 타입의 블록 정보 관리
 * @author hslee
 */
public class BlockV2Factory implements BlockFactory{

	private TBlockTotal blockInfo;

	private List<TTransactionTotalWithBLOBs> txList;
	private List<TTransactionView> txViewList;
	private List<TTxDataWithBLOBs> txDataList;

	private JsonObject blockObject;
	private List<JsonObject> txJsonList;
	private List<TAddressTotal> addressList;
	private Set<String> addressGroup;
	private Set<String> tokenInfoGroup;
	private Set<String> stakeGroup;

	private String version;

	private boolean isBreak;
	private boolean isDelegation;


	/* (non-Javadoc)
	 * @see com.dfg.icon.core.v3.vo.BlockFactory#init(com.google.gson.JsonObject)
	 */
	@Override
	public void init(JsonObject resultObject, int blockSize) throws Exception {

		isBreak = false;
		isDelegation = false;
		blockInfo 	= new TBlockTotal();
		txList 		= new ArrayList<>();
		txJsonList 	= new ArrayList<>();
		txDataList 	= new  ArrayList<>();
		addressGroup = new HashSet<>();
		tokenInfoGroup = new HashSet<>();
		version = IconCode.TXNVERSION_2.getCode();

		// 블록 정보 정리
		blockObject = resultObject.get("block").getAsJsonObject();
		String blockHash = blockObject.get("block_hash").getAsString();
		int blockHeight  = blockObject.get("height").getAsInt() ;
		blockInfo.setHash(HexType.toDBformatString(blockHash));
		blockInfo.setHeight(blockHeight);
		blockInfo.setCreateDate( FactoryUtil.changeTimeStampFormat(blockObject.get("time_stamp").getAsString()));
		blockInfo.setPrevHash(HexType.toDBformatString(blockObject.get("prev_block_hash").getAsString()));
		blockInfo.setBlockSize(blockSize);


		// 트랜잭션 정보 정리
		JsonArray txJsonArray = blockObject.get("confirmed_transaction_list").getAsJsonArray();
		String sumFee = "0";
		String sumAmount = "0";
		for( JsonElement confirmedTransactionListElement :   txJsonArray ) {
			JsonObject txObject = confirmedTransactionListElement.getAsJsonObject();
			txJsonList.add(txObject);

			TxFactory txFactory = null;
			String txVersion = FactoryUtil.getTxVersion(txObject);
			if(txVersion.equals(IconCode.TXNVERSION_3.getCode())) {
				txFactory = new TxV3Factory();
				txFactory.init(blockInfo, txObject);
			}else {
				txFactory = new TxV2Factory();
				txFactory.init(blockInfo, txObject);
				sumFee = HexUtil.decimalPlusDecimal(sumFee, txFactory.getFee(), DecimalType.ICX.getValue());
				sumAmount = HexUtil.decimalPlusDecimal(sumAmount, txFactory.getValue(), DecimalType.ICX.getValue());
			}


			txList.add(txFactory.getTransaction());
			txViewList.addAll(txFactory.getTransactionView());
			txDataList.add(txFactory.getData());

			if(!addressGroup.contains(txFactory.getTransaction().getFromAddr())) {
				addressGroup.add(txFactory.getTransaction().getFromAddr());
			}
			if(!addressGroup.contains(txFactory.getTransaction().getToAddr())) {
				addressGroup.add(txFactory.getTransaction().getToAddr());
			}
		}

		blockInfo.setTxCount(txList.size());
		// v2block에 v3tx가 없다는 가정하에...
		blockInfo.setFee(sumFee);
		blockInfo.setAmount(sumAmount);

	}
	@Override
	public List<TContract> getContractList() throws Exception {
		return null;
	}

	@Override
	public List<TContractHistory> getContractHistoryList() throws Exception {
		return null;
	}

	@Override
	public List<TContractHistory> getCanceledHistoryList() throws Exception {
		return null;
	}

	@Override
	public List<TTokenTx> getTokenTxList() throws Exception {
		return null;
	}

	@Override
	public List<TTokenTxView> getTokenTxViewList() throws Exception {
		return null;
	}

	@Override
	public List<TTxResultLogWithBLOBs> getTxResultLogList() throws Exception {
		return null;
	}

	@Override
	public List<TokenAddressGroupKey> getTokenAddressGroup() throws Exception {
		return new ArrayList<>();
	}

	@Override
	public Set<String> getAddressGroup() throws Exception {
		return addressGroup;
	}

	@Override
	public Set<String> getTokenInfoGroup() throws Exception {
		return tokenInfoGroup;
	}

	@Override
	public Set<String> getStakeGroup() {
		return stakeGroup;
	}

	/* (non-Javadoc)
	 * @see com.dfg.icon.core.v3.vo.BlockFactory#getBlock()
	 */
	@Override
	public TBlockTotal getBlockInfo() throws Exception {
		// TODO Auto-generated method stub
		return blockInfo;
	}

	@Override
	public String getVersion() throws Exception {
		return version;
	}

	/* (non-Javadoc)
	 * @see com.dfg.icon.core.v3.vo.BlockFactory#getTransactionList()
	 */
	@Override
	public List<TTransactionTotalWithBLOBs> getTransactionList() throws Exception {
		return txList;
	}

	@Override
	public List<TTransactionView> getTransactionViewList() throws Exception {
		return txViewList;
	}

	/* (non-Javadoc)
	 * @see com.dfg.icon.core.v3.vo.BlockFactory#getTransactionDataList()
	 */
	@Override
	public List<TTxDataWithBLOBs> getTransactionDataList() throws Exception {
		return txDataList;
	}

	@Override
	public List<TInternalTx> getInternalTransactionList() throws Exception {
		return null;
	}

	@Override
	public List<TInternalTxView> getInternalTxViewList() throws Exception {
		return null;
	}

	@Override
	public List<TFeeDetails> getFeeDetailList() {
		return null;
	}

	@Override
	public boolean isBreak(){
		return isBreak;
	}
	@Override
	public boolean isDelegation(){
		return isDelegation;
	}
	@Override
	public void setBreak(boolean isBreak) {
		this.isBreak = isBreak;
	}
	@Override
	public void setDelegation(boolean isDelegation) {
		this.isDelegation = isDelegation;
	}
}
