package com.dfg.icon.core.dao.icon;

import java.math.BigDecimal;

public class TAddressTotal extends TAddressTotalKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_ADDRESS_TOTAL.balance
     *
     * @mbg.generated
     */
    private String balance;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_ADDRESS_TOTAL.tx_count
     *
     * @mbg.generated
     */
    private Integer txCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_ADDRESS_TOTAL.node_type
     *
     * @mbg.generated
     */
    private String nodeType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_ADDRESS_TOTAL.is_update
     *
     * @mbg.generated
     */
    private Boolean isUpdate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_ADDRESS_TOTAL.balance_order
     *
     * @mbg.generated
     */
    private BigDecimal balanceOrder;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_ADDRESS_TOTAL.reported_count
     *
     * @mbg.generated
     */
    private Integer reportedCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_ADDRESS_TOTAL.report_count
     *
     * @mbg.generated
     */
    private Integer reportCount;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADDRESS_TOTAL.balance
     *
     * @return the value of T_ADDRESS_TOTAL.balance
     *
     * @mbg.generated
     */
    public String getBalance() {
        return balance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADDRESS_TOTAL.balance
     *
     * @param balance the value for T_ADDRESS_TOTAL.balance
     *
     * @mbg.generated
     */
    public void setBalance(String balance) {
        this.balance = balance == null ? null : balance.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADDRESS_TOTAL.tx_count
     *
     * @return the value of T_ADDRESS_TOTAL.tx_count
     *
     * @mbg.generated
     */
    public Integer getTxCount() {
        return txCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADDRESS_TOTAL.tx_count
     *
     * @param txCount the value for T_ADDRESS_TOTAL.tx_count
     *
     * @mbg.generated
     */
    public void setTxCount(Integer txCount) {
        this.txCount = txCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADDRESS_TOTAL.node_type
     *
     * @return the value of T_ADDRESS_TOTAL.node_type
     *
     * @mbg.generated
     */
    public String getNodeType() {
        return nodeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADDRESS_TOTAL.node_type
     *
     * @param nodeType the value for T_ADDRESS_TOTAL.node_type
     *
     * @mbg.generated
     */
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType == null ? null : nodeType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADDRESS_TOTAL.is_update
     *
     * @return the value of T_ADDRESS_TOTAL.is_update
     *
     * @mbg.generated
     */
    public Boolean getIsUpdate() {
        return isUpdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADDRESS_TOTAL.is_update
     *
     * @param isUpdate the value for T_ADDRESS_TOTAL.is_update
     *
     * @mbg.generated
     */
    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADDRESS_TOTAL.balance_order
     *
     * @return the value of T_ADDRESS_TOTAL.balance_order
     *
     * @mbg.generated
     */
    public BigDecimal getBalanceOrder() {
        return balanceOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADDRESS_TOTAL.balance_order
     *
     * @param balanceOrder the value for T_ADDRESS_TOTAL.balance_order
     *
     * @mbg.generated
     */
    public void setBalanceOrder(BigDecimal balanceOrder) {
        this.balanceOrder = balanceOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADDRESS_TOTAL.reported_count
     *
     * @return the value of T_ADDRESS_TOTAL.reported_count
     *
     * @mbg.generated
     */
    public Integer getReportedCount() {
        return reportedCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADDRESS_TOTAL.reported_count
     *
     * @param reportedCount the value for T_ADDRESS_TOTAL.reported_count
     *
     * @mbg.generated
     */
    public void setReportedCount(Integer reportedCount) {
        this.reportedCount = reportedCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADDRESS_TOTAL.report_count
     *
     * @return the value of T_ADDRESS_TOTAL.report_count
     *
     * @mbg.generated
     */
    public Integer getReportCount() {
        return reportCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADDRESS_TOTAL.report_count
     *
     * @param reportCount the value for T_ADDRESS_TOTAL.report_count
     *
     * @mbg.generated
     */
    public void setReportCount(Integer reportCount) {
        this.reportCount = reportCount;
    }
}