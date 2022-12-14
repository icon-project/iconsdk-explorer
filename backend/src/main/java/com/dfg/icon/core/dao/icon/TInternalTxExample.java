package com.dfg.icon.core.dao.icon;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TInternalTxExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_INTERNAL_TX
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_INTERNAL_TX
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_INTERNAL_TX
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_INTERNAL_TX
     *
     * @mbg.generated
     */
    public TInternalTxExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_INTERNAL_TX
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_INTERNAL_TX
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_INTERNAL_TX
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_INTERNAL_TX
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_INTERNAL_TX
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_INTERNAL_TX
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_INTERNAL_TX
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
     * This method corresponds to the database table T_INTERNAL_TX
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
     * This method corresponds to the database table T_INTERNAL_TX
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_INTERNAL_TX
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
     * This class corresponds to the database table T_INTERNAL_TX
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

        public Criteria andParentTxHashIsNull() {
            addCriterion("parent_tx_hash is null");
            return (Criteria) this;
        }

        public Criteria andParentTxHashIsNotNull() {
            addCriterion("parent_tx_hash is not null");
            return (Criteria) this;
        }

        public Criteria andParentTxHashEqualTo(String value) {
            addCriterion("parent_tx_hash =", value, "parentTxHash");
            return (Criteria) this;
        }

        public Criteria andParentTxHashNotEqualTo(String value) {
            addCriterion("parent_tx_hash <>", value, "parentTxHash");
            return (Criteria) this;
        }

        public Criteria andParentTxHashGreaterThan(String value) {
            addCriterion("parent_tx_hash >", value, "parentTxHash");
            return (Criteria) this;
        }

        public Criteria andParentTxHashGreaterThanOrEqualTo(String value) {
            addCriterion("parent_tx_hash >=", value, "parentTxHash");
            return (Criteria) this;
        }

        public Criteria andParentTxHashLessThan(String value) {
            addCriterion("parent_tx_hash <", value, "parentTxHash");
            return (Criteria) this;
        }

        public Criteria andParentTxHashLessThanOrEqualTo(String value) {
            addCriterion("parent_tx_hash <=", value, "parentTxHash");
            return (Criteria) this;
        }

        public Criteria andParentTxHashLike(String value) {
            addCriterion("parent_tx_hash like", value, "parentTxHash");
            return (Criteria) this;
        }

        public Criteria andParentTxHashNotLike(String value) {
            addCriterion("parent_tx_hash not like", value, "parentTxHash");
            return (Criteria) this;
        }

        public Criteria andParentTxHashIn(List<String> values) {
            addCriterion("parent_tx_hash in", values, "parentTxHash");
            return (Criteria) this;
        }

        public Criteria andParentTxHashNotIn(List<String> values) {
            addCriterion("parent_tx_hash not in", values, "parentTxHash");
            return (Criteria) this;
        }

        public Criteria andParentTxHashBetween(String value1, String value2) {
            addCriterion("parent_tx_hash between", value1, value2, "parentTxHash");
            return (Criteria) this;
        }

        public Criteria andParentTxHashNotBetween(String value1, String value2) {
            addCriterion("parent_tx_hash not between", value1, value2, "parentTxHash");
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

        public Criteria andHeightIsNull() {
            addCriterion("height is null");
            return (Criteria) this;
        }

        public Criteria andHeightIsNotNull() {
            addCriterion("height is not null");
            return (Criteria) this;
        }

        public Criteria andHeightEqualTo(Integer value) {
            addCriterion("height =", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotEqualTo(Integer value) {
            addCriterion("height <>", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightGreaterThan(Integer value) {
            addCriterion("height >", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("height >=", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLessThan(Integer value) {
            addCriterion("height <", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLessThanOrEqualTo(Integer value) {
            addCriterion("height <=", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightIn(List<Integer> values) {
            addCriterion("height in", values, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotIn(List<Integer> values) {
            addCriterion("height not in", values, "height");
            return (Criteria) this;
        }

        public Criteria andHeightBetween(Integer value1, Integer value2) {
            addCriterion("height between", value1, value2, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotBetween(Integer value1, Integer value2) {
            addCriterion("height not between", value1, value2, "height");
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

        public Criteria andFromAddrIsNull() {
            addCriterion("from_addr is null");
            return (Criteria) this;
        }

        public Criteria andFromAddrIsNotNull() {
            addCriterion("from_addr is not null");
            return (Criteria) this;
        }

        public Criteria andFromAddrEqualTo(String value) {
            addCriterion("from_addr =", value, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrNotEqualTo(String value) {
            addCriterion("from_addr <>", value, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrGreaterThan(String value) {
            addCriterion("from_addr >", value, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrGreaterThanOrEqualTo(String value) {
            addCriterion("from_addr >=", value, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrLessThan(String value) {
            addCriterion("from_addr <", value, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrLessThanOrEqualTo(String value) {
            addCriterion("from_addr <=", value, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrLike(String value) {
            addCriterion("from_addr like", value, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrNotLike(String value) {
            addCriterion("from_addr not like", value, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrIn(List<String> values) {
            addCriterion("from_addr in", values, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrNotIn(List<String> values) {
            addCriterion("from_addr not in", values, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrBetween(String value1, String value2) {
            addCriterion("from_addr between", value1, value2, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrNotBetween(String value1, String value2) {
            addCriterion("from_addr not between", value1, value2, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andToAddrIsNull() {
            addCriterion("to_addr is null");
            return (Criteria) this;
        }

        public Criteria andToAddrIsNotNull() {
            addCriterion("to_addr is not null");
            return (Criteria) this;
        }

        public Criteria andToAddrEqualTo(String value) {
            addCriterion("to_addr =", value, "toAddr");
            return (Criteria) this;
        }

        public Criteria andToAddrNotEqualTo(String value) {
            addCriterion("to_addr <>", value, "toAddr");
            return (Criteria) this;
        }

        public Criteria andToAddrGreaterThan(String value) {
            addCriterion("to_addr >", value, "toAddr");
            return (Criteria) this;
        }

        public Criteria andToAddrGreaterThanOrEqualTo(String value) {
            addCriterion("to_addr >=", value, "toAddr");
            return (Criteria) this;
        }

        public Criteria andToAddrLessThan(String value) {
            addCriterion("to_addr <", value, "toAddr");
            return (Criteria) this;
        }

        public Criteria andToAddrLessThanOrEqualTo(String value) {
            addCriterion("to_addr <=", value, "toAddr");
            return (Criteria) this;
        }

        public Criteria andToAddrLike(String value) {
            addCriterion("to_addr like", value, "toAddr");
            return (Criteria) this;
        }

        public Criteria andToAddrNotLike(String value) {
            addCriterion("to_addr not like", value, "toAddr");
            return (Criteria) this;
        }

        public Criteria andToAddrIn(List<String> values) {
            addCriterion("to_addr in", values, "toAddr");
            return (Criteria) this;
        }

        public Criteria andToAddrNotIn(List<String> values) {
            addCriterion("to_addr not in", values, "toAddr");
            return (Criteria) this;
        }

        public Criteria andToAddrBetween(String value1, String value2) {
            addCriterion("to_addr between", value1, value2, "toAddr");
            return (Criteria) this;
        }

        public Criteria andToAddrNotBetween(String value1, String value2) {
            addCriterion("to_addr not between", value1, value2, "toAddr");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(String value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(String value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(String value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(String value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(String value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(String value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLike(String value) {
            addCriterion("amount like", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotLike(String value) {
            addCriterion("amount not like", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<String> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<String> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(String value1, String value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(String value1, String value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table T_INTERNAL_TX
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
     * This class corresponds to the database table T_INTERNAL_TX
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