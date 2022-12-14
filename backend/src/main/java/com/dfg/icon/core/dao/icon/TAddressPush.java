package com.dfg.icon.core.dao.icon;

import java.util.Date;

public class TAddressPush extends TAddressPushKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_ADDRESS_PUSH.address
     *
     * @mbg.generated
     */
    private String address;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_ADDRESS_PUSH.endpoint
     *
     * @mbg.generated
     */
    private String endpoint;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_ADDRESS_PUSH.p256dh
     *
     * @mbg.generated
     */
    private String p256dh;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_ADDRESS_PUSH.auth
     *
     * @mbg.generated
     */
    private String auth;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_ADDRESS_PUSH.expire_date
     *
     * @mbg.generated
     */
    private Date expireDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_ADDRESS_PUSH.update_date
     *
     * @mbg.generated
     */
    private Date updateDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADDRESS_PUSH.address
     *
     * @return the value of T_ADDRESS_PUSH.address
     *
     * @mbg.generated
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADDRESS_PUSH.address
     *
     * @param address the value for T_ADDRESS_PUSH.address
     *
     * @mbg.generated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADDRESS_PUSH.endpoint
     *
     * @return the value of T_ADDRESS_PUSH.endpoint
     *
     * @mbg.generated
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADDRESS_PUSH.endpoint
     *
     * @param endpoint the value for T_ADDRESS_PUSH.endpoint
     *
     * @mbg.generated
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint == null ? null : endpoint.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADDRESS_PUSH.p256dh
     *
     * @return the value of T_ADDRESS_PUSH.p256dh
     *
     * @mbg.generated
     */
    public String getP256dh() {
        return p256dh;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADDRESS_PUSH.p256dh
     *
     * @param p256dh the value for T_ADDRESS_PUSH.p256dh
     *
     * @mbg.generated
     */
    public void setP256dh(String p256dh) {
        this.p256dh = p256dh == null ? null : p256dh.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADDRESS_PUSH.auth
     *
     * @return the value of T_ADDRESS_PUSH.auth
     *
     * @mbg.generated
     */
    public String getAuth() {
        return auth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADDRESS_PUSH.auth
     *
     * @param auth the value for T_ADDRESS_PUSH.auth
     *
     * @mbg.generated
     */
    public void setAuth(String auth) {
        this.auth = auth == null ? null : auth.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADDRESS_PUSH.expire_date
     *
     * @return the value of T_ADDRESS_PUSH.expire_date
     *
     * @mbg.generated
     */
    public Date getExpireDate() {
        return expireDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADDRESS_PUSH.expire_date
     *
     * @param expireDate the value for T_ADDRESS_PUSH.expire_date
     *
     * @mbg.generated
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADDRESS_PUSH.update_date
     *
     * @return the value of T_ADDRESS_PUSH.update_date
     *
     * @mbg.generated
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADDRESS_PUSH.update_date
     *
     * @param updateDate the value for T_ADDRESS_PUSH.update_date
     *
     * @mbg.generated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}