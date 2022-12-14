package com.dfg.icon.core.mappers.icon;

import com.dfg.icon.core.dao.icon.TAddressReport;
import com.dfg.icon.core.dao.icon.TAddressReportExample;
import com.dfg.icon.core.dao.icon.TAddressReportKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TAddressReportMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ADDRESS_REPORT
     *
     * @mbg.generated
     */
    long countByExample(TAddressReportExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ADDRESS_REPORT
     *
     * @mbg.generated
     */
    int deleteByExample(TAddressReportExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ADDRESS_REPORT
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(TAddressReportKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ADDRESS_REPORT
     *
     * @mbg.generated
     */
    int insert(TAddressReport record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ADDRESS_REPORT
     *
     * @mbg.generated
     */
    int insertSelective(TAddressReport record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ADDRESS_REPORT
     *
     * @mbg.generated
     */
    List<TAddressReport> selectByExampleWithRowbounds(TAddressReportExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ADDRESS_REPORT
     *
     * @mbg.generated
     */
    List<TAddressReport> selectByExample(TAddressReportExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ADDRESS_REPORT
     *
     * @mbg.generated
     */
    TAddressReport selectByPrimaryKey(TAddressReportKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ADDRESS_REPORT
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TAddressReport record, @Param("example") TAddressReportExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ADDRESS_REPORT
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TAddressReport record, @Param("example") TAddressReportExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ADDRESS_REPORT
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TAddressReport record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ADDRESS_REPORT
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TAddressReport record);
}