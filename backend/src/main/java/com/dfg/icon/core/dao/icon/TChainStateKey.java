package com.dfg.icon.core.dao.icon;

public class TChainStateKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_CHAIN_STATE.chain_name
     *
     * @mbg.generated
     */
    private String chainName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_CHAIN_STATE.chain_name
     *
     * @return the value of T_CHAIN_STATE.chain_name
     *
     * @mbg.generated
     */
    public String getChainName() {
        return chainName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_CHAIN_STATE.chain_name
     *
     * @param chainName the value for T_CHAIN_STATE.chain_name
     *
     * @mbg.generated
     */
    public void setChainName(String chainName) {
        this.chainName = chainName == null ? null : chainName.trim();
    }
}