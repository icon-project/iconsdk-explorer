package com.dfg.icon.web.v3.controller;

import com.dfg.icon.core.common.service.ResourceService;
import com.dfg.icon.core.dao.icon.TMainChart;
import com.dfg.icon.core.dao.icon.TMainTx;
import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.mappers.icon.MainMapper;
import com.dfg.icon.core.mappers.icon.TransactionV3Mapper;
import com.dfg.icon.core.v0.service.V0MainService;
import com.dfg.icon.core.v3.service.database.tenant.TenantContext;
import com.dfg.icon.web.v0.dto.CommonRes;
import com.dfg.icon.web.v0.dto.SimpleRes;
import com.dfg.icon.web.v0.dto.main.MainBlock;
import com.dfg.icon.web.v0.dto.main.MainInfo;
import com.dfg.icon.web.v0.dto.main.MainRes;
import com.dfg.icon.web.v3.dto.IcxCirculation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author bsm
 * 2018-01-22
 */
@Api(tags = {"v3 main"})
@RequestMapping("v3/{chainName}/main")
@RestController
public class V3SelectMainController {
	private static final Logger logger = LoggerFactory.getLogger(V3SelectMainController.class);

	@Autowired 
	V0MainService mainService ;

	@Autowired
	ResourceService resourceService;

	@Autowired
	MainMapper mainMapper;

	@ApiOperation(value = "Information Summary , Daily Transactions , Recent Transactions , Recent Blocks " , notes="outputvalue [ data : 결과  ]")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/mainInfo")
	public MainRes mainInfo(@PathVariable String chainName) {
		TenantContext.setTenant(chainName);
		MainRes res = new MainRes();

		try {

			logger.info("====================");
			logger.info("main page information : {}");

			MainInfo mainSummary = mainService.selectMainSummary();
			List<TMainTx> mainTx = mainService.selectRecentTx();
			List<MainBlock> mainBlock = mainService.selectRecentBlock();

			if(mainSummary==null || mainTx==null||mainBlock==null) {
				res.setCode(IconCode.NO_DATA);
			}else{
				res.setTmainInfo(mainSummary);
				res.setTmainTx(mainTx);
				res.setTmainBlock(mainBlock);
				res.setCode(IconCode.SUCCESS);
			}
			return res;

		} catch (Exception e) {
			logger.error("mainInfo"  , e);
			res.setError();
		} finally {
			TenantContext.clearTenant();
		}
		return res;
	}



	@ApiOperation(value = "Daily Transactions" , notes="outputvalue [ data : 결과  ]")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/mainChart")
	public SimpleRes mainChart(@PathVariable String chainName) {
		TenantContext.setTenant(chainName);
		SimpleRes res = new SimpleRes();

		try {

			logger.info("====================");
			logger.info("main page daily chart : {}");

			List<TMainChart> dailyTx = mainService.SelectDailyTx();

			if( dailyTx ==null ){
				res.setCode(IconCode.NO_DATA);
			}else{
				res.setData(dailyTx);
				res.setCode(IconCode.SUCCESS);
			}

		} catch (Exception e) {
			logger.error("mainChart" , e);
			res.setError();
		} finally {
			TenantContext.clearTenant();
		}
		return res;
	}

	@ApiOperation(value = "실제 유통량" , notes="실 유통량 조회")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/summary")
	public SimpleRes mainSummary(@PathVariable String chainName) {
		TenantContext.setTenant(chainName);
		SimpleRes res = new SimpleRes();

		try {
			logger.info("====================");
			logger.info("icx circulation request : {}");

			MainInfo info = mainService.selectMainSummary();
			IcxCirculation icx = new IcxCirculation();
			icx.setIcxCirculation(info.getIcxCirculationy());

			res.setResult("200");
			res.setDescription("success");
			res.setData(icx);

		} catch (Exception e) {
			logger.error("mainChart" , e);
			res.setError();
		} finally {
			TenantContext.clearTenant();
		}
		return res;
	}

	@ApiOperation(value = "tracker version" , notes="get tracker version")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/version")
	public SimpleRes getVersion(@PathVariable String chainName) {
		TenantContext.setTenant(chainName);
		SimpleRes res = new SimpleRes();
		res.setResult("200");
		res.setDescription("success");
		res.setData(resourceService.getTrackerVersion());
		TenantContext.clearTenant();
		return res;
	}

	@ApiOperation(value = "getTxCount in 24H" , notes="getTxCount in 24H")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/txCountIn24h")
	public SimpleRes selectTxCountInTwoFour(@PathVariable String chainName) {
		TenantContext.setTenant(chainName);
		SimpleRes res = new SimpleRes();
		res.setResult("200");
		res.setDescription("success");
		res.setData(mainMapper.selectTxCountInTwoFour());
		TenantContext.clearTenant();
		return res;
	}
}
