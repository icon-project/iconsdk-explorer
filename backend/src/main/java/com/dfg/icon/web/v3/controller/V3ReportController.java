package com.dfg.icon.web.v3.controller;

import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.v2.service.V2BlockChainService;
import com.dfg.icon.core.v3.service.V3AddressService;
import com.dfg.icon.core.v3.service.V3ReportService;
import com.dfg.icon.core.v3.service.V3TransactionService;
import com.dfg.icon.util.CommonUtil;
import com.dfg.icon.web.v3.dto.CommonListRes;
import com.dfg.icon.web.v3.dto.CommonRes;
import com.dfg.icon.web.v3.dto.PageReq;
import com.dfg.icon.web.v3.dto.ReportReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * 신고 controller
 *
 * @author LYJ
 */
@Api(tags = {"v3 report"})
@RequestMapping("v3/report")
@RestController
public class V3ReportController {
	private static final Logger logger = LoggerFactory.getLogger(V3ReportController.class);

	@Autowired
	V3ReportService reportService;

//	@ApiOperation(value = "주소 신고", notes = "주소 신고 \n\n reported : 신고된 주소\n\n reporter : 신고자 주소")
//	@ApiResponses(
//			value = {
//					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
//			})
//	@PostMapping(value = "/address")
//	public CommonRes reportAddress(@Valid
//			@RequestBody ReportReq req
//	) {
//		try {
//
//			logger.info("====================");
//			logger.info("report address : {}", req.toString());
//
//			return addressService.registerReport(req);
//
//		} catch (Exception e) {
//			CommonUtil.printException(logger, "reportAddressError : {}", e);
//			CommonRes cRes = new CommonRes();
//			cRes.setError();
//			return cRes;
//		}
//	}

	@ApiOperation(value = "주소 신고", notes = "주소 신고 \n\n reported : 신고된 주소\n\n reporter : 신고자 주소")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@PostMapping(value = "/address")
	public CommonRes reportAddress(
			@RequestParam(value = "imgFile", required = false) MultipartFile imgFile,
			@RequestParam(value = "reported") String reported,
			@RequestParam(value = "reporter") String reporter,
			@RequestParam(value = "refUrl", required = false) String refUrl,
			@RequestParam(value = "reportType", defaultValue = "0") Integer reportType
	) {
		try {

			ReportReq req = new ReportReq();
			req.setRefUrl(refUrl);
			req.setReported(reported);
			req.setReporter(reporter);
			req.setReportType(reportType);

			logger.info("====================");
			logger.info("report address : {}", req.toString());

			return reportService.registerReport(req, imgFile);

		} catch (Exception e) {
			CommonUtil.printException(logger, "reportAddressError : {}", e);
			CommonRes cRes = new CommonRes();
			cRes.setError();
			return cRes;
		}
	}
}
