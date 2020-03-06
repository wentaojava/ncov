package com.wentao.ncov.service;

import com.wentao.ncov.bo.GetCityDataTodayByMongodbIdBO;
import com.wentao.ncov.util.response.RestResponse;
import com.wentao.ncov.vo.GetCityDataTodayByMongodbIdVO;
import com.wentao.ncov.vo.GetDataTodayForMapVO;
import com.wentao.ncov.vo.GetDataTodayVO;
import com.wentao.ncov.vo.GetProvinceVO;

import java.util.List;

/**
 * @author wentao
 * @time 2020年02月13日
 * @copyright Gods bless me,code never with bug.
 */
public interface ViewDataService {
    /**
     * 获取省份信息
     *
     * @return java.util.List.com.wentao.ncov.vo.GetProvinceVO
     * @throws
     * @author wentao
     * @time 2020年02月13日
     * Gods bless me,code never with bug.
     */
    public RestResponse<List<GetProvinceVO>> getProvince();

    /**
     * 获取各省份疫情数据
     *
     * @return com.wentao.ncov.vo.GetProvinceVO
     * @throws
     * @author wentao
     * @time 2020年02月13日
     * Gods bless me,code never with bug.
     */
    public RestResponse<GetDataTodayForMapVO> getDataTodayForProvince();

    /**
     * 根据id获取对应城市数据
     *
     * @param bo
     * @return com.wentao.ncov.vo.GetCityDataTodayByMongodbIdVO
     * @throws
     * @author wentao
     * @time 2020年02月14日
     * Gods bless me,code never with bug.
     */
    public RestResponse<List<GetCityDataTodayByMongodbIdVO>> getCityDataTodayByMongodbId(GetCityDataTodayByMongodbIdBO bo);

    /**
     * 获取当日确诊，疑似，治愈，死亡人数数据
     *
     * @return com.wentao.ncov.vo.GetProvinceVO
     * @throws
     * @author wentao
     * @time 2020年02月13日
     * Gods bless me,code never with bug.
     */
    public RestResponse<GetDataTodayVO> getDataToday();
}
