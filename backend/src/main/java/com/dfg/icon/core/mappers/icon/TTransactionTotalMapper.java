package com.dfg.icon.core.mappers.icon;

import com.dfg.icon.core.dao.icon.TTransactionTotal;
import com.dfg.icon.core.dao.icon.TTransactionTotalExample;
import com.dfg.icon.core.dao.icon.TTransactionTotalKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TTransactionTotalMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION_TOTAL
     *
     * @mbg.generated
     */
    long countByExample(TTransactionTotalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION_TOTAL
     *
     * @mbg.generated
     */
    int deleteByExample(TTransactionTotalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION_TOTAL
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(TTransactionTotalKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION_TOTAL
     *
     * @mbg.generated
     */
    int insert(TTransactionTotal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION_TOTAL
     *
     * @mbg.generated
     */
    int insertSelective(TTransactionTotal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION_TOTAL
     *
     * @mbg.generated
     */
    List<TTransactionTotal> selectByExampleWithRowbounds(TTransactionTotalExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION_TOTAL
     *
     * @mbg.generated
     */
    List<TTransactionTotal> selectByExample(TTransactionTotalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION_TOTAL
     *
     * @mbg.generated
     */
    TTransactionTotal selectByPrimaryKey(TTransactionTotalKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION_TOTAL
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TTransactionTotal record, @Param("example") TTransactionTotalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION_TOTAL
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TTransactionTotal record, @Param("example") TTransactionTotalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION_TOTAL
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TTransactionTotal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION_TOTAL
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TTransactionTotal record);
}