package com.dfg.icon.web.v3.controller;

import javax.validation.Valid;

import com.dfg.icon.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.v3.service.V3AddressService;
import com.dfg.icon.core.v3.service.V3ContractService;
import com.dfg.icon.core.v3.service.V3TransactionService;
import com.dfg.icon.util.CommonUtil;
import com.dfg.icon.web.v3.dto.CommonListRes;
import com.dfg.icon.web.v3.dto.CommonRes;
import com.dfg.icon.web.v3.dto.ContractInfo;
import com.dfg.icon.web.v3.dto.PageReq;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.Calendar;
import java.util.Date;

/**
 * 스코어 리스트 조회 
 * @author hslee
 */
@Api(tags = {"v3 Contract"})
@RequestMapping("v3/contract")
@RestController
public class V3SelectContractController {
	private static final Logger logger = LoggerFactory.getLogger(V3SelectContractController.class);

	@Autowired
	V3ContractService v3ContractService;

	@Autowired
	V3AddressService addressService;

	@Autowired
	V3TransactionService transactionService;


	/** 컨트렉트 리스트 조회
	 */
	@ApiOperation(value = "Contract List" , notes="inputvalue [ page : 페이지   ]  outputvalue [ data : 결과   ( state ->[0: 대기 1: 성공 2: 실패]  ) ] ")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonListRes.class)
			})
	@GetMapping(value = "/list")
	public CommonListRes selectContractList(@Valid 
			@RequestParam(value="page", required=false, defaultValue = "1" ) Integer page,
			@RequestParam(value = "count", required = false) Integer count,
			@RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "keyword", required = false) String keyword
			){

		CommonListRes res = new CommonListRes();
		try {

			PageReq req = new PageReq();
			req.setPage(CommonUtil.changeUnderZero(page));
			if(count != null) {
				req.setCounting(count);
			}
			if(status != null) {
				req.setState(status);
			}
			if(keyword != null && !"".equals(keyword)) {
				if(keyword.length() > 2 && "cx".equals(keyword.toLowerCase().substring(0,2))) {
					req.setAddress(keyword);
				} else {
					req.setKeyword(keyword.toLowerCase());
				}
			}
			res.setListSize(v3ContractService.selectContractListCount(req));
			if(res.getListSize() > 0) {
				res.setData(v3ContractService.selectContractList(req));
				res.setCode(IconCode.SUCCESS);
			} else {
				res.setCode(IconCode.NO_DATA);
			}

		} catch (Exception e) {
			CommonUtil.printException(logger, "selectContractList :: {}", e);
			res.setError();
		}
		return res;
	}


	/** 컨트렉트 정보 조회
	 */
	@ApiOperation(value = "Contract Info" , notes="inputvalue [ addr : 주소]  outputvalue [ data : 결과  ( state ->[0: 대기 1: 성공 2: 실패]  ) ] ")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/info")
	public CommonRes selectContractInfo(@Valid 
			@RequestParam(value="addr", required=true ) String addr
			){
		CommonRes res = new CommonRes();
		try {

			ContractInfo result = v3ContractService.selectContractInfo(addr);
			res.setData((result == null) ? IconCode.NO_DATA : result);
			res.setCode(IconCode.SUCCESS);
		} catch (Exception e) {
			CommonUtil.printException(logger, "selectContractInfo :: {}", e);
			res.setError();
		}
		return res;
	}
 
	
	@ApiOperation(value = "Contract Detail" , notes=" outputvalue [ data : 결과   ( state ->[0: 대기 1: 성공 2: 실패]  ) ] ")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/detail")
	public CommonRes selectContractDetail(@Valid 
			@RequestParam(value="contractAddr", required=true ) String contractAddr

			){

		CommonRes res = new CommonRes();
		try {

			PageReq req = new PageReq();
			req.setAddress(contractAddr);
			
			res.setData(v3ContractService.selectContractDetail(req));
			res.setCode(IconCode.SUCCESS);
			
		} catch (Exception e) {
			CommonUtil.printException(logger, "selectContractList :: {}", e);
			res.setError();
		}
		return res;
	}
	

	/** 컨트랙트 트랜잭션 조회  
	 * @param page
	 * @return
	 */
	@ApiOperation(value = "Contract Transaction 조회 " , notes="inputvalue [addr : 컨트렉트 주소 , page : 페이지 ]  outputvalue [ data : 결과  ] \n\n\n\n"
	+ "txType" + "\n\n"
			+ "type : 0 (icx transfer) " + "\n\n"
			+ "type : 1 (token transfer) " + "\n\n"
			+ "type : 2 (score call) " + "\n\n"
			+ "type : 3 (score install) " + "\n\n"
			+ "type : 4 (score update) " + "\n\n")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/txList")
	public CommonListRes selectContractTransactionList(@Valid 
			@RequestParam(value="addr", required=true ) String addr ,
			@RequestParam(value="page", required=false, defaultValue = "1" ) int page,
			@RequestParam(value = "count", required = false) Integer count

			){
		CommonListRes res = new CommonListRes();
		try {
			PageReq req = new PageReq(10);
			if(count!=null){
				req.setCounting(count);
			}
			req.setAddress(addr);
			req.setPage(CommonUtil.changeUnderZero(page));
			res = v3ContractService.selectContractTxList(req);
		} catch (Exception e) {
			CommonUtil.printException(logger, "selectContractTransactionList :: {}", e);
			res.setError();
		}
		return res;
	}


	/** 컨트랙트 Token Transfers 조회   
	 * @param page
	 * @return
	 */
	@ApiOperation(value = "Contract Token Transfers 조회 " , notes="inputvalue [addr : 컨트렉트 주소 , page : 페이지 ]  outputvalue [ data : 결과   ] ")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/tokenTxList")
	public CommonListRes selectContractTokenTransferList(@Valid 
			@RequestParam(value="addr", required=true ) String addr , 
			@RequestParam(value="page", required=false, defaultValue = "1" ) int page,
			@RequestParam(value = "count", required = false,  defaultValue = "10" ) Integer count

			){
		CommonListRes res = new CommonListRes();
		try {
			PageReq req = new PageReq(10);
			if(count!=null){
				req.setCounting(count);
			}
			req.setAddress(addr);
			req.setPage(CommonUtil.changeUnderZero(page));
			res = v3ContractService.selectContractTokenTxList(req);
		} catch (Exception e) {
			CommonUtil.printException(logger, "selectContractTokenTxList :: {}", e);
			res.setError();
		}

		return res;
	}

	/** 컨트랙트 Internal Tx 조회
	 */
	@ApiOperation(value = "Contract Internal Tx 조회 " , notes="inputvalue [addr : 컨트렉트 주소 , page : 페이지 ]  outputvalue [ data : 결과   ] ")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/internalTxList")
	public CommonListRes selectContractInternalTransferList(@Valid
														 @RequestParam(value="addr", required=true ) String addr ,
														 @RequestParam(value="page", required=false, defaultValue = "1" ) int page,
														 @RequestParam(value = "count", required = false,  defaultValue = "10" ) Integer count

	){
		CommonListRes res = new CommonListRes();
		try {

			PageReq req = new PageReq();
			req.setPage(CommonUtil.changeUnderZero(page));
			if(count != null) {
				req.setCounting(count);
			}
			req.setAddress(addr);

			res = transactionService.selectInternalTxList(req);
		} catch (Exception e) {
			CommonUtil.printException(logger, "selectContractInternalTxList Error : {}", e);
			res.setError();
		}
		return res;
	}


	/** 컨트랙트 history 조회   
	 * @param page
	 * @return
	 */
	@ApiOperation(value = "Contract History 조회 " , notes="inputvalue [addr : 컨트렉트 주소 , page : 페이지 ]  outputvalue [ data : 결과  ( state ->[0: 대기 1: 성공 2: 실패]  )  ]  ")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/historyList")
	public CommonListRes selectContractHistoryList(@Valid 
			@RequestParam(value="addr", required=false ) String addr , 
			@RequestParam(value="page", required=false, defaultValue = "1" ) int page,
			@RequestParam(value = "count", required = false,  defaultValue = "10" ) Integer count){

		CommonListRes res = new CommonListRes();
		try {

			int listpage = CommonUtil.changeUnderZero(page);
			res.setData(v3ContractService.selectContractHistoryList(addr, listpage, count));
			res.setListSize(v3ContractService.selectContractHistoryListCount(addr));
			res.setCode(IconCode.SUCCESS);
		} catch (Exception e) {
			CommonUtil.printException(logger, "selectContractTokenTransferList :: {}", e);
			res.setError();
		}
		return res;
	}


	/** Contract EventLogList 조회
	 * @param page
	 * @param contractAddr
	 * @return
	 */
	@ApiOperation(value = "Contract EventLogList" , notes="inputvalue [ page : 보려하는 페이지 , count : 페이징 갯수  ,contractAddr: 컨트랙트 주소 ,txHash: txHash] \n\n"
			+ "outputvalue [ data : 결과  ] ")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonListRes.class)
			})
	@GetMapping(value = "/eventLogList")
	public CommonListRes getTokenEventLogList(@Valid
			@RequestParam(value="page",required=false ) Integer page,
			@RequestParam(value = "count", required = false) Integer count,
			@RequestParam(value = "contractAddr", required = false) String contractAddr
			)  {

		CommonListRes res = new CommonListRes();
		try {

			logger.info("====================");
			logger.info("getTokenEventLogList : page={} , count={} ,contractHash={}" , page, count ,contractAddr);

			PageReq req = new PageReq(10);
			req.setPage(CommonUtil.changeUnderZero(page));
			if(count != null) {
				req.setCounting(count);
			}

			if(contractAddr !=null && !"".equals(contractAddr)){
				req.setAddress(contractAddr);
			}

			res =  v3ContractService.selectTokenEventLogList(req);
			res.setCode(IconCode.SUCCESS);
		} catch (Exception e) {

			CommonUtil.printException(logger, "getTokenEventLogList error : {}", e);
			res.setError();
		}
		return res;
	}

	/** 승인이 필요한 컨트랙트(인스톨, 마지막 업데이트) 항목 조회
	 * @param page
	 * @return
	 */
	@ApiOperation(value = "Contract History 조회 " , notes="inputvalue [addr : 컨트렉트 주소 , page : 페이지 ]  outputvalue [ data : 결과  ( state ->[0: 대기 1: 성공 2: 실패]  )  ]  ")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/pendingList")
	public CommonListRes selectRequireContractList(@Valid
												   @RequestParam(value="page", required=false, defaultValue = "1" ) int page,
												   @RequestParam(value = "count", required = false,  defaultValue = "10" ) Integer count){
		CommonListRes res = new CommonListRes();
		try {
			PageReq req = new PageReq();
			req.setPage(CommonUtil.changeUnderZero(page));
			req.setCounting(count);
			res.setData(v3ContractService.selectRequireContractList(req));
			res.setListSize(v3ContractService.selectRequireContractListCount());
			res.setCode(IconCode.SUCCESS);
		} catch (Exception e) {
			CommonUtil.printException(logger, "selectContractTokenTransferList :: {}", e);
			res.setError();
		}
		return res;
	}

//	@ApiOperation(value = "Contract Tx 주소별 개수 조회 " , notes="startDate(yyyy-MM-dd) 15:00:00 ~ endDate(yyyy-MM-dd) 14:59:59")
//	@GetMapping(value = "/txCountForChallengeGroupByAddress")
//	public CommonRes selectScoreTxCountForChallenge(@Valid
//													@RequestParam(value="addr") String addr,
//													@RequestParam(value="date") String date
//	){
//		CommonRes res = new CommonRes();
//		long startTime = System.currentTimeMillis();
//		try {
//			PageReq req = new PageReq(100);
//			req.setAddress(addr);
//			Date dDay = DateUtil.getFormattedDate(date, "yyyy-MM-dd");
//			req.setStartDate(
//					DateUtil.getFormattedDateString(
//							DateUtil.getNextDateWithDateType(dDay, Calendar.DATE, -1), "yyyy-MM-dd"
//					) + " 15:00:00"
//			);
//			req.setEndDate(date + " 14:59:59");
//			res = v3ContractService.selectContractTxCountForTxChallengeGroupByAddress(req);
//		} catch (Exception e) {
//			CommonUtil.printException(logger, "selectContractTransactionCount : {}", e);
//			res.setError();
//		}
//		return res;
//	}
}
