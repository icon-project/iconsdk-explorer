package com.dfg.icon.core.mappers.icon;

import java.util.List;

import com.dfg.icon.web.v3.dto.SearchQuickInfo;

/** 빠른 검색  mapper
 * @author hslee
 *
 */
public interface quickSearchMapper {

	/**
	 * 컨트렉트 리스트 조회
	 */
	List<SearchQuickInfo> selectQuickSearch();


}
