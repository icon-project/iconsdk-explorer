package com.dfg.icon.core.v3.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.mappers.icon.quickSearchMapper;
import com.dfg.icon.core.v3.service.V3SelectQuickSearchService;
import com.dfg.icon.web.v3.dto.SearchQuickInfo;

/**
 *  빠른 검색 관련  Service
 * @author hslee
 *
 */
@Service
public class V3SelectQuickSelectImpl implements V3SelectQuickSearchService{

	@Autowired
	quickSearchMapper quickSearchMapper;
	
	
	/* (non-Javadoc)
	 * @see com.dfg.icon.core.v3.service.V3SelectQuickSearchService#selectQuickSearch(java.lang.String)
	 */
	@Override
	public List<SearchQuickInfo> selectQuickSearch(String targetWord) throws Exception {
		
		long start = System.currentTimeMillis();

		// 키를 리스트로 변경
		Object[] keyArray = SEARCH_MAP.keySet().toArray();
		int[] checkRange = {0, keyArray.length}; 

		// 검색 범위를 6.25%로 줄임 
		for(int i = 0 ; i < 4 ; i++) {
			checkRange = checkIndex(targetWord, checkRange, keyArray);
		}

		// 검색 시작 위치 
		int startIndex = checkRange[0];
		boolean searchCheck = false;

		// 첫번째데이터 위치 추적  
		for(int i = startIndex ; i <= checkRange[1]; i++) {
			if(keyArray[i].toString().startsWith(targetWord)){
				startIndex = i;
				searchCheck = true;
				break;
			}
		}

		// 검색된 데이터가 없음 
		List<SearchQuickInfo> returnList = new ArrayList<SearchQuickInfo>();
		if(!searchCheck) {
			String[] noData = {IconCode.NO_DATA.getCode(), "" ,""};
			returnList.add(new SearchQuickInfo(noData));
			return returnList;
		}

		// 실제 데이터 취득 
		HashMap dataMap = new HashMap<String ,Integer>(); 
		for(int i = startIndex ; i < keyArray.length; i++) {
			if(keyArray[i].toString().startsWith(targetWord)){
				String dbKey = keyArray[i].toString();
				dataMap.put(    dbKey,  SEARCH_MAP.get(dbKey)   );
			}else {
				break;
			}
		}

		// txCouter 기준으로 sort  
		Object[] returnData = dataMap.entrySet().toArray();
		Arrays.sort(returnData, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Map.Entry<String, Integer>) o2).getValue()
						.compareTo(((Map.Entry<String, Integer>) o1).getValue());
			}
		});
		
		// 데이터 정리 
		for (Object returnDataValue : returnData) {
			String key = ((Map.Entry<String, Integer>) returnDataValue).getKey();
			StringTokenizer values = new StringTokenizer( key, CONNECT_WORD );
			String[] keys = new String[3]; 
			int i = 0;
			while(values.hasMoreTokens()) {
				keys[i++] = values.nextToken();
			}
			
			returnList.add(new SearchQuickInfo(keys));
		}

		return returnList;
	}


	/**
	 * 배열에서 특정단어의 위치를 찾을때 범위를 50%로 줄여주는 함수   
	 * 
	 * @param targetWord
	 * @param checkRange
	 * @param keyArray
	 * @return
	 */
	public static int[] checkIndex(String targetWord , int[] checkRange  ,  Object[] keyArray) {

		int startIndex = checkRange[0];
		int endIndex = checkRange[1];

		int checkIndex = (endIndex - startIndex)/2 + startIndex ;
		int result = targetWord.compareTo(keyArray[checkIndex].toString());

		// traget이 배열 가운데보다 우측에 있을 경우
		if(result > 0) {
			checkRange[0] = checkIndex;
			checkRange[1] = endIndex;
		}else {
			checkRange[0] = startIndex;
			checkRange[1] = checkIndex;
		}
		return checkRange;
	}


	/* (non-Javadoc)
	 * @see com.dfg.icon.core.v3.service.V3SelectQuickSearchService#selectSearchMapData()
	 */
	@SuppressWarnings("static-access")
	public void selectSearchMapData() throws Exception {
		
		List<SearchQuickInfo> searchDataList = quickSearchMapper.selectQuickSearch();
		for( SearchQuickInfo  searchData : searchDataList ) {
			String key = searchData.getName() + CONNECT_WORD + searchData.getAddress() + CONNECT_WORD + searchData.getIrcVersion();
			key = key.toLowerCase();
			SEARCH_MAP.put(key, searchData.getTxCount().intValue());
		}
	}
}

