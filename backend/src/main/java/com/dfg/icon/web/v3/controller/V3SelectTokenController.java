package com.dfg.icon.web.v3.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.dfg.icon.core.v3.service.V3ChainInfoService;
import com.dfg.icon.core.v3.service.database.tenant.TenantContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dfg.icon.core.v3.service.V3TokenService;
import com.dfg.icon.util.CommonUtil;
import com.dfg.icon.web.v3.dto.CommonListRes;
import com.dfg.icon.web.v3.dto.CommonRes;
import com.dfg.icon.web.v3.dto.PageReq;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/** 
 * 토큰 관련 정보 조회
 * @author bsm
 */
@Api(tags = {"v3 token"})
@RequestMapping("v3/{chainName}/token")
@RestController
public class V3SelectTokenController {

	private static final Logger logger = LoggerFactory.getLogger(V3SelectTokenController.class);

	@Autowired
	V3TokenService tokenService;

	@Autowired
	V3ChainInfoService chainInfoService;
	/** 토큰 요약 정보 조회
	 * @param contractAddr
	 * @return
	 */
	@ApiOperation(value = "Token Summary" , notes="inputvalue [contracktAddr : 컨트랙트 주소] \n\n "
			+ "outputvalue [ data : 결과  ] ")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/summary")
	public CommonRes getTokenSummary(@PathVariable String chainName,
			@Valid
			@RequestParam(value="contractAddr" , required=true) String contractAddr
			)  {
		String url = chainInfoService.chainHost(chainName);
		TenantContext.setTenant(chainName);
		CommonRes res = new CommonRes();

		try{
			logger.info("====================");
			logger.info("getTokenSummary : contractAddr = {}" , contractAddr);

			res =  tokenService.selectTokenSummary(url, contractAddr);

		} catch (Exception e) {
			CommonUtil.printException(logger, "getTokenSummary error : {}", e);
			res.setError();
		} finally {
			TenantContext.clearTenant();
		}
		return res;
	}


	/** 토큰 리스트 조회 
	 * @param page
	 * @return
	 */
	@ApiOperation(value = "Token List" , notes="inputvalue [page : 페이지, count : 페이징 갯수 ] \n\n"
			+ "outputvalue [ data : 결과  ] ")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonListRes.class)
			})
	@GetMapping(value = "/list")
	public CommonListRes getTokenList(@PathVariable String chainName,
			@Valid
			@RequestParam(value="page",required=false ) Integer page,
			@RequestParam(value = "count", required = false) Integer count,
			@RequestParam(value = "keyword", required = false) String keyword
			)  {
		TenantContext.setTenant(chainName);
		CommonListRes res = new CommonListRes();
		try {

			logger.info("====================");
			logger.info("getTokenList : page = {}, count = {}", page, count);

			PageReq req = new PageReq();
			req.setPage(CommonUtil.changeUnderZero(page));
			if(count != null) {
				req.setCounting(count);
			}
			if(keyword != null && !"".equals(keyword)) {
				if(keyword.length() > 2) {
					if("cx".equals(keyword.toLowerCase().substring(0,2))) {
						req.setAddress(keyword);
					} else if("hx".equals(keyword.toLowerCase().substring(0,2))) {
						req.setAddress2(keyword);
					} else {
						req.setKeyword(keyword.toLowerCase());
					}
				} else {
					req.setKeyword(keyword.toLowerCase());
				}
			}

			res = tokenService.selectTokenList(req);


		} catch (Exception e) {
			CommonUtil.printException(logger, "getTokenList error : {}", e);
			res.setError();
		} finally {
			TenantContext.clearTenant();
		}
		return res;
	}


	/** 토큰 transfers 조회
	 * @param page
	 * @param contractAddr
	 * @return
	 */
	@ApiOperation(value = "Token Transfers" , notes="inputvalue [ page : 보려하는 페이지 , count : 페이징 갯수 , contractAddr : 컨트랙트 주소 ] \n\n"
			+ "outputvalue [ data : 결과  ] " + "\n\n" + "token TtxList의 contractAddr만 targetContractAddr과 동일")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/txList")
	public CommonListRes getTokenTransfers(@PathVariable String chainName,
			@Valid
			@RequestParam(value="page",required=false ) Integer page,
			@RequestParam(value = "count", required = false) Integer count,
			@RequestParam(value="contractAddr" , required=false) String contractAddr,
			@RequestParam(value="tokenAddr" , required=false) String tokenAddr
			)  {
		TenantContext.setTenant(chainName);
		CommonListRes res = new CommonListRes();
		try {

			logger.info("====================");
			logger.info("getTokenTransfers : page ={} , count={} , contractAddr = {}, tokenAddr = {}" , page, count, contractAddr, tokenAddr);

			PageReq req = new PageReq();
			req.setPage(CommonUtil.changeUnderZero(page));
			if(count != null) {
				req.setCounting(count);
			}
			if(contractAddr != null && !"".equals(contractAddr)){
				req.setAddress(contractAddr);
			}
			if(tokenAddr != null && !"".equals(tokenAddr)) {
				req.setAddress2(tokenAddr);
			}

			res =  tokenService.selectTokenTransfers(req);

		} catch (Exception e) {

			CommonUtil.printException(logger, "getTokenTransfers error : {}", e);
			res.setError();
		} finally {
			TenantContext.clearTenant();
		}
		return res;
	}


	/** 토큰 Holders 조회
	 * @param page
	 * @param contractAddr
	 * @return
	 */
	@ApiOperation(value = "Token Holders" , notes="inputvalue [ page : 보려하는 페이지 ,count : 페이징 갯수 , contracktAddr : 컨트랙트 주소] \n\n "
			+ "outputvalue [ data : 결과  ] ")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/holders")
	public CommonListRes getTokenHolders(@PathVariable String chainName,
			@Valid
			@RequestParam(value="page",required=false ) Integer page,
			@RequestParam(value = "count", required = false) Integer count,
			@RequestParam(value="contractAddr") String contractAddr
			)  {

		String url = chainInfoService.chainHost(chainName);
		TenantContext.setTenant(chainName);
		CommonListRes res = new CommonListRes();

		try{
			logger.info("====================");
			logger.info("getTokenHolders : page = {}, count = {}, contractAddr = {}" , page, count, contractAddr);

			PageReq req = new PageReq();
			req.setPage(CommonUtil.changeUnderZero(page));
			if(count != null) {
				req.setCounting(count);
			}
			req.setAddress(contractAddr);

			res = tokenService.selectTokenHolders(url, req);

		} catch (Exception e) {

			CommonUtil.printException(logger, "getTokenHolders error : {}", e);
			res.setError();
		} finally {
			TenantContext.clearTenant();
		}
		return res;
	}

}
