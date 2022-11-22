package com.dfg.icon.core.dao.icon;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TBtpHeaderExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_BTP_HEADER
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_BTP_HEADER
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_BTP_HEADER
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BTP_HEADER
     *
     * @mbg.generated
     */
    public TBtpHeaderExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BTP_HEADER
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BTP_HEADER
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BTP_HEADER
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BTP_HEADER
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BTP_HEADER
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BTP_HEADER
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BTP_HEADER
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BTP_HEADER
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BTP_HEADER
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BTP_HEADER
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table T_BTP_HEADER
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andBlockHeightIsNull() {
            addCriterion("block_height is null");
            return (Criteria) this;
        }

        public Criteria andBlockHeightIsNotNull() {
            addCriterion("block_height is not null");
            return (Criteria) this;
        }

        public Criteria andBlockHeightEqualTo(Integer value) {
            addCriterion("block_height =", value, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andBlockHeightNotEqualTo(Integer value) {
            addCriterion("block_height <>", value, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andBlockHeightGreaterThan(Integer value) {
            addCriterion("block_height >", value, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andBlockHeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("block_height >=", value, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andBlockHeightLessThan(Integer value) {
            addCriterion("block_height <", value, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andBlockHeightLessThanOrEqualTo(Integer value) {
            addCriterion("block_height <=", value, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andBlockHeightIn(List<Integer> values) {
            addCriterion("block_height in", values, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andBlockHeightNotIn(List<Integer> values) {
            addCriterion("block_height not in", values, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andBlockHeightBetween(Integer value1, Integer value2) {
            addCriterion("block_height between", value1, value2, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andBlockHeightNotBetween(Integer value1, Integer value2) {
            addCriterion("block_height not between", value1, value2, "blockHeight");
            return (Criteria) this;
        }

        public Criteria andNetworkIdIsNull() {
            addCriterion("network_id is null");
            return (Criteria) this;
        }

        public Criteria andNetworkIdIsNotNull() {
            addCriterion("network_id is not null");
            return (Criteria) this;
        }

        public Criteria andNetworkIdEqualTo(String value) {
            addCriterion("network_id =", value, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdNotEqualTo(String value) {
            addCriterion("network_id <>", value, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdGreaterThan(String value) {
            addCriterion("network_id >", value, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdGreaterThanOrEqualTo(String value) {
            addCriterion("network_id >=", value, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdLessThan(String value) {
            addCriterion("network_id <", value, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdLessThanOrEqualTo(String value) {
            addCriterion("network_id <=", value, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdLike(String value) {
            addCriterion("network_id like", value, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdNotLike(String value) {
            addCriterion("network_id not like", value, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdIn(List<String> values) {
            addCriterion("network_id in", values, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdNotIn(List<String> values) {
            addCriterion("network_id not in", values, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdBetween(String value1, String value2) {
            addCriterion("network_id between", value1, value2, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdNotBetween(String value1, String value2) {
            addCriterion("network_id not between", value1, value2, "networkId");
            return (Criteria) this;
        }

        public Criteria andUpdateNumberIsNull() {
            addCriterion("update_number is null");
            return (Criteria) this;
        }

        public Criteria andUpdateNumberIsNotNull() {
            addCriterion("update_number is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateNumberEqualTo(String value) {
            addCriterion("update_number =", value, "updateNumber");
            return (Criteria) this;
        }

        public Criteria andUpdateNumberNotEqualTo(String value) {
            addCriterion("update_number <>", value, "updateNumber");
            return (Criteria) this;
        }

        public Criteria andUpdateNumberGreaterThan(String value) {
            addCriterion("update_number >", value, "updateNumber");
            return (Criteria) this;
        }

        public Criteria andUpdateNumberGreaterThanOrEqualTo(String value) {
            addCriterion("update_number >=", value, "updateNumber");
            return (Criteria) this;
        }

        public Criteria andUpdateNumberLessThan(String value) {
            addCriterion("update_number <", value, "updateNumber");
            return (Criteria) this;
        }

        public Criteria andUpdateNumberLessThanOrEqualTo(String value) {
            addCriterion("update_number <=", value, "updateNumber");
            return (Criteria) this;
        }

        public Criteria andUpdateNumberLike(String value) {
            addCriterion("update_number like", value, "updateNumber");
            return (Criteria) this;
        }

        public Criteria andUpdateNumberNotLike(String value) {
            addCriterion("update_number not like", value, "updateNumber");
            return (Criteria) this;
        }

        public Criteria andUpdateNumberIn(List<String> values) {
            addCriterion("update_number in", values, "updateNumber");
            return (Criteria) this;
        }

        public Criteria andUpdateNumberNotIn(List<String> values) {
            addCriterion("update_number not in", values, "updateNumber");
            return (Criteria) this;
        }

        public Criteria andUpdateNumberBetween(String value1, String value2) {
            addCriterion("update_number between", value1, value2, "updateNumber");
            return (Criteria) this;
        }

        public Criteria andUpdateNumberNotBetween(String value1, String value2) {
            addCriterion("update_number not between", value1, value2, "updateNumber");
            return (Criteria) this;
        }

        public Criteria andPrevIsNull() {
            addCriterion("prev is null");
            return (Criteria) this;
        }

        public Criteria andPrevIsNotNull() {
            addCriterion("prev is not null");
            return (Criteria) this;
        }

        public Criteria andPrevEqualTo(String value) {
            addCriterion("prev =", value, "prev");
            return (Criteria) this;
        }

        public Criteria andPrevNotEqualTo(String value) {
            addCriterion("prev <>", value, "prev");
            return (Criteria) this;
        }

        public Criteria andPrevGreaterThan(String value) {
            addCriterion("prev >", value, "prev");
            return (Criteria) this;
        }

        public Criteria andPrevGreaterThanOrEqualTo(String value) {
            addCriterion("prev >=", value, "prev");
            return (Criteria) this;
        }

        public Criteria andPrevLessThan(String value) {
            addCriterion("prev <", value, "prev");
            return (Criteria) this;
        }

        public Criteria andPrevLessThanOrEqualTo(String value) {
            addCriterion("prev <=", value, "prev");
            return (Criteria) this;
        }

        public Criteria andPrevLike(String value) {
            addCriterion("prev like", value, "prev");
            return (Criteria) this;
        }

        public Criteria andPrevNotLike(String value) {
            addCriterion("prev not like", value, "prev");
            return (Criteria) this;
        }

        public Criteria andPrevIn(List<String> values) {
            addCriterion("prev in", values, "prev");
            return (Criteria) this;
        }

        public Criteria andPrevNotIn(List<String> values) {
            addCriterion("prev not in", values, "prev");
            return (Criteria) this;
        }

        public Criteria andPrevBetween(String value1, String value2) {
            addCriterion("prev between", value1, value2, "prev");
            return (Criteria) this;
        }

        public Criteria andPrevNotBetween(String value1, String value2) {
            addCriterion("prev not between", value1, value2, "prev");
            return (Criteria) this;
        }

        public Criteria andMessageCntIsNull() {
            addCriterion("message_cnt is null");
            return (Criteria) this;
        }

        public Criteria andMessageCntIsNotNull() {
            addCriterion("message_cnt is not null");
            return (Criteria) this;
        }

        public Criteria andMessageCntEqualTo(Integer value) {
            addCriterion("message_cnt =", value, "messageCnt");
            return (Criteria) this;
        }

        public Criteria andMessageCntNotEqualTo(Integer value) {
            addCriterion("message_cnt <>", value, "messageCnt");
            return (Criteria) this;
        }

        public Criteria andMessageCntGreaterThan(Integer value) {
            addCriterion("message_cnt >", value, "messageCnt");
            return (Criteria) this;
        }

        public Criteria andMessageCntGreaterThanOrEqualTo(Integer value) {
            addCriterion("message_cnt >=", value, "messageCnt");
            return (Criteria) this;
        }

        public Criteria andMessageCntLessThan(Integer value) {
            addCriterion("message_cnt <", value, "messageCnt");
            return (Criteria) this;
        }

        public Criteria andMessageCntLessThanOrEqualTo(Integer value) {
            addCriterion("message_cnt <=", value, "messageCnt");
            return (Criteria) this;
        }

        public Criteria andMessageCntIn(List<Integer> values) {
            addCriterion("message_cnt in", values, "messageCnt");
            return (Criteria) this;
        }

        public Criteria andMessageCntNotIn(List<Integer> values) {
            addCriterion("message_cnt not in", values, "messageCnt");
            return (Criteria) this;
        }

        public Criteria andMessageCntBetween(Integer value1, Integer value2) {
            addCriterion("message_cnt between", value1, value2, "messageCnt");
            return (Criteria) this;
        }

        public Criteria andMessageCntNotBetween(Integer value1, Integer value2) {
            addCriterion("message_cnt not between", value1, value2, "messageCnt");
            return (Criteria) this;
        }

        public Criteria andMessageRootIsNull() {
            addCriterion("message_root is null");
            return (Criteria) this;
        }

        public Criteria andMessageRootIsNotNull() {
            addCriterion("message_root is not null");
            return (Criteria) this;
        }

        public Criteria andMessageRootEqualTo(String value) {
            addCriterion("message_root =", value, "messageRoot");
            return (Criteria) this;
        }

        public Criteria andMessageRootNotEqualTo(String value) {
            addCriterion("message_root <>", value, "messageRoot");
            return (Criteria) this;
        }

        public Criteria andMessageRootGreaterThan(String value) {
            addCriterion("message_root >", value, "messageRoot");
            return (Criteria) this;
        }

        public Criteria andMessageRootGreaterThanOrEqualTo(String value) {
            addCriterion("message_root >=", value, "messageRoot");
            return (Criteria) this;
        }

        public Criteria andMessageRootLessThan(String value) {
            addCriterion("message_root <", value, "messageRoot");
            return (Criteria) this;
        }

        public Criteria andMessageRootLessThanOrEqualTo(String value) {
            addCriterion("message_root <=", value, "messageRoot");
            return (Criteria) this;
        }

        public Criteria andMessageRootLike(String value) {
            addCriterion("message_root like", value, "messageRoot");
            return (Criteria) this;
        }

        public Criteria andMessageRootNotLike(String value) {
            addCriterion("message_root not like", value, "messageRoot");
            return (Criteria) this;
        }

        public Criteria andMessageRootIn(List<String> values) {
            addCriterion("message_root in", values, "messageRoot");
            return (Criteria) this;
        }

        public Criteria andMessageRootNotIn(List<String> values) {
            addCriterion("message_root not in", values, "messageRoot");
            return (Criteria) this;
        }

        public Criteria andMessageRootBetween(String value1, String value2) {
            addCriterion("message_root between", value1, value2, "messageRoot");
            return (Criteria) this;
        }

        public Criteria andMessageRootNotBetween(String value1, String value2) {
            addCriterion("message_root not between", value1, value2, "messageRoot");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andBtpNetworkNetworkIdIsNull() {
            addCriterion("btp_network_network_id is null");
            return (Criteria) this;
        }

        public Criteria andBtpNetworkNetworkIdIsNotNull() {
            addCriterion("btp_network_network_id is not null");
            return (Criteria) this;
        }

        public Criteria andBtpNetworkNetworkIdEqualTo(String value) {
            addCriterion("btp_network_network_id =", value, "btpNetworkNetworkId");
            return (Criteria) this;
        }

        public Criteria andBtpNetworkNetworkIdNotEqualTo(String value) {
            addCriterion("btp_network_network_id <>", value, "btpNetworkNetworkId");
            return (Criteria) this;
        }

        public Criteria andBtpNetworkNetworkIdGreaterThan(String value) {
            addCriterion("btp_network_network_id >", value, "btpNetworkNetworkId");
            return (Criteria) this;
        }

        public Criteria andBtpNetworkNetworkIdGreaterThanOrEqualTo(String value) {
            addCriterion("btp_network_network_id >=", value, "btpNetworkNetworkId");
            return (Criteria) this;
        }

        public Criteria andBtpNetworkNetworkIdLessThan(String value) {
            addCriterion("btp_network_network_id <", value, "btpNetworkNetworkId");
            return (Criteria) this;
        }

        public Criteria andBtpNetworkNetworkIdLessThanOrEqualTo(String value) {
            addCriterion("btp_network_network_id <=", value, "btpNetworkNetworkId");
            return (Criteria) this;
        }

        public Criteria andBtpNetworkNetworkIdLike(String value) {
            addCriterion("btp_network_network_id like", value, "btpNetworkNetworkId");
            return (Criteria) this;
        }

        public Criteria andBtpNetworkNetworkIdNotLike(String value) {
            addCriterion("btp_network_network_id not like", value, "btpNetworkNetworkId");
            return (Criteria) this;
        }

        public Criteria andBtpNetworkNetworkIdIn(List<String> values) {
            addCriterion("btp_network_network_id in", values, "btpNetworkNetworkId");
            return (Criteria) this;
        }

        public Criteria andBtpNetworkNetworkIdNotIn(List<String> values) {
            addCriterion("btp_network_network_id not in", values, "btpNetworkNetworkId");
            return (Criteria) this;
        }

        public Criteria andBtpNetworkNetworkIdBetween(String value1, String value2) {
            addCriterion("btp_network_network_id between", value1, value2, "btpNetworkNetworkId");
            return (Criteria) this;
        }

        public Criteria andBtpNetworkNetworkIdNotBetween(String value1, String value2) {
            addCriterion("btp_network_network_id not between", value1, value2, "btpNetworkNetworkId");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table T_BTP_HEADER
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table T_BTP_HEADER
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}