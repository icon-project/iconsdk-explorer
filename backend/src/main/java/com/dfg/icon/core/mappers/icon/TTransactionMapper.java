package com.dfg.icon.core.mappers.icon;

import com.dfg.icon.core.dao.icon.TTransaction;
import com.dfg.icon.core.dao.icon.TTransactionExample;
import com.dfg.icon.core.dao.icon.TTransactionKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TTransactionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION
     *
     * @mbg.generated
     */
    long countByExample(TTransactionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION
     *
     * @mbg.generated
     */
    int deleteByExample(TTransactionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(TTransactionKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION
     *
     * @mbg.generated
     */
    int insert(TTransaction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION
     *
     * @mbg.generated
     */
    int insertSelective(TTransaction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION
     *
     * @mbg.generated
     */
    List<TTransaction> selectByExampleWithRowbounds(TTransactionExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION
     *
     * @mbg.generated
     */
    List<TTransaction> selectByExample(TTransactionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION
     *
     * @mbg.generated
     */
    TTransaction selectByPrimaryKey(TTransactionKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TTransaction record, @Param("example") TTransactionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TTransaction record, @Param("example") TTransactionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TTransaction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TTransaction record);
}