package com.dfg.icon.web.v0.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.dfg.icon.core.v3.service.database.tenant.TenantContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dfg.icon.core.v0.service.V0TransactionService;
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
@Api(tags = {"v0 transaction"})
@RequestMapping("v0/{chainName}/transaction")
@RestController
public class V0SelectTxController {
    private static final Logger logger = LoggerFactory.getLogger(V0SelectTxController.class);

    @Autowired
    V0TransactionService transactionService ;

    @ApiOperation(value = "RecentTx List" , notes="inputvalue [ page : 보려하는 페이지 ] \n\n"
    		+ "outputvalue [ data : 결과  , totalData: 트랜잭션 총 갯수 ] ")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success", response = CommonRes.class)
            })
    @GetMapping(value = "/recentTx")
    public CommonRes recentTxList(@PathVariable String chainName,
			@Valid
    		@RequestParam(value="page",required=false ) Integer page
    )  {
		TenantContext.setTenant(chainName);
    	CommonRes cRes = new CommonRes();
    	try {

    	PageReq req = new PageReq();
    	req.setPage(page);
    	if(req.getPage()==null){
    		req.setPage(1);
    	}
        logger.info("====================");
        logger.info("recentTx : {}");

        return transactionService.selectRecentTx(req);

    	} catch (Exception e) {
    		logger.error("recentTx"  , e);
			cRes.setError();
		}finally {
			TenantContext.clearTenant();
		}
		return cRes;
    }

    @ApiOperation(value = "Transaction Detail" , notes="inputvalue [ txHash : 선택한 txHash 값  ] \n\n"
			+ "outputvalue [ data : 결과  ] ")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success", response = CommonRes.class)
            })
    @GetMapping(value = "/txDetail")
    public CommonRes txDetail(@PathVariable String chainName,
			@Valid
    		@RequestParam("txHash") String txHash
    ) {
		TenantContext.setTenant(chainName);
    	CommonRes cRes = new CommonRes();

    	try {

    	PageReq req = new PageReq();
    	req.setTxHash(txHash);
        logger.info("====================");
        logger.info("txDetail : {}", req.toString());


        return transactionService.selectTxDetail(req);
    	} catch (Exception e) {
    		logger.error("txDetail"  , e);
			cRes.setError();
		} finally {
			TenantContext.clearTenant();
		}
		return cRes;
    }




}
