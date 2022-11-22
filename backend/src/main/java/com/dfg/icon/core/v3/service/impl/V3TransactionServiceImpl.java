package com.dfg.icon.core.v3.service.impl;

import java.util.List;

import com.dfg.icon.core.common.service.ResourceService;
import com.dfg.icon.core.dao.icon.*;
import com.dfg.icon.core.mappers.icon.*;
import com.dfg.icon.web.v0.dto.main.MainInfo;
import com.dfg.icon.web.v3.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.v3.service.V3TransactionService;
import com.dfg.icon.core.v3.vo.TxStateEnum;
import com.dfg.icon.util.FormatUtil;
import com.dfg.icon.util.HexUtil;

@Service
public class V3TransactionServiceImpl implements V3TransactionService {

	@Autowired
	TMainTxMapper tMainTxMapper;

	@Autowired
	TransactionV3Mapper transactionV3Mapper;

	@Autowired
	TTransactionMapper tTransactionMapper;

	@Autowired
	TTransactionTotalMapper tTransactionTotalMapper;

	@Autowired
	ContractMapper contractMapper;

	@Autowired
	TTxDataMapper tTxDataMapper;

	@Autowired
	MainMapper mainMapper;

	@Autowired
	BlockMapper blockMapper;

	@Autowired
	TFeeDetailsMapper tFeeDetailsMapper;

	@Autowired
	private ResourceService resourceService;

	/* 최근 트랜잭션 20개
	 * @see com.dfg.icon.core.v3.service.V3TransactionService#selectRecentTx(com.dfg.icon.web.v3.dto.PageReq)
	 */
	@Override
	public CommonListRes selectRecentTx(PageReq req) throws Exception {

		CommonListRes res = new CommonListRes();
		req.setPage((req.getPage()-1) * req.getCounting());
		int totalTxCount = getTxCount().intValue();
		
		if( req.getPage() > totalTxCount ) {
			res.setCode(IconCode.NO_DATA);
		}else {
			
			List<TxDetailDto> list = transactionV3Mapper.selectRecentTxList(req);

			if (!list.isEmpty()) {
				// 전체 트랜잭션 갯수
				res.setData(FormatUtil.txDetailV3Format(list));
				
				if( resourceService.getLimitTx() > totalTxCount){
					res.setListSize(totalTxCount);
				}else{
					res.setListSize(resourceService.getLimitTx());
				}
				res.setTotalSize(totalTxCount);
				res.setCode(IconCode.SUCCESS);
			} else {
				res.setData(list);
				res.setListSize(0);
				res.setTotalSize(0);
				res.setCode(IconCode.NO_DATA);
			}
		}
		
		return res;
	}

	/*  선택한 트랜잭션 상세 정보
	 * @see com.dfg.icon.core.v3.service.V3TransactionService#selectTxDetail(java.lang.String)
	 */
	@Override
	public CommonRes selectTxDetail(String txHash) throws Exception {

		CommonRes res = new CommonRes();

		TxDetail result = transactionV3Mapper.selectTxDetail(txHash);
		Integer lastHeightTx = blockMapper.selectLastBlockHeight();

		if (result != null && lastHeightTx != null) {

			Integer txHeight = result.getHeight();
			int confirmation = (lastHeightTx - txHeight) + 1;
			result.setStatus(TxStateEnum.getDetailByState(result.getState()));
			result.setConfirmation(confirmation);

			result.setInternalTxList(transactionV3Mapper.selectInternalTx(txHash));
			result.setTokenTxList(transactionV3Mapper.selectTokenTx(txHash));
			// 2018.12.27 LYJ
			// 속도때문에 selectTokenTx에서 T_TRANSACTION_TOTAL join을 해제함.
			// 이미 조회한 contractAddr값을 수동세팅.
			// 확인결과 front에서는 쓰지 않는 값.
			for(TxDetailTokenTx tx : result.getTokenTxList()) {
				tx.setTargetContractAddr(result.getTargetContractAddr());
			}

			TContractHistory history = new TContractHistory();
			List<ContractVersion> versionList = null;
			// 스코어 업로드/승인 tx 페이지에서 코드를 다운받을 수 있도록 버전 조회
			if(IconCode.SCORE_INSTALL_ADDR.getCode().equals(result.getToAddr())) {
				history.setCreateTx(txHash);
				versionList = contractMapper.selectContractVersion(history);
			} else if(IconCode.SCORE_CONTROL_ADDR.getCode().equals(result.getToAddr())) {
				history.setVerifiedTx(txHash);
				versionList = contractMapper.selectContractVersion(history);
			} else if(result.getToAddr() != null &&
					result.getToAddr().startsWith("cx") &&
					IconCode.TX_TYPE_UPDATE.getCode().equals(result.getTxType())) {
				history.setCreateTx(txHash);
				versionList = contractMapper.selectContractVersion(history);
			}

			if(versionList != null && versionList.size() > 0) {
				result.setContractVersion(versionList.get(0).getVersion());
			}
			TFeeDetailsKey key = new TFeeDetailsKey();
			key.setTxHash(txHash);
			TFeeDetails details = tFeeDetailsMapper.selectByPrimaryKey(key);
			if(details != null) {
				result.setStepUsedDetails(details.getStepUsedDetails());
			}


			res.setCode(IconCode.SUCCESS);
		} else {
			res.setCode(IconCode.NO_DATA);
		}
		res.setData(result);

		return res;
	}

