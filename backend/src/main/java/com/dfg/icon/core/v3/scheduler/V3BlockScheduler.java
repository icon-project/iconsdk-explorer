/**
 * V2 Bock Scheduler Class
 * <B>History</B>
 * 이용재 2018-01-18 최초 작성
 */
package com.dfg.icon.core.v3.scheduler;

import com.dfg.icon.core.common.service.ResourceService;
import com.dfg.icon.core.dao.icon.TChainState;
import com.dfg.icon.core.dao.icon.TContract;
import com.dfg.icon.core.dao.icon.TContractHistory;
import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.mappers.icon.ContractMapper;
import com.dfg.icon.core.v3.adapter.V3BlockChainAdapter;
import com.dfg.icon.core.v3.service.V3BlockChainService;
import com.dfg.icon.core.v3.service.V3ChainInfoService;
import com.dfg.icon.core.v3.service.V3ChainStateService;
import com.dfg.icon.core.v3.service.V3ContractService;
import com.dfg.icon.core.v3.service.database.tenant.Channel;
import com.dfg.icon.core.v3.service.database.tenant.TenantAwareThread;
import com.dfg.icon.core.v3.service.database.tenant.TenantContext;
import com.dfg.icon.util.CommonUtil;
import com.dfg.icon.util.DateUtil;
import com.dfg.icon.web.v3.dto.ChainInfo;
import com.dfg.icon.web.v3.dto.CommonListRes;
import com.dfg.icon.web.v3.dto.ContractPendingInfo;
import com.dfg.icon.web.v3.dto.PageReq;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Component
public class V3BlockScheduler {
	private static final Logger logger = LoggerFactory.getLogger(V3BlockScheduler.class);
	private static final Logger sLogger = LoggerFactory.getLogger("BlockSyncWorkerLogger");
	private final String block = "block-";
	private final String mainChart = "mainChart-";

	@Autowired
	private V3BlockChainAdapter blockChainAdapter;

	@Autowired
	private V3BlockChainService blockChainService;

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private V3ContractService contractService;

	@Autowired
	private V3ChainStateService chainState;

	@Autowired
	private ContractMapper contractMapper;

	@Autowired
	private V3ChainInfoService chainInfoService;

	@Autowired
	private V3ChainStateService chainStateService;

	@Autowired
	private Channel channel;

	private boolean isRepJsonUpdate = false;

	private List<ChainInfo> chainInfoList;


	@Value("${scheduler.MaxBlockCount}")
	Integer maxBlockCount ;

	@PostConstruct
	private void initMain() {
		sLogger.info("--------------------");
		sLogger.info("[Main] Init Update : {}", DateUtil.getNowDate());
		try {
			contractService.initContractInfo();
		} catch(Exception e) {
			sLogger.error("[Main] Init Update ERROR : {}", e.getMessage());
		}

		CommonListRes resList = chainInfoService.selectChainInfoList();
		chainInfoList = (List<ChainInfo>) resList.getData();
		for(ChainInfo chainInfo : chainInfoList){
			String url = chainInfoService.chainHost(chainInfo.getChainName());
			TenantContext.setTenant(chainInfo.getChannel());
			try {
				PageReq req = new PageReq(100);
				req.setPage(1);
				List<ContractPendingInfo> pendingInfos = contractService.selectRequireContractList(req);
				int count = 0;
				for (ContractPendingInfo info : pendingInfos) {
					logger.info(">>> [{}] {} {}, {} - {} - {}",
							count++,
							info.getVersion(), info.getContractName(),
							info.getCreateTx(), info.getContractAddr(), info.getCreateDate());

					JsonObject status = blockChainAdapter.getScoreStatus(url, info.getContractAddr());
					logger.info("  ==> status: {}", status.toString());
					tryToFixIncorrectState(url, status, info);
				}
			} catch (Exception e) {
				logger.error("[Main] ERROR while obtaining pending contract list: {}", e.getMessage());
				CommonUtil.printException(logger, "initMain :: {}", e);
			}finally {
				TenantContext.clearTenant();
			}
			blockSyncStart(chainInfo.getChainName());
			mainChartDailySyncStart(chainInfo.getChainName());
		}
	}


	private void blockSyncStart(String chainName){
		channel.blockSyncStart(chainName, this.block + chainName);
	}

	private void mainChartDailySyncStart(String chainName){
		channel.mainChartSyncStart(chainName, this.mainChart + chainName);
	}

	@Scheduled(cron = "${block.cron.pattern}")
	public void updateBlockSync() {
		for(ChainInfo chainInfo : chainInfoList){
			channel.putRequest(this.block + chainInfo.getChainName());
		}
	}

	@Scheduled(cron = "${maintx.cron.pattern}")
	public void updateMainChartDailySync() {
		for(ChainInfo chainInfo : chainInfoList){
			channel.putRequest(this.mainChart + chainInfo.getChainName());
		}
	}

