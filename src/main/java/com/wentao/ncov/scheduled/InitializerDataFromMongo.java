package com.wentao.ncov.scheduled;

import com.wentao.ncov.entity.mongo.DXYAreaCityEntity;
import com.wentao.ncov.entity.mongo.DXYAreaEntity;
import com.wentao.ncov.entity.mysql.AreaData;
import com.wentao.ncov.entity.mysql.CityData;
import com.wentao.ncov.entity.mysql.ProvinceData;
import com.wentao.ncov.mappers.AreaDataMapper;
import com.wentao.ncov.mappers.CityDataMapper;
import com.wentao.ncov.mappers.ProvinceDataMapper;
import com.wentao.ncov.util.MapStructUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 每日00点10从mongo中获取前一天最终数据至mysql
 *
 * @author wentao
 * @time 2020年02月09日
 * @copyright Gods bless me,code never with bug.
 */
@Component
@Transactional(rollbackFor = RuntimeException.class)
public class InitializerDataFromMongo {

    private static final Logger log = LoggerFactory.getLogger(InitializerDataFromMongo.class);

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private AreaDataMapper areaDataMapper;

    @Resource
    private CityDataMapper cityDataMapper;

    @Resource
    private ProvinceDataMapper provinceDataMapper;

    @Scheduled(cron = "0 10 0 * * *")
    public void scheduledMethod() {
        log.info("get data from mongo start.....");
        List<DXYAreaEntity> dxyAreaEntityList = new ArrayList<>();
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DAY_OF_MONTH, -1);
        Date startDate = c.getTime();
        startDate.setHours(0);
        startDate.setMinutes(0);
        startDate.setSeconds(0);

        today.setHours(0);
        today.setMinutes(0);
        today.setSeconds(0);

       /* SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date today = null;
        try {
            startDate = sdf.parse("2020-02-12");
            today = sdf.parse("2020-02-13");
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        Query query = new Query();
        Criteria criteria = Criteria.where("updateTime").gt(startDate.getTime()).lt(today.getTime());
        query.addCriteria(criteria);
        try {
            dxyAreaEntityList = mongoTemplate.find(query, DXYAreaEntity.class);
        } catch (Exception e) {
            log.error("get  data from mongo failed,exception=", e);
            log.info("get  data from mongo end");
            return;
        }

        if (CollectionUtils.isEmpty(dxyAreaEntityList)) {
            log.info("get  data from mongo end,data is empty");
            log.info("get data from mongo end,date=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            return;
        }

        //取出当天updateTime最大的重复数据
        List<DXYAreaEntity> areaEntityListForRemoveDuplication = new ArrayList<>(dxyAreaEntityList);
        List<DXYAreaEntity> list = dxyAreaEntityList.stream().filter(dxyAreaEntity -> {
            Boolean flag = true;
            for (DXYAreaEntity areaEntity : areaEntityListForRemoveDuplication) {
                if (areaEntity.getProvinceShortName().equals(dxyAreaEntity.getProvinceShortName())) {
                    if (areaEntity.getUpdateTime() > dxyAreaEntity.getUpdateTime()) {
                        flag = false;
                    }
                }
            }
            return flag;
        }).collect(Collectors.toList());
        //转成mysql对应对象
        List<AreaData> areaDataList = list.stream().map(dxyAreaEntity -> {
            AreaData areaData = MapStructUtil.INSTANCE.buildAreaData(dxyAreaEntity);
            areaData.setUpdateTime(new Date(dxyAreaEntity.getUpdateTime()));
            return areaData;
        }).collect(Collectors.toList());


        //取出城市疫情数据
        List<CityData> cityDataList = new ArrayList<>();
        for (DXYAreaEntity dxyAreaEntity : list) {
            if (!CollectionUtils.isEmpty(dxyAreaEntity.getCities())) {
                for (DXYAreaCityEntity city : dxyAreaEntity.getCities()) {
                    CityData cityData = MapStructUtil.INSTANCE.buildCityData(city);
                    cityData.setParentLocationId(dxyAreaEntity.getLocationId());
                    cityData.setCreateTime(new Date());
                    cityDataList.add(cityData);
                }
            }
        }


        try {
            //存入省份疫情数据
            Integer resultCount = areaDataMapper.insertAll(areaDataList);
            log.info("save area data to mysql end,count=" + resultCount);
            //存储城市疫情数据
            Integer cityResultCount = cityDataMapper.insertAll(cityDataList);
            log.info("save city data to mysql end,count=" + cityResultCount);
        } catch (Exception e) {
            log.error("save area or city data to mysql error,e=", e);
            throw new RuntimeException();
        }

        //检查前一日是否有数据更新，如果没有则取出上一天的数据再次存储为当天数据
        //获取省份信息
        List<ProvinceData> provinceData = provinceDataMapper.selectAll();
        //根据省份信息判断
        List<ProvinceData> provinceDataListForCheckDouble = provinceData.stream().filter(provinceData1 -> {
            Boolean flag = true;
            for (AreaData areaData : areaDataList) {
                if (areaData.getProvinceShortName().equals(provinceData1.getProvinceShortName())) {
                    flag = false;
                }
            }
            return flag;
        }).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(provinceDataListForCheckDouble)) {
            //全省份都有数据更新，后续动作结束
            log.info("get data from mongo end,date=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            return;

        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        //获取前一日省份数据
        List<AreaData> areaDataListForYesterday = areaDataMapper.selectDataByUpdate(provinceDataListForCheckDouble, calendar.getTime());
        List<CityData> cityDataForYesterday = new ArrayList<>();
        //前一日数据也为空，则代表爬虫未获取到,那就全部初始化为0吧
        if (CollectionUtils.isEmpty(areaDataListForYesterday)) {
            for (ProvinceData data : provinceDataListForCheckDouble) {
                AreaData areaData = new AreaData();
                BeanUtils.copyProperties(data, areaData);
                areaData.setId(String.valueOf(System.currentTimeMillis()));
                areaData.setConfirmedCount(0);
                areaData.setCuredCount(0);
                areaData.setDeadCount(0);
                areaData.setSuspectedCount(0);
                areaData.setUpdateTime(startDate);
                areaDataListForYesterday.add(areaData);
            }
        } else {
            //获取前一日城市数据
            cityDataForYesterday = cityDataMapper.selectDataByLocationIdAndcreateTime(areaDataListForYesterday, c.getTime());
        }

        //存储数据
        try {
            //存入省份疫情数据
            Integer resultCount = areaDataMapper.insertAll(areaDataListForYesterday);
            log.info("save area data for Yesterday to mysql end,count=" + resultCount);
            //存储城市疫情数据
            Integer cityResultCount = 0;
            if (!CollectionUtils.isEmpty(cityDataForYesterday)) {
                cityResultCount = cityDataMapper.insertAll(cityDataForYesterday);
            }
            log.info("save city data  for Yesterday to mysql end,count=" + cityResultCount);
        } catch (Exception e) {
            log.error("save area or city for Yesterday data to mysql error,e=", e);
            throw new RuntimeException();
        }

        log.info("get data from mongo end,date=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

}
