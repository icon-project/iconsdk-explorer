package com.dfg.icon.core.dao.icon;

import java.math.BigDecimal;

public class TTokenAddress extends TTokenAddressKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_TOKEN_ADDRESS.tx_count
     *
     * @mbg.generated
     */
    private Integer txCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_TOKEN_ADDRESS.quantity
     *
     * @mbg.generated
     */
    private String quantity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_TOKEN_ADDRESS.quantity_order
     *
     * @mbg.generated
     */
    private BigDecimal quantityOrder;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_TOKEN_ADDRESS.tx_count
     *
     * @return the value of T_TOKEN_ADDRESS.tx_count
     *
     * @mbg.generated
     */
    public Integer getTxCount() {
        return txCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_TOKEN_ADDRESS.tx_count
     *
     * @param txCount the value for T_TOKEN_ADDRESS.tx_count
     *
     * @mbg.generated
     */
    public void setTxCount(Integer txCount) {
        this.txCount = txCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_TOKEN_ADDRESS.quantity
     *
     * @return the value of T_TOKEN_ADDRESS.quantity
     *
     * @mbg.generated
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_TOKEN_ADDRESS.quantity
     *
     * @param quantity the value for T_TOKEN_ADDRESS.quantity
     *
     * @mbg.generated
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity == null ? null : quantity.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_TOKEN_ADDRESS.quantity_order
     *
     * @return the value of T_TOKEN_ADDRESS.quantity_order
     *
     * @mbg.generated
     */
    public BigDecimal getQuantityOrder() {
        return quantityOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_TOKEN_ADDRESS.quantity_order
     *
     * @param quantityOrder the value for T_TOKEN_ADDRESS.quantity_order
     *
     * @mbg.generated
     */
    public void setQuantityOrder(BigDecimal quantityOrder) {
        this.quantityOrder = quantityOrder;
    }
}