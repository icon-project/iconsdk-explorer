package com.dfg.icon.core.v3.service.impl;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dfg.icon.core.common.service.ResourceService;
import com.dfg.icon.core.dao.icon.*;
import com.dfg.icon.core.mappers.icon.*;
import com.dfg.icon.core.v3.service.*;
import com.dfg.icon.core.v3.vo.*;
import com.dfg.icon.util.DateUtil;
import com.dfg.icon.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.dfg.icon.core.v0.service.V0MainService;
import com.dfg.icon.core.v2.vo.GenesisAccVo;
import com.dfg.icon.core.v3.adapter.V3BlockChainAdapter;
import com.dfg.icon.util.HexUtil;

/**
 * The type V 3 block chain service.
 */
@Service
public class V3BlockChainServiceImpl implements V3BlockChainService {

	private static final Logger logger = LoggerFactory.getLogger(V3BlockChainServiceImpl.class);
	private static final Logger sLogger = LoggerFactory.getLogger("ScheduleLogger");

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
	 * The V 3 chain state.
	 */
	@Autowired
	V3ChainState chainState;

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

	@Autowired
	V3TxResultService txResultService;

	@Autowired
	ResourceService resourceService;

	@Autowired
	TAddressHistoryMapper tAddressHistoryMapper;

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


	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public void blockLimit(Integer limit) {
		if(limit == null) {
			return;
		}
		long count = getBlockCount();
		if (limit.longValue() < count) {
			sLogger.info("block limit over : {}", count - limit.longValue());
			TBlock block = getDateRowForDeleteInBlock(limit - 1);
			if (block != null) {
				TBlockExample example = new TBlockExample();
				example.createCriteria().andHeightLessThan(block.getHeight());
				tBlockMapper.deleteByExample(example);
			}
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public void transactionLimit(Integer limit) {
		if(limit == null) {
			return;
		}
//		String height = transactionMapper.selectCreateDateByLimit(limit);
//		if(height != null && !height.equals("")){
//			transactionMapper.deleteCreateDateByLimit(height);
//		}
		TTransaction limitTx = transactionMapper.selectTransactionByLimit(limit);
		if(limitTx != null) {
//			sLogger.info("delete limite transaction until {}, {}", limitTx.getHeight(), limitTx.getCreateDate());
			transactionMapper.deleteTransactionByLimit(limitTx);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public void addressLimit(Integer limit) {
		if(limit == null) {
			return;
		}
		long count = getAddressCount();
		if (limit.longValue() < count) {
			sLogger.info("address limit over : {}", count - limit.longValue());
			TAddress address = getBalanceRowForDelete(limit - 1);
			if (address != null) {
				addressMapper.deleteAddressByBalanceLessThan(address.getBalance());
			}
		}
	}

	/**
	 * main view update
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public void updateMain() throws Exception {
		v0MainService.updateMainBlock();
		//v0MainService.updateMainChart();
		v0MainService.updateMainTx();
	}

	/**
	 * mainChart 14days full update
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public void updateChart() throws Exception {
		//v0MainService.updateMainChartDaily();

		// 15일 이전 데이터를 삭제
		mainMapper.deleteOldDataDaliyChart();

		// 전일 데이터를 입력
		mainMapper.insertNewChartData();

	}

	/**
	 * 유효한 balance address 집계
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public void collectBalanceAddress() throws Exception {

		TAddressHistory history = new TAddressHistory();

		Date now = DateUtil.getNowDate();
		history.setCreateDate(now);
		history.setStandardDate(now);

		TAddressHistoryExample historyExample = new TAddressHistoryExample();
		historyExample.createCriteria().andStandardDateEqualTo(history.getStandardDate());
		if(tAddressHistoryMapper.countByExample(historyExample) == 0) {
			TAddressTotalExample example = new TAddressTotalExample();
			history.setTotalAddressCount((int) tAddressTotalMapper.countByExample(example));
			history.setBalanceAddressCount(addressMapper.selectBalanceAddressCount());
			tAddressHistoryMapper.insert(history);
		}
	}

	/**해당 높이의 블록 데이터를 조회 
	 * @param height
	 * @return
	 */
	private TBlock getBlockByHeightInDB(Integer height) {
		if(height == null) {
			return null;
		}
		TBlockKey key = new TBlock();
		key.setHeight(height);
		return tBlockMapper.selectByPrimaryKey(key);
	}


	/**
	 * 블록정보 및 트랜잭션 데이터를 입력 
	 * @param bfList
	 * @return
	 * @throws Exception
	 */
	private int insertBlockFactory(String url, List<BlockFactory> bfList) throws Exception {

		int txCount = getSumBlockTxCount(bfList);
		int startHeight = bfList.get(0).getBlockInfo().getHeight();
		int endHeight = bfList.get(bfList.size()-1).getBlockInfo().getHeight();

		long startTime = System.currentTimeMillis();
		long middleTime = startTime;

		List<TTransactionTotalWithBLOBs> txList = new ArrayList<>();
		List<TTxDataWithBLOBs> txDataList = new ArrayList<>();
		List<TContract> contractList = new ArrayList<>();
		List<TContractHistory> contractHistoryList = new ArrayList<>();
		List<TContractHistory> canceledHistoryList = new ArrayList<>();
		List<TTokenTx> tokenTxList = new ArrayList<>();
		List<TTxResultLogWithBLOBs> txResultLogList = new ArrayList<>();
		List<TTokenAddress> tokenAddressList = new ArrayList<>();
		List<TAddressTotal> addressList = new ArrayList<>();
		List<TInternalTx> tInternalTxList = new ArrayList<>();
		List<TInternalTxView> tInternalTxViewList = new ArrayList<>();
		Set<String> tokenInfoGroup = new HashSet<>();
		List<TTransactionView> txViewList = new ArrayList<>();
		List<TTokenTxView> tokenTxViewList = new ArrayList<>();
		List<TBlockTotal> blockList = new ArrayList<>();
		Set<String> addressGroupList = new HashSet<>();
		List<TokenAddressGroupKey> tokenAddressGroupList = new ArrayList<>();
		List<TBalanceQueue> stakeList = new ArrayList<>();
		List<TFeeDetails> feeDetailsList = new ArrayList<>();

		TAddressTotal treasuryTotal = getTreasuryAddressTotal();
		if(treasuryTotal == null) {
			treasuryTotal = addressService.initTreasuryTotal(url);
			tAddressTotalMapper.insert(treasuryTotal);
		}
		addressGroupList.add(treasuryTotal.getAddress());

		for(BlockFactory bf : bfList) {
			txList.addAll(bf.getTransactionList());
			txDataList.addAll(bf.getTransactionDataList());
			contractList.addAll(bf.getContractList());
			contractHistoryList.addAll(bf.getContractHistoryList());
			canceledHistoryList.addAll(bf.getCanceledHistoryList());
			tokenTxList.addAll(bf.getTokenTxList());
			txResultLogList.addAll(bf.getTxResultLogList());
			if(bf.getFeeDetailList() != null) {
				feeDetailsList.addAll(bf.getFeeDetailList());
			}

//			for (String addr : bf.getAddressGroup()) {
//				if(!addressGroupList.contains(addr)) {
//					addressGroupList.add(addr);
//				}
//			}
			addressGroupList.addAll(bf.getAddressGroup());
			for (TokenAddressGroupKey key : bf.getTokenAddressGroup()) {
				if(!tokenAddressGroupList.contains(key)) {
					tokenAddressGroupList.add(key);
				}
			}
			tInternalTxList.addAll(bf.getInternalTransactionList());
			tInternalTxViewList.addAll(bf.getInternalTxViewList());
			tokenInfoGroup.addAll(bf.getTokenInfoGroup());
			txViewList.addAll(bf.getTransactionViewList());
			tokenTxViewList.addAll(bf.getTokenTxViewList());
			blockList.add(bf.getBlockInfo());
			for(String address : bf.getStakeGroup()) {
				RpcStakeRes res = blockChainAdapter.getStake(url, address);
				// Updated by chanwo on 2020-07-06
//				if(res != null && res.getResult() != null &&
//						res.getResult().getUnstakeBlockHeight() != null) {
//					Integer unStakeHeight = HexUtil.changeStringToInteger(res.getResult().getUnstakeBlockHeight());
//					if(endHeight < unStakeHeight) {
//						// 현재의 endHeight 보다 이후 정산인 경우에 한해 queue에 입력.
//						TBalanceQueue balanceQueue = new TBalanceQueue();
//						balanceQueue.setAddress(address);
//						balanceQueue.setHeight(unStakeHeight);
//						stakeList.add(balanceQueue);
//					}
//				}
				if(res != null && res.getResult() != null) {
					if (res.getResult().getUnstakeBlockHeight() != null) {
						Integer unStakeHeight = HexUtil.changeStringToInteger(res.getResult().getUnstakeBlockHeight());
						if(endHeight < unStakeHeight) {
							// 현재의 endHeight 보다 이후 정산인 경우에 한해 queue에 입력.
							TBalanceQueue balanceQueue = new TBalanceQueue();
							balanceQueue.setAddress(address);
							balanceQueue.setHeight(unStakeHeight);
							stakeList.add(balanceQueue);
						}
					} else if(res.getResult().getUnstakes() != null) {
						for(RpcUnstakeResult unstakeRes : res.getResult().getUnstakes()) {
							if(unstakeRes.getUnstakeBlockHeight() == null) continue;
							Integer unStakeHeight = HexUtil.changeStringToInteger(unstakeRes.getUnstakeBlockHeight());
							if(endHeight < unStakeHeight) {
								TBalanceQueue balanceQueue = new TBalanceQueue();
								balanceQueue.setAddress(address);
								balanceQueue.setHeight(unStakeHeight);
								stakeList.add(balanceQueue);
							}
						}
					}
				}
				// fin. Updated by chanwo on 2020-07-06
			}
		}

		// token getbalance
		for(TokenAddressGroupKey key : tokenAddressGroupList) {
			TTokenAddress tokenAddress = new TTokenAddress();
			tokenAddress.setAddress(key.getTokenAddr());
			tokenAddress.setContractAddr(key.getContractAddr());
			tokenAddress.setQuantity(getTokenBalance(key.getTokenAddr(), key.getContractAddr(), key.getDecimals()));
			tokenAddressList.add(tokenAddress);
		}


		long balanceTime = System.currentTimeMillis();
		// icx getbalance
		Set<String> whiteList = addressService.getWhiteAddressList();
		for(String addr : addressGroupList) {
			TAddressTotal addrTotal = new TAddressTotal();
			addrTotal.setAddress(addr);
			if(Validator.isAddressPattern(addr)) {
				if(whiteList.contains(addr)) {
					String balance = getBalance(url, addr, DecimalType.ICX.getValue());
					RpcStakeRes stakeRes = blockChainAdapter.getStake(url, addr);
					if(stakeRes != null && stakeRes.getResult() != null) {
						String stakeBalance = HexUtil.toDecimalString(stakeRes.getResult().getStake(), DecimalType.ICX.getValue());
						String unstakeBalance = HexUtil.toDecimalString(stakeRes.getResult().getUnstake(), DecimalType.ICX.getValue());
						// Updated by chanwo on 2020-07-06
						if(stakeRes.getResult().getUnstakes() != null) {
							for(RpcUnstakeResult unstakeRes : stakeRes.getResult().getUnstakes()) {
								unstakeBalance = HexUtil.decimalPlusHex(unstakeBalance, unstakeRes.getUnstake(), DecimalType.ICX.getValue());
							}
						}
						// fin. Updated by chanwo on 2020-07-06
						
						balance = HexUtil.decimalPlusDecimal(balance, stakeBalance, DecimalType.ICX.getValue());
						balance = HexUtil.decimalPlusDecimal(balance, unstakeBalance, DecimalType.ICX.getValue());
						addrTotal.setBalance(balance);
					} else {
						addrTotal.setBalance(balance);
					}
				} else {
					addrTotal.setBalance(getBalance(url, addr, DecimalType.ICX.getValue()));
				}
			} else {
				// 엔진에서 getBalance 주소형식을 체크하도록 되어서, 일부 주소 문제때문에 적용.
				addrTotal.setBalance("0");
			}
			addrTotal.setBalanceOrder(new BigDecimal(addrTotal.getBalance()));
			addressList.add(addrTotal);
		}

//		if(resourceService.isLogSpeed()) {
			sLogger.info("[Speed] getBalanceTime {} addr, {}s",
					addressGroupList.size(),
					(System.currentTimeMillis() - balanceTime )/1000.0f);
			sLogger.info("[Speed] Insert Array preWork {}s ",
					(System.currentTimeMillis() - middleTime )/1000.0f);
			middleTime = System.currentTimeMillis();
//		}
		long startInsertTime = System.currentTimeMillis();
		if(feeDetailsList.size() > 0) {
			transactionMapper.insertFeeDetails(feeDetailsList);
			if (resourceService.isLogSpeed()) {
				sLogger.info("[InsertSpeed] feeDetails {}, {}s, {}s",
						feeDetailsList.size(),
						(System.currentTimeMillis() - middleTime) / 1000.0f,
						(System.currentTimeMillis() - startTime) / 1000.0f);
				middleTime = System.currentTimeMillis();
			}
		}
		if(txList.size() > 0) {
			transactionMapper.insertTxArray(txList);
			if (resourceService.isLogSpeed()) {
				sLogger.info("[InsertSpeed] tx {}, {}s, {}s",
						txList.size(),
						(System.currentTimeMillis() - middleTime) / 1000.0f,
						(System.currentTimeMillis() - startTime) / 1000.0f);
				middleTime = System.currentTimeMillis();
			}
			transactionMapper.insertTxTotalArray(txList);
			if (resourceService.isLogSpeed()) {
				sLogger.info("[InsertSpeed] txTotal {}, {}s, {}s",
						txList.size(),
						(System.currentTimeMillis() - middleTime) / 1000.0f,
						(System.currentTimeMillis() - startTime) / 1000.0f);
				middleTime = System.currentTimeMillis();
			}
		}
		if(txViewList.size() > 0) {
			transactionMapper.insertTxViewArray(txViewList);
			if (resourceService.isLogSpeed()) {
				sLogger.info("[InsertSpeed] txView {}, {}s, {}s",
						txViewList.size(),
						(System.currentTimeMillis() - middleTime) / 1000.0f,
						(System.currentTimeMillis() - startTime) / 1000.0f);
				middleTime = System.currentTimeMillis();
			}
		}
		// txData 정보 등록
		if(txDataList.size() > 0) {
			transactionMapper.insertTxDataArray(txDataList);
			if(resourceService.isLogSpeed()) {
				sLogger.info("[InsertSpeed] txData {}, {}s, {}s",
						txDataList.size(),
						(System.currentTimeMillis() - middleTime )/1000.0f,
						(System.currentTimeMillis() - startTime )/1000.0f);
				middleTime = System.currentTimeMillis();
			}
		}

		//contract insert/update
		if(contractList.size() > 0) {
			contractMapper.insertContractArray(contractList);
			if (resourceService.isLogSpeed()) {
				sLogger.info("[InsertSpeed] contract {}, {}s, {}s",
						contractList.size(),
						(System.currentTimeMillis() - middleTime) / 1000.0f,
						(System.currentTimeMillis() - startTime) / 1000.0f);
				middleTime = System.currentTimeMillis();
			}
		}


		if (contractHistoryList.size() > 0) {
			contractMapper.insertContractHistoryArray(contractHistoryList);
			if(resourceService.isLogSpeed()) {
				sLogger.info("[InsertSpeed] contractHistory {}, {}s, {}s",
						contractHistoryList.size(),
						(System.currentTimeMillis() - middleTime )/1000.0f,
						(System.currentTimeMillis() - startTime )/1000.0f);
				middleTime = System.currentTimeMillis();
			}
		}

		// token transaction
		if(tokenTxList.size() > 0) {
			tokenMapper.insertTokenTxArray(tokenTxList);
			if(resourceService.isLogSpeed()) {
				sLogger.info("[InsertSpeed] tokenTx {}, {}s, {}s",
						tokenTxList.size(),
						(System.currentTimeMillis() - middleTime )/1000.0f,
						(System.currentTimeMillis() - startTime )/1000.0f);
				middleTime = System.currentTimeMillis();
			}
		}
		if(tokenTxViewList.size() > 0) {
			transactionMapper.insertTxTokenViewArray(tokenTxViewList);
			if(resourceService.isLogSpeed()) {
				sLogger.info("[InsertSpeed] tokenTxView {}, {}s, {}s",
						tokenTxViewList.size(),
						(System.currentTimeMillis() - middleTime )/1000.0f,
						(System.currentTimeMillis() - startTime )/1000.0f);
				middleTime = System.currentTimeMillis();
			}
		}

		// event log
		if( txResultLogList != null &&  txResultLogList.size() > 0) {
			tokenMapper.insertTxResultLogArray(txResultLogList);
			if(resourceService.isLogSpeed()) {
				sLogger.info("[InsertSpeed] txResultLog {}, {}s, {}s",
						txResultLogList.size(),
						(System.currentTimeMillis() - middleTime )/1000.0f,
						(System.currentTimeMillis() - startTime )/1000.0f);
				middleTime = System.currentTimeMillis();
			}
		}

		// 토큰 잔액'만' 적용
		if(tokenAddressList.size() > 0) {
			addressMapper.upsertTokenAddressArray(tokenAddressList);
			if(resourceService.isLogSpeed()) {
				sLogger.info("[UpdateSpeed] tokenAddress quantity {}, {}s, {}s",
						tokenAddressList.size(),
						(System.currentTimeMillis() - middleTime )/1000.0f,
						(System.currentTimeMillis() - startTime )/1000.0f);
				middleTime = System.currentTimeMillis();
			}
		}

		// 주소 잔액'만' 적용
		if(addressList.size() > 0)  {
			addressMapper.upsertAddressArray(addressList);
			if(resourceService.isLogSpeed()) {
				sLogger.info("[UpdateSpeed] address balance {}, {}s, {}s",
						addressList.size(),
						(System.currentTimeMillis() - middleTime )/1000.0f,
						(System.currentTimeMillis() - startTime )/1000.0f);
				middleTime = System.currentTimeMillis();
			}
		}

		// internal Transaction
		if(tInternalTxList.size() > 0) {
			transactionMapper.insertInternalTx(tInternalTxList);
			if(resourceService.isLogSpeed()) {
				sLogger.info("[InsertSpeed] internalTx {}s, {}s",
						(System.currentTimeMillis() - middleTime )/1000.0f,
						(System.currentTimeMillis() - startTime )/1000.0f);
				middleTime = System.currentTimeMillis();
			}
		}

		// internal Transaction View
		if(tInternalTxViewList.size() > 0) {
			transactionMapper.insertInternalTxView(tInternalTxViewList);
			if(resourceService.isLogSpeed()) {
				sLogger.info("[InsertSpeed] internalTxView {}s, {}s",
						(System.currentTimeMillis() - middleTime )/1000.0f,
						(System.currentTimeMillis() - startTime )/1000.0f);
				middleTime = System.currentTimeMillis();
			}
		}

		// accept/reject 되지 못한 transaction's txType to cancel(9)
		for(TContractHistory history : canceledHistoryList) {
			contractMapper.updateHistoryToCancel(history);
			contractMapper.updateContractTxToCancel(history);
			transactionMapper.updateTxTotalCancel(history);
			transactionMapper.updateTxCancel(history);
			transactionMapper.updateTxViewCancel(history);
		}
		if(resourceService.isLogSpeed()) {
			sLogger.info("[UpdateSpeed] ContractHistoryCancel {}, {}s, {}s",
					canceledHistoryList.size(),
					(System.currentTimeMillis() - middleTime )/1000.0f,
					(System.currentTimeMillis() - startTime )/1000.0f);
			middleTime = System.currentTimeMillis();
		}

		// txcount 증가 적용
		addressMapper.upsertTokenTxCountByTokenTransactionRange(startHeight, endHeight);
		if(resourceService.isLogSpeed()) {
			sLogger.info("[UpdateSpeed] tokenAddress txCount Refresh {}s, {}s",
					(System.currentTimeMillis() - middleTime )/1000.0f,
					(System.currentTimeMillis() - startTime )/1000.0f);
			middleTime = System.currentTimeMillis();
		}
		addressMapper.upsertTxCountByTransactionRange(startHeight, endHeight);
		if(resourceService.isLogSpeed()) {
			sLogger.info("[UpdateSpeed] address txCount Refresh {}s, {}s",
					(System.currentTimeMillis() - middleTime )/1000.0f,
					(System.currentTimeMillis() - startTime )/1000.0f);
			middleTime = System.currentTimeMillis();
		}

		// TAddress update
		addressMapper.updateAddressByTotal();
		addressMapper.initUpdateAddressTotal();
		if(resourceService.isLogSpeed()) {
			sLogger.info("[UpdateSpeed] addressTotal To Address {}s, {}s",
					(System.currentTimeMillis() - middleTime )/1000.0f,
					(System.currentTimeMillis() - startTime )/1000.0f);
			middleTime = System.currentTimeMillis();
		}

		// 토큰 컨트랙트의 홀더, 트랜잭션 카운트 업데이트
		if(tokenInfoGroup != null && tokenInfoGroup.size() > 0) {
			tokenMapper.updateTokenInfoHolderCountAndTxCountRange(startHeight, endHeight);
			if(resourceService.isLogSpeed()) {
				sLogger.info("[UpdateSpeed] tokenHolder&tokenTxCount {}s, {}s",
						(System.currentTimeMillis() - middleTime )/1000.0f,
						(System.currentTimeMillis() - startTime )/1000.0f);
				middleTime = System.currentTimeMillis();
			}
		}

		// 블록 정보 등록
		blockMapper.insertBlockArray(blockList);
		blockMapper.insertBlockTotalArray(blockList);
		if(resourceService.isLogSpeed()) {
			sLogger.info("[InsertSpeed] block&blockTotal {}, {}s, {}s",
					blockList.size(),
					(System.currentTimeMillis() - middleTime )/1000.0f,
					(System.currentTimeMillis() - startTime )/1000.0f);
			middleTime = System.currentTimeMillis();
		}

		if(resourceService.isLogSpeed()) {
			sLogger.info("[UpdateSpeed] address prep blockCount Refresh {}s, {}s",
					(System.currentTimeMillis() - middleTime )/1000.0f,
					(System.currentTimeMillis() - startTime )/1000.0f);
			middleTime = System.currentTimeMillis();
		}

		sLogger.info("###DB checkSpeed InsertBlock={}, InsertTx={}, time={}",
				bfList.size(), txCount, (System.currentTimeMillis()-startInsertTime)/1000.0f );

		// 메인 블록 정보 저장
		int blockListSize = blockList.size();
		int startindex = 0;
		int endindex = blockListSize;
		if(blockListSize > 20 ) {
			startindex = endindex - 20;
		}

		List<TBlockTotal>  top20BlockList = blockList.subList(startindex, endindex);
		List<TMainBlock> mainBlockList = new ArrayList<TMainBlock>();
		for (TBlockTotal block : top20BlockList) {
			mainBlockList.add(setMainBlockToTotal(block));
		}
		mainMapper.insertMainBlockList(mainBlockList);

		if(speedLogFlag) sLogger.info("###DB checkSpeed tMainBlockMapper.insert = {}",
				(  System.currentTimeMillis() - startInsertTime )/1000.0f);

		// 메인 Tx 정보 저장
		int txListSize = txList.size();
		startindex = 0;
		endindex = txListSize;
		if(txListSize > 20 ) {
			startindex = endindex - 20;
		}
		List<TTransactionTotalWithBLOBs>  top20TxList = txList.subList(startindex, endindex);
		List<TMainTx> mainTxList = new ArrayList<TMainTx>();
		for (TTransactionTotalWithBLOBs tx : top20TxList) {
			mainTxList.add(setTransactionToTotal(tx));
		}
		if(mainTxList.size() > 0) {
			mainMapper.insertMainTxList(mainTxList);
			if(speedLogFlag) sLogger.info("###DB checkSpeed insertMainTxList = {}",
					( System.currentTimeMillis() - startInsertTime )/1000.0f);
		}
		v0MainService.updateMainInfo(url, txList.size(), getTreasuryAddressTotal().getBalance());

		sLogger.info("###CHAIN checkSpeed cycleEnd  = blockCount {}, {}->{} txCount {}, DB {}s, time: {}s",
				bfList.size(), startHeight, endHeight, txCount,
				(System.currentTimeMillis() - startInsertTime) / 1000.0f,
				(System.currentTimeMillis() - startTime) / 1000.0f);


		return bfList.size();
	}

	/**주소의 자리수가 40이고 16진수의 데이터일 경우 앞에 hx를추가함 
	 * @param addr
	 * @return
	 */
	private String checkAddress(String addr) {

		Pattern p = Pattern.compile("^[A-Fa-f0-9]{40}$");
		Matcher m =  p.matcher(addr);
		if(m.find()) {
			return "hx" + addr;
		}
		return addr;
	}


	private long getBlockCount() {
		return tBlockMapper.countByExample(new TBlockExample());
	}

	private long getTransactionCount() {
		return tTransactionMapper.countByExample(new TTransactionExample());
	}

	private long getAddressCount() {
		return tAddressMapper.countByExample(new TAddressExample());
	}

	/**  tx 테이블 삭제할 경계 row 조회
	 * @param limit
	 * @return
	 */
	private TBlock getDateRowForDeleteInBlock(Integer limit) {
		TBlockExample example = new TBlockExample();
		example.createCriteria();
		example.setOrderByClause("height desc limit " + limit + ", 1");
		List<TBlock> block = tBlockMapper.selectByExample(example);
		if(block != null && block.size()>0) {
			return block.get(0);
		} else {
			return null;
		}
	}

	/** get row address for delete
	 * @param limit
	 * @return
	 */
	private TAddress getBalanceRowForDelete(Integer limit) {
		TAddressExample example = new TAddressExample();
		example.createCriteria();
		example.setOrderByClause("balance_order desc limit " + limit + ", 1");
		List<TAddress> address = tAddressMapper.selectByExample(example);
		if(address != null && address.size() > 0) {
			return address.get(0);
		} else {
			return null;
		}
	}

	/** blockTotal setter
	 * @param block
	 * @return
	 */
	private TMainBlock setMainBlockToTotal(TBlockTotal block) {
		TMainBlock tMainBlock = new TMainBlock();
		tMainBlock.setBlockHeight(block.getHeight());
		tMainBlock.setHash(block.getHash());
		tMainBlock.setTxCount(block.getTxCount());
		tMainBlock.setCreateDate(block.getCreateDate());
		return tMainBlock;
	}

	/** txTotal setter
	 * @param tx
	 * @return
	 */
	private TMainTx setTransactionToTotal(TTransactionTotalWithBLOBs tx) {
		TMainTx mainTx = new TMainTx();
		mainTx.setTxHash(tx.getTxHash());
		mainTx.setAmount(tx.getAmount());
		mainTx.setFee(tx.getFee());
		mainTx.setCreateDate(tx.getCreateDate());
		mainTx.setState(tx.getState());
		return mainTx; 
	}

	private TAddressTotal getTreasuryAddressTotal() {
		TAddressTotalExample example = new TAddressTotalExample();
		example.createCriteria().andNodeTypeEqualTo("Treasury");
		List<TAddressTotal> treasury = tAddressTotalMapper.selectByExample(example);
		if(treasury != null && treasury.size() > 0) {
			return treasury.get(0);
		} else {
			return null;
		}
	}

	// only used once in genesis
	//@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	private int insertGenesis(String url, GenesisRpcResult rpcResult, Integer blockSize) throws Exception {
		String msg = rpcResult.getConfrimedTransactionList().get(0).getMessage();
		String genesisFeeSum = "0";
		int txCount = 0;
		String treasury = null;
		List<TTransactionView> viewList = new ArrayList<>();
		List<TTransactionTotalWithBLOBs> txList = new ArrayList<>();
		List<TBlockTotal> blockList = new ArrayList<>();
		for(GenesisAccVo vo : rpcResult.getConfrimedTransactionList().get(0).getAccounts()) {
			TAddress address = null;
			TAddressTotal addressTotal = null;

			address = new TAddress();
			address.setAddress(vo.getAddress());
			address.setBalance(HexUtil.toDecimalString(vo.getBalance(), DecimalType.ICX.getValue()));
			address.setBalanceOrder(new BigDecimal(address.getBalance()));

			if(vo.getName().equals("god")) {
				address.setNodeType("Genesis");
			} else if(vo.getName().equals("treasury") || vo.getName().equals("fee_treasury")) {
				address.setNodeType("Treasury");
				treasury = address.getBalance();
			}

			if("0".equals(address.getBalance())) {
				address.setTxCount(0);
			} else {
				address.setTxCount(1);
				genesisFeeSum = HexUtil.decimalPlusDecimal(genesisFeeSum, address.getBalance(), DecimalType.ICX.getValue());

				TTransactionTotalWithBLOBs transaction = new TTransactionTotalWithBLOBs();
				transaction.setHeight(0);
				transaction.setTxHash("genesistx " + txCount);
				txCount++;
				transaction.setToAddr(address.getAddress());
				transaction.setFee("0");
				transaction.setAmount(address.getBalance());
				transaction.setHash(HexType.toDBformatString(rpcResult.getBlockHash()));
				transaction.setCreateDate(null);
				transaction.setFromAddr(null);
				transaction.setTxType((byte)0);
				transaction.setState(true);
				transaction.setStepLimit("0");
				transaction.setStepUsedTx("0");
				transaction.setStepPrice("0");
				transaction.setInternalTxCount(0);
				txList.add(transaction);
				TTransactionView view = new TTransactionView();
				view.setTxHash(transaction.getTxHash());
				view.setHeight(0);
				view.setAddress(transaction.getToAddr());
				view.setTxType((byte)0);
				view.setAddressType((byte)1);
				viewList.add(view);
			}

			addressTotal = new TAddressTotal();
			addressTotal.setAddress(address.getAddress());
			addressTotal.setBalance(address.getBalance());
			addressTotal.setNodeType(address.getNodeType());
			addressTotal.setTxCount(address.getTxCount());

			if(address != null) {
				tAddressMapper.insertSelective(address);
				tAddressTotalMapper.insertSelective(addressTotal);
			}
		}
		transactionMapper.insertTxArray(txList);
		transactionMapper.insertTxTotalArray(txList);
		transactionMapper.insertTxViewArray(viewList);
		TBlockTotal block = new TBlockTotal();
		block.setCreateDate(null);
		block.setTxCount(txCount);
		block.setPrevHash(rpcResult.getPrevBlockHash());
		block.setHash(HexType.toDBformatString(rpcResult.getBlockHash()));
		block.setHeight(rpcResult.getHeight());
		block.setFee("0");
		block.setAmount(genesisFeeSum);
		block.setBlockSize(blockSize);
		blockList.add(block);
		blockMapper.insertBlockArray(blockList);
		blockMapper.insertBlockTotalArray(blockList);

		TTxDataWithBLOBs txData = new TTxDataWithBLOBs();
		txData.setTxHash(HexType.toDBformatString(rpcResult.getBlockHash()));
		txData.setDataBody(msg);
		tTxDataMapper.insert(txData);

		v0MainService.updateMainInfo(url, txCount, treasury);

		return 1;
	}

	@Override
	public String getBalance(String url, String address, Integer decimals) throws Exception{
		RpcBalanceRes res = blockChainAdapter.getBalance(url, address);
		return HexUtil.toDecimalString(res.getResult(), decimals);
	}

	@Override
	public String getTokenBalance(String address, String contractAddr, Integer decimals) throws Exception{
		String result =  blockChainAdapter.getIcxCall(contractAddr, "balanceOf", address);
		if(result == null){
			result = "0x0";
		}
		return HexUtil.toDecimalString(result, decimals);
	}

	/*  하나의 block을 입력 처리 (tx, address, block 묶음)
	 * @see com.dfg.icon.core.v3.service.V3BlockChainService#blockChainSyncUpdateAllinOne(java.lang.Integer)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public void blockChainSyncUpdateAllinOne(String url, String chainName, Integer height, Integer lastHeight) throws Exception {

		Integer initHeight = height;
		Integer updateHeight = height;
		List<BlockFactory> bfList = new ArrayList<>();
		boolean isDelegation = false;

		int txViewSum 		= 0;
		int txTotalCount 	= 0;

		// 로그용
		long initTime = System.currentTimeMillis();
		long startTime = System.currentTimeMillis();
		float startBlockTimeDiff = 0;

		for(; height <= lastHeight ; height++) {
			updateHeight = height+1;
			TBlock block = getBlockByHeightInDB(height);
			if (block == null) {
				if (height == 0) {
					// genesis
					GenesisRpcRes blockRes = blockChainAdapter.getBlockInfoByGenesis(url);
					insertGenesis(url, blockRes.getResult(), blockRes.toString().getBytes().length);
				} else {
					BlockFactory bf = null;
					try {
						// get factory
						BlockFactory blockFactory = blockChainAdapter.getBlockFactoryByHeight(url, height);
						// get txResult
						bf = txResultService.initTxResult(url, blockFactory);
						if(bf.isDelegation()) {
							isDelegation = true;
						}
					} catch (Exception e) {
						sLogger.error("getBlock or transactionResult Error : {}", e.getMessage(), e);
						updateHeight--;
						break;
					}
					bfList.add(bf);

					if(bf.isBreak()) {
						TBlockTotal blockInfo0 = bfList.get(0).getBlockInfo();
						sLogger.info("[Speed] getJsonRpc+factoryInit {} -> {}, {} blocks : {}s ",
								blockInfo0.getHeight(),
								bf.getBlockInfo().getHeight(),
								bf.getBlockInfo().getHeight() - blockInfo0.getHeight() + 1,
								(System.currentTimeMillis() - startTime) / 1000.0f);
						insertBlockFactory(url, bfList);
						if (blockInfo0.getCreateDate() != null) {
							startBlockTimeDiff = (System.currentTimeMillis() - blockInfo0.getCreateDate().getTime()) / 1000.0f;
							sLogger.info("[Speed] Scheduler Late Time : {}s, {}", startBlockTimeDiff, startBlockTimeDiff > 10.0f ? "DelayWarning" : "DelaySafe");
						}

						bfList.clear();
						// 로그용
						startTime = System.currentTimeMillis();
					}
				}
			}

			if(bfList.size() > 0){
				txViewSum += bfList.get( bfList.size() -1 ).getTransactionViewList().size();
				txTotalCount += bfList.get( bfList.size() -1 ).getTransactionList().size();

				if(txViewSum > maxTxCount  ||  txTotalCount > maxTxCount){
					sLogger.info("XXXXX CHAIN MaxTxCount OVER  = block {}  , count   {}  / {}",  updateHeight, txViewSum, txTotalCount);
					break;

				}
			}
		}
		if(bfList.size() > 0) {
//			if(resourceService.isLogSpeed()) {
				sLogger.info("[Speed] getBlockByHeight & getTxResult block {} -> {} : {}s ",
						bfList.get(0).getBlockInfo().getHeight(), bfList.get(bfList.size()-1).getBlockInfo().getHeight(),
						(  System.currentTimeMillis() - startTime )/1000.0f);
//			}
			insertBlockFactory(url, bfList);
			startBlockTimeDiff = (System.currentTimeMillis() - bfList.get(bfList.size()-1).getBlockInfo().getCreateDate().getTime())/1000.0f;
			sLogger.info("[Speed] Schelduler Late Time : {}s, {}", startBlockTimeDiff, startBlockTimeDiff > 10.0f ? "DelayWarning" : "DelaySafe");
		}
		chainState.updateChainState(chainName, updateHeight);
		sLogger.info("[Speed] Schelduler End : {} -> {}, {}", initHeight, updateHeight-1, (System.currentTimeMillis()-initTime)/1000.0f);
    }

	/**
	 * BlockFactoryList 의 모든 txCount 조회
	 * @param bfList
	 * @return
	 * @throws Exception
	 */
    public int getSumBlockTxCount(List<BlockFactory> bfList ) throws Exception {

		int txCount = 0;
		for( BlockFactory  bf :  bfList ){
			txCount +=  bf.getTransactionList().size();
		}
		return txCount;
	}

}