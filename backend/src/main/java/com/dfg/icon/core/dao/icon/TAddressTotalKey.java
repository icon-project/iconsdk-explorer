package com.dfg.icon.core.dao.icon;

public class TAddressTotalKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_ADDRESS_TOTAL.address
     *
     * @mbg.generated
     */
    private String address;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADDRESS_TOTAL.address
     *
     * @return the value of T_ADDRESS_TOTAL.address
     *
     * @mbg.generated
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADDRESS_TOTAL.address
     *
     * @param address the value for T_ADDRESS_TOTAL.address
     *
     * @mbg.generated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}