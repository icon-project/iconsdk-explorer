package com.dfg.icon.core.v0.service.impl;

import java.util.List;

import com.dfg.icon.core.dao.icon.TTransactionExample;
import com.dfg.icon.core.mappers.icon.BlockMapper;
import com.dfg.icon.core.mappers.icon.TTransactionMapper;
import com.dfg.icon.core.v3.vo.DecimalType;
import com.dfg.icon.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.mappers.icon.TMainTxMapper;
import com.dfg.icon.core.mappers.icon.TransactionMapper;
import com.dfg.icon.core.v0.service.V0TransactionService;
import com.dfg.icon.util.FormatUtil;
import com.dfg.icon.util.HexUtil;
import com.dfg.icon.web.v0.dto.CommonRes;
import com.dfg.icon.web.v3.dto.TxDetail;
import com.dfg.icon.web.v3.dto.PageReq;

/**
 * @author bsm
 * 2018-01-22
 */
@Service
public class V0TransactionServiceImpl implements V0TransactionService{


	@Autowired
	TMainTxMapper tMainTxMapper;

	@Autowired
	TransactionMapper transactionMapper;

	@Autowired
	TTransactionMapper tTransactionMapper;

	@Autowired
	BlockMapper blockMapper;

	/*
	 * 최근 트랜잭션 20개
	 */
	@Override
	public CommonRes selectRecentTx(PageReq req) throws Exception {

		CommonRes res = new CommonRes();
		req.setPage((CommonUtil.changeUnderZero(req.getPage())-1)*20);

		List<TxDetail> list = transactionMapper.selectRecentTxList(req);


		if(!list.isEmpty()){
			// 전체 트랜잭션 갯수
			String total = getTxCount();
			list = FormatUtil.txDetailFormat(list);
			res.setData(list);
			res.setTotalData(total);
			res.setCode(IconCode.SUCCESS);
		} else{
			res.setData(list);
			res.setTotalData("");
			res.setCode(IconCode.NO_DATA);
		}
		return res;
	}

	/*
	 * 선택한 트랜잭션 상세 정보
	 */
	@Override
	public CommonRes selectTxDetail(PageReq req) throws Exception {

		CommonRes res = new CommonRes();

		TxDetail result = transactionMapper.selectTxDetail(req.getTxHash());
		Integer height = transactionMapper.selectTxHeight(req.getTxHash());
		Integer lastHeight = blockMapper.selectLastBlockHeight();

		if(result!=null && height!=null && lastHeight!=null ){
			int confirmation = (lastHeight-height)+1;

	        String amount = HexUtil.decimalFormat(result.getAmount(), DecimalType.ICX.getValue()) ;

			result.setStatus("Successfully Confirmed");
			result.setConfirmation(confirmation);
			result.setAmount(amount);
			res.setData(result);
			res.setTotalData("");
			res.setCode(IconCode.SUCCESS);
		}else{

			res.setData(result);
			res.setTotalData("");
			res.setCode(IconCode.NO_DATA);
		}

		return res;
	}

	private String getTxCount() {
		long txCnt = tTransactionMapper.countByExample(new TTransactionExample());

		return String.valueOf(txCnt);
	}
}
