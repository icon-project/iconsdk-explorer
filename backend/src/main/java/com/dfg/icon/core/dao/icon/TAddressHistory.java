package com.dfg.icon.core.dao.icon;

import java.util.Date;

public class TAddressHistory extends TAddressHistoryKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_ADDRESS_HISTORY.balance_address_count
     *
     * @mbg.generated
     */
    private Integer balanceAddressCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_ADDRESS_HISTORY.total_address_count
     *
     * @mbg.generated
     */
    private Integer totalAddressCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_ADDRESS_HISTORY.standard_date
     *
     * @mbg.generated
     */
    private Date standardDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_ADDRESS_HISTORY.create_date
     *
     * @mbg.generated
     */
    private Date createDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADDRESS_HISTORY.balance_address_count
     *
     * @return the value of T_ADDRESS_HISTORY.balance_address_count
     *
     * @mbg.generated
     */
    public Integer getBalanceAddressCount() {
        return balanceAddressCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADDRESS_HISTORY.balance_address_count
     *
     * @param balanceAddressCount the value for T_ADDRESS_HISTORY.balance_address_count
     *
     * @mbg.generated
     */
    public void setBalanceAddressCount(Integer balanceAddressCount) {
        this.balanceAddressCount = balanceAddressCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADDRESS_HISTORY.total_address_count
     *
     * @return the value of T_ADDRESS_HISTORY.total_address_count
     *
     * @mbg.generated
     */
    public Integer getTotalAddressCount() {
        return totalAddressCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADDRESS_HISTORY.total_address_count
     *
     * @param totalAddressCount the value for T_ADDRESS_HISTORY.total_address_count
     *
     * @mbg.generated
     */
    public void setTotalAddressCount(Integer totalAddressCount) {
        this.totalAddressCount = totalAddressCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADDRESS_HISTORY.standard_date
     *
     * @return the value of T_ADDRESS_HISTORY.standard_date
     *
     * @mbg.generated
     */
    public Date getStandardDate() {
        return standardDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADDRESS_HISTORY.standard_date
     *
     * @param standardDate the value for T_ADDRESS_HISTORY.standard_date
     *
     * @mbg.generated
     */
    public void setStandardDate(Date standardDate) {
        this.standardDate = standardDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADDRESS_HISTORY.create_date
     *
     * @return the value of T_ADDRESS_HISTORY.create_date
     *
     * @mbg.generated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADDRESS_HISTORY.create_date
     *
     * @param createDate the value for T_ADDRESS_HISTORY.create_date
     *
     * @mbg.generated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}