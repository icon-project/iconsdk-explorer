package com.dfg.icon.core.dao.icon;

public class TTxResultLogWithBLOBs extends TTxResultLog {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_TX_RESULT_LOG.event_log
     *
     * @mbg.generated
     */
    private String eventLog;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_TX_RESULT_LOG.event_log
     *
     * @return the value of T_TX_RESULT_LOG.event_log
     *
     * @mbg.generated
     */
    public String getEventLog() {
        return eventLog;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_TX_RESULT_LOG.event_log
     *
     * @param eventLog the value for T_TX_RESULT_LOG.event_log
     *
     * @mbg.generated
     */
    public void setEventLog(String eventLog) {
        this.eventLog = eventLog == null ? null : eventLog.trim();
    }
}