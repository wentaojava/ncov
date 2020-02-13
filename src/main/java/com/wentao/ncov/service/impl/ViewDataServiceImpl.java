package com.wentao.ncov.service.impl;

import com.wentao.ncov.entity.mongo.DXYAreaEntity;
import com.wentao.ncov.entity.mysql.ProvinceData;
import com.wentao.ncov.mappers.ProvinceDataMapper;
import com.wentao.ncov.service.MongoDBService;
import com.wentao.ncov.service.ViewDataService;
import com.wentao.ncov.util.response.RestResponse;
import com.wentao.ncov.vo.GetDataTodayVO;
import com.wentao.ncov.vo.GetProvinceVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wentao
 * @time 2020年02月13日
 * @copyright Gods bless me,code never with bug.
 */
@Service
@Slf4j
public class ViewDataServiceImpl implements ViewDataService {
    @Resource
    private ProvinceDataMapper provinceDataMapper;
    @Resource
    private MongoDBService mongoDBService;

    /**
     * 获取省份信息
     *
     * @return java.util.List.com.wentao.ncov.vo.GetProvinceVO
     * @throws
     * @author wentao
     * @time 2020年02月13日
     * Gods bless me,code never with bug.
     */
    @Override
    public RestResponse<List<GetProvinceVO>> getProvince() {
        List<GetProvinceVO> list = provinceDataMapper.selectAllForGetProvince();
        return new RestResponse<>(list);
    }

    /**
     * 获取当日确诊，疑似，治愈，死亡人数数据
     *
     * @return com.wentao.ncov.vo.GetProvinceVO
     * @throws
     * @author wentao
     * @time 2020年02月13日
     * Gods bless me,code never with bug.
     */
    @Override
    public RestResponse<GetDataTodayVO> getDataToday() {
        //查询中国省份
        List<ProvinceData> provinceData = provinceDataMapper.selectProvinceByCountry("中国");
        List<DXYAreaEntity> areaEntityList = mongoDBService.getDataToday(provinceData);
        return null;
    }
}
