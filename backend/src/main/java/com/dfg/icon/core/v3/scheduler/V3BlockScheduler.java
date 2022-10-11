/**
 * V2 Bock Scheduler Class
 * <B>History</B>
 * 이용재 2018-01-18 최초 작성
 */
package com.dfg.icon.core.v3.scheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import com.dfg.icon.core.dao.icon.TContract;
import com.dfg.icon.core.dao.icon.TContractHistory;
import com.dfg.icon.core.mappers.icon.ContractMapper;
import com.dfg.icon.core.v3.service.V3ContractService;
import com.dfg.icon.util.DateUtil;
import com.dfg.icon.web.v3.dto.ContractPendingInfo;
import com.dfg.icon.web.v3.dto.PageReq;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dfg.icon.core.common.service.ResourceService;
import com.dfg.icon.core.common.service.ScheduleService;
import com.dfg.icon.core.dao.icon.TSchedulerFlag;
import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.v3.adapter.V3BlockChainAdapter;
import com.dfg.icon.core.v3.service.V3BlockChainService;
import com.dfg.icon.util.CommonUtil;

/**
 * 일정시간주기로 블록 내용을 조회 하는 스케쥴러 
 * @author hslee
 */
@Component
public class V3BlockScheduler {
	private static final Logger logger = LoggerFactory.getLogger(V3BlockScheduler.class);
	private static final Logger sLogger = LoggerFactory.getLogger("ScheduleLogger");

	@Autowired
	private V3BlockChainAdapter blockChainAdapter;

	@Autowired
	private V3BlockChainService blockChainService;

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private V3ContractService contractService;

	@Autowired
	private ContractMapper contractMapper;

	private boolean isRepJsonUpdate = false;

	@Value("${scheduler.MaxBlockCount}")
	Integer maxBlockCount ;

	// 스케줄러 강제 복귀 텀
	private int ignoreSchedulerTime = 180000;
	
