package com.dfg.icon.web.v0.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.dfg.icon.core.v3.service.database.tenant.TenantContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dfg.icon.core.v0.service.V0SearchService;
import com.dfg.icon.web.v0.dto.CommonRes;
import com.dfg.icon.web.v0.dto.SearchReq;
import com.dfg.icon.web.v0.dto.SearchRes;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author bsm
 * 2018-01-22
 */
@Api(tags = {"v0 search"})
@RequestMapping("v0/{chainName}/search")
@RestController
public class V0SearchController {
	private static final Logger logger = LoggerFactory.getLogger(V0SearchController.class);

	@Autowired
	V0SearchService searchService ;

	@ApiOperation(value = "Block Serch (height , hash) , Tx Search" , notes="inputvalue [Block Hash : 블록 해쉬 값 , Tx Hash : Tx 해쉬 값] \n\n"
			+ "outputvalue [ data : 블록 해쉬 , 트랜잭션 해쉬 판별  ] ")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/Search")
	public SearchRes search(@PathVariable String chainName,
							@Valid @RequestParam("data") String data
			) {
		TenantContext.setTenant(chainName);
		SearchRes res = new SearchRes();
		try {
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
			logger.info("X-Real-IP : "+ request.getHeader("X-Real-IP"));

			SearchReq req = new SearchReq();
			req.setData(data);
			logger.info("====================");
			logger.info("search : {}");

			res.setData(searchService.search(req));

		} catch (Exception e) {
			logger.error("Search" ,e);
			res.setError();
		} finally {
			TenantContext.clearTenant();
		}
		return res ;
	}
}
