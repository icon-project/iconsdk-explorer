package com.dfg.icon.web.v3.controller;

import com.dfg.icon.core.dao.icon.TContract;
import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.mappers.icon.TContractMapper;
import com.dfg.icon.core.v3.service.V3ChainInfoService;
import com.dfg.icon.core.v3.service.database.tenant.TenantContext;
import com.dfg.icon.util.CommonUtil;
import com.dfg.icon.web.v3.dto.CommonListRes;
import com.dfg.icon.web.v3.dto.CommonRes;
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

@Api(tags = {"v3 chain"})
@RequestMapping("v3/chain")
@RestController
public class V3MultiChainInfoController {
    private static final Logger logger = LoggerFactory.getLogger(V3MultiChainInfoController.class);

    @Autowired
    V3ChainInfoService chainInfo;

    @ApiOperation(value = "Chain info list Search")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success", response = CommonRes.class)
            })
    @GetMapping
    public CommonListRes getChainInfoList() {
        TenantContext.setDefaultTenant();
        try {
            return chainInfo.selectChainInfoList();

        } catch (Exception e) {
            CommonUtil.printException(logger, "getChainInfoListError : {}", e);
            CommonListRes cRes = new CommonListRes();
            cRes.setError();
            return cRes;
        } finally {
            TenantContext.clearTenant();
        }
    }


    @ApiOperation(value = "Chain info Search" , notes="input value [name : chain 명]")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success", response = CommonRes.class)
            })
    @GetMapping(value = "/info")
    public CommonRes getChainInfo(@RequestParam("name") String name) {


        TenantContext.setDefaultTenant();
        try {
            return chainInfo.selectChainByName(name);

        } catch (Exception e) {
            CommonUtil.printException(logger, "getChainInfoError : {}", e);
            com.dfg.icon.web.v3.dto.CommonRes cRes = new com.dfg.icon.web.v3.dto.CommonRes();
            cRes.setError();
            return cRes;
        } finally {
            TenantContext.clearTenant();
        }
    }

}
