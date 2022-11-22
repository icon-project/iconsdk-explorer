package com.dfg.icon.core.mappers.icon;

import com.dfg.icon.core.dao.icon.TBtpHeader;
import com.dfg.icon.core.dao.icon.TBtpHeaderExample;
import com.dfg.icon.core.dao.icon.TBtpHeaderKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TBtpHeaderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BTP_HEADER
     *
     * @mbg.generated
     */
    long countByExample(TBtpHeaderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BTP_HEADER
     *
     * @mbg.generated
     */
    int deleteByExample(TBtpHeaderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BTP_HEADER
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(TBtpHeaderKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BTP_HEADER
     *
     * @mbg.generated
     */
    int insert(TBtpHeader record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BTP_HEADER
     *
     * @mbg.generated
     */
    int insertSelective(TBtpHeader record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BTP_HEADER
     *
     * @mbg.generated
     */
    List<TBtpHeader> selectByExampleWithRowbounds(TBtpHeaderExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BTP_HEADER
     *
     * @mbg.generated
     */
    List<TBtpHeader> selectByExample(TBtpHeaderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BTP_HEADER
     *
     * @mbg.generated
     */
    TBtpHeader selectByPrimaryKey(TBtpHeaderKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BTP_HEADER
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TBtpHeader record, @Param("example") TBtpHeaderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BTP_HEADER
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TBtpHeader record, @Param("example") TBtpHeaderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BTP_HEADER
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TBtpHeader record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_BTP_HEADER
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TBtpHeader record);
}