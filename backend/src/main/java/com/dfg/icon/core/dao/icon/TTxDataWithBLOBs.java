package com.dfg.icon.core.dao.icon;

public class TTxDataWithBLOBs extends TTxData {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_TX_DATA.data_body
     *
     * @mbg.generated
     */
    private String dataBody;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_TX_DATA.data_body
     *
     * @return the value of T_TX_DATA.data_body
     *
     * @mbg.generated
     */
    public String getDataBody() {
        return dataBody;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_TX_DATA.data_body
     *
     * @param dataBody the value for T_TX_DATA.data_body
     *
     * @mbg.generated
     */
    public void setDataBody(String dataBody) {
        this.dataBody = dataBody == null ? null : dataBody.trim();
    }
}