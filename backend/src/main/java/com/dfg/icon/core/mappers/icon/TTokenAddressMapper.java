package com.dfg.icon.core.mappers.icon;

import com.dfg.icon.core.dao.icon.TTokenAddress;
import com.dfg.icon.core.dao.icon.TTokenAddressExample;
import com.dfg.icon.core.dao.icon.TTokenAddressKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TTokenAddressMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_ADDRESS
     *
     * @mbg.generated
     */
    long countByExample(TTokenAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_ADDRESS
     *
     * @mbg.generated
     */
    int deleteByExample(TTokenAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_ADDRESS
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(TTokenAddressKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_ADDRESS
     *
     * @mbg.generated
     */
    int insert(TTokenAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_ADDRESS
     *
     * @mbg.generated
     */
    int insertSelective(TTokenAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_ADDRESS
     *
     * @mbg.generated
     */
    List<TTokenAddress> selectByExampleWithRowbounds(TTokenAddressExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_ADDRESS
     *
     * @mbg.generated
     */
    List<TTokenAddress> selectByExample(TTokenAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_ADDRESS
     *
     * @mbg.generated
     */
    TTokenAddress selectByPrimaryKey(TTokenAddressKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_ADDRESS
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TTokenAddress record, @Param("example") TTokenAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_ADDRESS
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TTokenAddress record, @Param("example") TTokenAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_ADDRESS
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TTokenAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_ADDRESS
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TTokenAddress record);
}