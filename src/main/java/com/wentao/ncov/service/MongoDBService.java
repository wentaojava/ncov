package com.wentao.ncov.service;

import com.wentao.ncov.entity.mongo.DXYAreaEntity;
import com.wentao.ncov.entity.mysql.ProvinceData;

import java.util.List;

/**
 * 获取mongo数据库信息
 *
 * @author wentao
 * @time 2020年02月09日
 * @copyright Gods bless me,code never with bug.
 */
public interface MongoDBService {
    /**
     * 根据省份获取数据
     *
     * @param provinceData
     * @return
     * @throws
     * @author wentao
     * @time 2020年02月13日
     * Gods bless me,code never with bug.
     */
    List<DXYAreaEntity> getDataToday(List<ProvinceData> provinceData);
}
