package com.dfg.icon.core.dao.icon;

import java.util.ArrayList;
import java.util.List;

public class TTokenTxViewExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_TOKEN_TX_VIEW
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_TOKEN_TX_VIEW
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_TOKEN_TX_VIEW
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_TX_VIEW
     *
     * @mbg.generated
     */
    public TTokenTxViewExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_TX_VIEW
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_TX_VIEW
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_TX_VIEW
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_TX_VIEW
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_TX_VIEW
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_TX_VIEW
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_TX_VIEW
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
     * This method corresponds to the database table T_TOKEN_TX_VIEW
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
     * This method corresponds to the database table T_TOKEN_TX_VIEW
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_TX_VIEW
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
     * This class corresponds to the database table T_TOKEN_TX_VIEW
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
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

        public Criteria andTxHashIsNull() {
            addCriterion("tx_hash is null");
            return (Criteria) this;
        }

        public Criteria andTxHashIsNotNull() {
            addCriterion("tx_hash is not null");
            return (Criteria) this;
        }

        public Criteria andTxHashEqualTo(String value) {
            addCriterion("tx_hash =", value, "txHash");
            return (Criteria) this;
        }

        public Criteria andTxHashNotEqualTo(String value) {
            addCriterion("tx_hash <>", value, "txHash");
            return (Criteria) this;
        }

        public Criteria andTxHashGreaterThan(String value) {
            addCriterion("tx_hash >", value, "txHash");
            return (Criteria) this;
        }

        public Criteria andTxHashGreaterThanOrEqualTo(String value) {
            addCriterion("tx_hash >=", value, "txHash");
            return (Criteria) this;
        }

        public Criteria andTxHashLessThan(String value) {
            addCriterion("tx_hash <", value, "txHash");
            return (Criteria) this;
        }

        public Criteria andTxHashLessThanOrEqualTo(String value) {
            addCriterion("tx_hash <=", value, "txHash");
            return (Criteria) this;
        }

        public Criteria andTxHashLike(String value) {
            addCriterion("tx_hash like", value, "txHash");
            return (Criteria) this;
        }

        public Criteria andTxHashNotLike(String value) {
            addCriterion("tx_hash not like", value, "txHash");
            return (Criteria) this;
        }

        public Criteria andTxHashIn(List<String> values) {
            addCriterion("tx_hash in", values, "txHash");
            return (Criteria) this;
        }

        public Criteria andTxHashNotIn(List<String> values) {
            addCriterion("tx_hash not in", values, "txHash");
            return (Criteria) this;
        }

        public Criteria andTxHashBetween(String value1, String value2) {
            addCriterion("tx_hash between", value1, value2, "txHash");
            return (Criteria) this;
        }

        public Criteria andTxHashNotBetween(String value1, String value2) {
            addCriterion("tx_hash not between", value1, value2, "txHash");
            return (Criteria) this;
        }

        public Criteria andTxIndexIsNull() {
            addCriterion("tx_index is null");
            return (Criteria) this;
        }

        public Criteria andTxIndexIsNotNull() {
            addCriterion("tx_index is not null");
            return (Criteria) this;
        }

        public Criteria andTxIndexEqualTo(Integer value) {
            addCriterion("tx_index =", value, "txIndex");
            return (Criteria) this;
        }

        public Criteria andTxIndexNotEqualTo(Integer value) {
            addCriterion("tx_index <>", value, "txIndex");
            return (Criteria) this;
        }

        public Criteria andTxIndexGreaterThan(Integer value) {
            addCriterion("tx_index >", value, "txIndex");
            return (Criteria) this;
        }

        public Criteria andTxIndexGreaterThanOrEqualTo(Integer value) {
            addCriterion("tx_index >=", value, "txIndex");
            return (Criteria) this;
        }

        public Criteria andTxIndexLessThan(Integer value) {
            addCriterion("tx_index <", value, "txIndex");
            return (Criteria) this;
        }

        public Criteria andTxIndexLessThanOrEqualTo(Integer value) {
            addCriterion("tx_index <=", value, "txIndex");
            return (Criteria) this;
        }

        public Criteria andTxIndexIn(List<Integer> values) {
            addCriterion("tx_index in", values, "txIndex");
            return (Criteria) this;
        }

        public Criteria andTxIndexNotIn(List<Integer> values) {
            addCriterion("tx_index not in", values, "txIndex");
            return (Criteria) this;
        }

        public Criteria andTxIndexBetween(Integer value1, Integer value2) {
            addCriterion("tx_index between", value1, value2, "txIndex");
            return (Criteria) this;
        }

        public Criteria andTxIndexNotBetween(Integer value1, Integer value2) {
            addCriterion("tx_index not between", value1, value2, "txIndex");
            return (Criteria) this;
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

        public Criteria andAddressTypeIsNull() {
            addCriterion("address_type is null");
            return (Criteria) this;
        }

        public Criteria andAddressTypeIsNotNull() {
            addCriterion("address_type is not null");
            return (Criteria) this;
        }

        public Criteria andAddressTypeEqualTo(Boolean value) {
            addCriterion("address_type =", value, "addressType");
            return (Criteria) this;
        }

        public Criteria andAddressTypeNotEqualTo(Boolean value) {
            addCriterion("address_type <>", value, "addressType");
            return (Criteria) this;
        }

        public Criteria andAddressTypeGreaterThan(Boolean value) {
            addCriterion("address_type >", value, "addressType");
            return (Criteria) this;
        }

        public Criteria andAddressTypeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("address_type >=", value, "addressType");
            return (Criteria) this;
        }

        public Criteria andAddressTypeLessThan(Boolean value) {
            addCriterion("address_type <", value, "addressType");
            return (Criteria) this;
        }

        public Criteria andAddressTypeLessThanOrEqualTo(Boolean value) {
            addCriterion("address_type <=", value, "addressType");
            return (Criteria) this;
        }

        public Criteria andAddressTypeIn(List<Boolean> values) {
            addCriterion("address_type in", values, "addressType");
            return (Criteria) this;
        }

        public Criteria andAddressTypeNotIn(List<Boolean> values) {
            addCriterion("address_type not in", values, "addressType");
            return (Criteria) this;
        }

        public Criteria andAddressTypeBetween(Boolean value1, Boolean value2) {
            addCriterion("address_type between", value1, value2, "addressType");
            return (Criteria) this;
        }

        public Criteria andAddressTypeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("address_type not between", value1, value2, "addressType");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table T_TOKEN_TX_VIEW
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
     * This class corresponds to the database table T_TOKEN_TX_VIEW
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