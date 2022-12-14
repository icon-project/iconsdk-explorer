package com.dfg.icon.core.dao.icon;

public class TTokenTxView extends TTokenTxViewKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_TOKEN_TX_VIEW.contract_addr
     *
     * @mbg.generated
     */
    private String contractAddr;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_TOKEN_TX_VIEW.tx_hash
     *
     * @mbg.generated
     */
    private String txHash;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_TOKEN_TX_VIEW.tx_index
     *
     * @mbg.generated
     */
    private Integer txIndex;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_TOKEN_TX_VIEW.block_height
     *
     * @mbg.generated
     */
    private Integer blockHeight;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_TOKEN_TX_VIEW.address
     *
     * @mbg.generated
     */
    private String address;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_TOKEN_TX_VIEW.address_type
     *
     * @mbg.generated
     */
    private Boolean addressType;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_TOKEN_TX_VIEW.contract_addr
     *
     * @return the value of T_TOKEN_TX_VIEW.contract_addr
     *
     * @mbg.generated
     */
    public String getContractAddr() {
        return contractAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_TOKEN_TX_VIEW.contract_addr
     *
     * @param contractAddr the value for T_TOKEN_TX_VIEW.contract_addr
     *
     * @mbg.generated
     */
    public void setContractAddr(String contractAddr) {
        this.contractAddr = contractAddr == null ? null : contractAddr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_TOKEN_TX_VIEW.tx_hash
     *
     * @return the value of T_TOKEN_TX_VIEW.tx_hash
     *
     * @mbg.generated
     */
    public String getTxHash() {
        return txHash;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_TOKEN_TX_VIEW.tx_hash
     *
     * @param txHash the value for T_TOKEN_TX_VIEW.tx_hash
     *
     * @mbg.generated
     */
    public void setTxHash(String txHash) {
        this.txHash = txHash == null ? null : txHash.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_TOKEN_TX_VIEW.tx_index
     *
     * @return the value of T_TOKEN_TX_VIEW.tx_index
     *
     * @mbg.generated
     */
    public Integer getTxIndex() {
        return txIndex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_TOKEN_TX_VIEW.tx_index
     *
     * @param txIndex the value for T_TOKEN_TX_VIEW.tx_index
     *
     * @mbg.generated
     */
    public void setTxIndex(Integer txIndex) {
        this.txIndex = txIndex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_TOKEN_TX_VIEW.block_height
     *
     * @return the value of T_TOKEN_TX_VIEW.block_height
     *
     * @mbg.generated
     */
    public Integer getBlockHeight() {
        return blockHeight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_TOKEN_TX_VIEW.block_height
     *
     * @param blockHeight the value for T_TOKEN_TX_VIEW.block_height
     *
     * @mbg.generated
     */
    public void setBlockHeight(Integer blockHeight) {
        this.blockHeight = blockHeight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_TOKEN_TX_VIEW.address
     *
     * @return the value of T_TOKEN_TX_VIEW.address
     *
     * @mbg.generated
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_TOKEN_TX_VIEW.address
     *
     * @param address the value for T_TOKEN_TX_VIEW.address
     *
     * @mbg.generated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_TOKEN_TX_VIEW.address_type
     *
     * @return the value of T_TOKEN_TX_VIEW.address_type
     *
     * @mbg.generated
     */
    public Boolean getAddressType() {
        return addressType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_TOKEN_TX_VIEW.address_type
     *
     * @param addressType the value for T_TOKEN_TX_VIEW.address_type
     *
     * @mbg.generated
     */
    public void setAddressType(Boolean addressType) {
        this.addressType = addressType;
    }
}