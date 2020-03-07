package com.wentao.ncov.mappers;


import com.wentao.ncov.bo.GetNationalDataByDateBO;
import com.wentao.ncov.entity.mysql.NationalData;
import com.wentao.ncov.vo.GetNationalDataByDateVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NationalDataMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table National_Data
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table National_Data
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    int insert(NationalData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table National_Data
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    NationalData selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table National_Data
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    List<NationalData> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table National_Data
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    int updateByPrimaryKey(NationalData record);

    /**
     * 根据日期区间获取数据
     *
     * @param bo
     * @return GetNationalDataByDateVO
     * @throws
     * @author wentao
     * @time 2020年03月07日
     * Gods bless me,code never with bug.
     */
    List<GetNationalDataByDateVO> getDataByDate(GetNationalDataByDateBO bo);
}
