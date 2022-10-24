package com.dfg.icon.core.mappers.icon;

import com.dfg.icon.core.dao.icon.TTransactionView;
import com.dfg.icon.core.dao.icon.TTransactionViewExample;
import com.dfg.icon.core.dao.icon.TTransactionViewKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TTransactionViewMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION_VIEW
     *
     * @mbg.generated
     */
    long countByExample(TTransactionViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION_VIEW
     *
     * @mbg.generated
     */
    int deleteByExample(TTransactionViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION_VIEW
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(TTransactionViewKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION_VIEW
     *
     * @mbg.generated
     */
    int insert(TTransactionView record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION_VIEW
     *
     * @mbg.generated
     */
    int insertSelective(TTransactionView record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION_VIEW
     *
     * @mbg.generated
     */
    List<TTransactionView> selectByExampleWithRowbounds(TTransactionViewExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION_VIEW
     *
     * @mbg.generated
     */
    List<TTransactionView> selectByExample(TTransactionViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION_VIEW
     *
     * @mbg.generated
     */
    TTransactionView selectByPrimaryKey(TTransactionViewKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION_VIEW
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TTransactionView record, @Param("example") TTransactionViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION_VIEW
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TTransactionView record, @Param("example") TTransactionViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION_VIEW
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TTransactionView record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TRANSACTION_VIEW
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TTransactionView record);
}