package com.dfg.icon.core.dao.icon;

public class TContract extends TContractKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_CONTRACT.version
     *
     * @mbg.generated
     */
    private Integer version;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_CONTRACT.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_CONTRACT.irc_version
     *
     * @mbg.generated
     */
    private String ircVersion;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_CONTRACT.symbol
     *
     * @mbg.generated
     */
    private String symbol;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_CONTRACT.holder_addr
     *
     * @mbg.generated
     */
    private String holderAddr;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_CONTRACT.holder_count
     *
     * @mbg.generated
     */
    private Integer holderCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_CONTRACT.transfer_count
     *
     * @mbg.generated
     */
    private Integer transferCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_CONTRACT.total_supply
     *
     * @mbg.generated
     */
    private String totalSupply;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_CONTRACT.decimals
     *
     * @mbg.generated
     */
    private Integer decimals;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_CONTRACT.state
     *
     * @mbg.generated
     */
    private Byte state;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_CONTRACT.active_sort
     *
     * @mbg.generated
     */
    private Boolean activeSort;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_CONTRACT.name_sort
     *
     * @mbg.generated
     */
    private Boolean nameSort;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_CONTRACT.version
     *
     * @return the value of T_CONTRACT.version
     *
     * @mbg.generated
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_CONTRACT.version
     *
     * @param version the value for T_CONTRACT.version
     *
     * @mbg.generated
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_CONTRACT.name
     *
     * @return the value of T_CONTRACT.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_CONTRACT.name
     *
     * @param name the value for T_CONTRACT.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_CONTRACT.irc_version
     *
     * @return the value of T_CONTRACT.irc_version
     *
     * @mbg.generated
     */
    public String getIrcVersion() {
        return ircVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_CONTRACT.irc_version
     *
     * @param ircVersion the value for T_CONTRACT.irc_version
     *
     * @mbg.generated
     */
    public void setIrcVersion(String ircVersion) {
        this.ircVersion = ircVersion == null ? null : ircVersion.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_CONTRACT.symbol
     *
     * @return the value of T_CONTRACT.symbol
     *
     * @mbg.generated
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_CONTRACT.symbol
     *
     * @param symbol the value for T_CONTRACT.symbol
     *
     * @mbg.generated
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol == null ? null : symbol.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_CONTRACT.holder_addr
     *
     * @return the value of T_CONTRACT.holder_addr
     *
     * @mbg.generated
     */
    public String getHolderAddr() {
        return holderAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_CONTRACT.holder_addr
     *
     * @param holderAddr the value for T_CONTRACT.holder_addr
     *
     * @mbg.generated
     */
    public void setHolderAddr(String holderAddr) {
        this.holderAddr = holderAddr == null ? null : holderAddr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_CONTRACT.holder_count
     *
     * @return the value of T_CONTRACT.holder_count
     *
     * @mbg.generated
     */
    public Integer getHolderCount() {
        return holderCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_CONTRACT.holder_count
     *
     * @param holderCount the value for T_CONTRACT.holder_count
     *
     * @mbg.generated
     */
    public void setHolderCount(Integer holderCount) {
        this.holderCount = holderCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_CONTRACT.transfer_count
     *
     * @return the value of T_CONTRACT.transfer_count
     *
     * @mbg.generated
     */
    public Integer getTransferCount() {
        return transferCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_CONTRACT.transfer_count
     *
     * @param transferCount the value for T_CONTRACT.transfer_count
     *
     * @mbg.generated
     */
    public void setTransferCount(Integer transferCount) {
        this.transferCount = transferCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_CONTRACT.total_supply
     *
     * @return the value of T_CONTRACT.total_supply
     *
     * @mbg.generated
     */
    public String getTotalSupply() {
        return totalSupply;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_CONTRACT.total_supply
     *
     * @param totalSupply the value for T_CONTRACT.total_supply
     *
     * @mbg.generated
     */
    public void setTotalSupply(String totalSupply) {
        this.totalSupply = totalSupply == null ? null : totalSupply.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_CONTRACT.decimals
     *
     * @return the value of T_CONTRACT.decimals
     *
     * @mbg.generated
     */
    public Integer getDecimals() {
        return decimals;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_CONTRACT.decimals
     *
     * @param decimals the value for T_CONTRACT.decimals
     *
     * @mbg.generated
     */
    public void setDecimals(Integer decimals) {
        this.decimals = decimals;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_CONTRACT.state
     *
     * @return the value of T_CONTRACT.state
     *
     * @mbg.generated
     */
    public Byte getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_CONTRACT.state
     *
     * @param state the value for T_CONTRACT.state
     *
     * @mbg.generated
     */
    public void setState(Byte state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_CONTRACT.active_sort
     *
     * @return the value of T_CONTRACT.active_sort
     *
     * @mbg.generated
     */
    public Boolean getActiveSort() {
        return activeSort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_CONTRACT.active_sort
     *
     * @param activeSort the value for T_CONTRACT.active_sort
     *
     * @mbg.generated
     */
    public void setActiveSort(Boolean activeSort) {
        this.activeSort = activeSort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_CONTRACT.name_sort
     *
     * @return the value of T_CONTRACT.name_sort
     *
     * @mbg.generated
     */
    public Boolean getNameSort() {
        return nameSort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_CONTRACT.name_sort
     *
     * @param nameSort the value for T_CONTRACT.name_sort
     *
     * @mbg.generated
     */
    public void setNameSort(Boolean nameSort) {
        this.nameSort = nameSort;
    }
}