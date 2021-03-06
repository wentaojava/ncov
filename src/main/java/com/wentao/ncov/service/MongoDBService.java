package com.wentao.ncov.service;

import com.wentao.ncov.entity.mongo.DXYAreaEntity;
import com.wentao.ncov.entity.mongo.DXYAreaEntityForMap;
import com.wentao.ncov.entity.mongo.DXYNationalData;
import com.wentao.ncov.vo.GetProvinceVO;

import java.util.List;
import java.util.Map;

/**
 * 获取mongo数据库信息
 *
 * @author wentao
 * @time 2020年02月09日
 * @copyright Gods bless me,code never with bug.
 */
public interface MongoDBService {
    /**
     * 获取当日数据
     *
     * @param
     * @return
     * @throws
     * @author wentao
     * @time 2020年02月13日
     * Gods bless me,code never with bug.
     */
    public Map<String, DXYAreaEntityForMap> getDataTodayForProvince();

    /**
     * 根据id获取对应数据
     *
     * @param id
     * @return com.wentao.ncov.vo.GetCityDataTodayByMongodbIdVO
     * @throws
     * @author wentao
     * @time 2020年02月14日
     * Gods bless me,code never with bug.
     */
    public DXYAreaEntity getDataTodayByMongodbId(String id);

    /**
     * 获取当日统计数据
     *
     * @param
     * @return
     * @throws
     * @author wentao
     * @time 2020年03月03日
     * Gods bless me,code never with bug.
     */
    public DXYNationalData getNationalDataToday();

    /**
     * 获取今日疫情中的省份信息
     *
     * @param
     * @return GetProvinceVO
     * @throwsa
     * @autho wentao
     * @time 2020年03月07日
     * Gods bless me,code never with bug.
     */
    public List<GetProvinceVO> getTodayProvince();
}
