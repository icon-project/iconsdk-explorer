package com.dfg.icon.core.dao.icon;

public class TContractKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_CONTRACT.contract_addr
     *
     * @mbg.generated
     */
    private String contractAddr;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_CONTRACT.contract_addr
     *
     * @return the value of T_CONTRACT.contract_addr
     *
     * @mbg.generated
     */
    public String getContractAddr() {
        return contractAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_CONTRACT.contract_addr
     *
     * @param contractAddr the value for T_CONTRACT.contract_addr
     *
     * @mbg.generated
     */
    public void setContractAddr(String contractAddr) {
        this.contractAddr = contractAddr == null ? null : contractAddr.trim();
    }
}