package com.dfg.icon.core.v3.service.impl;

import com.dfg.icon.core.dao.icon.TChainState;
import com.dfg.icon.core.dao.icon.TChainStateKey;
import com.dfg.icon.core.mappers.icon.TChainStateMapper;
import com.dfg.icon.core.v3.service.V3ChainState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class V3ChainStateImpl implements V3ChainState {
    private static final Logger logger = LoggerFactory.getLogger(V3ChainStateImpl.class);

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
}
