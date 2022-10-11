package com.dfg.icon.core.mappers.icon;

import com.dfg.icon.core.dao.icon.TContractHistory;
import com.dfg.icon.core.dao.icon.TContractHistoryExample;
import com.dfg.icon.core.dao.icon.TContractHistoryKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TContractHistoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CONTRACT_HISTORY
     *
     * @mbg.generated
     */
    long countByExample(TContractHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CONTRACT_HISTORY
     *
     * @mbg.generated
     */
    int deleteByExample(TContractHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CONTRACT_HISTORY
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(TContractHistoryKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CONTRACT_HISTORY
     *
     * @mbg.generated
     */
    int insert(TContractHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CONTRACT_HISTORY
     *
     * @mbg.generated
     */
    int insertSelective(TContractHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CONTRACT_HISTORY
     *
     * @mbg.generated
     */
    List<TContractHistory> selectByExampleWithRowbounds(TContractHistoryExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CONTRACT_HISTORY
     *
     * @mbg.generated
     */
    List<TContractHistory> selectByExample(TContractHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CONTRACT_HISTORY
     *
     * @mbg.generated
     */
    TContractHistory selectByPrimaryKey(TContractHistoryKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CONTRACT_HISTORY
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TContractHistory record, @Param("example") TContractHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CONTRACT_HISTORY
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TContractHistory record, @Param("example") TContractHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CONTRACT_HISTORY
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TContractHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CONTRACT_HISTORY
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TContractHistory record);
}