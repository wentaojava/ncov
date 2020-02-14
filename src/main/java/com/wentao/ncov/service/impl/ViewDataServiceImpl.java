package com.wentao.ncov.service.impl;

import com.wentao.ncov.bo.GetCityDataTodayByMongodbIdBO;
import com.wentao.ncov.entity.mongo.DXYAreaEntity;
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
import java.util.concurrent.atomic.AtomicReference;

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
    public RestResponse<GetDataTodayVO> getDataToday() {
        //查询中国省份
        List<ProvinceData> provinceData = provinceDataMapper.selectProvinceByCountry("中国");
        Map<String, DXYAreaEntity> areaEntityMap = mongoDBService.getDataToday(provinceData);

        AtomicReference<Integer> confirmedCount = new AtomicReference<>(0);
        AtomicReference<Integer> suspectedCount = new AtomicReference<>(0);
        AtomicReference<Integer> curedCount = new AtomicReference<>(0);
        AtomicReference<Integer> deadCount = new AtomicReference<>(0);
        List<AreaData> areaDataList = new ArrayList<>();
        List<ProvinceData> provinceForNull = new ArrayList<>();
        //依次判断个省份数据是否齐全并计算
        provinceData.forEach(province -> {
            if (areaEntityMap.containsKey(province.getProvinceShortName())
                    && null != areaEntityMap.get(province.getProvinceShortName())) {
                AreaData areaData = MapStructUtil.INSTANCE.buildAreaData(areaEntityMap.get(province.getProvinceShortName()));
                confirmedCount.updateAndGet(v -> v + areaData.getConfirmedCount());
                suspectedCount.updateAndGet(v -> v + areaData.getSuspectedCount());
                curedCount.updateAndGet(v -> v + areaData.getCuredCount());
                deadCount.updateAndGet(v -> v + areaData.getDeadCount());
                areaDataList.add(areaData);
            } else {
                provinceForNull.add(province);
            }
        });
        //补充当天未爬取到数据重查mysql中最新时间的数据并计算
        if (!CollectionUtils.isEmpty(provinceForNull)) {
            List<AreaData> areaData = areaDataMapper.selectDataByProvince(provinceForNull);
            if (!CollectionUtils.isEmpty(areaData)) {
                areaData.forEach(areaData1 -> {
                    confirmedCount.updateAndGet(v -> v + areaData1.getConfirmedCount());
                    suspectedCount.updateAndGet(v -> v + areaData1.getSuspectedCount());
                    curedCount.updateAndGet(v -> v + areaData1.getCuredCount());
                    deadCount.updateAndGet(v -> v + areaData1.getDeadCount());
                });
            }
        }

        GetDataTodayVO getDataTodayVO = new GetDataTodayVO();
        getDataTodayVO.setConfirmedCount(confirmedCount.get());
        getDataTodayVO.setSuspectedCount(suspectedCount.get());
        getDataTodayVO.setCuredCount(curedCount.get());
        getDataTodayVO.setDeadCount(deadCount.get());
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
            dxyAreaEntity.getCities().forEach(dxyAreaCityEntity -> {
                if (null != dxyAreaCityEntity) {
                    GetCityDataTodayByMongodbIdVO vo = MapStructUtil.INSTANCE.buildCityDataForVO(dxyAreaCityEntity);
                    list.add(vo);
                }
            });
            return new RestResponse<>(list);
        }

    }
}
