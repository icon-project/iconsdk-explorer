package com.dfg.icon.web.v0.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.dfg.icon.core.mappers.icon.TransactionMapper;
import com.dfg.icon.core.v3.service.V3ChainInfoService;
import com.dfg.icon.core.v3.service.database.tenant.TenantContext;
import com.dfg.icon.web.v3.dto.TxDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.dfg.icon.core.mappers.icon.AddressMapper;
import com.dfg.icon.core.v0.service.V0WalletDetailService;
import com.dfg.icon.core.v2.service.V2BlockChainService;
import com.dfg.icon.web.v0.dto.CommonRes;
import com.dfg.icon.web.v3.dto.PageReq;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author bsm
 * 2018-01-22
 */
@Api(tags = {"v0 wallet"})
@RequestMapping("v0/{chainName}/wallet")
@RestController
public class V0SelectWalletController {
	private static final Logger logger = LoggerFactory.getLogger(V0SelectWalletController.class);

	@Autowired 
	V0WalletDetailService walletDetailService ;

	@Autowired
	V2BlockChainService blockChainService;

	@Autowired
	V3ChainInfoService chainInfoService;

	@Autowired
	AddressMapper addressMapper;

	@Autowired
	TransactionMapper transactionMapper;


	@ApiOperation(value = "Addresses List" , notes="outputvalue [ data : 결과 , totalData: 총 주소 갯수  ] ")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/addrList")
	public CommonRes addrList(@PathVariable String chainName,
			@Valid
			@RequestParam(value="page",required=false) Integer page
			) {
		TenantContext.setTenant(chainName);
		CommonRes cRes = new CommonRes();
		PageReq req = new PageReq();
		try {
		
		req.setPage(page);
		if(req.getPage()==null){
			req.setPage(1);
		}
		logger.info("====================");
		logger.info("addrList : {}" );

		return walletDetailService.selectAddrList(req);
		
		} catch (Exception e) {
			logger.error("addrList"  , e);
			cRes.setError();
		}finally {
			TenantContext.clearTenant();
		}
		
		return cRes;
		
	}

	@GetMapping(value = "/balance")
	public String getBalance(@PathVariable String chainName,
			@Valid
			@RequestParam(value="address") String address
			) {
		
		try {
			String url = chainInfoService.chainHost(chainName);
			TenantContext.setTenant(chainName);
		logger.info("====================");
		logger.info("[balance] address : {}", address );

		return blockChainService.getBalance(url, address);
		
		} catch (Exception e) {
			logger.error("balance"  , e);
			return null;
		} finally {
			TenantContext.clearTenant();
		}
	}
}
