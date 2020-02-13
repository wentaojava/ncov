package com.wentao.ncov.mappers;

import com.wentao.ncov.entity.mysql.ProvinceData;
import com.wentao.ncov.vo.GetProvinceVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProvinceDataMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Province_data
     *
     * @mbggenerated Tue Feb 11 17:37:58 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Province_data
     *
     * @mbggenerated Tue Feb 11 17:37:58 CST 2020
     */
    int insert(ProvinceData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Province_data
     *
     * @mbggenerated Tue Feb 11 17:37:58 CST 2020
     */
    ProvinceData selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Province_data
     *
     * @mbggenerated Tue Feb 11 17:37:58 CST 2020
     */
    List<ProvinceData> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Province_data
     *
     * @mbggenerated Tue Feb 11 17:37:58 CST 2020
     */
    int updateByPrimaryKey(ProvinceData record);

    /**
     * 获取省份信息
     *
     * @return java.util.List.com.wentao.ncov.vo.GetProvinceVO
     * @throws
     * @author wentao
     * @time 2020年02月13日
     * Gods bless me,code never with bug.
     */
    List<GetProvinceVO> selectAllForGetProvince();
}