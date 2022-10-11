package com.dfg.icon.web.v3.controller;

import java.util.List;


import javax.validation.Valid;

import com.dfg.icon.core.exception.IconException;
import com.dfg.icon.web.v3.dto.CommonListRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.mappers.icon.BlockMapper;
import com.dfg.icon.core.v3.service.V3BlockService;
import com.dfg.icon.util.CommonUtil;
import com.dfg.icon.web.v0.dto.TxInBlock;
import com.dfg.icon.web.v0.dto.block.Block;
import com.dfg.icon.web.v3.dto.CommonRes;
import com.dfg.icon.web.v3.dto.PageReq;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author bsm
 * 2018-01-22
 */
@Api(tags = {"v3 block"})
@RequestMapping("v3/block")
@RestController
public class V3SelectBlockController {
    private static final Logger logger = LoggerFactory.getLogger(V3SelectBlockController.class);

    @Autowired 
    V3BlockService blockService ; 
    
    @Autowired
	BlockMapper blockMapper;
    
    @ApiOperation(value = "RecentBlock List 페이징 조회" , notes="recentBlock list 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success", response = CommonRes.class)
            })
    @GetMapping(value = "/list")
    public CommonListRes recentBlock(@Valid 
    		@RequestParam(value = "page", required = false) Integer page,
    		@RequestParam(value = "count", required = false) Integer count
    		)  {
    	
    	
    	try {
		
	        logger.info("====================");
	        logger.info("recentBlock : {}", page);

	        PageReq req = new PageReq();
	        req.setPage(CommonUtil.changeUnderZero(page) );
	        if(count != null) {
                req.setCounting(count);
            }
	
	        return blockService.selectRecentBlock(req) ;
	        
	    	
			} catch (Exception e) {
				CommonListRes res = new CommonListRes();
				CommonUtil.printException(logger, "recentBlockError : {}", e);
				res.setError();
				return res;
			}
		
    }
    
    @ApiOperation(value = "Block Detail and Transaction in the block" , notes="inputvalue [ height : 선택한 Block height 값  ] \n\n"
			+ "outputvalue [ data : 결과 (blockSize는 현재 사용하지 않음) , totalData: 블록안의 트랜잭션 갯수 ] ")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success", response = CommonRes.class)
            })
    @GetMapping(value = "/info")
    public CommonRes getBlockDetail(@Valid
    		@RequestParam(value = "height", required = false) Integer height,
			@RequestParam(value = "hash", required = false) String hash,
    		@RequestParam(value="page", required=false) Integer page
    ) {
    	
    	CommonRes cRes = new CommonRes();
    	try {
			if("".equals(height)) {
				height = null;
			}
			if("".equals(hash)) {
				hash = null;
			}

			if(height == null && hash == null) {
				throw new IconException(IconCode.BLOCK_ERROR);
			}
	    	PageReq req = new PageReq(10);
	    	req.setHeight(height);
			req.setHash(hash);
	    	req.setPage(CommonUtil.changeUnderZero(page));

	        logger.info("====================");
	        logger.info("blockDetail : {}");

	        Block blockDetail = blockService.selectBlockDetail(req);
			cRes.setData(blockDetail);

	        if(blockDetail==null){
	        	cRes.setCode(IconCode.NO_DATA);
	        }else {
		        cRes.setCode(IconCode.SUCCESS);
	        }
	        
    	} catch (Exception e) {
    		logger.error("blockDetail", e);
    		cRes.setError();
		}
		return cRes;
    }

	@ApiOperation(value = "Block Detail and Transaction in the block" , notes="inputvalue [ height : 선택한 Block height 값  ] \n\n"
			+ "outputvalue [ data : 결과 (blockSize는 현재 사용하지 않음) , totalData: 블록안의 트랜잭션 갯수 ] \n\n\n\n"
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
	public CommonListRes getBlockTxList(@Valid
								 @RequestParam(value = "height", required = false) Integer height,
								 @RequestParam(value = "hash", required = false) String hash,
								 @RequestParam(value="page", required=false) Integer page,
								 @RequestParam(value = "count", required = false) Integer count
	) {

		CommonListRes cRes = new CommonListRes();
		try {

			if(height == null && hash == null) {
				throw new IconException(IconCode.BLOCK_ERROR);
			}
			PageReq req = new PageReq(10);
			req.setHeight(height);
			req.setHash(hash);
			req.setPage(CommonUtil.changeUnderZero(page));
			if(count != null){
				req.setCounting(count);
			}
			logger.info("====================");
			logger.info("blockDetail : {}");

	        List<TxInBlock> txInBlock = blockService.selectTxInBlock(req);
			cRes.setData(txInBlock);
			if(txInBlock==null || txInBlock.size() == 0){
				cRes.setListSize(0);
				cRes.setCode(IconCode.NO_DATA);
			} else {
				cRes.setListSize(blockMapper.selectTxCountInBlock(req));
				cRes.setCode(IconCode.SUCCESS);
			}

		} catch (Exception e) {
			logger.error("blockDetail" , e);
			cRes.setError();
		}
		return cRes;
	}
}
