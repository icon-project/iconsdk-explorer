package com.dfg.icon.core.mappers.icon;

import com.dfg.icon.core.dao.icon.TChainState;
import com.dfg.icon.core.dao.icon.TChainStateExample;
import com.dfg.icon.core.dao.icon.TChainStateKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TChainStateMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CHAIN_STATE
     *
     * @mbg.generated
     */
    long countByExample(TChainStateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CHAIN_STATE
     *
     * @mbg.generated
     */
    int deleteByExample(TChainStateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CHAIN_STATE
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(TChainStateKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CHAIN_STATE
     *
     * @mbg.generated
     */
    int insert(TChainState record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CHAIN_STATE
     *
     * @mbg.generated
     */
    int insertSelective(TChainState record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CHAIN_STATE
     *
     * @mbg.generated
     */
    List<TChainState> selectByExampleWithRowbounds(TChainStateExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CHAIN_STATE
     *
     * @mbg.generated
     */
    List<TChainState> selectByExample(TChainStateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CHAIN_STATE
     *
     * @mbg.generated
     */
    TChainState selectByPrimaryKey(TChainStateKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CHAIN_STATE
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TChainState record, @Param("example") TChainStateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CHAIN_STATE
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TChainState record, @Param("example") TChainStateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CHAIN_STATE
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TChainState record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_CHAIN_STATE
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TChainState record);

    int chainStateByName(@Param("chainName") String chainName);

}