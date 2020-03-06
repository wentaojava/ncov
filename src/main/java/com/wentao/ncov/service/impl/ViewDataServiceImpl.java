package com.wentao.ncov.service.impl;

import com.wentao.ncov.bo.GetCityDataTodayByMongodbIdBO;
import com.wentao.ncov.entity.mongo.DXYAreaEntity;
import com.wentao.ncov.entity.mongo.DXYAreaEntityForMap;
import com.wentao.ncov.entity.mongo.DXYNationalData;
import com.wentao.ncov.entity.mysql.AreaData;
import com.wentao.ncov.entity.mysql.ProvinceData;
import com.wentao.ncov.exceptionhandler.SystemErrorCode;
import com.wentao.ncov.mappers.AreaDataMapper;
import com.wentao.ncov.mappers.ProvinceDataMapper;
import com.wentao.ncov.service.MongoDBService;
import com.wentao.ncov.service.ViewDataService;
import com.wentao.ncov.util.MapStructUtil;
import com.wentao.ncov.util.response.RestResponse;
import com.wentao.ncov.vo.GetCityDataTodayByMongodbIdVO;
import com.wentao.ncov.vo.GetDataTodayForMapVO;
import com.wentao.ncov.vo.GetDataTodayVO;
import com.wentao.ncov.vo.GetProvinceVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @Resource
    private AreaDataMapper areaDataMapper;

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
    public RestResponse<GetDataTodayForMapVO> getDataTodayForProvince() {
        //查询中国省份
        List<ProvinceData> provinceData = provinceDataMapper.selectProvinceByCountry("中国");
        Map<String, DXYAreaEntityForMap> areaEntityMap = mongoDBService.getDataTodayForProvince();
        List<AreaData> areaDataList = new ArrayList<>();
        List<ProvinceData> provinceForNull = new ArrayList<>();
        //依次判断个省份数据是否齐全
        provinceData.forEach(province -> {
            if (areaEntityMap.containsKey(province.getProvinceName())
                    && null != areaEntityMap.get(province.getProvinceName())) {
                AreaData areaData = MapStructUtil.INSTANCE.buildAreaData(areaEntityMap.get(province.getProvinceName()));
                areaData.setProvinceShortName(province.getProvinceShortName());
                areaDataList.add(areaData);
            } else {
                provinceForNull.add(province);
            }
        });
        //补充当天未爬取到数据重查mysql中最新时间的数据并计算
        if (!CollectionUtils.isEmpty(provinceForNull)) {
            List<AreaData> areaData = areaDataMapper.selectDataByProvince(provinceForNull);
            if (!CollectionUtils.isEmpty(areaData)) {
                areaDataList.addAll(areaData);
            }
        }

        GetDataTodayForMapVO getDataTodayVO = new GetDataTodayForMapVO();
        getDataTodayVO.setAreaDataList(areaDataList);
        return new RestResponse<>(getDataTodayVO);
    }


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
    @Override
    public RestResponse<List<GetCityDataTodayByMongodbIdVO>> getCityDataTodayByMongodbId(GetCityDataTodayByMongodbIdBO bo) {
        List<GetCityDataTodayByMongodbIdVO> list = new ArrayList<>();
        if (null == bo || StringUtils.isEmpty(bo.getId())) {
            return new RestResponse<>().withCode(SystemErrorCode.PARAM_NOT_ALLOW.getCode())
                    .withMessage(SystemErrorCode.PARAM_NOT_ALLOW.getMessage());
        }

        DXYAreaEntity dxyAreaEntity = mongoDBService.getDataTodayByMongodbId(bo.getId());
        if (null == dxyAreaEntity) {
            return new RestResponse<>(list);
        } else {
            if (CollectionUtils.isEmpty(dxyAreaEntity.getCities())) {
                //城市或地区为空展示省份
                GetCityDataTodayByMongodbIdVO vo = MapStructUtil.INSTANCE.buildCityDataForVO(dxyAreaEntity);
                list.add(vo);
            } else {
                dxyAreaEntity.getCities().forEach(dxyAreaCityEntity -> {
                    if (null != dxyAreaCityEntity) {
                        GetCityDataTodayByMongodbIdVO vo = MapStructUtil.INSTANCE.buildCityDataForVO(dxyAreaCityEntity);
                        list.add(vo);
                    }
                });
            }

            return new RestResponse<>(list);
        }

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
        GetDataTodayVO getDataTodayVO = new GetDataTodayVO();
        //从mongodb中查询统计数据
        DXYNationalData nationalData = mongoDBService.getNationalDataToday();
        if (null != nationalData) {
            getDataTodayVO = MapStructUtil.INSTANCE.buildDXYNationalDataToGetDataTodayVO(nationalData);
        }
        return new RestResponse<>(getDataTodayVO);
    }
}
