package com.dfg.icon.core.v3.service;

import com.dfg.icon.core.dao.icon.TChainState;

public interface V3ChainState {
    TChainState selectChainState(String chainName);
    void updateChainState(String chainName, int height);
}
