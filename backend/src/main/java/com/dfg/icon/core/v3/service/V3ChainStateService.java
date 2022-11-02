package com.dfg.icon.core.v3.service;

import com.dfg.icon.core.dao.icon.TChainState;

public interface V3ChainStateService {
    TChainState selectChainState(String chainName);
    void updateChainState(String chainName, int height);
    void insertChainState(String chainName, int height);
    boolean existsByChainName(String chainName);
}
