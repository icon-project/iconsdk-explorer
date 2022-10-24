package com.dfg.icon.core.mappers.icon;

import java.util.List;

import com.dfg.icon.core.dao.icon.TMainBlock;
import com.dfg.icon.core.dao.icon.TMainTx;
import com.dfg.icon.web.v0.dto.main.MainInfo;
import org.apache.ibatis.annotations.Param;

public interface MainMapper {

	
	/**
	 * 메인 정보 조회 
	 */
	MainInfo selectMainInfo();

	/**
	 * 트랜잭션 총 개수
	 */
	Integer sumByTxCount();
	
	/** 메인 TX에 데이터를 리스트 형태로 입력 
	 * @return
	 */
	int insertMainTxList(List<TMainTx> mainTxList);
	
	
	/** 메인 Block 에 데이터를 리스트 형태로 입력
	 * @return
	 */
	int insertMainBlockList(List<TMainBlock> mainTxList);


	/** 메인 Block에서 필요한 데이터 이외는 삭제
	 * @return
	 */
	int deleteMainBlock(@Param("count")  int count);

	/** 메인 Transaction에서 필요한 데이터 이외는 삭제
	 * @return
	 */
	int deleteMainTx(@Param("count")  int count);

	/**
	 * 차트에서 오래된 데이터를 삭제
	 * @return
	 */
	int deleteOldDataDaliyChart();

	/**
	 * 신규 차트 데이터 입력
	 * @return
	 */
	int insertNewChartData();

	Integer selectTxCountInTwoFour();
}

