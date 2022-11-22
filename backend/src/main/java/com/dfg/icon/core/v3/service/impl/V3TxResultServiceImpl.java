package com.dfg.icon.core.v3.service.impl;

import com.dfg.icon.core.dao.icon.*;
import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.mappers.icon.*;
import com.dfg.icon.core.v0.service.V0MainService;
import com.dfg.icon.core.v3.adapter.V3BlockChainAdapter;
import com.dfg.icon.core.v3.service.*;
import com.dfg.icon.core.v3.vo.*;
import com.dfg.icon.util.FactoryUtil;
import com.dfg.icon.util.HexUtil;
import com.dfg.icon.util.IRCUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.web3j.rlp.RlpDecoder;
import org.web3j.rlp.RlpList;
import org.web3j.rlp.RlpString;
import org.web3j.rlp.RlpType;

import java.util.Base64;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class V3TxResultServiceImpl implements V3TxResultService {

	private static final Logger logger = LoggerFactory.getLogger(V3TxResultServiceImpl.class);
	private static final Logger sLogger = LoggerFactory.getLogger("ScheduleLogger");

	private static final String CONTENT_TYPE_JAVA = "application/java";
	private static final int AUDIT_ENABLED = 0x2;
	private int serviceConfig = -1;

	boolean speedLogFlag = true;

	/**
	 * The T block mapper.
	 */
	@Autowired
	TBlockMapper tBlockMapper;

	/**
	 * The T block total mapper.
	 */
	@Autowired
	TBlockTotalMapper tBlockTotalMapper;

	/**
	 * The T transaction mapper.
	 */
	@Autowired
	TTransactionMapper tTransactionMapper;

	/**
	 * The T transaction total mapper.
	 */
	@Autowired
	TTransactionTotalMapper tTransactionTotalMapper;

	/**
	 * The Token service.
	 */
	@Autowired
	V3TokenService tokenService;

	/**
	 * The T address mapper.
	 */
	@Autowired
	TAddressMapper tAddressMapper;

	/**
	 * The T address total mapper.
	 */
	@Autowired
	TAddressTotalMapper tAddressTotalMapper;

	/**
	 * The Address mapper.
	 */
	@Autowired
	AddressMapper addressMapper;

	@Autowired
	BlockMapper blockMapper;

	/**
	 * The Transaction mapper.
	 */
	@Autowired
	TransactionMapper transactionMapper;

	/**
	 * The T tx data mapper.
	 */
	@Autowired
	TTxDataMapper tTxDataMapper;

	/**
	 * The Contract mapper.
	 */
	@Autowired
	ContractMapper contractMapper;

	/**
	 * The Token mapper.
	 */
	@Autowired
	TokenMapper tokenMapper;

	/**
	 * The Block chain adapter.
	 */
	@Autowired
	V3BlockChainAdapter blockChainAdapter;

	/**
	 * The V 0 main service.
	 */
	@Autowired
	V0MainService v0MainService;

	/**
	 * The Transaction manager.
	 */
	@Autowired
	PlatformTransactionManager transactionManager;

	/**
	 * The Main mapper.
	 */
	@Autowired
	MainMapper mainMapper;

	/**
	 * The T main block mapper.
	 */
	@Autowired
	TMainBlockMapper tMainBlockMapper;

	/**
	 * The Address service.
	 */
	@Autowired
	V3AddressService addressService;

	/**
	 * The T contract history mapper.
	 */
	@Autowired
	TContractHistoryMapper tContractHistoryMapper;

	/**
	 * The T contract mapper.
	 */
	@Autowired
	TContractMapper tContractMapper;

	/**
	 * The Contract service.
	 */
	@Autowired
	V3ContractService contractService;

	/**
	 * The Score path.
	 */
	@Value("${score.path}")
	String SCORE_PATH;

	/**
	 * The Irc file path.
	 */
	@Value("${irc.path}")
	String IRC_FILE_PATH;

	@Value("${scheduler.MaxTxCount}")
	Integer maxTxCount ;


	/**
	 * blockFactory의 txFactory를 txResult 조회하여 적용 후 반환
	 *
	 * contractTx와 tokenTx등의 정보는 txSuccess 인 경우에 한해 집어넣고, 실패한 트랜잭션은 생성하지 않는다.
	 * 실패한 트랜잭션은 TTransaction에만 기록한다.
	 *
	 */
	public BlockFactory initTxResult(String url, BlockFactory bf) throws Exception {

		// v3 블록의 수수료, icx 총 거래량
		String sumFee = "0";
		String sumAmount = "0";
		Integer blockHeight = bf.getBlockInfo().getHeight();

		long startTime = System.currentTimeMillis();
		long sumJsonTime = 0;

		// 총 트랜잭션에 대하여
		for(int i=0; i<bf.getTransactionList().size(); i++) {

			String txHash = bf.getTransactionList().get(i).getTxHash();
			String txAmount = bf.getTransactionList().get(i).getAmount();
			String txFee = bf.getTransactionList().get(i).getFee();		// v2 fee
			String txToAddr = bf.getTransactionList().get(i).getToAddr();
			String txFromAddr = bf.getTransactionList().get(i).getFromAddr();
			Date txCreateDate = bf.getTransactionList().get(i).getCreateDate();

			TxResultFactory txResultfactory = blockChainAdapter.getTransactionResult(url, txHash);
			sumJsonTime += txResultfactory.getJsonTime();
			// 성공실패 0:실패 1:성공
			boolean txStatus = txResultfactory.getStatus();
			bf.getTransactionList().get(i).setState(txStatus);
			bf.getTransactionList().get(i).setStepUsedTx(txResultfactory.getStepUsed());
			bf.getTransactionList().get(i).setStepPrice(txResultfactory.getStepPrice());
			if(txResultfactory.getStepUsedDetails() != null) {
				TFeeDetails details = new TFeeDetails();
				details.setTxHash(txHash);
				details.setStepUsedDetails(txResultfactory.getStepUsedDetails());
				bf.getFeeDetailList().add(details);
			}
			if(txStatus/* == Boolean.parseBoolean(IconCode.TX_RESULT_SUCCESS.getCode())*/) {
				// 성공은 토탈 icx 합계
				sumAmount = HexUtil.decimalPlusDecimal(sumAmount, txAmount, DecimalType.ICX.getValue());
			}else {
				if(txResultfactory.getFailure() != null) {
					bf.getTransactionList().get(i).setErrorCode(
							HexUtil.applyDecimal(txResultfactory.getFailure().getCode(), 0));
					bf.getTransactionList().get(i).setErrorMsg(txResultfactory.getFailure().getMessage());
				}
			}

			TContractHistory contractHistory = null;

			// version 4이상 추가될 경우 조건 변경 필요.(v2제외하고 포함되도록)
			if(txResultfactory.getVersion().equals(IconCode.TXNVERSION_3.getCode())) {
				// get v3 tx Fee update
				txFee = txResultfactory.getFee();
				bf.getTransactionList().get(i).setFee(txFee);

				// default txType = icx. score이면 아래 분기에서 덮어쓴다.
				bf.getTransactionList().get(i).setTxType(Byte.parseByte(IconCode.TX_TYPE_ICX.getCode()));

				String dataType = bf.getTransactionList().get(i).getDataType();
				String data = bf.getTransactionDataList().get(i).getDataBody();
				// toAddr이 score 인 경우
				if (txToAddr != null && txToAddr.startsWith("cx")) {
					JsonObject rootObject = getRootObjectOfJsonString(dataType, data);

					// cx이면 기본 contract주소를 set하고, deploy(cx000...000/1)이면 이후 변경해준다.
					bf.getTransactionList().get(i).setContractAddr(txToAddr);

					//------------------------------------------
					// 이하 스코어 분기
					if(IconCode.TXDATATYPE_DEPLOY.getCode().equals(dataType)) {
						// deploy = install OR update.
						bf.setBreak(true);
						if(IconCode.SCORE_INSTALL_ADDR.getCode().equals(txToAddr)) {
							bf = installContract(url, i, bf, txResultfactory, rootObject);
						} else {
							bf = updateContract(url, i, bf, txResultfactory, rootObject);
						}
					} else if(IconCode.TXDATATYPE_CALL.getCode().equals(dataType)) {
						bf.getTransactionList().get(i).setTxType(Byte.parseByte(IconCode.TX_TYPE_CALL.getCode()));
						// call = 승인 or 리젝 or 토큰 or scoreCall
						if(IconCode.SCORE_CONTROL_ADDR.getCode().equals(txToAddr)) {
							String targetMethod = rootObject.get("method").getAsString();
							if(IconCode.TX_METHOD_TYPE_ACCEPTSCORE.getCode().equals(targetMethod)
									|| IconCode.TX_METHOD_TYPE_REJECTSCORE.getCode().equals(targetMethod)) {

								String targetTxHash = null;
								if(rootObject.get("params").getAsJsonObject().get("txHash") != null &&
										!(rootObject.get("params").getAsJsonObject().get("txHash").isJsonNull())) {
									targetTxHash = rootObject.get("params").getAsJsonObject().get("txHash").getAsString();
									contractHistory = contractService.getContractHistoryByCreateTx(targetTxHash);
								}

								// 승인 or 리젝
								bf.setBreak(true);
								if(contractHistory == null) {
									logger.error("[TxResultInit] targetTxHash contractHistory 정보가 없음. txHash={}", targetTxHash);
								} else {
									bf.getTransactionList().get(i).setContractAddr(contractHistory.getContractAddr());
									if(IconCode.TX_METHOD_TYPE_ACCEPTSCORE.getCode().equals(targetMethod)) {
										bf = acceptContract(url, i, bf, txResultfactory, contractHistory);
									} else if(IconCode.TX_METHOD_TYPE_REJECTSCORE.getCode().equals(targetMethod)) {
										bf = rejectContract(i, bf, txResultfactory, contractHistory);
									}
								}
								// 2020.09.08 거버넌스 트랜잭션에 대한 이벤트로그 전체 저장하도록 변경
								// bf = setEventResultLog(i, bf, txResultfactory);

							} else if(IconCode.TX_METHOD_TYPE_REGISTER_PROPOSAL.getCode().equals(targetMethod)) {
								bf.getTransactionList().get(i).setTxType(Byte.parseByte(IconCode.TX_TYPE_REGISTER_PROPOSAL.getCode()));
							} else if(IconCode.TX_TYPE_CANCEL_PROPOSAL.getCode().equals(targetMethod)) {
								bf.getTransactionList().get(i).setTxType(Byte.parseByte(IconCode.TX_TYPE_CANCEL_PROPOSAL.getCode()));
							} else if(IconCode.TX_TYPE_VOTE_PROPOSAL.getCode().equals(targetMethod)) {
								bf.getTransactionList().get(i).setTxType(Byte.parseByte(IconCode.TX_TYPE_VOTE_PROPOSAL.getCode()));
							} else {
							}
							// 2020.09.08 거버넌스 트랜잭션에 대한 이벤트로그 전체 저장하도록 변경
							// 이벤트로그가 없는 경우 empty list로 나오므로 무관
							bf = setEventResultLog(url, i, bf, txResultfactory);

						} else if(IconCode.SCORE_INSTALL_ADDR.getCode().equals(txToAddr)) {
							bf.setDelegation(true);
							// iiss prep call
							String targetMethod = rootObject.get("method").getAsString();
						} else {
							// 토큰 전송 txType
							bf.getTransactionList().get(i).setTxType(Byte.parseByte(IconCode.TX_TYPE_TOKEN.getCode()));
							int bItxCount = bf.getInternalTransactionList().size();
							bf = setTxEventLog(url, i, bf, txResultfactory);
							int fItxCount = bf.getInternalTransactionList().size();
							bf.getTransactionList().get(i).setInternalTxCount(fItxCount - bItxCount);
						}
					} else {
						int bItxCount = bf.getInternalTransactionList().size();
						bf = setTxEventLog(url, i, bf, txResultfactory);
						int fItxCount = bf.getInternalTransactionList().size();
						bf.getTransactionList().get(i).setInternalTxCount(fItxCount - bItxCount);
					}
				} else if(txToAddr == null) { // check txToAddr is null, handle "base" dataType
					JsonObject rootObject = getRootObjectOfJsonString(dataType, data);

					if(IconCode.TXDATATYPE_BASE.getCode().equals(dataType)) {
						bf.getTransactionList().get(i).setTxType(Byte.parseByte(IconCode.TX_TYPE_ICX.getCode()));
						bf = setBaseEventResultLog(i, bf, txResultfactory); // get event logs to insert
					}
				}
			}

			// 수수료 합계
			sumFee = HexUtil.decimalPlusDecimal(sumFee, txFee, DecimalType.ICX.getValue());
			bf = addTransactionView(bf, i);
			if(bf.getTransactionList().get(i).getInternalTxCount() == null) {
				bf.getTransactionList().get(i).setInternalTxCount(0);
			}

		}

		bf.getBlockInfo().setAmount(sumAmount);
		bf.getBlockInfo().setFee(sumFee);

		sLogger.info("[Speed] getTxResult ntk : {}s, total : {}s",
				sumJsonTime / 1000.0f,
				(System.currentTimeMillis() - startTime) / 1000.0f);

		return bf;
	}

	private JsonObject getRootObjectOfJsonString(String dataType, String data) {
		JsonParser parser = new JsonParser();
		JsonElement dataElement = null;

		if (dataType != null && !dataType.equals("message")) {
			dataElement = parser.parse(data);
			if (dataElement.isJsonPrimitive()) {
				if (!dataElement.isJsonObject() && "".equals(dataElement.getAsString())) {
					dataElement = parser.parse("{}");
				}
			}
		}
		if (dataElement == null || dataElement.isJsonNull()) {
			dataElement = parser.parse("{}");
		}

		return dataElement.getAsJsonObject();
	}

	private boolean isAcceptMode(String url) {
		if (serviceConfig == -1) {
			String res = blockChainAdapter.getIcxCall(url, IconCode.SCORE_INSTALL_ADDR.getCode(), "getServiceConfig");
			serviceConfig = HexUtil.changeHexToInteger(res);
		}
		return (serviceConfig & AUDIT_ENABLED) == 0;
	}

	/**
	 * depoly install
	 * @param i
	 * @param bf
	 * @param trf
	 * @param rootObject
	 * @return
	 * @throws Exception
	 */
	private BlockFactory installContract(String url, int i, BlockFactory bf, TxResultFactory trf, JsonObject rootObject) throws Exception {

		String txHash = bf.getTransactionList().get(i).getTxHash();
		String txFromAddr = bf.getTransactionList().get(i).getFromAddr();
		Date txCreateDate = bf.getTransactionList().get(i).getCreateDate();
		String trfScoreAddress = trf.getScoreAddress();
		boolean txStatus = trf.getStatus();
		int scoreVersion = 1;

		// score install txType
		bf.getTransactionList().get(i).setTxType(Byte.parseByte(IconCode.TX_TYPE_INSTALL.getCode()));

		if (!txStatus/* == Integer.parseInt(IconCode.TX_RESULT_FAILURE.getCode())*/) {
			// 실패 처리.
			rootObject.remove("content");
			bf.getTransactionDataList().get(i).setDataBody(rootObject.toString());
			return bf;
		}
		bf.getTransactionList().get(i).setContractAddr(trfScoreAddress);
		String contentType = rootObject.get("contentType").getAsString();
		if (isAcceptMode(url) || CONTENT_TYPE_JAVA.equals(contentType)) {
			// Auto approval or Java contract
			TContractHistory contractHistory = contractService.getContractHistoryForInsert(trfScoreAddress, scoreVersion, "-", txHash, txCreateDate, null, null, txFromAddr, Byte.parseByte(IconCode.SCORE_ACCEPT.getCode()));
			bf.getContractHistoryList().add(contractHistory);
			assignScoreApi(url, i, bf, contractHistory);
		} else {
			// 팬딩type set
			TContractHistory contractHistory = contractService.getContractHistoryForInsert(trfScoreAddress, scoreVersion, "-", txHash, txCreateDate, null, null, txFromAddr, Byte.parseByte(IconCode.SCORE_PENDING.getCode()));
			bf.getContractHistoryList().add(contractHistory);
			TContract contract = contractService.getContractForInsert(trfScoreAddress, null, scoreVersion, null, Byte.parseByte(IconCode.SCORE_PENDING.getCode()));
			bf.getContractList().add(contract);
		}
		bf.getAddressGroup().add(trfScoreAddress);

		FactoryUtil.createZipFileFromByteArrayString(
				SCORE_PATH, trfScoreAddress + "_" + scoreVersion + ".zip", rootObject.get("content").getAsString());
		rootObject.remove("content");
		bf.getTransactionDataList().get(i).setDataBody(rootObject.toString());

		return bf;
	}

	/**
	 * deploy update
	 * @param i
	 * @param bf
	 * @param trf
	 * @param rootObject
	 * @return
	 * @throws Exception
	 */
	private BlockFactory updateContract(String url, int i, BlockFactory bf, TxResultFactory trf, JsonObject rootObject) throws Exception {

		String txHash = bf.getTransactionList().get(i).getTxHash();
		String txFromAddr = bf.getTransactionList().get(i).getFromAddr();
		Date txCreateDate = bf.getTransactionList().get(i).getCreateDate();
		String trfScoreAddress = trf.getScoreAddress();
		boolean txStatus = trf.getStatus();

		// score update txType
		bf.getTransactionList().get(i).setTxType(Byte.parseByte(IconCode.TX_TYPE_UPDATE.getCode()));

		if (!txStatus/* == Integer.parseInt(IconCode.TX_RESULT_FAILURE.getCode())*/) {
			// 실패 처리.
			rootObject.remove("content");
			bf.getTransactionDataList().get(i).setDataBody(rootObject.toString());
			// history는 넣지 않는다. (version 증가 안함)
			return bf;
		}
		int updateVersion = contractService.getContractLastVersionForUpdate(trfScoreAddress);	// txToAddr = trfScoreAddress
		bf.getTransactionList().get(i).setContractAddr(trfScoreAddress);
		String contentType = rootObject.get("contentType").getAsString();
		if (isAcceptMode(url) || CONTENT_TYPE_JAVA.equals(contentType)) {
			// Auto approval or Java contract
			TContractHistory contractHistory = contractService.getContractHistoryForInsert(trfScoreAddress, updateVersion, "-", txHash, txCreateDate, txHash, txCreateDate, txFromAddr, Byte.parseByte(IconCode.SCORE_ACCEPT.getCode()));
			bf.getContractHistoryList().add(contractHistory);
			assignScoreApi(url, i, bf, contractHistory);
		} else {
			// 팬딩type set
			TContractHistory contractHistory = contractService.getContractHistoryForInsert(trfScoreAddress, updateVersion, "-", txHash, txCreateDate, null, null, txFromAddr, Byte.parseByte(IconCode.SCORE_PENDING.getCode()));
			bf.getContractHistoryList().add(contractHistory);
		}

		FactoryUtil.createZipFileFromByteArrayString(
				SCORE_PATH, trfScoreAddress + "_" + updateVersion + ".zip", rootObject.get("content").getAsString());
		rootObject.remove("content");
		bf.getTransactionDataList().get(i).setDataBody(rootObject.toString());

		return bf;
	}

	private BlockFactory acceptContract(String url, int i, BlockFactory bf, TxResultFactory trf, TContractHistory contractHistory) throws Exception {
		boolean txStatus = trf.getStatus();

		Integer historyVersion = contractHistory.getVersion();
		String historyScoreAddress = contractHistory.getContractAddr();

		// 승인 구분
		if(historyVersion.intValue() == 1) {
			bf.getTransactionList().get(i).setTxType(Byte.parseByte(IconCode.TX_TYPE_INSTALL_ACCEPT.getCode()));
		} else {
			bf.getTransactionList().get(i).setTxType(Byte.parseByte(IconCode.TX_TYPE_UPDATE_ACCEPT.getCode()));
		}
		if(!txStatus/* == Integer.parseInt(IconCode.TX_RESULT_FAILURE.getCode())*/) {
			return bf;
		}

		// getScoreApi를 이용하여 deploy 정보 set
		// accept는 historyContract를 아래 assign method에서 처리.
		// reject는 이 method 단계에서 처리.
		assignScoreApi(url, i, bf, /*trf,*/ contractHistory);

		if (!bf.getTokenInfoGroup().contains(historyScoreAddress)) {
			bf.getTokenInfoGroup().add(historyScoreAddress);
		}
		// 이전 승인 안된 deploy의 cancel 처리
		bf.getCanceledHistoryList().addAll(contractMapper.selectHistoryForCancel(contractHistory));
		return bf;
	}

	private BlockFactory rejectContract(int i, BlockFactory bf, TxResultFactory trf, TContractHistory contractHistory) throws Exception {
		boolean txStatus = trf.getStatus();

		String txHash = bf.getTransactionList().get(i).getTxHash();
		Date txCreateDate = bf.getTransactionList().get(i).getCreateDate();
		Integer historyVersion = contractHistory.getVersion();
		String historyScoreAddress = contractHistory.getContractAddr();

		// 리젝 구분
		if(historyVersion.intValue() == 1) {
			bf.getTransactionList().get(i).setTxType(Byte.parseByte(IconCode.TX_TYPE_INSTALL_REJECT.getCode()));
		} else {
			bf.getTransactionList().get(i).setTxType(Byte.parseByte(IconCode.TX_TYPE_UPDATE_REJECT.getCode()));
		}

		if(!txStatus/* == Integer.parseInt(IconCode.TX_RESULT_FAILURE.getCode())*/) {
			return bf;
		}

		byte state = Byte.parseByte(IconCode.SCORE_REJECT.getCode());
		if (historyVersion == 1) {
			bf.getContractList().add(contractService.getContractForInsert(historyScoreAddress, null, historyVersion, null, state));
		}
		// reject는 contractHistory를 여기에서 처리.
		contractHistory.setState(state);
		contractHistory.setVerifiedDate(txCreateDate);
		contractHistory.setVerifiedTx(txHash);
		bf.getContractHistoryList().add(contractHistory);
		bf.getCanceledHistoryList().addAll(contractMapper.selectHistoryForCancel(contractHistory));
		return bf;
	}

	private void assignScoreApi(String url, int i, BlockFactory bf, TContractHistory contractHistory) throws Exception {
		Integer historyVersion = contractHistory.getVersion();
		String historyScoreAddress = contractHistory.getContractAddr();

		String txHash = bf.getTransactionList().get(i).getTxHash();
		Date txCreateDate = bf.getTransactionList().get(i).getCreateDate();

		// scoreAddress 로 icx_getScoreApi 호출
		JsonArray scoreApiArray = null;

		try {
			scoreApiArray = blockChainAdapter.getIcxScoreApi(url, historyScoreAddress);
		} catch (Exception e) {
			logger.error("[TxResultInit] getIcxScoreApi scoreAddress = {}", historyScoreAddress);
			logger.error(e.getMessage());
			if (historyVersion == 1) {
				TContract contract = contractService.getContractByAddress(historyScoreAddress);
				if(contract != null) {
					contract.setState(Byte.parseByte(IconCode.SCORE_ERROR.getCode()));
					contract.setActiveSort(true);
					bf.getContractList().add(contract);
				} else {
					contract = contractService.getContractForInsert(historyScoreAddress, "-", 1, "-", Byte.parseByte(IconCode.SCORE_ERROR.getCode()));
					bf.getContractList().add(contract);
				}
			}

			contractHistory.setVerifiedTx(txHash);
			contractHistory.setVerifiedDate(txCreateDate);
			contractHistory.setState(Byte.parseByte(IconCode.SCORE_ERROR.getCode()));
			bf.getContractHistoryList().add(contractHistory);
			return;
		}

		boolean nameFlag = false;
		for (JsonElement jo : scoreApiArray) {
			if (jo.getAsJsonObject().get("name") != null) {
				String namestr = jo.getAsJsonObject().get("name").getAsString();
				if ("name".equals(namestr)) {
					nameFlag = true;
					break;
				}
			}
		}

		String scoreName = "-";
		if (nameFlag) {
			scoreName = blockChainAdapter.getIcxCall(url, historyScoreAddress, "name");
		}

		if(scoreName == null) {
			scoreName = "-";	// flag가 있는데 이름이 null인 경우가 존재...
		} else if(scoreName.length() > 256) {
			scoreName = scoreName.substring(0, 256);
		}

		String ircVersion = IRCUtil.checkIRCVersion(scoreApiArray, IRC_FILE_PATH);

		Byte state = Byte.parseByte(IconCode.SCORE_ACCEPT.getCode());
		bf.getContractList().add(contractService.getContractForInsert(
				historyScoreAddress,
				scoreName,
				historyVersion,
				ircVersion,
				state));
		contractHistory.setState(state);
		contractHistory.setVerifiedDate(txCreateDate);
		contractHistory.setVerifiedTx(txHash);
		bf.getContractHistoryList().add(contractHistory);

		// 스코어가 토큰일 경우
		if (IconCode.IRC2_TOKEN.getCode().equals(ircVersion)) {
			if(historyVersion == 1) {
				String tokenDecimals = blockChainAdapter.getIcxCall(url, historyScoreAddress, "decimals");
				if (tokenDecimals == null) {
					tokenDecimals = IconCode.TOKEN_DEFAULT_DECIMALS.getCode();
				} else if(HexUtil.changeHexToInteger(tokenDecimals) > 18) {
					// limit decimal
					tokenDecimals = IconCode.TOKEN_DEFAULT_DECIMALS.getCode();
				}
				
				String tokenSymbol = blockChainAdapter.getIcxCall(url, historyScoreAddress, "symbol");
				if(tokenSymbol == null) {
					tokenSymbol = "-";	// 토큰인데 심볼이 null인 경우가 존재...
				} else if(tokenSymbol.length() > 8) {
					tokenSymbol = tokenSymbol.substring(0, 8);
				}
				String tokenTotalSupply = blockChainAdapter.getIcxCall(url, historyScoreAddress, "totalSupply");

				tokenTotalSupply = HexUtil.applyDecimal(tokenTotalSupply, HexUtil.changeHexToInteger(tokenDecimals));

				TContract info = getContractForInsert(
						historyScoreAddress,
						historyVersion,
						scoreName,
						tokenSymbol,
						contractHistory.getCreator(),
						tokenTotalSupply,
						HexUtil.changeHexToInteger(tokenDecimals));
				bf.getContractList().add(info);

				TokenAddressGroupKey key1 = new TokenAddressGroupKey(contractHistory.getCreator(), historyScoreAddress, HexUtil.changeHexToInteger(tokenDecimals));
				if (!bf.getTokenAddressGroup().contains(key1)) {
					bf.getTokenAddressGroup().add(key1);
				}
			} else {/* 인스톨이 아닌 업데이트 토큰 승인일때 할 작업. 현재는 없다.*/}
		}
	}

	/**
	 *  EventLog만 입력 set
	 * @param i
	 * @param bf
	 * @param trf
	 * @return
	 * @throws Exception
	 */
	private BlockFactory setEventResultLog(String url, int i, BlockFactory bf, TxResultFactory trf) throws Exception {

		String txHash = bf.getTransactionList().get(i).getTxHash();
		String txToAddr = bf.getTransactionList().get(i).getToAddr();
		Date txCreateDate = bf.getTransactionList().get(i).getCreateDate();
		int blockHeight = bf.getBlockInfo().getHeight();

		List<EventLog> txEventLog = trf.getEventLogs();
		int indexCount = 0;
		for(EventLog event : txEventLog) {
			if(event.getIndexed().size() > 0) {
				String eventMethod = event.getIndexed().get(0);
				if(IconCode.SCORE_METHOD_BTP_NETWORK_OPEN.getCode().equals(eventMethod)){
					JsonObject networkInfo  = blockChainAdapter.getBtpNetworkInfo(url, event.getIndexed().get(2));

					//TODO rlp refactoring
					TBtpNetwork btpNetwork = new TBtpNetwork();
					btpNetwork.setNetworkId(networkInfo.get("networkID").getAsString());
					btpNetwork.setNetworkName(networkInfo.get("networkName").getAsString());
					btpNetwork.setNetworkTypeId(networkInfo.get("networkTypeID").getAsString());
					btpNetwork.setNetworkTypeName(networkInfo.get("networkTypeName").getAsString());
					btpNetwork.setStartHeight(Integer.decode(networkInfo.get("startHeight").getAsString()));
					bf.getBtpNetworkList().add(btpNetwork);
				}

				TTxResultLogWithBLOBs resultLog = new TTxResultLogWithBLOBs();
				resultLog.setTxHash(txHash);
				resultLog.setMethod(eventMethod);
				resultLog.setContractAddr(txToAddr);
				resultLog.setHeight(blockHeight);
				resultLog.setAge(txCreateDate);
				resultLog.setTxIndex(indexCount);
				resultLog.setEventLog(event.toString());
				bf.getTxResultLogList().add(resultLog);
			}
			indexCount++;
		}
		return bf;
	}

	private BlockFactory setBaseEventResultLog(int i, BlockFactory bf, TxResultFactory trf) throws Exception {
		String txHash = bf.getTransactionList().get(i).getTxHash();
		Date txCreateDate = bf.getTransactionList().get(i).getCreateDate();
		int blockHeight = bf.getBlockInfo().getHeight();

		List<EventLog> txEventLog = trf.getEventLogs();
		int indexCount = 0;
		for(EventLog event : txEventLog) {
			if(event.getIndexed().size() > 0) {
				String eventMethod = event.getIndexed().get(0);
				String scoreAddress = event.getScoreAddress();

				if(IconCode.SCORE_METHOD_REWARDFUND_TRANSFER.getCode().equals(eventMethod)) { // RewardFundTransferred(str,Address,Address,int)
					bf.getTransactionList().get(i).setTxType(Byte.parseByte(IconCode.TX_TYPE_ICX.getCode()));
					List<String> eventData = event.getData();
					if(eventData != null) {
						if (eventData.get(1) != null && !bf.getAddressGroup().contains(eventData.get(1))) { // from
							bf.getAddressGroup().add(eventData.get(1));
						}
						if (eventData.get(2) != null && !bf.getAddressGroup().contains(eventData.get(2))) { // to
							bf.getAddressGroup().add(eventData.get(2));
						}
					}
				}
				TTxResultLogWithBLOBs resultLog = getTxResultLogForInsert(txHash, eventMethod, scoreAddress, blockHeight, txCreateDate, indexCount, event.toString());
				bf.getTxResultLogList().add(resultLog);
			}
			indexCount++;
		}
		return bf;
	}

	/**
	 *  EventLog Token/InternalTx 입력 set
	 * @param i
	 * @param bf
	 * @param trf
	 * @return
	 * @throws Exception
	 */
	private BlockFactory setTxEventLog(String url, int i, BlockFactory bf, TxResultFactory trf) throws Exception {

		String txHash = bf.getTransactionList().get(i).getTxHash();
		String txFee = trf.getFee();
		String txToAddr = bf.getTransactionList().get(i).getToAddr();
		Date txCreateDate = bf.getTransactionList().get(i).getCreateDate();
		int blockHeight = bf.getBlockInfo().getHeight();

		List<EventLog> txEventLog = trf.getEventLogs();
		int indexCount = 0;
		int indexInternalTx = 0;
		int indexTokenTx = 0;
		for(EventLog event : txEventLog) {
			if(event.getIndexed().size() > 0) {

				String eventMethod = event.getIndexed().get(0);


				TTxResultLogWithBLOBs resultLog = getTxResultLogForInsert(txHash, eventMethod, txToAddr, blockHeight, txCreateDate, indexCount, event.toString());
				bf.getTxResultLogList().add(resultLog);

				String scoreAddress = event.getScoreAddress();


				if (IconCode.SCORE_METHOD_ICXSEND.getCode().equals(eventMethod)) {
					bf.getTransactionList().get(i).setTxType(Byte.parseByte(IconCode.TX_TYPE_CALL.getCode()));
					TInternalTx tInternalTx = getInternalTxForInsert(txHash, indexInternalTx, scoreAddress, blockHeight, txCreateDate, event.getIndexed().get(1), event.getIndexed().get(2), HexUtil.toDecimalString(event.getIndexed().get(3), DecimalType.ICX.getValue()));
					bf.getInternalTransactionList().add(tInternalTx);
					bf.getInternalTxViewList().addAll(getInternalTxViewForInsert(tInternalTx));
					indexInternalTx++;
					if (!bf.getAddressGroup().contains(tInternalTx.getFromAddr())) {
						bf.getAddressGroup().add(tInternalTx.getFromAddr());
					}
					if (!bf.getAddressGroup().contains(tInternalTx.getToAddr())) {
						bf.getAddressGroup().add(tInternalTx.getToAddr());
					}
				} else if(IconCode.SCORE_METHOD_TRANSFER.getCode().equals(eventMethod)) {
					TContract contractInfo = contractService.getContractByAddress(scoreAddress);
					if(contractInfo == null) {
						for(int idx=bf.getContractList().size()-1; idx>-1; idx--) {
							if(txToAddr.equals(bf.getContractList().get(idx).getContractAddr())) {
								contractInfo = bf.getContractList().get(idx);
								break;
							}
						}
						if(contractInfo == null) {
							logger.error("[TxResultInit] contract 정보가 없음.");
							continue;
						}
					}

					if(IconCode.IRC2_TOKEN.getCode().equals(contractInfo.getIrcVersion())) {

						TTokenTx tokenTx = getTokenTxForInsert(scoreAddress, blockHeight, txHash, indexTokenTx, txCreateDate, event.getIndexed().get(1), event.getIndexed().get(2), HexUtil.toDecimalString(event.getIndexed().get(3), contractInfo.getDecimals()), contractInfo.getIrcVersion(), txFee);
						bf.getTokenTxList().add(tokenTx);
						bf.getTokenTxViewList().addAll(getTokenTxViewForInsert(tokenTx));
						indexTokenTx++;

						TokenAddressGroupKey key1 = new TokenAddressGroupKey(tokenTx.getFromAddr(), tokenTx.getContractAddr(), contractInfo.getDecimals());
						TokenAddressGroupKey key2 = new TokenAddressGroupKey(tokenTx.getToAddr(), tokenTx.getContractAddr(), contractInfo.getDecimals());

						if (!bf.getAddressGroup().contains(tokenTx.getFromAddr())) {
							bf.getAddressGroup().add(tokenTx.getFromAddr());
						}
						if (!bf.getAddressGroup().contains(tokenTx.getToAddr())) {
							bf.getAddressGroup().add(tokenTx.getToAddr());
						}

						if (!bf.getTokenAddressGroup().contains(key1)) {
							bf.getTokenAddressGroup().add(key1);
						}
						if (!bf.getTokenAddressGroup().contains(key2) && !IconCode.SCORE_INSTALL_ADDR.getCode().equals(key2.getTokenAddr())) {
							bf.getTokenAddressGroup().add(key2);
						}
					} else {/* method가 tokenTransfer인데 score가 IRC2가 아닌경우 */}
				} else if(IconCode.SCORE_METHOD_BTP_MESSAGE.getCode().equals(eventMethod)){ /* btp message */
					TBtpHeader btpHeader = addBtpHeader(url, event.getIndexed().get(1), bf);
					if(btpHeader != null){
						bf.getBtpHeaderMap().put(event.getIndexed().get(1), btpHeader);
					}

					bf.getTransactionList().get(i).setBtpHeaderBlockHeight(bf.getBlockInfo().getHeight());
					bf.getTransactionList().get(i).setBtpHeaderNetworkId(event.getIndexed().get(1));
					bf.getTransactionList().get(i).setBtpMessageSn(Integer.decode(event.getIndexed().get(2)));

				} else {/* method가 icxTransfer/tokenTransfer 둘 다 아닌 경우 */}
			}
			indexCount++;
		}
		return bf;
	}

	private TBtpHeader addBtpHeader(String url, String networkId, BlockFactory bf) throws Exception {
		TBtpHeader btpHeader = null;
		if(!bf.getBtpHeaderMap().containsKey(networkId)){
			String strBtpBlock = blockChainAdapter.getBtpHeader(url, networkId, bf.getBlockInfo().getHeight()+1);
			RlpList btpBlock = RlpDecoder.decode(Base64.getDecoder().decode(strBtpBlock));
			List<RlpType> rlpList = ((RlpList)(btpBlock.getValues().get(0))).getValues();

			//TODO rlp refactoring
			btpHeader = new TBtpHeader();
			btpHeader.setBlockHeight(Integer.decode(((RlpString) rlpList.get(0)).asString()));
			btpHeader.setNetworkId(networkId);
			btpHeader.setUpdateNumber(((RlpString) rlpList.get(5)).asString());
			btpHeader.setPrev(((RlpString) rlpList.get(6)).asString());
			btpHeader.setMessageCnt(Integer.decode(((RlpString) rlpList.get(7)).asString()));
			btpHeader.setMessageRoot(((RlpString) rlpList.get(8)).asString());
			btpHeader.setCreateDate(bf.getBlockInfo().getCreateDate());
			btpHeader.setBtpNetworkNetworkId(networkId);
		}


		return btpHeader;
	}


	private BlockFactory addTransactionView(BlockFactory bf, int i) throws Exception {
		String txHash = bf.getTransactionList().get(i).getTxHash();
		String txToAddr = bf.getTransactionList().get(i).getToAddr();
		String txFromAddr = bf.getTransactionList().get(i).getFromAddr();
		String contractAddr = bf.getTransactionList().get(i).getContractAddr();
		Integer height = bf.getTransactionList().get(i).getHeight();
		Byte txType = bf.getTransactionList().get(i).getTxType();

		// txView set
		if(txToAddr != null) {
			if(!txToAddr.equals(txFromAddr)) {
				TTransactionView toView = new TTransactionView();
				toView.setAddress(txToAddr);
				toView.setTxHash(txHash);
				toView.setHeight(height);
				toView.setAddressType((byte) 1);
				toView.setTxType(txType);
				bf.getTransactionViewList().add(toView);
			}
		}
		if(txFromAddr != null) {
			TTransactionView fromView = new TTransactionView();
			fromView.setTxHash(txHash);
			fromView.setHeight(height);
			fromView.setAddress(txFromAddr);
			fromView.setTxType(txType);
			if(txFromAddr.equals(txToAddr)){
				fromView.setAddressType((byte)2);
				bf.getTransactionViewList().add(fromView);
			} else {
				fromView.setAddressType((byte) 0);
				bf.getTransactionViewList().add(fromView);
			}
		}
		// contractTx조회용으로도 view 추가
		if(contractAddr != null) {
			if((!contractAddr.equals(txToAddr))&&(!contractAddr.equals(txFromAddr))) {
				TTransactionView contractView = new TTransactionView();
				contractView.setAddress(contractAddr);
				contractView.setTxHash(txHash);
				contractView.setHeight(height);
				contractView.setTxType(txType);
				bf.getTransactionViewList().add(contractView);
			}
		}
		return bf;
	}

	private TTxResultLogWithBLOBs getTxResultLogForInsert(String txHash, String method, String contractAddr, Integer height, Date age, Integer txIndex, String eventLog) {
		TTxResultLogWithBLOBs resultLog = new TTxResultLogWithBLOBs();
		resultLog.setTxHash(txHash);
		resultLog.setMethod(method);
		resultLog.setContractAddr(contractAddr);
		resultLog.setHeight(height);
		resultLog.setAge(age);
		resultLog.setTxIndex(txIndex);
		resultLog.setEventLog(eventLog);
		return resultLog;
	}

	private TContract getContractForInsert(
			String contractAddr,
			Integer version,
			String name,
			String symbol,
			String holderAddr,
			String totalSupply,
			Integer decimals
	) {
		TContract contract = new TContract();
		contract.setContractAddr(contractAddr);
		contract.setVersion(version);
		contract.setName(name);
		contract.setSymbol(symbol);
		contract.setHolderAddr(holderAddr);
		contract.setTotalSupply(totalSupply);
		contract.setDecimals(decimals);
		if("-".equals(name)) {
			contract.setNameSort(true);
		} else {
			contract.setNameSort(false);
		}

		return contract;
	}

	public TTokenTx getTokenTxForInsert(String contractAddr, Integer height, String txHash, Integer txIndex, Date age, String fromAddr, String toAddr, String quantity, String ircVersion, String fee) {
		TTokenTx tokenTx = new TTokenTx();
		tokenTx.setContractAddr(contractAddr);
		tokenTx.setBlockHeight(height);
		tokenTx.setTxHash(txHash);
		tokenTx.setTxIndex(txIndex);
		tokenTx.setAge(age);
		tokenTx.setFromAddr(fromAddr);
		tokenTx.setToAddr(toAddr);
		tokenTx.setQuantity(quantity);
		tokenTx.setIrcVersion(ircVersion);
		tokenTx.setFee(fee);

		return tokenTx;
	}

	public List<TTokenTxView> getTokenTxViewForInsert(TTokenTx tx) {
		List<TTokenTxView> viewList = new ArrayList<>();

		TTokenTxView txView = new TTokenTxView();
		txView.setBlockHeight(tx.getBlockHeight());
		txView.setTxHash(tx.getTxHash());
		txView.setTxIndex(tx.getTxIndex());
		txView.setContractAddr(tx.getContractAddr());
		txView.setAddress(tx.getFromAddr());
		txView.setAddressType(false);
		viewList.add(txView);
		if(!tx.getToAddr().equals(tx.getFromAddr())) {
			TTokenTxView txView2 = new TTokenTxView();
			txView2.setBlockHeight(tx.getBlockHeight());
			txView2.setTxHash(tx.getTxHash());
			txView2.setTxIndex(tx.getTxIndex());
			txView2.setContractAddr(tx.getContractAddr());
			txView2.setAddressType(true);
			txView2.setAddress(tx.getToAddr());
			viewList.add(txView2);
		}
		return viewList;
	}

	private TInternalTx getInternalTxForInsert(String txHash, Integer txIndex, String contractAddr, Integer height, Date createDate, String fromAddr, String toAddr, String amount) {
		TInternalTx tInternalTx = new TInternalTx();
		tInternalTx.setParentTxHash(txHash);
		tInternalTx.setTxIndex(txIndex);
		tInternalTx.setContractAddr(contractAddr);
		tInternalTx.setHeight(height);
		tInternalTx.setCreateDate(createDate);
		tInternalTx.setFromAddr(fromAddr);
		tInternalTx.setToAddr(toAddr);
		tInternalTx.setAmount(amount);
		return tInternalTx;
	}

	private List<TInternalTxView> getInternalTxViewForInsert(TInternalTx tx) {
		List<TInternalTxView> viewList = new ArrayList<>();
		TInternalTxView txView = new TInternalTxView();
		txView.setParentTxHash(tx.getParentTxHash());
		txView.setTxIndex(tx.getTxIndex());
		txView.setAddress(tx.getFromAddr());
		txView.setAddressType(false);
		viewList.add(txView);
		if(!tx.getToAddr().equals(tx.getFromAddr())) {
			TInternalTxView txView2 = new TInternalTxView();
			txView2.setParentTxHash(tx.getParentTxHash());
			txView2.setTxIndex(tx.getTxIndex());
			txView2.setAddress(tx.getToAddr());
			txView2.setAddressType(true);
			viewList.add(txView2);
		}
		return viewList;
	}
}