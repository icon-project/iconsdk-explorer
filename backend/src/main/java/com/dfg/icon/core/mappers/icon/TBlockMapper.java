package com.dfg.icon.core.mappers.icon;

import com.dfg.icon.core.dao.icon.TBlock;
import com.dfg.icon.core.dao.icon.TBlockExample;
import com.dfg.icon.core.dao.icon.TBlockKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TBlockMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BLOCK
     *
     * @mbg.generated
     */
    long countByExample(TBlockExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BLOCK
     *
     * @mbg.generated
     */
    int deleteByExample(TBlockExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BLOCK
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(TBlockKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BLOCK
     *
     * @mbg.generated
     */
    int insert(TBlock record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BLOCK
     *
     * @mbg.generated
     */
    int insertSelective(TBlock record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BLOCK
     *
     * @mbg.generated
     */
    List<TBlock> selectByExampleWithRowbounds(TBlockExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BLOCK
     *
     * @mbg.generated
     */
    List<TBlock> selectByExample(TBlockExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BLOCK
     *
     * @mbg.generated
     */
    TBlock selectByPrimaryKey(TBlockKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BLOCK
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TBlock record, @Param("example") TBlockExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BLOCK
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TBlock record, @Param("example") TBlockExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BLOCK
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TBlock record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BLOCK
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TBlock record);
}