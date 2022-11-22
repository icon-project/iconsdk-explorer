package com.dfg.icon.core.v3.service.impl;

import com.dfg.icon.core.dao.icon.*;
import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.mappers.icon.BtpMapper;
import com.dfg.icon.core.mappers.icon.TBtpNetworkMapper;
import com.dfg.icon.core.v3.service.V3BtpNetworkService;
import com.dfg.icon.web.v0.dto.block.Address;
import com.dfg.icon.web.v3.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class V3BtpNetworkServiceImpl implements V3BtpNetworkService {


    @Autowired
    TBtpNetworkMapper tBtpNetworkMapper;

    @Autowired
    BtpMapper btpMapper;



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
        res.setListSize(totalBtpNetwork);
        if(networkList != null || networkList.size() >0){
            res.setCode(IconCode.SUCCESS);
        } else {
            res.setCode(IconCode.NO_DATA);
        }
        return res;
    }

    @Override
    public CommonRes getBtpNetwork(String networkId) {
        TBtpNetworkKey key = new TBtpNetworkKey();
        key.setNetworkId(networkId);
        TBtpNetwork tBtpNetwork = tBtpNetworkMapper.selectByPrimaryKey(key);
        CommonRes res = new CommonRes();
        res.setData(tBtpNetwork);
        res.setCode(IconCode.SUCCESS);
        return res;
    }
}
