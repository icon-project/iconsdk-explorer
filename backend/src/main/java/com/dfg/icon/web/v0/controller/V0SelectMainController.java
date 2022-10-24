package com.dfg.icon.web.v0.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dfg.icon.core.v3.service.database.tenant.TenantContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dfg.icon.core.dao.icon.TMainChart;
import com.dfg.icon.core.dao.icon.TMainTx;
import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.v0.service.V0MainService;
import com.dfg.icon.web.v0.dto.CommonRes;
import com.dfg.icon.web.v0.dto.SimpleRes;
import com.dfg.icon.web.v0.dto.main.MainBlock;
import com.dfg.icon.web.v0.dto.main.MainInfo;
import com.dfg.icon.web.v0.dto.main.MainRes;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author bsm
 * 2018-01-22
 */
@Api(tags = {"v0 main"})
@RequestMapping("v0/{chainName}/main")
@RestController
public class V0SelectMainController {
	private static final Logger logger = LoggerFactory.getLogger(V0SelectMainController.class);

	@Autowired
	V0MainService mainService ;

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
		}finally {
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
	public SimpleRes mainChart() {

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
		}
		return res;
	}

}
