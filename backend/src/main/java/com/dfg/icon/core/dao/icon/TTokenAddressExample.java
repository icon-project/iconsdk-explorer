package com.dfg.icon.core.dao.icon;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TTokenAddressExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_TOKEN_ADDRESS
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_TOKEN_ADDRESS
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_TOKEN_ADDRESS
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_ADDRESS
     *
     * @mbg.generated
     */
    public TTokenAddressExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_ADDRESS
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_ADDRESS
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_ADDRESS
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_ADDRESS
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_ADDRESS
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_ADDRESS
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_ADDRESS
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
     * This method corresponds to the database table T_TOKEN_ADDRESS
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
     * This method corresponds to the database table T_TOKEN_ADDRESS
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_ADDRESS
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
     * This class corresponds to the database table T_TOKEN_ADDRESS
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

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andContractAddrIsNull() {
            addCriterion("contract_addr is null");
            return (Criteria) this;
        }

        public Criteria andContractAddrIsNotNull() {
            addCriterion("contract_addr is not null");
            return (Criteria) this;
        }

        public Criteria andContractAddrEqualTo(String value) {
            addCriterion("contract_addr =", value, "contractAddr");
            return (Criteria) this;
        }

        public Criteria andContractAddrNotEqualTo(String value) {
            addCriterion("contract_addr <>", value, "contractAddr");
            return (Criteria) this;
        }

        public Criteria andContractAddrGreaterThan(String value) {
            addCriterion("contract_addr >", value, "contractAddr");
            return (Criteria) this;
        }

        public Criteria andContractAddrGreaterThanOrEqualTo(String value) {
            addCriterion("contract_addr >=", value, "contractAddr");
            return (Criteria) this;
        }

        public Criteria andContractAddrLessThan(String value) {
            addCriterion("contract_addr <", value, "contractAddr");
            return (Criteria) this;
        }

        public Criteria andContractAddrLessThanOrEqualTo(String value) {
            addCriterion("contract_addr <=", value, "contractAddr");
            return (Criteria) this;
        }

        public Criteria andContractAddrLike(String value) {
            addCriterion("contract_addr like", value, "contractAddr");
            return (Criteria) this;
        }

        public Criteria andContractAddrNotLike(String value) {
            addCriterion("contract_addr not like", value, "contractAddr");
            return (Criteria) this;
        }

        public Criteria andContractAddrIn(List<String> values) {
            addCriterion("contract_addr in", values, "contractAddr");
            return (Criteria) this;
        }

        public Criteria andContractAddrNotIn(List<String> values) {
            addCriterion("contract_addr not in", values, "contractAddr");
            return (Criteria) this;
        }

        public Criteria andContractAddrBetween(String value1, String value2) {
            addCriterion("contract_addr between", value1, value2, "contractAddr");
            return (Criteria) this;
        }

        public Criteria andContractAddrNotBetween(String value1, String value2) {
            addCriterion("contract_addr not between", value1, value2, "contractAddr");
            return (Criteria) this;
        }

        public Criteria andTxCountIsNull() {
            addCriterion("tx_count is null");
            return (Criteria) this;
        }

        public Criteria andTxCountIsNotNull() {
            addCriterion("tx_count is not null");
            return (Criteria) this;
        }

        public Criteria andTxCountEqualTo(Integer value) {
            addCriterion("tx_count =", value, "txCount");
            return (Criteria) this;
        }

        public Criteria andTxCountNotEqualTo(Integer value) {
            addCriterion("tx_count <>", value, "txCount");
            return (Criteria) this;
        }

        public Criteria andTxCountGreaterThan(Integer value) {
            addCriterion("tx_count >", value, "txCount");
            return (Criteria) this;
        }

        public Criteria andTxCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("tx_count >=", value, "txCount");
            return (Criteria) this;
        }

        public Criteria andTxCountLessThan(Integer value) {
            addCriterion("tx_count <", value, "txCount");
            return (Criteria) this;
        }

        public Criteria andTxCountLessThanOrEqualTo(Integer value) {
            addCriterion("tx_count <=", value, "txCount");
            return (Criteria) this;
        }

        public Criteria andTxCountIn(List<Integer> values) {
            addCriterion("tx_count in", values, "txCount");
            return (Criteria) this;
        }

        public Criteria andTxCountNotIn(List<Integer> values) {
            addCriterion("tx_count not in", values, "txCount");
            return (Criteria) this;
        }

        public Criteria andTxCountBetween(Integer value1, Integer value2) {
            addCriterion("tx_count between", value1, value2, "txCount");
            return (Criteria) this;
        }

        public Criteria andTxCountNotBetween(Integer value1, Integer value2) {
            addCriterion("tx_count not between", value1, value2, "txCount");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNull() {
            addCriterion("quantity is null");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNotNull() {
            addCriterion("quantity is not null");
            return (Criteria) this;
        }

        public Criteria andQuantityEqualTo(String value) {
            addCriterion("quantity =", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotEqualTo(String value) {
            addCriterion("quantity <>", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThan(String value) {
            addCriterion("quantity >", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThanOrEqualTo(String value) {
            addCriterion("quantity >=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThan(String value) {
            addCriterion("quantity <", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThanOrEqualTo(String value) {
            addCriterion("quantity <=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLike(String value) {
            addCriterion("quantity like", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotLike(String value) {
            addCriterion("quantity not like", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityIn(List<String> values) {
            addCriterion("quantity in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotIn(List<String> values) {
            addCriterion("quantity not in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityBetween(String value1, String value2) {
            addCriterion("quantity between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotBetween(String value1, String value2) {
            addCriterion("quantity not between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityOrderIsNull() {
            addCriterion("quantity_order is null");
            return (Criteria) this;
        }

        public Criteria andQuantityOrderIsNotNull() {
            addCriterion("quantity_order is not null");
            return (Criteria) this;
        }

        public Criteria andQuantityOrderEqualTo(BigDecimal value) {
            addCriterion("quantity_order =", value, "quantityOrder");
            return (Criteria) this;
        }

        public Criteria andQuantityOrderNotEqualTo(BigDecimal value) {
            addCriterion("quantity_order <>", value, "quantityOrder");
            return (Criteria) this;
        }

        public Criteria andQuantityOrderGreaterThan(BigDecimal value) {
            addCriterion("quantity_order >", value, "quantityOrder");
            return (Criteria) this;
        }

        public Criteria andQuantityOrderGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("quantity_order >=", value, "quantityOrder");
            return (Criteria) this;
        }

        public Criteria andQuantityOrderLessThan(BigDecimal value) {
            addCriterion("quantity_order <", value, "quantityOrder");
            return (Criteria) this;
        }

        public Criteria andQuantityOrderLessThanOrEqualTo(BigDecimal value) {
            addCriterion("quantity_order <=", value, "quantityOrder");
            return (Criteria) this;
        }

        public Criteria andQuantityOrderIn(List<BigDecimal> values) {
            addCriterion("quantity_order in", values, "quantityOrder");
            return (Criteria) this;
        }

        public Criteria andQuantityOrderNotIn(List<BigDecimal> values) {
            addCriterion("quantity_order not in", values, "quantityOrder");
            return (Criteria) this;
        }

        public Criteria andQuantityOrderBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("quantity_order between", value1, value2, "quantityOrder");
            return (Criteria) this;
        }

        public Criteria andQuantityOrderNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("quantity_order not between", value1, value2, "quantityOrder");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table T_TOKEN_ADDRESS
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
     * This class corresponds to the database table T_TOKEN_ADDRESS
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