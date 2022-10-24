package com.dfg.icon.core.common.service.impl;

import com.dfg.icon.core.common.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ResourceServiceImpl implements ResourceService {

    private static final Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);

    @Value("${server.name}")
    private String serverName;

    @Value("${block.scheduler.name}")
    private String blockSchedulerName;

    @Value("${block.scheduler.version}")
    private String blockSchedulerVersion;

    @Value("${limit.block}")
    private Integer limitBlock;

    @Value("${limit.transaction}")
    private Integer limitTransaction;

    @Value("${limit.address}")
    private Integer limitAddress;

    @Value("${limit.main.block}")
    private Integer limitMainBlock;

    @Value("${limit.main.tx}")
    private Integer limitMainTx;

    @Value("${circulation.static}")
    private Integer circulationStatic;

    @Value("${log.speed}")
    private Integer logSpeed;

    @Value("${tracker.version}")
    private String trackerVersion;

    @PostConstruct
    private void init() {
        logger.info("---------------");
        logger.info("ServerName : {}", serverName);
        logger.info("SchedulerName : {}", blockSchedulerName);
        logger.info("SchedulerVersion : {}", blockSchedulerVersion);
        logger.info("limitBlock : {}", limitBlock);
        logger.info("limitTx : {}", limitTransaction);
        logger.info("limitAddress : {}", limitAddress);
        logger.info("limitMainBlock : {}", limitMainBlock);
        logger.info("limitMainTx : {}", limitMainTx);
    }

    @Override
    public String getServerName() {
        return serverName;
    }

    @Override
    public Integer getLimitBlock() {
        return limitBlock;
    }

    @Override
    public Integer getLimitTx() {
        return limitTransaction;
    }

    @Override
    public Integer getLimitAddress() {
        return limitAddress;
    }

    @Override
    public Integer getLimitMainBlock() {
        return limitMainBlock;
    }

    @Override
    public Integer getLimitMainTx() {
        return limitMainTx;
    }

    @Override
    public Boolean isIcxStatic() {
        return circulationStatic>0?true:false;
    }

    @Override
    public Boolean isLogSpeed() {
        return logSpeed>0?true:false;
    }

    @Override
    public String getTrackerVersion() {
        return trackerVersion;
    }
}
