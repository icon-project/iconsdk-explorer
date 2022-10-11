package com.dfg.icon.core.v0.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfg.icon.core.dao.icon.TContract;
import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.mappers.icon.SearchMapper;
import com.dfg.icon.core.v0.service.V0SearchService;
import com.dfg.icon.web.v0.dto.SearchReq;
import com.dfg.icon.web.v0.dto.SimpleRes;

/**
 * @author bsm
 * 2018-01-22
 */
@Service
public class V0SearchServiceImpl implements V0SearchService{


	@Autowired
	SearchMapper searchMapper;

	/* 
	 * 블록, 트랜잭션 해쉬로 검색 , 주소가 토큰인지 여부 확인
	 */
	@Override
	public SimpleRes search(SearchReq req) throws Exception {

		SimpleRes res = new SimpleRes();

		if(req.getData().contains("cx")){
			TContract tokenResult = searchMapper.isTokenCheck(req.getData());
			if(tokenResult==null){
				res.setData("-");
				res.setCode(IconCode.NO_DATA);
			} else {
				res.setData(tokenResult.getIrcVersion());
				res.setCode(IconCode.SUCCESS);
			}
			
		}else{

			int hash = searchMapper.blockHashSearch(req.getData());
			//int txHash = searchMapper.txHashSearch(req.getData());

			if(hash == 1){
				res.setCode(IconCode.SUCCESS);
				res.setData("Block Hash");
			}else {
				res.setCode(IconCode.SUCCESS);
				res.setData("Transaction Hash");
			}
			/*else {
				res.setData("");
				res.setCode(IconErrorCode.NO_DATA);
			}*/

		}



		return res;
	}



}
