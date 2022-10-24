package com.dfg.icon.core.v0.service;

import java.util.List;

import com.dfg.icon.core.dao.icon.TMainChart;
import com.dfg.icon.core.dao.icon.TMainInfo;
import com.dfg.icon.core.dao.icon.TMainTx;
import com.dfg.icon.web.v0.dto.SimpleRes;
import com.dfg.icon.web.v0.dto.main.MainBlock;
import com.dfg.icon.web.v0.dto.main.MainInfo;
import com.dfg.icon.web.v0.dto.main.MainRes;

public interface V0MainService {

	/**
	 * 메인 정보 요약 조회
	 */
	MainInfo selectMainSummary() throws Exception;


	/**
	 * 일일 그래프 조회
	 */
	List<TMainChart> SelectDailyTx() throws Exception;

	/**
	 * 최신 트랜잭션 정보 조회
	 */
	List<TMainTx> selectRecentTx() throws Exception;

	/**
	 * 최신 블록 정보 조회
	 */
	List<MainBlock> selectRecentBlock() throws Exception;

	/**
	 * 메인 블록 업데이트
	 */
	void updateMainBlock() throws Exception;

	/**
	 * 메인 차트 업데이트
	 */
	void updateMainChart() throws Exception;

	/**
	 * 메인 트랜잭션 업데이트
	 */
	void updateMainTx() throws Exception;

	/**
	 * 메인 정보 업데이트
	 */
	void updateMainInfo(String url, int txAddedCount, String treasury) throws Exception;

	void updateMainChartDaily() throws Exception;

	TMainInfo getMainInfo();

	String getIcxCirculationy(String totalSupply);
}
