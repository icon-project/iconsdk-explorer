package com.dfg.icon.web.v3.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.v3.service.V3SelectQuickSearchService;
import com.dfg.icon.util.CommonUtil;
import com.dfg.icon.web.v3.dto.CommonListRes;
import com.dfg.icon.web.v3.dto.SearchQuickInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 빠른 검색  Controller
 * @author hslee
 */
@Api(tags = {"v3 selectQuickSearch"})
@RequestMapping("v3/selectQuickSearch")
@RestController
public class V3selectQuickSearch {
	private static final Logger logger = LoggerFactory.getLogger(V3selectQuickSearch.class);

	@Autowired
	V3SelectQuickSearchService v3SelectQuickSearchService;

	/** 토큰 / 심볼이름 빠른 검색   
	 * @param text
	 * @return
	 */
	@ApiOperation(value = "selectQuickSearch" , notes="inputvalue [ text : 페이지   ]  outputvalue [ data : 결과  ] ")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonListRes.class)
			})
	@GetMapping(value = "/selectQuickSearch")
	public CommonListRes selectQuickSearch(@Valid 
			@RequestParam(value="text", required=true ) String text
			){
		CommonListRes res = new CommonListRes();
		try {

			List<SearchQuickInfo> searchDataList = v3SelectQuickSearchService.selectQuickSearch(text);
			res.setData(searchDataList);
			res.setListSize(searchDataList.size());
			res.setCode(IconCode.SUCCESS);
		} catch (Exception e) {
			CommonUtil.printException(logger, "selectQuickSearch :: {}", e);
			res.setError();
		}
		return res;
	}
}