	/**
	 *  루프체인에서 신규 데이터를 가지고 와서 조회 + 스케쥴러을 이용한 기능 구현 
	 */
	@Scheduled(cron = "${block.cron.pattern}")
	public void updateBlockSync() {
		sLogger.info("--------------------");

		if(!IconCode.SCHEDULER_VER3.getCode().equals(resourceService.getBlockSchedulerVersion())){
			return ;
		}

		// 로그를 위한 랜덤 함수
		Random generator = new Random();
		int randomKey = generator.nextInt();
		sLogger.info("block_sync_start " + randomKey);

		// 다른 서버에서 작업중일 경우를 대비한 검증 작업
		TSchedulerFlag flag = checkSchedulingOuterServer(randomKey);

		// 실제 데이터 수집 작업
		try {
			// get lastBlock
			Integer lastHeight = blockChainAdapter.getHeightByBlock(null);
			String nextHeight = flag.getStartPosition();
			sLogger.info("[Block] LastBlock : {}", lastHeight);

			// Block 단위로 데이터 입력
			int iNextHeight = Integer.parseInt(nextHeight);
			if( (lastHeight  - iNextHeight) > maxBlockCount  ){
				lastHeight = iNextHeight + maxBlockCount ;
			}

			// Block 단위로 데이터 입력
			try {
				blockChainService.blockChainSyncUpdateAllinOne(iNextHeight, lastHeight, flag.getUpdateDate());
			} catch (Exception e) {
				CommonUtil.printException(sLogger, "SyncUpdate error : {}", e);
			}

			sLogger.info("[Block] nextHeight : {} , lastHeight : {} ", Integer.parseInt(nextHeight) , lastHeight);
			// block update가 있었던 경우에만. 불필요한 반복과정 생략

			long startTime = System.currentTimeMillis();
			if(Integer.parseInt(nextHeight) <= lastHeight) {
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
		}finally {
			scheduleService.inActiveBlockSchedule(resourceService.getServerName());
			sLogger.info("block_ end" + randomKey);
		}

	}

	/** 다른 서버에서 작업중인지 확인 및 작업중 등록  
	 * @param randomKey
	 * @return
	 */
	private TSchedulerFlag checkSchedulingOuterServer(int randomKey) {
		TSchedulerFlag flag = scheduleService.getBlockSchedule();
		if(flag == null || "N".equals(flag.getActiveYn())) {
			flag =  scheduleService.activeBlockSchedule(resourceService.getServerName());
		} else {
			if("P".equals(flag.getActiveYn())) {
				sLogger.info("block_start pause");
				return null;
			} else if("R".equals(flag.getActiveYn())) {
				if(flag.getActiveServer().equals(resourceService.getServerName())){
					scheduleService.inActiveBlockSchedule(resourceService.getServerName());
				}
				return null;
			} else if(System.currentTimeMillis() - flag.getUpdateDate().getTime() < ignoreSchedulerTime) {
				// 스케줄 강제 시작 복귀 3분
				sLogger.info("block_start end type {} using {}",randomKey, flag.getActiveServer());
				return null;
			} else {
				flag = scheduleService.activeBlockSchedule(resourceService.getServerName());
				sLogger.info("block_start ignore flag and continue schedule : ");
			}
		}
		return flag;
	}

	private TSchedulerFlag checkPrepActiveScheduler(String name, Integer term) {
		TSchedulerFlag flag = scheduleService.getSchedule(name);
		long lastTerm = 0;
		if(flag != null && flag.getStartPosition() != null) {
			lastTerm = System.currentTimeMillis() - Long.parseLong(flag.getStartPosition());
		}
		if(flag == null) {
			flag =  scheduleService.activeSchedule(name, resourceService.getServerName());
		} else if("N".equals(flag.getActiveYn())){
			if(lastTerm > term) {
				flag =  scheduleService.activeSchedule(name, resourceService.getServerName());
			} else {
				sLogger.info("iiss remain time {}sec", (term - lastTerm)/1000.0);
				return null;
			}
		} else {
			sLogger.info("iiss using {}", flag.getActiveServer());
			return null;
		}
		return flag;
	}

	@Scheduled(cron = "${maintx.cron.pattern}")
	public void updateMainChartDailySync() {
		sLogger.info("--------------------");
		sLogger.info("[Main] DailyChart/CollectBalAddr Update : {}", resourceService.getServerName());
		try {
			blockChainService.updateChart();
			blockChainService.collectBalanceAddress();
			isRepJsonUpdate = true;
		} catch(Exception e) {
			sLogger.error("[Main] DailyChart Update ERROR : {}", e.getMessage());
		}
	}

	@PostConstruct
	public void initMain() throws Exception {
		sLogger.info("--------------------");
		sLogger.info("[Main] Init Update : {}", DateUtil.getNowDate());
		try {
			contractService.initContractInfo();
		} catch(Exception e) {
			sLogger.error("[Main] Init Update ERROR : {}", e.getMessage());
		}

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

				JsonObject status = blockChainAdapter.getScoreStatus(info.getContractAddr());
				logger.info("  ==> status: {}", status.toString());
				tryToFixIncorrectState(status, info);
			}
		} catch (Exception e) {
			logger.error("[Main] ERROR while obtaining pending contract list: {}", e.getMessage());
			CommonUtil.printException(logger, "initMain :: {}", e);
		}
	}

	private void tryToFixIncorrectState(JsonObject status, ContractPendingInfo info) {
		if (status.has("current") && !status.has("next")) {
			JsonObject current = status.get("current").getAsJsonObject();
			String deployTx = current.get("deployTxHash").getAsString();
			String auditTx = current.get("auditTxHash").getAsString();
			if (deployTx != null && deployTx.equals(auditTx)) {
				TContractHistory contractHistory = contractService.getContractHistoryByCreateTx(deployTx);
				String scoreAddress = contractHistory.getContractAddr();
				Integer historyVersion = contractHistory.getVersion();
				String scoreName = blockChainAdapter.getIcxCall(scoreAddress, "name");
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

	public void activeRepJsonUpdate() {
		isRepJsonUpdate = true;
	}
}
