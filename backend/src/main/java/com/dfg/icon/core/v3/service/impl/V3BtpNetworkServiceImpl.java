package com.dfg.icon.core.v3.service.impl;

import com.dfg.icon.core.dao.icon.*;
import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.mappers.icon.BtpMapper;
import com.dfg.icon.core.mappers.icon.TBtpNetworkMapper;
import com.dfg.icon.core.v3.adapter.V3BlockChainAdapter;
import com.dfg.icon.core.v3.service.V3BtpNetworkService;
import com.dfg.icon.web.v0.dto.block.Address;
import com.dfg.icon.web.v3.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class V3BtpNetworkServiceImpl implements V3BtpNetworkService {


    @Autowired
    TBtpNetworkMapper tBtpNetworkMapper;

    @Autowired
    BtpMapper btpMapper;

    @Autowired
    V3BlockChainAdapter blockChainAdapter;



    @Override
    public CommonListRes getBtpNetworkList(PageReq req) {
        CommonListRes res = new CommonListRes();
        //페이징
        int page = (req.getPage() - 1) * req.getCounting();

        req.setPage(page);

        // 총 주소 정보 조회
        List<BtpNetwork> networkList = btpMapper.selectBtpNetworkList(page);

        //전체 주소 갯수 조회
        Integer totalBtpNetwork = btpMapper.selectCountBtpNetworkList();

        res.setData(networkList);
        if(networkList != null || networkList.size() >0){
            res.setListSize(networkList.size());
            res.setTotalSize(totalBtpNetwork);
            res.setCode(IconCode.SUCCESS);
        } else {
            res.setCode(IconCode.NO_DATA);
        }
        return res;
    }

    @Override
    public CommonRes getBtpNetwork(String networkId) throws Exception{
        TBtpNetworkKey key = new TBtpNetworkKey();
        key.setNetworkId(networkId);
        TBtpNetwork tBtpNetwork = tBtpNetworkMapper.selectByPrimaryKey(key);

        JsonObject networkInfo  = blockChainAdapter.getBtpNetworkInfo(
                tBtpNetwork.getUrl(), tBtpNetwork.getNetworkId());


        CommonRes res = new CommonRes();
        res.setData(new ObjectMapper().readValue(networkInfo.toString(), Map.class));
        res.setCode(IconCode.SUCCESS);
        return res;
    }
}
