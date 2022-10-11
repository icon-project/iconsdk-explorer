package com.dfg.icon.web.v0.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.dfg.icon.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.mappers.icon.BlockMapper;
import com.dfg.icon.core.v0.service.V0BlockService;
import com.dfg.icon.web.v0.dto.BlockRes;
import com.dfg.icon.web.v0.dto.CommonRes;
import com.dfg.icon.web.v3.dto.PageReq;
import com.dfg.icon.web.v0.dto.TxInBlock;
import com.dfg.icon.web.v0.dto.block.Block;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author bsm
 * 2018-01-22
 */
@Api(tags = {"v0 block"})
@RequestMapping("v0/block")
@RestController
public class V0SelectBlockController {
    private static final Logger logger = LoggerFactory.getLogger(V0SelectBlockController.class);

    @Autowired 
    V0BlockService blockService ; 
    
    @Autowired
	BlockMapper blockMapper;
    
    @ApiOperation(value = "RecentBlock List" , notes="inputvalue [ page : 보려하는 페이지 ] \n\n"
    		+ "outputvalue [ data : 결과  ] ")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success", response = CommonRes.class)
            })
    @GetMapping(value = "/recentBlock")
    public CommonRes recentBlock(@Valid @RequestParam(value = "page", required = false) Integer page
    		)  {
    	CommonRes res = new CommonRes();
    	
    	try {
		
	        logger.info("====================");
	        logger.info("recentBlock : {}", page);

	        
	        PageReq req = new PageReq();
	        req.setPage(CommonUtil.changeUnderZero(page));
	
	        return blockService.selectRecentBlock(req) ;
	        
	    	
			} catch (Exception e) {
				logger.error("recentBlock" , e);
				res.setError();
			}
		return res;
    }
    
    @ApiOperation(value = "Block Detail and Transaction in the block" , notes="inputvalue [ height : 선택한 Block height 값  ] \n\n"
			+ "outputvalue [ data : 결과 (blockSize는 현재 사용하지 않음) , totalData: 블록안의 트랜잭션 갯수 ] ")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success", response = CommonRes.class)
            })
    @GetMapping(value = "/blockDetail")
    public CommonRes blockDetail(@Valid
    		@RequestParam("height") Integer height,
    		@RequestParam(value="page", required=false) Integer page
    ) {
    	
    	CommonRes cRes = new CommonRes();
    	try {
		
	    	PageReq req = new PageReq();
	    	req.setHeight(height);
	    	req.setPage(CommonUtil.changeUnderZero(page));
	        logger.info("====================");
	        logger.info("blockDetail : {}");
	
	        BlockRes res = new BlockRes();
	       
	        
	        Block blockDetail = blockService.selectBlockDetail(req);
	        List<TxInBlock> txInBlock = blockService.selectTxInBlock(req);
	        
	        if(blockDetail==null){
	        	cRes.setData(res);
	        	cRes.setTotalData("");
	        	cRes.setCode(IconCode.NO_DATA);
	        }else {
		        res.setBlockDetail(blockDetail);
		        res.setTxInBlock(txInBlock);
		        cRes.setData(res);
		        cRes.setTotalData(String.valueOf(blockMapper.selectTotalTxInBlock(req.getHeight())));
		        cRes.setCode(IconCode.SUCCESS);
	        }
	        
    	} catch (Exception e) {
    		logger.error("blockDetail" , e);
    		cRes.setError();
		}
		return cRes;
    }
    
    @ApiOperation(value = "Block Detail and Tx in the Block By hash" , notes="inputvalue [ hash : 선택한 Block hash 값  ] \n\n"
			+ "outputvalue [ data : 결과 (blockSize는 현재 사용하지 않음) , totalData: 블록안의 트랜잭션 갯수 ] ")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success", response = CommonRes.class)
            })
    @GetMapping(value = "/blockDetailByHash")
    public CommonRes blockDetailByHash(@Valid
    		@RequestParam("hash") String hash,
    		@RequestParam(value="page", required=false) Integer page
    ) {
    	CommonRes cRes = new CommonRes();
    	try {
			
			
			PageReq req = new PageReq();
			req.setHash(hash);
			req.setPage(page);
			if(req.getPage()==null){
				req.setPage(1);
			}
		    logger.info("====================");
		    logger.info("blockDetailByHash : {}");
		    
		    BlockRes res = new BlockRes();
		   
		    
		    Block blockDetailByHash = blockService.selectBlockDetailByHash(req);
		    List<TxInBlock> txInBlockByHash = blockService.selectTxInBlockByHash(req);
		    
		    if(blockDetailByHash==null ){
		    	cRes.setData(res);
		    	cRes.setTotalData("");
		    	cRes.setCode(IconCode.NO_DATA);
		    }else {
		        res.setBlockDetail(blockDetailByHash);
		        res.setTxInBlock(txInBlockByHash);
		        cRes.setData(res);
		        cRes.setTotalData(String.valueOf(blockMapper.selectTotalTxInBlockByHash(req.getHash())));
		        cRes.setCode(IconCode.SUCCESS);
		    }
		   
		    
    	} catch (Exception e) {
    		logger.error("blockDetailByHash" , e);
    		cRes.setError();
		}
    	 return cRes;
    }
    

}
