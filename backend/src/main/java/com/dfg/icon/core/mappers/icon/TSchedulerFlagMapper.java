package com.dfg.icon.core.mappers.icon;

import com.dfg.icon.core.dao.icon.TSchedulerFlag;
import com.dfg.icon.core.dao.icon.TSchedulerFlagExample;
import com.dfg.icon.core.dao.icon.TSchedulerFlagKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSchedulerFlagMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SCHEDULER_FLAG
     *
     * @mbg.generated
     */
    long countByExample(TSchedulerFlagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SCHEDULER_FLAG
     *
     * @mbg.generated
     */
    int deleteByExample(TSchedulerFlagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SCHEDULER_FLAG
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(TSchedulerFlagKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SCHEDULER_FLAG
     *
     * @mbg.generated
     */
    int insert(TSchedulerFlag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SCHEDULER_FLAG
     *
     * @mbg.generated
     */
    int insertSelective(TSchedulerFlag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SCHEDULER_FLAG
     *
     * @mbg.generated
     */
    List<TSchedulerFlag> selectByExampleWithRowbounds(TSchedulerFlagExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SCHEDULER_FLAG
     *
     * @mbg.generated
     */
    List<TSchedulerFlag> selectByExample(TSchedulerFlagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SCHEDULER_FLAG
     *
     * @mbg.generated
     */
    TSchedulerFlag selectByPrimaryKey(TSchedulerFlagKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SCHEDULER_FLAG
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TSchedulerFlag record, @Param("example") TSchedulerFlagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SCHEDULER_FLAG
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TSchedulerFlag record, @Param("example") TSchedulerFlagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SCHEDULER_FLAG
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TSchedulerFlag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SCHEDULER_FLAG
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TSchedulerFlag record);
}