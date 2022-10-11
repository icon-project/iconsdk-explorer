package com.dfg.icon.web.v3.controller;

import com.dfg.icon.core.v3.service.V3TransactionService;
import com.dfg.icon.util.CommonUtil;
import com.dfg.icon.web.v3.dto.CommonRes;
import com.dfg.icon.web.v3.dto.CommonListRes;
import com.dfg.icon.web.v3.dto.PageReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 트랜잭션 페이지 조회용 controller
 * @author LYJ
 */
@Api(tags = {"v3 transaction"})
@RequestMapping("v3/transaction")
@RestController
public class V3SelectTxController {
	private static final Logger logger = LoggerFactory.getLogger(V3SelectTxController.class);

	@Autowired
	V3TransactionService transactionService ;

	/**
	 * tx조회 (페이징)
	 */
	@ApiOperation(value = "tx 페이징 조회" , notes="tx page, 리스트 조회")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/recentTx")
	public CommonListRes recentTxList(@Valid
			@RequestParam(value="page",required = false) Integer page,
			@RequestParam(value="count",required = false) Integer count

			) {

		try {
			logger.info("====================");
			logger.info("recentTx : {}");

			PageReq req = new PageReq();
			req.setPage(CommonUtil.changeUnderZero(page));
			if(count != null) {
				req.setCounting(count);
			}

			return transactionService.selectRecentTx(req);

		} catch (Exception e) {
			CommonListRes cRes = new CommonListRes();
			CommonUtil.printException(logger, "recentTxList Error {}" ,e);
			cRes.setError();
			return cRes;
		}
	}

	/**
	 * tx 상세 조회
	 */
	@ApiOperation(value = "Tx 상세 조회" , notes="txType" + "\n\n"
			+ "type : 0 (icx transfer) " + "\n\n"
			+ "type : 1 (token transfer) " + "\n\n"
			+ "type : 2 (score call) " + "\n\n"
			+ "type : 3 (score install) " + "\n\n"
			+ "type : 4 (score update) " + "\n\n"
			+ "type : 5 (score accept) " + "\n\n"
			+ "type : 6 (score reject) " + "\n\n"
			+ "type : 7 (update accept) " + "\n\n"
			+ "type : 8 (update reject) " + "\n\n"
			+ "type : 9 (update canceled) " + "\n\n")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/txDetail")
	public CommonRes getTxDetail(@Valid
			@RequestParam("txHash") String txHash
			) {
		try {
			logger.info("====================");
			logger.info("txDetail : {}", txHash);

			StringBuffer hexPrefixSb = new StringBuffer();
			hexPrefixSb.append("0x");

			if(!txHash.startsWith(hexPrefixSb.toString())){
				txHash = hexPrefixSb.append(txHash).toString();
			}

			return transactionService.selectTxDetail(txHash);
		} catch (Exception e) {
			CommonRes cRes = new CommonRes();
			CommonUtil.printException(logger, "getTxDetail Error {}" ,e);
			cRes.setError();
			return cRes;
		}
	}

	/** Internal Transaction List 조회
	 * @return
	 */
	@ApiOperation(value = "Internal Transaction List" , notes="inputvalue [ page : 보려하는 페이지 , count : 페이징 개수, txHash : 트랜잭션 해시] \n\n"
			+ "outputvalue [ data : 결과  ] ")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonListRes.class)
			})
	@GetMapping(value = "/internalTxList")
	public CommonListRes getInternalTxList(@Valid
			@RequestParam(value="page",required=false ) Integer page,
			@RequestParam(value = "count", required = false) Integer count,
			@RequestParam(value = "txHash", required = false) String txHash
			)  {

		CommonListRes res = new CommonListRes();
		try {

			logger.info("====================");
			logger.info("getInternalTxList : page={} , count={} , txHash={} " , page, count ,txHash);

			PageReq req = new PageReq();
			req.setPage(CommonUtil.changeUnderZero(page));
			if(count != null) {
				req.setCounting(count);
			}
			if(txHash !=null && !"".equals(txHash)){
				req.setTxHash(txHash);
			}

			res =  transactionService.selectInternalTxList(req);

		} catch (Exception e) {

			CommonUtil.printException(logger, "getInternalTxList error : {}", e);
			res.setError();
		}
		return res;
	}


	/** Transaction EventLogList 조회
	 * @return
	 */
	@ApiOperation(value = "Transaction EventLogList" , notes="inputvalue [ page : 보려하는 페이지 , count : 페이징 갯수  , txHash: txHash] \n\n"
			+ "outputvalue [ data : 결과  ] ")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonListRes.class)
			})
	@GetMapping(value = "/eventLogList")
	public CommonListRes getTxEventLogList(@Valid
			@RequestParam(value="page",required=false ) Integer page,
			@RequestParam(value = "count", required = false) Integer count,
			@RequestParam(value = "txHash", required = false) String txHash
			)  {

		CommonListRes res = new CommonListRes();
		try {

			logger.info("====================");
			logger.info("getTokenEventLogList : page={} , count={} , txHash={} " , page, count ,txHash);

			PageReq req = new PageReq(10);
			req.setPage(CommonUtil.changeUnderZero(page));
			if(count != null) {
				req.setCounting(count);
			}

			if(txHash !=null && !"".equals(txHash)){
				req.setTxHash(txHash);
			}

			res =  transactionService.selectTxEventLogList(req);

		} catch (Exception e) {

			CommonUtil.printException(logger, "getTokenEventLogList error : {}", e);
			res.setError();
		}
		return res;
	}


	@ApiOperation(value = "TxData 조회" , notes="txData 조회(이미지 용)")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/txData")
	public String getTxData(@Valid
								 @RequestParam("txHash") String txHash
	) {
		try {
			logger.info("====================");
			logger.info("txData : {}", txHash);

			StringBuffer hexPrefixSb = new StringBuffer();
			hexPrefixSb.append("0x");

			if(!txHash.startsWith(hexPrefixSb.toString())){
				txHash = hexPrefixSb.append(txHash).toString();
			}

			return transactionService.selectTxDataImage(txHash);
		} catch (Exception e) {
			CommonUtil.printException(logger, "getTxData Error : {}", e);
			return null;
		}
	}

	@ApiOperation(value = "TxCount 조회" , notes="txCount 조회(score)")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/count")
	public Long getTxCount(@Valid
							@RequestParam("type") String txType
	) {
		try {
			logger.info("====================");
			logger.info("count : type={}", txType);

			return transactionService.selectCount(txType);
		} catch (Exception e) {
			CommonUtil.printException(logger, "getCount Error : {}" ,e);
			return null;
		}
	}

}
