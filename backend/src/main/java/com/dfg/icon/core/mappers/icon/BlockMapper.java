package com.dfg.icon.core.mappers.icon;

import java.util.List;


import com.dfg.icon.core.dao.icon.TBlock;
import com.dfg.icon.core.dao.icon.TBlockTotal;
import org.apache.ibatis.annotations.Param;

import com.dfg.icon.core.dao.icon.TMainChart;
import com.dfg.icon.web.v0.dto.TxInBlock;
import com.dfg.icon.web.v0.dto.block.Block;
import com.dfg.icon.web.v0.dto.main.MainBlock;
import com.dfg.icon.web.v3.dto.PageReq;
import com.dfg.icon.web.v3.dto.RecentBlock;
import com.dfg.icon.web.v0.dto.RecentBlockDto;

public interface BlockMapper {

	
	/** 
	 * 전체 블록 정보 조회 
	 */
	List<RecentBlockDto> selectRecentBlock(@Param("page") int page) ;
	
	/** 
	 * 전체 블록 정보 조회 v3 버전
	 */
	List<RecentBlock> selectRecentBlock(PageReq req);
	
	/**
	 * 전체 블록안의 트랜잭션 갯수
	 */
	String selectTotalTxInBlock(@Param("height") int height );
	
	/**
	 * 전체 블록안의 트랜잭션 갯수 해쉬로 검색 
	 */
	String selectTotalTxInBlockByHash(@Param("hash") String hash );
	
	/** 
	 * 선택한 블록에 관한 블록 정보 
	 */
	Block selectBlockDetail(PageReq req);
	
	/**
	 *  블록 안에 있는 트랜잭션 정보 조회 
	 */
	List<TxInBlock> selectTxInBlock(PageReq req);
	Integer selectTxCountInBlock(PageReq req);
	
	/** 
	 * 선택한 블록에 관한 블록 정보 해쉬로 검색
	 */
//	Block selectBlockDetailByHash(PageReq req);
	
	/** 
	 * 블록 안에 있는 트랜잭션 정보 조회 해쉬로 검색 
	 */
	List<TxInBlock> selectTxInBlockByHash(PageReq req);
	
	
	/**
	 * 마지막 블록 높이 값 조회 
	 */
	Integer selectLastBlockHeight();
	
	/**
	 * Main 블록 정보 
	 */
	List<MainBlock> selectMainBlock();

	/**
	 * 데일리 블록차트
	 */
	List<TMainChart> selectBlockChartDaily();

	/**
	 * 오늘자 실시간 블록 차트
	 */
	List<TMainChart> selectBlockChart();

	int insertBlockArray(List<TBlockTotal> list);
	int insertBlockTotalArray(List<TBlockTotal> list);
}