	/**
	 *  루프체인에서 신규 데이터를 가지고 와서 조회 + 스케쥴러을 이용한 기능 구현 
	 */
	public TenantAwareThread updateBlockSync(String url, String chainName, String threadName) {
		return new TenantAwareThread(chainName, () -> {
			while (!Thread.interrupted()) {
				if(channel.getRequestState(threadName) == true){
					sLogger.info("----------- updateBlockSync ---------");
					try {
						// get lastBlock
						Integer lastHeight = blockChainAdapter.getHeightByBlock(url, null);
						TChainState state = chainState.selectChainState(threadName.split("-")[1]);
						if(state == null){
							chainStateService.insertChainState(chainName, 0);
							state = chainState.selectChainState(chainName);
						}
						int nextHeight = state.getBlockHeight();
						sLogger.info("[Block] LastBlock : {}", lastHeight);

						// Block 단위로 데이터 입력
						if( (lastHeight  - nextHeight) > maxBlockCount  ){
							lastHeight = nextHeight + maxBlockCount ;
						}

						// Block 단위로 데이터 입력
						try {
							blockChainService.blockChainSyncUpdateAllinOne(url, chainName, nextHeight, lastHeight);
						} catch (Exception e) {
							CommonUtil.printException(sLogger, "SyncUpdate error : {}", e);
						}

						sLogger.info("[Block] nextHeight : {} , lastHeight : {} ", nextHeight , lastHeight);
						// block update가 있었던 경우에만. 불필요한 반복과정 생략

						long startTime = System.currentTimeMillis();
						if(nextHeight <= lastHeight) {
							// recent 테이블 관련 갯수 정리
							blockChainService.blockLimit(resourceService.getLimitBlock());
							sLogger.info("[Block] limit block time : {} s ", (System.currentTimeMillis() - startTime) / 1000.0f);
							startTime = System.currentTimeMillis();
							blockChainService.transactionLimit(resourceService.getLimitTx());
							sLogger.info("[Block] limit transaction time : {} s ", (System.currentTimeMillis() - startTime) / 1000.0f);
							startTime = System.currentTimeMillis();
							blockChainService.addressLimit(resourceService.getLimitAddress());
							sLogger.info("[Block] limit address time : {} s ", (System.currentTimeMillis() - startTime) / 1000.0f);
							startTime = System.currentTimeMillis();
							blockChainService.updateMain();
							sLogger.info("[Block] update main time : {} s ", (System.currentTimeMillis() - startTime) / 1000.0f);
						}

					} catch(Exception e) {
						CommonUtil.printException(sLogger, "[Block] Update ERROR : {}", e);
					} finally {
						channel.takeRequest(threadName);
					}
				}
			}

		});
	}

	//TODO refactoring merge tenantAwareThread
	public TenantAwareThread updateMainChartDailySync(String chainName, String threadName) {
		return new TenantAwareThread(threadName, () -> {
			while (!Thread.interrupted()) {
				if (channel.getRequestState(threadName) == true) {
					sLogger.info("---------- updateMainChartDailySync ----------");
					try {
						blockChainService.updateChart();
						blockChainService.collectBalanceAddress();
					} catch(Exception e) {
						sLogger.error("[Main] DailyChart Update ERROR : {}", e.getMessage());
					}
				}
			}
		});

	}




	private void tryToFixIncorrectState(String url, JsonObject status, ContractPendingInfo info) {
		if (status.has("current") && !status.has("next")) {
			JsonObject current = status.get("current").getAsJsonObject();
			String deployTx = current.get("deployTxHash").getAsString();
			String auditTx = current.get("auditTxHash").getAsString();
			if (deployTx != null && deployTx.equals(auditTx)) {
				TContractHistory contractHistory = contractService.getContractHistoryByCreateTx(deployTx);
				String scoreAddress = contractHistory.getContractAddr();
				Integer historyVersion = contractHistory.getVersion();
				String scoreName = blockChainAdapter.getIcxCall(url, scoreAddress, "name");
				if (scoreName == null) {
					scoreName = "-";
				}
				logger.info(">>> scoreName = {}", scoreName);
				Byte state = Byte.parseByte(IconCode.SCORE_ACCEPT.getCode());

				List<TContract> contractList = new ArrayList<>();
				contractList.add(contractService.getContractForInsert(scoreAddress, scoreName, historyVersion, "-", state));
				contractMapper.insertContractArray(contractList);

				List<TContractHistory> contractHistoryList = new ArrayList<>();
				contractHistory.setState(state);
				contractHistory.setVerifiedDate(info.getCreateDate());
				contractHistory.setVerifiedTx(deployTx);
				contractHistoryList.add(contractHistory);
				contractMapper.insertContractHistoryArray(contractHistoryList);
			}
		}
	}




}
