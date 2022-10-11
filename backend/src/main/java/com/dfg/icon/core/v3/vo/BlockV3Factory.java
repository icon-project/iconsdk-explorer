package com.dfg.icon.core.v3.vo;

import java.util.*;

import com.dfg.icon.core.dao.icon.*;
import com.dfg.icon.core.exception.IconCode;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.dfg.icon.util.FactoryUtil;
import com.dfg.icon.util.HexUtil;


/**
 * v3 타입의 블록 정보 관리
 * @author hslee
 */
public class BlockV3Factory implements BlockFactory{

	private TBlockTotal blockInfo;
	private String version;

	private List<TTransactionTotalWithBLOBs> txList;
	private List<TTransactionView> txViewList;
	private List<TTxDataWithBLOBs> txDataList;
	private List<TContract> contractList;
	private List<TContractHistory> contractHistoryList;
	private List<TContractHistory> canceledHistoryList;
	private List<TTokenTx> tokenTxList;
	private List<TTokenTxView> tokenTxViewList;
	private List<TTxResultLogWithBLOBs> txResultLogList;
	private List<JsonObject> txJsonList;
	private List<TFeeDetails> feeDetailList;

	private List<TTokenAddress> tokenAddressList;
	private List<TokenAddressGroupKey> tokenAddressGroup;
	private List<TAddressTotal> addressList;
	private Set<String> addressGroup;
	private Set<String> tokenInfoGroup;
	private List<TInternalTx> internalTxList;
	private List<TInternalTxView> internalTxViewList;

	private Set<String> stakeGroup;

	private Set<String> delegateGroup;

	private JsonObject blockObject;

	private boolean isBreak;
	private boolean isDelegation;

	/* (non-Javadoc)
	 * @see com.dfg.icon.core.v3.vo.BlockFactory#init(com.google.gson.JsonObject)
	 */
	@Override
	public void init(JsonObject resultObject, int blockSize) throws Exception {


		isBreak = false;
		isDelegation = false;
		version = IconCode.TXNVERSION_3.getCode();
		blockInfo 	= new TBlockTotal();
		txList 		= new ArrayList<>();
		txViewList = new ArrayList<>();
		txJsonList 	= new ArrayList<>();
		txDataList 	= new  ArrayList<>();
		contractList = new ArrayList<>();
		tokenTxList = new ArrayList<>();
		tokenTxViewList = new ArrayList<>();
		contractHistoryList = new ArrayList<>();
		canceledHistoryList = new ArrayList<>();
		txResultLogList = new ArrayList<>();
		tokenAddressList = new ArrayList<>();
		tokenAddressGroup = new ArrayList<>();
		addressList = new ArrayList<>();
		addressGroup = new HashSet<>();
		tokenInfoGroup = new HashSet<>();
		internalTxList = new ArrayList<>();
		internalTxViewList = new ArrayList<>();
		delegateGroup = new HashSet<>();
		feeDetailList = new ArrayList<>();
		stakeGroup = new HashSet<>();


		blockObject = resultObject;
		// 블록 정보 정리
		if(blockObject.get("block_hash") != null && !blockObject.get("block_hash").isJsonNull()) {
			// version 0.1a
			blockInfo.setHash(HexType.toDBformatString(blockObject.get("block_hash").getAsString()));
		} else if(blockObject.get("hash") != null && !blockObject.get("hash").isJsonNull()){
			// version 0.3
			blockInfo.setHash(HexType.toDBformatString(blockObject.get("hash").getAsString()));
		}
		blockInfo.setHeight(HexUtil.changeStringToInteger(blockObject.get("height").getAsString()));
//		blockInfo.setCreateDate( FactoryUtil.changeHexTimeStampFormat(blockObject.get("time_stamp").getAsString()) );

		if(blockObject.get("time_stamp") != null && !blockObject.get("time_stamp").isJsonNull()) {
			// version 0.1a
			blockInfo.setCreateDate(FactoryUtil.changeStringTimeStampFormat(blockObject.get("time_stamp").getAsString()));
		} else if(blockObject.get("timestamp") != null && !blockObject.get("timestamp").isJsonNull()){
			// version 0.3
			blockInfo.setCreateDate(FactoryUtil.changeStringTimeStampFormat(blockObject.get("timestamp").getAsString()));
		}
		if(blockObject.get("prev_block_hash") != null && !blockObject.get("prev_block_hash").isJsonNull()) {
			// version 0.1a
			blockInfo.setPrevHash(HexType.toDBformatString(blockObject.get("prev_block_hash").getAsString()));
		} else if(blockObject.get("prevHash") != null && !blockObject.get("prevHash").isJsonNull()){
			// version 0.3
			blockInfo.setPrevHash(HexType.toDBformatString(blockObject.get("prevHash").getAsString()));
		}
		blockInfo.setBlockSize(blockSize);

		// 트랜잭션 정보 정리
		JsonArray txJsonArray;
		if(blockObject.get("confirmed_transaction_list") != null && !blockObject.get("confirmed_transaction_list").isJsonNull()) {
			txJsonArray = blockObject.get("confirmed_transaction_list").getAsJsonArray();
		} else {
			txJsonArray = blockObject.get("transactions").getAsJsonArray();
		}

		for( JsonElement confirmedTransactionListElement :   txJsonArray ) {
			JsonObject txObject = confirmedTransactionListElement.getAsJsonObject();
			txJsonList.add(txObject);

			TxFactory txFactory = null;
			String txVersion = FactoryUtil.getTxVersion(txObject);
			if(txVersion.equals(IconCode.TXNVERSION_3.getCode())) {
				txFactory = new TxV3Factory();
			}else {
				txFactory = new TxV2Factory();
			}
			txFactory.init(blockInfo, txObject);

			txList.add(txFactory.getTransaction());
			txViewList.addAll(txFactory.getTransactionView());
			txDataList.add(txFactory.getData());

			String fromAddr = txFactory.getTransaction().getFromAddr();
			String toAddr = txFactory.getTransaction().getToAddr();
			if(fromAddr != null && fromAddr.length() > 2 && "cx".equals(fromAddr.substring(0, 2))) {
				if(!tokenInfoGroup.contains(fromAddr)) {
					tokenInfoGroup.add(fromAddr);
				}
			}
			if(toAddr != null && toAddr.length() > 2 && "cx".equals(toAddr.substring(0, 2))) {
				if(!tokenInfoGroup.contains(toAddr)) {
					tokenInfoGroup.add(toAddr);
				}
			}
			if(fromAddr != null && !addressGroup.contains(fromAddr)) {
				addressGroup.add(fromAddr);
			}
			if(toAddr != null && !addressGroup.contains(toAddr)) {
//				if (IconCode.SCORE_INSTALL_ADDR.getCode().equals(toAddr)) {
//					if("icx".equals(txFactory.getTransaction().getDataType())) {
//						addressGroup.add(toAddr);
//					}
//				} else {
					addressGroup.add(toAddr);	// install, controll 주소도 등록을 해야 contractInfo에 정보가 나옴...
//				}
			}
		}

		blockInfo.setTxCount(txList.size());
	}

