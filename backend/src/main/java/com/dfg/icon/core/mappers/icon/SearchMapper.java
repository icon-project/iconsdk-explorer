package com.dfg.icon.core.mappers.icon;

import org.apache.ibatis.annotations.Param;

import com.dfg.icon.core.dao.icon.TContract;

public interface SearchMapper {
	
	/**
	 * 블록 해쉬로 블록 정보 조회
	 */
	int blockHashSearch(@Param("hash") String hash);
	
	/** 컨트랙트주소가 토큰인지 확인 후 정보 조회 
	 * @param address
	 * @return
	 */
	TContract isTokenCheck(@Param("address") String address);
	
	/**
	 * 블록 높이로 블록 정보 조회
	 */
	int blockHeightSearch(@Param("height") String height);
	
	/**
	 * 트랜잭션 해쉬로 트랜잭션 정보 조회
	 */
	int txHashSearch(@Param("txHash") String txHash);
	
}

