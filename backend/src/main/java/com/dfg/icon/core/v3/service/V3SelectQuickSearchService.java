package com.dfg.icon.core.v3.service;

import java.util.List;
import java.util.TreeMap;

import com.dfg.icon.web.v3.dto.SearchQuickInfo;

/**
 * 빠른 검색 관련  Service
 * @author hslee
 *
 */
public interface V3SelectQuickSearchService {
	
	/**
	 * 1시간에 한번씩 토큰이름/심볼/ 컨트렉트이름등을 조회해서 데이터를 저장 하고 있는 TreeMap
	 * Name(Symbol) + CONNECT_WORD + address + CONNECT_WORD + ircVersion 순으로 Key가 구성되어야함 
	 * 
	 */
	static TreeMap<String , Integer> SEARCH_MAP = new TreeMap<String , Integer>();  
	
	/**
	 * SEARCH_MAP에서 사용되는 연결단어 
	 */
	static String CONNECT_WORD = "@#$";
	
	/** 빠른 검색  
	 * @param text
	 * @return
	 * @throws Exception
	 */
	List<SearchQuickInfo> selectQuickSearch(String text) throws Exception;
	
	
	/** Search_Map 데이터를 조회 
	 * @throws Exception
	 */
	void selectSearchMapData() throws Exception;
	
	
}
