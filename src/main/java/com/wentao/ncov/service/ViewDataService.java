package com.wentao.ncov.service;

import com.wentao.ncov.bo.GetCityDataTodayByMongodbIdBO;
import com.wentao.ncov.bo.GetNationalDataByDateBO;
import com.wentao.ncov.util.response.RestResponse;
import com.wentao.ncov.vo.*;

import java.util.List;

/**
 * @author wentao
 * @time 2020年02月13日
 * @copyright Gods bless me,code never with bug.
 */
public interface ViewDataService {
    /**
     * 获取今日疫情中的省份信息
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

    /**
     * 根据开始，结束日期获取全国数据
     *
     * @param body
     * @return GetNationalDataByDateVO
     * @throws
     * @author wentao
     * @time 2020年03月07日
     * Gods bless me,code never with bug.
     */
    public RestResponse<List<GetNationalDataByDateVO>> getNationalDataByDate(GetNationalDataByDateBO body);
}
