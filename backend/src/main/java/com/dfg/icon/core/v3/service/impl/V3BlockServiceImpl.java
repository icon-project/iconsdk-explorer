
package com.dfg.icon.core.v3.service.impl;

import java.util.List;

import com.dfg.icon.core.common.service.ResourceService;
import com.dfg.icon.core.dao.icon.TTxData;
import com.dfg.icon.core.dao.icon.TTxDataKey;
import com.dfg.icon.core.dao.icon.TTxDataWithBLOBs;
import com.dfg.icon.core.mappers.icon.TTxDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.mappers.icon.BlockMapper;
import com.dfg.icon.core.mappers.icon.TMainTxMapper;
import com.dfg.icon.core.v3.service.V3BlockService;
import com.dfg.icon.core.v3.vo.DecimalType;
import com.dfg.icon.util.FormatUtil;
import com.dfg.icon.util.HexUtil;
import com.dfg.icon.web.v3.dto.CommonListRes;
import com.dfg.icon.web.v0.dto.TxInBlock;
import com.dfg.icon.web.v0.dto.block.Block;
import com.dfg.icon.web.v3.dto.PageReq;
import com.dfg.icon.web.v3.dto.RecentBlock;

/**
 * @author bsm
 * 2018-01-22
 */
@Service
public class V3BlockServiceImpl implements V3BlockService{


	@Autowired
	TMainTxMapper tMainTxMapper;
	
	@Autowired
	BlockMapper blockMapper;

	@Autowired
	TTxDataMapper tTxDataMapper;

	@Autowired
	ResourceService resourceService;

	/* 최근 블록 갯수 20개
	 * @see com.dfg.icon.core.v3.service.V3BlockService#selectRecentBlock(com.dfg.icon.web.v3.dto.PageReq)
	 */
	@Override
	public CommonListRes selectRecentBlock(PageReq req) throws Exception {
		
		CommonListRes res = new CommonListRes();
	
		int count = req.getCounting();
		req.setPage((req.getPage()-1) * count);
		
		// 전체 블록 갯수
		Integer blockCount = blockMapper.selectLastBlockHeight();
		if(blockCount == null) {
			blockCount = 0;
		} else {
			// block이 0번부터이므로 마지막 높이 +1해주면 전체 블록개수
			blockCount++;
		}
		
		if(blockCount == 0){
			res.setData("");
			res.setListSize(0);
			res.setTotalSize(0);
			res.setCode(IconCode.NO_DATA);
		}else{
			List<RecentBlock> list = blockMapper.selectRecentBlock(req);
			
			list = FormatUtil.blockFormat(list);
			
			res.setData(list);
			if( resourceService.getLimitBlock() > blockCount){
				res.setListSize(blockCount);
			}else{
				res.setListSize(resourceService.getLimitBlock());
			}
			res.setTotalSize(blockCount);
			res.setCode(IconCode.SUCCESS);
		}
		return res;
	}

	/* 선택한 블록 상세 정보
	 * @see com.dfg.icon.core.v3.service.V3BlockService#selectBlockDetail(com.dfg.icon.web.v3.dto.PageReq)
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
			
			int lastBlock = blockMapper.selectLastBlockHeight();
			
			if(result.getHeight() == lastBlock){
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

	/*  선택한 블록 안의 트랜잭션 정보 
	 * @see com.dfg.icon.core.v3.service.V3BlockService#selectTxInBlock(com.dfg.icon.web.v3.dto.PageReq)
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

	/* 해쉬로 선택한 블록 정보 조회 
	 * @see com.dfg.icon.core.v3.service.V3BlockService#selectBlockDetailByHash(com.dfg.icon.web.v3.dto.PageReq)
	 */
	@Override
	public Block selectBlockDetailByHash(PageReq req) throws Exception {
		// 블록해쉬로 블록 정보 조회 
		Block result = blockMapper.selectBlockDetail(req);
		if (result == null) {
			return result;
		} else {
			int lastBlock = blockMapper.selectLastBlockHeight();

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

	/* 해쉬로 선택한 블록안에 있는 트랜잭션 정보 조회 
	 * @see com.dfg.icon.core.v3.service.V3BlockService#selectTxInBlockByHash(com.dfg.icon.web.v3.dto.PageReq)
	 */
	@Override
	public List<TxInBlock> selectTxInBlockByHash(PageReq req) throws Exception {
		
		
		req.setPage((req.getPage()-1) * req.getCounting());
		List<TxInBlock> result = blockMapper.selectTxInBlockByHash(req);
		
		return result;
		
	}

}
