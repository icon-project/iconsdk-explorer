
package com.dfg.icon.core.v0.service.impl;

import java.util.List;

import com.dfg.icon.core.dao.icon.TTxData;
import com.dfg.icon.core.dao.icon.TTxDataKey;
import com.dfg.icon.core.dao.icon.TTxDataWithBLOBs;
import com.dfg.icon.core.mappers.icon.TTxDataMapper;
import com.dfg.icon.core.v3.vo.DecimalType;
import com.dfg.icon.web.v3.dto.RecentBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.mappers.icon.BlockMapper;
import com.dfg.icon.core.mappers.icon.TMainTxMapper;
import com.dfg.icon.core.v0.service.V0BlockService;
import com.dfg.icon.util.FormatUtil;
import com.dfg.icon.util.HexUtil;
import com.dfg.icon.web.v0.dto.CommonRes;
import com.dfg.icon.web.v3.dto.PageReq;
import com.dfg.icon.web.v0.dto.TxInBlock;
import com.dfg.icon.web.v0.dto.block.Block;

/**
 * @author bsm
 * 2018-01-22
 */
@Service
public class V0BlockServiceImpl implements V0BlockService{


	@Autowired
	TMainTxMapper tMainTxMapper;
	
	@Autowired
	BlockMapper blockMapper;

	@Autowired
	TTxDataMapper tTxDataMapper;

	/* 
	 * 최근 블록 갯수 20개
	 */
	@Override
	public CommonRes selectRecentBlock(PageReq req) throws Exception {
		
		CommonRes res = new CommonRes();

		req.setPage((req.getPage()-1) * req.getCounting());
		
		// 전체 블록 갯수
		Integer blockCount = blockMapper.selectLastBlockHeight();
		if(blockCount == null) {
			blockCount = 0;
		} else {
			blockCount++;
		}
		
		if(blockCount == 0){
			res.setData("");
			res.setTotalData("");
			res.setCode(IconCode.NO_DATA);
		}else{
			List<RecentBlock> list = blockMapper.selectRecentBlock(req);
			
			list = FormatUtil.blockFormat(list);
			
			res.setData(list);
			res.setTotalData(String.valueOf(blockCount));
			res.setCode(IconCode.SUCCESS);
		}
		return res;
	}

	/* 
	 * 선택한 블록 상세 정보 
	 */
	@Override
	public Block selectBlockDetail(PageReq req) throws Exception {

		Block result = blockMapper.selectBlockDetail(req);
		if(result == null) {
			return result;
		}else {
			if(!("".equals(result.getAmount()) || "-".equals(result.getAmount()))) {
				String amount = HexUtil.decimalFormat(result.getAmount(), DecimalType.ICX.getValue()) ;
				result.setAmount(amount);
			}

			//마지막 블록
			int lastBlock = blockMapper.selectLastBlockHeight();
			
			if(req.getHeight() == lastBlock){
				result.setLastBlock("Last Block");
			}else{
				result.setLastBlock("-");
			}

			if(result.getHeight().intValue() == 0) {
				TTxDataKey key = new TTxData();
				key.setTxHash(result.getHash());
				TTxDataWithBLOBs genesis = tTxDataMapper.selectByPrimaryKey(key);
				result.setMessage(genesis.getDataBody());
			}
		}
		
		
		return result;
	}

	/* 
	 * 선택한 블록 안의 트랜잭션 정보 
	 */
	@Override
	public List<TxInBlock> selectTxInBlock(PageReq req ) throws Exception {
		
		req.setPage((req.getPage()-1) * req.getCounting());
		
		
		List<TxInBlock> result = blockMapper.selectTxInBlock(req);
		for (int i = 0; i < result.size(); i++) {
			double amount = Double.parseDouble(result.get(i).getAmount());
			String b = String.format("%.4f", amount);
			result.get(i).setAmount(b);
		}
		
		return result;
	}

	/* 
	 * 해쉬로 선택한 블록 정보 조회 
	 */
	@Override
	public Block selectBlockDetailByHash(PageReq req) throws Exception {
		// 블록해쉬로 블록 정보 조회 
		Block result = blockMapper.selectBlockDetail(req);
		if (result == null) {
			return result;
		} else {
			// 마지막 블록
			Integer lastBlock = blockMapper.selectLastBlockHeight();

			if (result.getHeight() == lastBlock) {
				result.setLastBlock("Last Block");
			} else {
				result.setLastBlock("-");
			}
			if (result.getHeight().intValue() == 0) {
				TTxDataKey key = new TTxData();
				key.setTxHash(result.getHash());
				TTxDataWithBLOBs genesis = tTxDataMapper.selectByPrimaryKey(key);
				result.setMessage(genesis.getDataBody());
			}
		}
		return result;
	}

	/* 
	 * 해쉬로 선택한 블록안에 있는 트랜잭션 정보 조회 
	 */
	@Override
	public List<TxInBlock> selectTxInBlockByHash(PageReq req) throws Exception {
		
		req.setPage( (req.getPage()-1 ) * req.getCounting());
		
		List<TxInBlock> result = blockMapper.selectTxInBlockByHash(req);
		
		return result;
		
	}

}
