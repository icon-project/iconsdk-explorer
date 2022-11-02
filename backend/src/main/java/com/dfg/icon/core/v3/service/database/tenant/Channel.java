package com.dfg.icon.core.v3.service.database.tenant;

import com.dfg.icon.core.dao.icon.TChainInfo;
import com.dfg.icon.core.dao.icon.TChainInfoKey;
import com.dfg.icon.core.mappers.icon.TChainInfoMapper;
import com.dfg.icon.core.v3.scheduler.V3BlockScheduler;
import com.dfg.icon.core.v3.service.V3ChainInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class Channel {
    private static final Logger logger = LoggerFactory.getLogger(Channel.class);

    @Autowired
    private V3BlockScheduler scheduler;

    @Autowired
    V3ChainInfoService v3ChainInfoService;

    private ConcurrentMap<String, Boolean> requestQueuePool = new ConcurrentHashMap<>();
    private ConcurrentMap<String, TenantAwareThread> threadPool = new ConcurrentHashMap<>();

    public synchronized void putRequest(String name) {
        if(requestQueuePool.get(name) == false && trackerAlive(name)){
            requestQueuePool.replace(name, true);
        }
    }

    public synchronized Boolean getRequestState(String name) {
        return requestQueuePool.get(name);
    }

    public synchronized void takeRequest(String name){
        requestQueuePool.replace(name, false);
    }

    public boolean trackerAlive(String chainName){
        if(threadPool.get(chainName) != null){
            return threadPool.get(chainName).isAlive();
        }
        return false;
    }

    public void trackerThreadClose(String chainName) {
        if (threadPool.containsKey(chainName)){
            threadPool.get(chainName).interrupt();
        }
    }

    public void blockSyncStart(String chainName, String threadName) {
        threadPool.put(threadName, scheduler.updateBlockSync(v3ChainInfoService.chainHost(chainName), chainName, threadName));
        requestQueuePool.put(threadName, Boolean.FALSE);
        threadPool.get(threadName).start();
    }

    public void mainChartSyncStart(String chainName, String threadName) {
        threadPool.put(threadName, scheduler.updateMainChartDailySync(chainName, threadName));
        requestQueuePool.put(threadName, Boolean.FALSE);
        threadPool.get(threadName).start();
    }

}
