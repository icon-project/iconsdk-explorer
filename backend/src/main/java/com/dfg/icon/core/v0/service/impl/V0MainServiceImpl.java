package com.dfg.icon.core.v0.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.dfg.icon.core.common.service.ResourceService;
import com.dfg.icon.core.dao.icon.*;
import com.dfg.icon.core.mappers.icon.*;
import com.dfg.icon.core.v3.adapter.V3BlockChainAdapter;
import com.dfg.icon.core.v3.vo.DecimalType;
import com.dfg.icon.core.v3.vo.IcxSupplyVo;
import com.dfg.icon.core.v3.vo.RpcBalanceRes;
import com.dfg.icon.util.DateUtil;
import com.dfg.icon.util.HexUtil;
import com.dfg.icon.web.v3.dto.PageReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfg.icon.core.v0.service.V0MainService;
import com.dfg.icon.web.v0.dto.main.MainBlock;
import com.dfg.icon.web.v0.dto.main.MainInfo;

/**
 * @author bsm
 * 2018-01-22
 */
@Service
public class V0MainServiceImpl implements V0MainService{


	@Autowired
	BlockMapper blockMapper;
	
	@Autowired
	MainMapper mainMapper;
	
	@Autowired
	TMainInfoMapper tMainInfoMapper;
	
	@Autowired
	TMainChartMapper tMainChartMapper;
	
	@Autowired
	TMainTxMapper tMainTxMapper;

	@Autowired
	AddressMapper addressMapper;

	@Autowired
	ResourceService resourceService;

	@Autowired
	V3BlockChainAdapter blockChainAdapter;

	/* 
	 * 메인 정보 요약 
	 */
	@Override
	public MainInfo selectMainSummary() throws Exception {
		
		MainInfo mainInfo = mainMapper.selectMainInfo();
		if(mainInfo == null){
			return null;
		}
		//환율 계산
		String selRate = mainMapper.selectRate();
		if(selRate == null) {
			selRate = "0";
		}
		double rate =  Double.parseDouble(selRate)  ;
//		double icxSupply =  Double.parseDouble(mainInfo.getIcxSupply());
		double icxCirculationy = Double.parseDouble(mainInfo.getIcxSupply());
		if(mainInfo.getIcxCirculationy() != null) {
			icxCirculationy =  Double.parseDouble(mainInfo.getIcxCirculationy());
		}
		mainInfo.setMarketCap(rate * icxCirculationy );
		
		return mainInfo;
	}

	/* 
	 * 일일 차트 
	 */
	@Override
	public List<TMainChart> SelectDailyTx() throws Exception {
		
		TMainChartExample example = new TMainChartExample();
		List<TMainChart> chart = tMainChartMapper.selectByExample(example);
		if(chart.isEmpty()){
			return null;
		}
		return chart;
	}

	/* 
	 * 최근 트랜잭션 
	 */
	@Override
	public List<TMainTx> selectRecentTx() throws Exception {
		TMainTxExample example = new TMainTxExample();
		example.setOrderByClause("create_date desc limit 20");
		List<TMainTx> recentTx = tMainTxMapper.selectByExample(example);
		for (int i = 0; i < recentTx.size(); i++) {
			double amount = Double.parseDouble(recentTx.get(i).getAmount());
			String b = String.format("%.4f", amount);
			recentTx.get(i).setAmount(b);
		}
		
		if(recentTx.isEmpty()){
			return null;
		}
		return recentTx;
	}

	/**
	 * 최근 블록 조회
	 */
	@Override
	public List<MainBlock> selectRecentBlock() throws Exception {
		
		List<MainBlock> recentBlock = blockMapper.selectMainBlock();
		if(recentBlock.isEmpty()){
			return null;
		}
		return recentBlock;
	}

	/**
	 * 메인 블록 정리
	 * - 스케줄러에서 블록 추가시 T_BLOCK, T_BLOCK_TOTAL, T_MAIN_BLOCK 삽입.
	 * - 스케줄러 종료 후 MainBlock의 상위 20raw만 남기고 삭제.
	 */
	@Override
	public void updateMainBlock() throws Exception {
		mainMapper.deleteMainBlock(resourceService.getLimitMainBlock());
	}


	/**
	 * 메인 차트 업데이트 
	 */
	@Override
	public void updateMainChart() throws Exception{
		// Main Chart.
		List<TMainChart> chart = blockMapper.selectBlockChart();
		if(chart != null && chart.size() > 0 && chart.get(0) != null) {
			TMainChartExample example = new TMainChartExample();
			example.createCriteria().andTargetDateEqualTo(chart.get(0).getTargetDate());
			tMainChartMapper.updateByExampleSelective(chart.get(0), example);
		}
	}

	/**
	 * 메인 트랜잭션 업데이트 
	 */
	@Override
	public void updateMainTx() throws Exception {
		mainMapper.deleteMainTx(resourceService.getLimitMainTx());
	}


	/**
	 * 메인정보 업데이트 
	 */
	//TODO Block explorer (delete treasury???)
	@Override
	public void updateMainInfo(String url, int txAddedCount, String treasury) throws Exception {
		RpcBalanceRes res =  blockChainAdapter.getTotalSupply(url);
		TMainInfo mainInfo = getMainInfo();
		PageReq req = new PageReq();
		req.setGrade((byte)0);

		if(mainInfo == null) {
			mainInfo = new TMainInfo();
			mainInfo.setTransactionCount(mainMapper.sumByTxCount());
			tMainInfoMapper.insert(mainInfo);
		} else {
			mainInfo.setTransactionCount(mainInfo.getTransactionCount() + txAddedCount);
			tMainInfoMapper.updateByExample(mainInfo, new TMainInfoExample());
		}
	}

	/* 
	 * 메인 일일 차트 업데이트  
	 */
	@Override
	public void updateMainChartDaily() throws Exception {
		TMainChartExample example = new TMainChartExample();
		example.createCriteria();
		tMainChartMapper.deleteByExample(example);

		List<TMainChart> chartList = blockMapper.selectBlockChartDaily();
		List<String> dayList = DateUtil.getChartDailyList();

		for(String day : dayList) {
			TMainChart mainChart = new TMainChart();
			mainChart.setTargetDate(DateUtil.getFormattedDate(day, "yyyy-MM-dd"));
			for(TMainChart chart : chartList) {
				if(chart.getTargetDate().getTime() == mainChart.getTargetDate().getTime()) {
					mainChart.setTxCount(chart.getTxCount());
					break;
				}
			}
			if(mainChart.getTxCount() == null) {
				mainChart.setTxCount(0);
			}
			tMainChartMapper.insert(mainChart);
		}
	}


	/*
	 * 메인 정보 
	 */
	@Override
	public TMainInfo getMainInfo() {
		TMainInfoExample infoExample = new TMainInfoExample();
		infoExample.createCriteria();
		List<TMainInfo> mainInfo = tMainInfoMapper.selectByExample(infoExample);
		if(mainInfo != null && mainInfo.size() > 0) {
			return mainInfo.get(0);
		} else {
			return null;
		}
	}

	@Override
	public String getIcxCirculationy(String totalSupply) {

		String icxSupply = totalSupply;

		IcxSupplyVo icx = addressMapper.sumFCAIcx();
		if(icx != null && icx.getBalance() != null) {
			BigDecimal total = new BigDecimal(totalSupply);
			BigDecimal fca = new BigDecimal(icx.getBalance());
			icxSupply = total.subtract(fca).toString();
		}

		return icxSupply;
	}
	
}
