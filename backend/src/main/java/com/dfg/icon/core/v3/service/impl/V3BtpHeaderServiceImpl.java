package com.dfg.icon.core.v3.service.impl;

import com.dfg.icon.core.dao.icon.TBtpHeader;
import com.dfg.icon.core.dao.icon.TBtpHeaderKey;
import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.mappers.icon.BtpMapper;
import com.dfg.icon.core.mappers.icon.TBtpHeaderMapper;
import com.dfg.icon.core.v3.adapter.V3BlockChainAdapter;
import com.dfg.icon.core.v3.service.V3BtpHeaderService;
import com.dfg.icon.util.HexUtil;
import com.dfg.icon.web.v3.dto.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.rlp.RlpDecoder;
import org.web3j.rlp.RlpList;
import org.web3j.rlp.RlpString;
import org.web3j.rlp.RlpType;

import java.util.Base64;
import java.util.List;

@Service
public class V3BtpHeaderServiceImpl implements V3BtpHeaderService {

    private static final Logger logger = LoggerFactory.getLogger(V3BtpHeaderServiceImpl.class);

    @Autowired
    TBtpHeaderMapper tBtpHeaderMapper;

    @Autowired
    BtpMapper btpMapper;

    /**
     * The Block chain adapter.
     */
    @Autowired
    V3BlockChainAdapter blockChainAdapter;

    @Override
    public CommonListRes getBtpHeaderList(PageReq req, String networkId) {
        CommonListRes res = new CommonListRes();
        //페이징
        int page = (req.getPage() - 1) * req.getCounting();

        req.setPage(page);

        // 총 주소 정보 조회
        List<BtpHeader> headerList = btpMapper.selectBtpHeaderList(page, req.getCounting(), networkId);

        //전체 주소 갯수 조회
        Integer totalBtpHeader = btpMapper.selectCountBtpHeaderList(networkId);

        res.setData(headerList);
        if(headerList != null || headerList.size() >0){
            res.setListSize(headerList.size());
            res.setTotalSize(totalBtpHeader);
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
    public CommonRes getBtpMessage(String url, int height, String networkId, int sequenceNumber) throws Exception{
        JsonArray msgs = blockChainAdapter.getBtpMessage(url, networkId, height+1);
        BtpMessage btpMessage = new BtpMessage();
        for (JsonElement msg : msgs) {
            RlpList btpBlock = RlpDecoder.decode(Base64.getDecoder().decode(msg.getAsString()));
            List<RlpType> rlpList = ((RlpList)(btpBlock.getValues().get(0))).getValues();
            if(sequenceNumber == HexUtil.changeHexToInteger(((RlpString) rlpList.get(3)).asString())) {
                btpMessage.setSource(new String(Hex.decodeHex(((RlpString) rlpList.get(0)).asString().split("0x")[1].toCharArray()), "UTF-8"));
                btpMessage.setDestination(new String(Hex.decodeHex(((RlpString) rlpList.get(1)).asString().split("0x")[1].toCharArray()), "UTF-8"));
                btpMessage.setService(new String(Hex.decodeHex(((RlpString) rlpList.get(2)).asString().split("0x")[1].toCharArray()), "UTF-8"));
                btpMessage.setSn(sequenceNumber);
                btpMessage.setPayload(new String(Hex.decodeHex(((RlpString) rlpList.get(4)).asString().split("0x")[1].toCharArray()), "UTF-8"));
            }
        }
        CommonRes res = new CommonRes();
        res.setData(btpMessage);
        res.setCode(IconCode.SUCCESS);

        return res;
    }
}
