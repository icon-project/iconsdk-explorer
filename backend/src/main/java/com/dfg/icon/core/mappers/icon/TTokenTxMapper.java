package com.dfg.icon.core.mappers.icon;

import com.dfg.icon.core.dao.icon.TTokenTx;
import com.dfg.icon.core.dao.icon.TTokenTxExample;
import com.dfg.icon.core.dao.icon.TTokenTxKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TTokenTxMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_TX
     *
     * @mbg.generated
     */
    long countByExample(TTokenTxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_TX
     *
     * @mbg.generated
     */
    int deleteByExample(TTokenTxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_TX
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(TTokenTxKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_TX
     *
     * @mbg.generated
     */
    int insert(TTokenTx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_TX
     *
     * @mbg.generated
     */
    int insertSelective(TTokenTx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_TX
     *
     * @mbg.generated
     */
    List<TTokenTx> selectByExampleWithRowbounds(TTokenTxExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_TX
     *
     * @mbg.generated
     */
    List<TTokenTx> selectByExample(TTokenTxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_TX
     *
     * @mbg.generated
     */
    TTokenTx selectByPrimaryKey(TTokenTxKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_TX
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TTokenTx record, @Param("example") TTokenTxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_TX
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TTokenTx record, @Param("example") TTokenTxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_TX
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TTokenTx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOKEN_TX
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TTokenTx record);
}