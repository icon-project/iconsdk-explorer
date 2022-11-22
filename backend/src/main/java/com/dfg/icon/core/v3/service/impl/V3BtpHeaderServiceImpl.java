package com.dfg.icon.core.v3.service.impl;

import com.dfg.icon.core.dao.icon.TBtpHeader;
import com.dfg.icon.core.dao.icon.TBtpHeaderKey;
import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.mappers.icon.BtpMapper;
import com.dfg.icon.core.mappers.icon.TBtpHeaderMapper;
import com.dfg.icon.core.v3.service.V3BtpHeaderService;
import com.dfg.icon.web.v3.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class V3BtpHeaderServiceImpl implements V3BtpHeaderService {

    @Autowired
    TBtpHeaderMapper tBtpHeaderMapper;

    @Autowired
    BtpMapper btpMapper;

    @Override
    public CommonListRes getBtpHeaderList(PageReq req) {
        CommonListRes res = new CommonListRes();
        //페이징
        int page = (req.getPage() - 1) * req.getCounting();

        req.setPage(page);

        // 총 주소 정보 조회
        List<BtpHeader> headerList = btpMapper.selectBtpHeaderList(page);

        //전체 주소 갯수 조회
        Integer totalBtpHeader = btpMapper.selectCountBtpHeaderList();

        res.setData(headerList);
        res.setListSize(totalBtpHeader);
        if(headerList != null || headerList.size() >0){
            res.setCode(IconCode.SUCCESS);
        } else {
            res.setCode(IconCode.NO_DATA);
        }
        return res;
    }

    @Override
    public CommonListRes getBtpHeaderListByNetworkId(String networkId) {
        CommonListRes res = new CommonListRes();

        List<BtpHeader> headerList = btpMapper.selectBtpHeaderListByNetworkId(networkId);
        res.setData(headerList);
        if(headerList != null || headerList.size() >0){
            res.setCode(IconCode.SUCCESS);
        } else {
            res.setCode(IconCode.NO_DATA);
        }
        return res;
    }

    @Override
    public CommonRes getBtpHeader(int height, String networkId) {
        TBtpHeaderKey key = new TBtpHeaderKey();
        key.setNetworkId(networkId);
        key.setBlockHeight(height);
        TBtpHeader tBtpHeader = tBtpHeaderMapper.selectByPrimaryKey(key);
        CommonRes res = new CommonRes();
        res.setData(tBtpHeader);
        res.setCode(IconCode.SUCCESS);
        return res;
    }

    @Override
    public CommonRes getBtpMessage(int height, String networkId) {
        //TODO
        return null;
    }
}