	/*  Internal Tx List 조회
	 * @see com.dfg.icon.core.v3.service.V3TransactionService#selectInternalTxList(com.dfg.icon.web.v3.dto.PageReq)
	 */
	public CommonListRes selectInternalTxList(PageReq req) throws Exception {
		req.setPage((req.getPage()-1) * req.getCounting());
		CommonListRes res = new CommonListRes();

		List<InternalTx> txList = null;
		if(req.getAddress2() == null) {	// 특정 주소의 internal이 아닌 경우
			txList = transactionV3Mapper.selectInternalTxPaging(req);
			res.setListSize(transactionV3Mapper.selectInternalTxCount(req));
		} else {
			// 특정 주소의 internal인 경우 view로 조회
			txList = transactionV3Mapper.selectInternalTxPagingByAddress(req);
			res.setListSize(transactionV3Mapper.selectInternalTxCountByAddress(req));
		}

		res.setTotalSize(res.getListSize());
		res.setData(txList);

		if (txList != null && txList.size() > 0) {
			res.setCode(IconCode.SUCCESS);
		} else {
			res.setCode(IconCode.NO_DATA);
		}

		return res;
	}

	/*  TxEventLog 리스트 조회 
	 * @see com.dfg.icon.core.v3.service.V3TransactionService#selectTxEventLogList(com.dfg.icon.web.v3.dto.PageReq)
	 */
	@Override
	public CommonListRes selectTxEventLogList(PageReq req) throws Exception {

		req.setPage((req.getPage() - 1) * req.getCounting());

		CommonListRes res = new CommonListRes();

		List<TxResultLog> resultLog = transactionV3Mapper.selectEventLogList(req);
		res.setData(resultLog);

		if (resultLog == null) {
			res.setListSize(0);
			res.setTotalSize(0);
			res.setCode(IconCode.NO_DATA);
		} else {

			Integer listSize = transactionV3Mapper.selectTotalCountEventLog(req);
			res.setListSize(listSize);
			res.setTotalSize(listSize);
			res.setCode(IconCode.SUCCESS);

		}
		return res;
	}

	@Override
	public String selectTxDataImage(String txHash) throws Exception {
		TTxDataKey key = new TTxData();
		key.setTxHash(txHash);
		TTxDataWithBLOBs result = tTxDataMapper.selectByPrimaryKey(key);

		if(result == null) {
			return null;
		}
		return HexUtil.hexToImage(result.getDataBody());
	}


	private Integer getTxCount() {
		MainInfo mainInfo = mainMapper.selectMainInfo();
		if(mainInfo != null && mainInfo.getTransactionCount() != null) {
			return mainInfo.getTransactionCount();
		}
		return 0;

	}

	public Long selectCount(String txType) throws Exception {
		TTransactionTotalExample example = new TTransactionTotalExample();
		if("score".equals(txType)) {
			example.createCriteria().andContractAddrIsNotNull();
			return tTransactionTotalMapper.countByExample(example);
		}
		return null;
	}

	@Override
	public CommonListRes selectTxBtp(String networkId, int height) throws Exception {
		CommonListRes res = new CommonListRes();

		List<TxDetailDto> txBtpList = transactionV3Mapper.selectTxBtpList(height, networkId);
		res.setData(txBtpList);

		if (!txBtpList.isEmpty()) {
			res.setData(FormatUtil.txDetailV3Format(txBtpList));
			res.setCode(IconCode.SUCCESS);
		} else {
			res.setListSize(0);
			res.setTotalSize(0);
			res.setCode(IconCode.NO_DATA);
		}
		return res;
	}

}
