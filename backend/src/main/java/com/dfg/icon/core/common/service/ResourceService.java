package com.dfg.icon.core.common.service;


public interface ResourceService {
    String getServerName();
    Integer getLimitBlock();
    Integer getLimitTx();
    Integer getLimitAddress();
    Integer getLimitMainBlock();
    Integer getLimitMainTx();
    Boolean isIcxStatic();
    Boolean isLogSpeed();
    String getTrackerVersion();
}
