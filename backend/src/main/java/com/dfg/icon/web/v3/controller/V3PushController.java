package com.dfg.icon.web.v3.controller;

import com.dfg.icon.core.v3.service.V3PushService;
import com.dfg.icon.util.CommonUtil;
import com.dfg.icon.web.v3.dto.CommonRes;
import com.dfg.icon.web.v3.dto.PushKeyReq;
import com.dfg.icon.web.v3.dto.PushReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * push controller
 *
 * @author LYJ
 */
@Api(tags = {"v3 push"})
@RequestMapping("v3/push")
@RestController
public class V3PushController {
	private static final Logger logger = LoggerFactory.getLogger(V3PushController.class);

	@Autowired
	V3PushService pushService;

	@ApiOperation(value = "push 등록여부 확인", notes = "push 등록여부 확인")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/isRegistered")
	public CommonRes isRegisterPush(@Valid
								  @ModelAttribute PushKeyReq req
	) {
		try {

			logger.info("====================");
			logger.info("is registered : {}", req.toString());

			return pushService.isRegistered(req);

		} catch (Exception e) {
			CommonUtil.printException(logger, "isRegisterError : {}", e);
			CommonRes cRes = new CommonRes();
			cRes.setError();
			return cRes;
		}
	}

	@ApiOperation(value = "push 등록", notes = "push 등록")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@PostMapping(value = "/register")
	public CommonRes registerPush(@Valid
			@RequestBody PushReq req
	) {
		try {

			logger.info("====================");
			logger.info("register push : {}", req.toString());

			return pushService.registerPush(req);

		} catch (Exception e) {
			CommonUtil.printException(logger, "registerPushError : {}", e);
			CommonRes cRes = new CommonRes();
			cRes.setError();
			return cRes;
		}
	}

	@ApiOperation(value = "push 삭제", notes = "push 삭제")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@DeleteMapping(value = "/withdraw")
	public CommonRes withdrawPush(@Valid
								  @ModelAttribute PushKeyReq req
	) {
		try {

			logger.info("====================");
			logger.info("withdraw push : {}", req.toString());

			return pushService.withdrawPush(req);

		} catch (Exception e) {
			CommonUtil.printException(logger, "registerPushError : {}", e);
			CommonRes cRes = new CommonRes();
			cRes.setError();
			return cRes;
		}
	}
}
