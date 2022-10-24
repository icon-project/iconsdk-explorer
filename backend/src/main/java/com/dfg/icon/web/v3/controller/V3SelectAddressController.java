package com.dfg.icon.web.v3.controller;

import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.v2.service.V2BlockChainService;
import com.dfg.icon.core.v3.service.V3AddressService;
import com.dfg.icon.core.v3.service.V3TransactionService;
import com.dfg.icon.core.v3.service.database.tenant.TenantContext;
import com.dfg.icon.util.CommonUtil;
import com.dfg.icon.util.Validator;
import com.dfg.icon.web.v3.dto.*;
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
 * 주소페이지 조회용 controller
 *
 * @author LYJ
 */
@Api(tags = {"v3 address"})
@RequestMapping("v3/{chainName}/address")
@RestController
public class V3SelectAddressController {
	private static final Logger logger = LoggerFactory.getLogger(V3SelectAddressController.class);

	@Autowired
	V3AddressService addressService;

	@Autowired
	V2BlockChainService blockChainService;

	@Autowired
	V3TransactionService transactionService;

	@ApiOperation(value = "주소 리스트 조회", notes = "address page, 주소 리스트 조회")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/list")
	public CommonListRes addrList(
			@PathVariable String chainName,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "count", required = false) Integer count
			) {
		try {
			TenantContext.setTenant(chainName);
			logger.info("====================");
			logger.info("addressList : page = {}, count = {}", page, count);

			PageReq req = new PageReq();
			req.setPage(CommonUtil.changeUnderZero(page));
			if(count != null) {
				req.setCounting(count);
			}

			return addressService.getAddressList(req);

		} catch (Exception e) {
			CommonUtil.printException(logger, "addrListError : {}", e);
			CommonListRes cRes = new CommonListRes();
			cRes.setError();
			return cRes;
		} finally {
			TenantContext.clearTenant();
		}
	}

	@ApiOperation(value = "주소 상세정보 조회", notes = "address page, 상세 정보 조회")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/info")
	public CommonRes addressInfo(
			@PathVariable String chainName,
			@RequestParam("address") String address
			) {
		TenantContext.setTenant(chainName);
		try {
			if(!Validator.isAddressPattern(address)) {
				CommonRes cRes = new CommonRes();
				cRes.setError();
				return cRes;
			}
			PageReq req = new PageReq();
			req.setAddress(address);

			logger.info("====================");
			logger.info("addressInfo : address = {}", address);

			return addressService.selectAddressInfo(req);
		} catch (Exception e) {
			CommonUtil.printException(logger, "addressInfoError : {}", e);
			CommonRes cRes = new CommonRes();
			cRes.setError();
			return cRes;
		} finally {
			TenantContext.clearTenant();
		}
	}

	@ApiOperation(value = "주소의 거래내역 조회", notes = "address page, 트랜잭션 조회" + "\n\n" 
			+ "txtype : 0 (icx transfer) " + "\n\n"
			+ "txType : 1 (token transfer) " + "\n\n"
			+ "txType : 2 (score call) " + "\n\n"
			+ "txType : 3 (score install) " + "\n\n"
			+ "txType : 4 (score update) " + "\n\n"
			+ "\n\n"
			+ " pageKind : 이전 페이지면 pre / 다음 페이지면 next  "+ "\n\n"
			+ " id       : 이전 페이지면 현재화면의 첫번째 데이터 아이디  / 다음 페이지면 이전 페이지면 현재화면의 마지막 데이터 아이디 "+ "\n\n"
	)
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/txList")
	public CommonListRes addressTxList(
			@PathVariable String chainName,
			@RequestParam("address") String address,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "count", required = false) Integer count,
			@RequestParam(value = "type", required = false) Byte type,
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "pageKind", required = false) String pageKind
		   ) {
		TenantContext.setTenant(chainName);
		CommonListRes cRes = new CommonListRes();
		if(!Validator.isAddressPattern(address)) {
			cRes.setError();
			return cRes;
		}
		try {
			PageReq req = new PageReq(10);
			if(count!=null){
				req.setCounting(count);
			}

			if(type != null && !"".equals(type)){
				req.setTxType(type);
			}

			if(id != null && !"".equals(id)){
				req.setId(id);
			}

			if(pageKind != null && !"".equals(pageKind)){
				if( !pageKind.equals(IconCode.pageKind_Next) && !pageKind.equals(IconCode.pageKind_Pre)){
					throw  new Exception();
				}
				req.setPageKind(pageKind);
			}

			req.setAddress(address);
			req.setPage(CommonUtil.changeUnderZero(page));
			logger.info("====================");
			logger.info("addressTxList : address = {} , page = {}, count = {} ", address, page, count);

			cRes = addressService.getAddressTxList(req);

		} catch (Exception e) {
			CommonUtil.printException(logger, "addressTxListError : {}", e);
			cRes.setError();
		} finally {
			TenantContext.clearTenant();
		}

		return cRes;

	}


	@ApiOperation(value = "주소의 거래내역 조회(지갑용)", notes = "address page, 트랜잭션 조회" + "\n\n" 
			+ "txtype : 0 (icx transfer) " + "\n\n"
			+ "txType : 1 (token transfer) " + "\n\n"
			+ "txType : 2 (score call) " + "\n\n"
			+ "txType : 3 (score install) " + "\n\n"
			+ "txType : 4 (score update) " + "\n\n")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/txListForWallet")
	public CommonListRes addressTxListForWallet(
			@PathVariable String chainName,
			@RequestParam("address") String address,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "count", required = false) Integer count,
			@RequestParam(value = "type", required = false) Byte type
			) {
		TenantContext.setTenant(chainName);
		CommonListRes cRes = new CommonListRes();
		if(!Validator.isAddressPattern(address)) {
			cRes.setError();
			return cRes;
		}
		try {
			PageReq req = new PageReq(10);
			if(count!=null){
				req.setCounting(count);
			}
			if(type != null && !"".equals(type)){
				req.setTxType(type);
			}
			req.setAddress(address);
			req.setPage(CommonUtil.changeUnderZero(page));
			logger.info("====================");
			logger.info("addressTxListForWallet : address = {} , page = {}, count = {} ", address, page, count);

			cRes = addressService.getAddressTxListForWallet(req);

		} catch (Exception e) {
			CommonUtil.printException(logger, "addressTxListError : {}", e);
			cRes.setError();
		} finally {
			TenantContext.clearTenant();
		}

		return cRes;

	}


	@ApiOperation(value = "주소의 토큰 거래내역 조회", notes = "address page, 토큰 트랜잭션 조회" +"\n\n" + "contractAddr이 targetContractAddr과 동일")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/tokenTxList")
	public CommonListRes addressTokenTxList(
			@PathVariable String chainName,
			@RequestParam("address") String address,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "count", required = false) Integer count

			) {
		TenantContext.setTenant(chainName);
		CommonListRes cRes = new CommonListRes();
		if(!Validator.isAddressPattern(address)) {
			cRes.setError();
			return cRes;
		}
		try {
			PageReq req = new PageReq();
			req.setAddress(address);
			req.setPage(CommonUtil.changeUnderZero(page));
			if(count != null) {
				req.setCounting(count);
			}


			logger.info("====================");
			logger.info("addressTokenTxList :  page = {} , count = {}", page, count);


			cRes = addressService.selectAddressTokenTx(req);
			
		} catch (Exception e) {
			CommonUtil.printException(logger, "addressTokenTxListError : {}", e);
			cRes.setError();
		} finally {
			TenantContext.clearTenant();
		}

		return cRes;

	}

	@ApiOperation(value = "주소의 Internal Tx 조회", notes = "address page, Internal Tx 조회")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/internalTxList")
	public CommonListRes addressInternalTxList(
			@PathVariable String chainName,
			@RequestParam("address") String address,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "count", required = false) Integer count

			) {
		TenantContext.setTenant(chainName);
		CommonListRes cRes = new CommonListRes();
		if(!Validator.isAddressPattern(address)) {
			cRes.setError();
			return cRes;
		}
		try {
			logger.info("====================");
			logger.info("addressTokenTxList :  page = {} , count = {}", page, count);

			PageReq req = new PageReq();
			req.setAddress2(address);
			req.setPage(CommonUtil.changeUnderZero(page));
			if(count != null) {
				req.setCounting(count);
			}

			return transactionService.selectInternalTxList(req);
		} catch (Exception e) {
			CommonUtil.printException(logger, "addressTokenTxListError : {}", e);
			cRes.setError();
		} finally {
			TenantContext.clearTenant();
		}

		return cRes;

	}

	@ApiOperation(value = "유효잔액 보유 주소 개수 조회", notes = "유효잔액 보유 주소 개수 조회")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/count")
	public CommonRes addressCount(@PathVariable String chainName) {
		TenantContext.setTenant(chainName);
		CommonRes cRes = new CommonRes();

		try {
			logger.info("====================");
			logger.info("addressCount :  ");

			return addressService.getBalanceAddressCount();
		} catch (Exception e) {
			CommonUtil.printException(logger, "addressTokenTxListError : {}", e);
			cRes.setError();
		} finally {
			TenantContext.clearTenant();
		}

		return cRes;

	}
}
