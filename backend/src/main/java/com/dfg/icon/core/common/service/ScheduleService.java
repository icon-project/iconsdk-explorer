package com.dfg.icon.core.common.service;

import com.dfg.icon.core.dao.icon.TSchedulerFlag;

public interface ScheduleService {
    TSchedulerFlag activeBlockSchedule(String server);
    TSchedulerFlag activeSchedule(String name, String server);
    TSchedulerFlag inActiveBlockSchedule(String server);
    TSchedulerFlag inActiveSchedule(String name, String server);
    void updateBlockScheduler(String nextPosition);
    void updateScheduler(String name, String nextPosition);

    TSchedulerFlag getBlockSchedule();
    TSchedulerFlag getSchedule(String name);
}
