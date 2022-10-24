package com.dfg.icon.web.v3.controller;

import com.dfg.icon.core.v3.service.V3DownloadService;
import com.dfg.icon.core.v3.service.database.tenant.TenantContext;
import com.dfg.icon.util.Validator;
import com.dfg.icon.web.v3.dto.PageReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by LYJ on 2019-02-28.
 */
@Api(tags = {"v3 download"})
@RequestMapping("v3/{chainName}/download")
@RestController
public class V3DownTxController {

    private static final Logger logger = LoggerFactory.getLogger(V3DownTxController.class);

    @Autowired
    V3DownloadService v3DownloadService;

    @ApiOperation(value = "byAddress/byDate, txList download" , notes="byAddress/byDate, txList download")
    @GetMapping(value = "/txList")
    public void downloadTxListCSV(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable String chainName,
            @RequestParam(value="fromAddr", required = false) String address,
            @RequestParam(value="toAddr", required = false) String address2,
            @RequestParam(value="startDate") String startDate,
            @RequestParam(value="endDate") String endDate
    ) throws Exception {
        TenantContext.setTenant(chainName);
        if(!request.getRemoteHost().equals("127.0.0.1")) {
            return;
        }
        logger.info("====================");
        logger.info("v3/download/txList : startDate={}, endDate={}, fromAddr={}, toAddr={}",
                startDate, endDate, address, address2);

        if (!Validator.isValidDatePattern(startDate)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "wrong startDate");
        } else if (!Validator.isValidDatePattern(endDate)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "wrong endDate");
        } else {
            PageReq req = new PageReq();
            req.setAddress(address);
            req.setAddress2(address2);
            req.setStartDate(startDate);
            req.setEndDate(endDate);

            v3DownloadService.downloadList(req, response);
        }
        TenantContext.clearTenant();

    }

    @ApiOperation(value = "byAddress/byDate, txCount download" ,
            notes="byAddress/byDate, txCount download \n\n type 0:fromAddr, 1:toAddr")
    @GetMapping(value = "/txCount")
    public void downloadTxCountCSV(
            @PathVariable String chainName,
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value="startDate") String startDate,
            @RequestParam(value="endDate") String endDate,
            @RequestParam(value = "type", defaultValue = "0") String type
    ) throws Exception {
        TenantContext.setTenant(chainName);
        if(!request.getRemoteHost().equals("127.0.0.1")) {
            return;
        }
        logger.info("====================");
        logger.info("v3/download/txCount : startDate={}, endDate={}, type={}",
                startDate, endDate, type);
        if (!Validator.isValidDatePattern(startDate)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "wrong startDate");
        } else if (!Validator.isValidDatePattern(endDate)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "wrong endDate");
        } else if (!Validator.isValidDownType(type)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "wrong type");
        } else {
            PageReq req = new PageReq();
            req.setStartDate(startDate);
            req.setEndDate(endDate);
            req.setType(type);

            v3DownloadService.downloadTxCount(req, response);
        }
        TenantContext.clearTenant();
    }

    @ApiOperation(value = "byDate, balance address download" ,
            notes="byDate, balance address download ")
    @GetMapping(value = "/addressCount")
    public void downloadAddressCountCSV(
            @PathVariable String chainName,
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value="startDate") String startDate,
            @RequestParam(value="endDate") String endDate
    ) throws Exception {
        TenantContext.setTenant(chainName);
        if(!request.getRemoteHost().equals("127.0.0.1")) {
            return;
        }
        logger.info("====================");
        logger.info("v3/download/addressCount : startDate={}, endDate={}",
                startDate, endDate);
        if (!Validator.isValidDatePattern(startDate)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "wrong startDate");
        } else if (!Validator.isValidDatePattern(endDate)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "wrong endDate");
        } else {
            PageReq req = new PageReq();
            req.setStartDate(startDate);
            req.setEndDate(endDate);

            v3DownloadService.downloadAddressCount(req, response);
        }
        TenantContext.clearTenant();
    }
}