	@Override
	public List<TContract> getContractList() throws Exception {
		return contractList;
	}

	@Override
	public List<TContractHistory> getContractHistoryList() throws Exception {
		return contractHistoryList;
	}

	@Override
	public List<TContractHistory> getCanceledHistoryList() throws Exception {
		return canceledHistoryList;
	}

	/**
	 * T_TOKEN_TX 에 넣을 데이터 array
	 */
	@Override
	public List<TTokenTx> getTokenTxList() throws Exception {
		return tokenTxList;
	}

	@Override
	public List<TTokenTxView> getTokenTxViewList() throws Exception {
		return tokenTxViewList;
	}

	/**
	 * T_TX_RESULT_LOG에 넣을 데이터 array
	 */
	@Override
	public List<TTxResultLogWithBLOBs> getTxResultLogList() throws Exception {
		return txResultLogList;
	}

	/**
	 * txResult를 진행하면서 업데이트해야할 TokenAddress를 수집하는 임시 array
	 */
	@Override
	public List<TokenAddressGroupKey> getTokenAddressGroup() throws Exception {
		return tokenAddressGroup;
	}

	/**
	 * txResult를 진행하면서 업데이트 해야 할 Address를 수집하는 임시 array
	 */
	@Override
	public Set<String> getAddressGroup() throws Exception {
		return addressGroup;
	}

	/**
	 * TokenInfo의 txCount, holderCount를 업데이트
	 * @return
	 * @throws Exception
	 */
	@Override
	public Set<String> getTokenInfoGroup() throws Exception {
		return tokenInfoGroup;
	}
	/* (non-Javadoc)
	 * @see com.dfg.icon.core.v3.vo.BlockFactory#getBlock()
	 */
	@Override
	public TBlockTotal getBlockInfo() throws Exception {
		return blockInfo;
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
	public String getVersion() throws Exception {
		return version;
	}

	@Override
	public List<TInternalTx> getInternalTransactionList() throws Exception {
		return internalTxList;
	}

	@Override
	public List<TInternalTxView> getInternalTxViewList() throws Exception {
		return internalTxViewList;
	}

	@Override
	public List<TFeeDetails> getFeeDetailList() {
		return feeDetailList;
	}

	@Override
	public Set<String> getStakeGroup() {
		return stakeGroup;
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
