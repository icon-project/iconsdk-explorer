package com.dfg.icon.core.dao.icon;

public class TTokenAddressKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_TOKEN_ADDRESS.address
     *
     * @mbg.generated
     */
    private String address;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_TOKEN_ADDRESS.contract_addr
     *
     * @mbg.generated
     */
    private String contractAddr;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_TOKEN_ADDRESS.address
     *
     * @return the value of T_TOKEN_ADDRESS.address
     *
     * @mbg.generated
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_TOKEN_ADDRESS.address
     *
     * @param address the value for T_TOKEN_ADDRESS.address
     *
     * @mbg.generated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_TOKEN_ADDRESS.contract_addr
     *
     * @return the value of T_TOKEN_ADDRESS.contract_addr
     *
     * @mbg.generated
     */
    public String getContractAddr() {
        return contractAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_TOKEN_ADDRESS.contract_addr
     *
     * @param contractAddr the value for T_TOKEN_ADDRESS.contract_addr
     *
     * @mbg.generated
     */
    public void setContractAddr(String contractAddr) {
        this.contractAddr = contractAddr == null ? null : contractAddr.trim();
    }
}