package com.dfg.icon.core.v3.service.impl;

import com.dfg.icon.core.dao.icon.TChainState;
import com.dfg.icon.core.dao.icon.TChainStateKey;
import com.dfg.icon.core.mappers.icon.TChainStateMapper;
import com.dfg.icon.core.v3.service.V3ChainStateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class V3ChainStateServiceImpl implements V3ChainStateService {
    private static final Logger logger = LoggerFactory.getLogger(V3ChainStateServiceImpl.class);

    @Autowired
    TChainStateMapper tChainStateMapper;

    @Override
    public TChainState selectChainState(String chainName) {
        TChainStateKey key = new TChainStateKey();
        key.setChainName(chainName);
        return tChainStateMapper.selectByPrimaryKey(key);
    }

    @Override
    public void updateChainState(String chainName, int height) {
        TChainStateKey key = new TChainStateKey();
        key.setChainName(chainName);
        TChainState tChainState = tChainStateMapper.selectByPrimaryKey(key);
        tChainState.setBlockHeight(height);
        tChainStateMapper.updateByPrimaryKey(tChainState);
    }

    @Override
    public void insertChainState(String chainName, int height) {
        int check = tChainStateMapper.chainStateByName(chainName);
        if(check == 0){
            TChainState tChainState = new TChainState();
            tChainState.setChainName(chainName);
            tChainState.setBlockHeight(height);
            tChainStateMapper.insert(tChainState);
        }
    }

    @Override
    public boolean existsByChainName(String chainName) {
        return tChainStateMapper.chainStateByName(chainName) == 0 ? true : false;
    }
}
