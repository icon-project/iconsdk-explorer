package com.dfg.icon.web.v3.controller;

import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.exception.IconException;
import com.dfg.icon.core.mappers.icon.BlockMapper;
import com.dfg.icon.core.v3.service.V3BlockService;
import com.dfg.icon.core.v3.service.V3BtpHeaderService;
import com.dfg.icon.core.v3.service.V3BtpNetworkService;
import com.dfg.icon.core.v3.service.database.tenant.TenantContext;
import com.dfg.icon.util.CommonUtil;
import com.dfg.icon.web.v0.dto.TxInBlock;
import com.dfg.icon.web.v0.dto.block.Block;
import com.dfg.icon.web.v3.dto.CommonListRes;
import com.dfg.icon.web.v3.dto.CommonRes;
import com.dfg.icon.web.v3.dto.PageReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author bsm
 * 2018-01-22
 */
@Api(tags = {"v3 btp"})
@RequestMapping("v3/{chainName}/btp")
@RestController
public class V3SelectBtpController {
    private static final Logger logger = LoggerFactory.getLogger(V3SelectBtpController.class);

    @Autowired
	V3BtpHeaderService btpHeaderService;

	@Autowired
	V3BtpNetworkService btpNetworkService;

    
    @ApiOperation(value = "BTP Header List 페이징 조회" , notes="BTP Header list 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success", response = CommonRes.class)
            })
    @GetMapping(value = "/header/list")
    public CommonListRes getBtpHeaderList(@PathVariable String chainName,
			@Valid
			@RequestParam(value = "networkId", required = false) String networkId,
    		@RequestParam(value = "page", required = false) Integer page,
    		@RequestParam(value = "count", required = false) Integer count
    		)  {
		TenantContext.setTenant(chainName);
    	
    	try {
		
	        logger.info("====================");
	        logger.info("BTP Header : {}", page);

	        PageReq req = new PageReq();
	        req.setPage(CommonUtil.changeUnderZero(page) );
	        if(count != null) {
                req.setCounting(count);
            }
	
	        return btpHeaderService.getBtpHeaderList(req, networkId) ;
	        
	    	
			} catch (Exception e) {
				CommonListRes res = new CommonListRes();
				CommonUtil.printException(logger, "BTPHeaderError : {}", e);
				res.setError();
				return res;
			} finally {
			TenantContext.clearTenant();
		}
		
    }

	@ApiOperation(value = "BTP Header " , notes="BTP Header detail 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success", response = CommonRes.class)
            })
    @GetMapping(value = "/header/info")
    public CommonRes getBtpHeaderDetail(@PathVariable String chainName,
			@Valid
    		@RequestParam(value = "height", required = false) Integer height,
			@RequestParam(value = "networkId", required = false) String networkId
    ) {
		TenantContext.setTenant(chainName);
    	CommonRes cRes = new CommonRes();
    	try {
			if("".equals(height)) {
				height = null;
			}
			if("".equals(networkId)) {
				networkId = null;
			}

			if(height == null && networkId == null) {
				throw new IconException(IconCode.BTP_ERROR);
			}

	        logger.info("====================");
	        logger.info("BTP Header Detail : {}");

			cRes = btpHeaderService.getBtpHeader(height, networkId);

	        
    	} catch (Exception e) {
    		logger.error("blockDetail", e);
    		cRes.setError();
		} finally {
			TenantContext.clearTenant();
		}
		return cRes;
    }


	@ApiOperation(value = "BTP Network List 페이징 조회" , notes="BTP Network list 조회")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/network/list")
	public CommonListRes getBtpNetworkList(@PathVariable String chainName,
										  @Valid
										  @RequestParam(value = "page", required = false) Integer page,
										  @RequestParam(value = "count", required = false) Integer count
	)  {
		TenantContext.setTenant(chainName);

		try {

			logger.info("====================");
			logger.info("BTP Network : {}", page);

			PageReq req = new PageReq();
			req.setPage(CommonUtil.changeUnderZero(page) );
			if(count != null) {
				req.setCounting(count);
			}

			return btpNetworkService.getBtpNetworkList(req);


		} catch (Exception e) {
			CommonListRes res = new CommonListRes();
			CommonUtil.printException(logger, "BTPHeaderError : {}", e);
			res.setError();
			return res;
		} finally {
			TenantContext.clearTenant();
		}

	}

	@ApiOperation(value = "BTP Network " , notes="BTP Network detail 조회")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/network/info")
	public CommonRes getBtpHeaderDetail(@PathVariable String chainName,
										@Valid
										@RequestParam(value = "networkId", required = false) String networkId
	) {
		TenantContext.setTenant(chainName);
		CommonRes cRes = new CommonRes();
		try {
			if(networkId == "" || networkId == null) {
				throw new IconException(IconCode.BTP_ERROR);
			}

			logger.info("====================");
			logger.info("BTP Network Detail : {}");

			cRes = btpNetworkService.getBtpNetwork(networkId);

		} catch (Exception e) {
			logger.error("blockDetail", e);
			cRes.setError();
		} finally {
			TenantContext.clearTenant();
		}
		return cRes;
	}
}
