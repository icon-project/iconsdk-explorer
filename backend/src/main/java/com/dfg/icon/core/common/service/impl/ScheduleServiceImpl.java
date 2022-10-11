package com.dfg.icon.core.common.service.impl;

import com.dfg.icon.core.common.service.ResourceService;
import com.dfg.icon.core.common.service.ScheduleService;
import com.dfg.icon.core.dao.icon.TSchedulerFlag;
import com.dfg.icon.core.dao.icon.TSchedulerFlagKey;
import com.dfg.icon.core.mappers.icon.TSchedulerFlagMapper;
import com.dfg.icon.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    private static final Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);


    @Autowired
    private TSchedulerFlagMapper tSchedulerFlagMapper;

    @Autowired
    private ResourceService resourceService;

    /**
     * Set scheduler is using
     */
    @Override
    public TSchedulerFlag activeBlockSchedule(String server) {
        TSchedulerFlagKey key = new TSchedulerFlagKey();
        key.setScheduleName(resourceService.getBlockSchedulerName());
        TSchedulerFlag flag = tSchedulerFlagMapper.selectByPrimaryKey(key);
        if(flag == null) {
            flag = new TSchedulerFlag();
            flag.setScheduleName(resourceService.getBlockSchedulerName());
            flag.setActiveYn("Y");
            flag.setActiveServer(server);
            flag.setStartPosition("0");
            flag.setUpdateDate(DateUtil.getNowDate());
            tSchedulerFlagMapper.insert(flag);
        } else {
            if("P".equals(flag.getActiveYn()) || "R".equals(flag.getActiveYn())) {
                // nothing;
                // 중지 또는 예약 상황이면 스케줄을 바꾸지 않는다.
            } else {
                flag.setActiveYn("Y");
                flag.setActiveServer(server);
                flag.setUpdateDate(DateUtil.getNowDate());
                tSchedulerFlagMapper.updateByPrimaryKey(flag);
            }
        }
        return flag;
    }

    /**
     * Set scheduler is free
     */
    @Override
    public TSchedulerFlag inActiveBlockSchedule(String server) {
        TSchedulerFlagKey key = new TSchedulerFlagKey();
        key.setScheduleName(resourceService.getBlockSchedulerName());
        TSchedulerFlag flag = tSchedulerFlagMapper.selectByPrimaryKey(key);
        if(flag == null) {
            flag = new TSchedulerFlag();
            flag.setScheduleName(resourceService.getBlockSchedulerName());
            flag.setActiveYn("N");
            flag.setActiveServer(server);
            flag.setUpdateDate(DateUtil.getNowDate());
            tSchedulerFlagMapper.insert(flag);
        } else {
            if("Y".equals(flag.getActiveYn())) {
                // 스케줄러였던 상황이면 종료.
                flag.setActiveYn("N");
            } else if("R".equals(flag.getActiveYn())) {
                // 관리자가 DB의 Y스케줄 상태를 R예약으로 바꿨다면 자신의 스케줄 종료 후 중지
                flag.setActiveYn("P");
            }
            flag.setActiveServer(server);
            flag.setUpdateDate(DateUtil.getNowDate());
            tSchedulerFlagMapper.updateByPrimaryKey(flag);
        }
        return flag;
    }

    /**
     * Set next block in scheduler
     */
    @Override
    public void updateBlockScheduler(String nextPosition) {
        TSchedulerFlagKey key = new TSchedulerFlagKey();
        key.setScheduleName(resourceService.getBlockSchedulerName());
        TSchedulerFlag flag = tSchedulerFlagMapper.selectByPrimaryKey(key);
        flag.setStartPosition(nextPosition);
        tSchedulerFlagMapper.updateByPrimaryKey(flag);
    }

    @Override
    public void updateScheduler(String name, String nextPosition) {
        TSchedulerFlagKey key = new TSchedulerFlagKey();
        key.setScheduleName(name);
        TSchedulerFlag flag = tSchedulerFlagMapper.selectByPrimaryKey(key);
        flag.setStartPosition(nextPosition);
        tSchedulerFlagMapper.updateByPrimaryKey(flag);
    }

    /**
     * Get scheduler
     */
    @Override
    public TSchedulerFlag getBlockSchedule() {
        TSchedulerFlagKey key = new TSchedulerFlagKey();
        key.setScheduleName(resourceService.getBlockSchedulerName());
        return tSchedulerFlagMapper.selectByPrimaryKey(key);
    }

    /**
     * Get scheduler by name
     */
    @Override
    public TSchedulerFlag getSchedule(String name) {
        TSchedulerFlagKey key = new TSchedulerFlagKey();
        key.setScheduleName(name);
        return tSchedulerFlagMapper.selectByPrimaryKey(key);
    }

    @Override
    public TSchedulerFlag activeSchedule(String name, String server) {
        TSchedulerFlagKey key = new TSchedulerFlagKey();
        key.setScheduleName(name);
        TSchedulerFlag flag = tSchedulerFlagMapper.selectByPrimaryKey(key);
        if(flag == null) {
            flag = new TSchedulerFlag();
            flag.setScheduleName(name);
            flag.setActiveYn("Y");
            flag.setStartPosition(String.valueOf(DateUtil.getNowDate().getTime()));
            flag.setActiveServer(server);
            flag.setUpdateDate(DateUtil.getNowDate());
            tSchedulerFlagMapper.insert(flag);
        } else {
            if("P".equals(flag.getActiveYn()) || "R".equals(flag.getActiveYn())) {
                // nothing;
                // 중지 또는 예약 상황이면 스케줄을 바꾸지 않는다.
            } else {
                flag.setActiveYn("Y");
                flag.setActiveServer(server);
                flag.setUpdateDate(DateUtil.getNowDate());
                tSchedulerFlagMapper.updateByPrimaryKey(flag);
            }
        }
        return flag;
    }

    /**
     * Set scheduler is free
     */
    @Override
    public TSchedulerFlag inActiveSchedule(String name, String server) {
        TSchedulerFlagKey key = new TSchedulerFlagKey();
        key.setScheduleName(name);
        TSchedulerFlag flag = tSchedulerFlagMapper.selectByPrimaryKey(key);
        if(flag == null) {
            flag = new TSchedulerFlag();
            flag.setScheduleName(name);
            flag.setActiveYn("N");
            flag.setActiveServer(server);
            flag.setUpdateDate(DateUtil.getNowDate());
            tSchedulerFlagMapper.insert(flag);
        } else {
            if("Y".equals(flag.getActiveYn())) {
                // 스케줄러였던 상황이면 종료.
                flag.setActiveYn("N");
            } else if("R".equals(flag.getActiveYn())) {
                // 관리자가 DB의 Y스케줄 상태를 R예약으로 바꿨다면 자신의 스케줄 종료 후 중지
                flag.setActiveYn("P");
            }
            flag.setActiveServer(server);
            flag.setUpdateDate(DateUtil.getNowDate());
            tSchedulerFlagMapper.updateByPrimaryKey(flag);
        }
        return flag;
    }
}
