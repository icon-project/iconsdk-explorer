package com.dfg.icon.core.v3.service.impl;

import com.dfg.icon.core.dao.icon.TChainInfo;
import com.dfg.icon.core.dao.icon.TChainInfoKey;
import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.mappers.icon.TChainInfoMapper;
import com.dfg.icon.core.v3.service.V3ChainInfoService;
import com.dfg.icon.core.v3.service.database.ChainDatabase;
import com.dfg.icon.core.v3.vo.MultiChainInfo;
import com.dfg.icon.core.v3.service.database.tenant.TenantContext;
import com.dfg.icon.web.v3.dto.ChainInfo;
import com.dfg.icon.web.v3.dto.CommonListRes;
import com.dfg.icon.web.v3.dto.CommonRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class V3ChainInfoServiceImpl implements V3ChainInfoService {
    private static final Logger logger = LoggerFactory.getLogger(V3ChainInfoServiceImpl.class);

    @Autowired
    private TChainInfoMapper tChainInfoMapper;

    @Autowired
    private TChainInfoMapper chainInfoMapper;

    @Autowired
    private ChainDatabase chainDatabase;

    @PostConstruct
    public void init(){
        MultiChainInfo chains = chainDatabase.getMultichainInfo();
        TenantContext.setDefaultTenant();
        for(MultiChainInfo.ChainInfo chainInfo : chains.getChainInfos()){
            insertChainInfo(chainInfo);
        }
        TenantContext.clearTenant();
    }

    private void insertChainInfo(MultiChainInfo.ChainInfo chainInfo){
        int check = chainInfoMapper.chainInfoByName(chainInfo.getName());
        if(check == 0 ){
            //TODO check key
            TChainInfo tChainInfo = new TChainInfo();
            tChainInfo.setChainName(chainInfo.getName());
            tChainInfo.setTitle(chainInfo.getTitle());
            tChainInfo.setApi(chainInfo.getApi());
            tChainInfo.setVersion(chainInfo.getVersion());
            tChainInfo.setHost(chainInfo.getHost());
            tChainInfo.setChannel(chainInfo.getChannel());
            chainInfoMapper.insert(tChainInfo);
        }
    }


    @Override
    public CommonListRes selectChainInfoList() {
        CommonListRes res = new CommonListRes();
        List<ChainInfo> chainInfoList = tChainInfoMapper.selectAll();
        res.setCode(IconCode.SUCCESS);
        res.setData(chainInfoList);
        return res;
    }

    @Override
    public CommonRes selectChainByName(String name) {
        CommonRes res = new CommonRes();
        TChainInfoKey key = new TChainInfoKey();
        key.setChainName(name);
        TChainInfo tChainInfo = tChainInfoMapper.selectByPrimaryKey(key);
        res.setData(tChainInfo);
        res.setCode(IconCode.SUCCESS);
        return res;
    }
    @Override
    public String chainHost(String name){
        String apiUrl;
        TChainInfoKey key = new TChainInfoKey();
        key.setChainName(name);
        TChainInfo tChainInfo = tChainInfoMapper.selectByPrimaryKey(key);
        if(tChainInfo.getHost().contains("api/")) {
            apiUrl = tChainInfo.getHost();
        } else {
            apiUrl = (new StringBuffer()).append(tChainInfo.getHost()).append("/").append(tChainInfo.getApi()).toString().replace("//api/v3", "/api/v3");
            if(tChainInfo.getChannel() != null){
                apiUrl = apiUrl + tChainInfo.getChannel();
            }
        }
        return apiUrl;
    }
}
