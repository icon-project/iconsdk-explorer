package com.dfg.icon.web.v0.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.dfg.icon.core.mappers.icon.TransactionMapper;
import com.dfg.icon.web.v3.dto.TxDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("v0/wallet")
@RestController
public class V0SelectWalletController {
	private static final Logger logger = LoggerFactory.getLogger(V0SelectWalletController.class);

	@Autowired 
	V0WalletDetailService walletDetailService ;

	@Autowired
	V2BlockChainService blockChainService;

	@Autowired
	AddressMapper addressMapper;

	@Autowired
	TransactionMapper transactionMapper;
	
	@Value("${server.url}")
	String serverUrl;
	
	@Value("${qrCode.path}")
	String qrcodePath;

	@ApiOperation(value = "Addresses List" , notes="outputvalue [ data : 결과 , totalData: 총 주소 갯수  ] ")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
			})
	@GetMapping(value = "/addrList")
	public CommonRes addrList(@Valid 
			@RequestParam(value="page",required=false) Integer page
			) {
		
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
		}
		
		return cRes;
		
	}

//	@ApiOperation(value = "Wallet Information and Transaction List" , notes="inputvalue [ address : 주소(from , to) , startDate : 시작날짜 (2017-12-28) ,endDate:끝날짜 (2018-01-01), page: 페이지  ] \n\n"
//			+ "outputvalue [ data : 결과  , totalData: 주소에 해당하는 트랜잭션 갯수 ] ")
//	@ApiResponses(
//			value = {
//					@ApiResponse(code = 200, message = "Success", response = CommonRes.class)
//			})
//	@GetMapping(value = "/walletDetailTxList")
//	public CommonRes walletDetailTxList(@Valid
//			@RequestParam("address") String address,
//			@RequestParam(value="startDate", required=false) String startDate,
//			@RequestParam(value="endDate" , required=false) String endDate,
//			@RequestParam(value="page",required=false) Integer page
//
//			) {
//
//		CommonRes cRes = new CommonRes();
//
//		try {
//			WalletTxReq req = new WalletTxReq();
//			req.setAddress(address);
//			req.setStartDate(startDate);
//			req.setEndDate(endDate);
//			req.setPage(page);
//			if(req.getPage()==null){
//				req.setPage(1);
//			}
//			logger.info("====================");
//			logger.info("walletDetailTxList : {}", req.toString());
//
//			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
//			accessCollectService.putAccessLogHashMap(request);
//
//			// 해당 주소가 qrcode가 없으면은 qrcode를 신규 생성
////			QRcodeUtil.checkQrCode(serverUrl, qrcodePath, address);
//
//			WalletRes res = new WalletRes();
//			WalletInfoDto walletDetail = walletDetailService.selectWalletDetail(req);
//			List<TxDetail> walletTx = walletDetailService.selectWalletTx(req);
//
//			if(walletDetail==null){
//				cRes.setData(res);
//				cRes.setTotalData("");
//				cRes.setCode(IconCode.NO_DATA);
//			}else {
//
//				res.setWalletDetail(walletDetail);
//				res.setWalletTx(walletTx);
//
//				PageReq pageReq = new PageReq(10);
//				pageReq.setAddress(address);
//				cRes.setTotalData(transactionMapper.selectTotalTxCount(pageReq).toString());
//				cRes.setData(res);
//				cRes.setCode(IconCode.SUCCESS);
//			}
//		} catch (Exception e) {
//			logger.error("walletDetailTxList"  , e);
//			cRes.setError();
//		}
//
//		return cRes;
//
//	}

	@GetMapping(value = "/balance")
	public String getBalance(@Valid
			@RequestParam(value="address") String address
			) {
		
		try {
		
		logger.info("====================");
		logger.info("[balance] address : {}", address );

		return blockChainService.getBalance(address);
		
		} catch (Exception e) {
			logger.error("balance"  , e);
			return null;
		}
	}
}
