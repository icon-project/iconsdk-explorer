package com.dfg.icon.core.dao.icon;

public class TFeeDetails extends TFeeDetailsKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_FEE_DETAILS.step_used_details
     *
     * @mbg.generated
     */
    private String stepUsedDetails;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_FEE_DETAILS.step_used_details
     *
     * @return the value of T_FEE_DETAILS.step_used_details
     *
     * @mbg.generated
     */
    public String getStepUsedDetails() {
        return stepUsedDetails;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_FEE_DETAILS.step_used_details
     *
     * @param stepUsedDetails the value for T_FEE_DETAILS.step_used_details
     *
     * @mbg.generated
     */
    public void setStepUsedDetails(String stepUsedDetails) {
        this.stepUsedDetails = stepUsedDetails == null ? null : stepUsedDetails.trim();
    }
}