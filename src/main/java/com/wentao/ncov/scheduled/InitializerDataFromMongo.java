package com.wentao.ncov.scheduled;

import com.wentao.ncov.entity.mongo.DXYAreaCityEntity;
import com.wentao.ncov.entity.mongo.DXYAreaEntity;
import com.wentao.ncov.entity.mongo.DXYNationalData;
import com.wentao.ncov.entity.mysql.AreaData;
import com.wentao.ncov.entity.mysql.CityData;
import com.wentao.ncov.entity.mysql.NationalData;
import com.wentao.ncov.entity.mysql.ProvinceData;
import com.wentao.ncov.mappers.AreaDataMapper;
import com.wentao.ncov.mappers.CityDataMapper;
import com.wentao.ncov.mappers.NationalDataMapper;
import com.wentao.ncov.mappers.ProvinceDataMapper;
import com.wentao.ncov.util.MapStructUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
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
@Slf4j
public class InitializerDataFromMongo {

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private AreaDataMapper areaDataMapper;

    @Resource
    private CityDataMapper cityDataMapper;

    @Resource
    private ProvinceDataMapper provinceDataMapper;

    @Resource
    private NationalDataMapper nationalDataMapper;

    @Scheduled(cron = "0 10 0 * * *")
    public void scheduledMethod() {
        log.info("get data from mongo start.....");
        List<DXYAreaEntity> dxyAreaEntityList = new ArrayList<>();
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DAY_OF_MONTH, -1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        sdf.setTimeZone(TimeZone.getTimeZone("CTT"));
        String date = sdf.format(c.getTime());


        /*
        //此处用于修复定时任务出错的某天数据
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date today = null;
        try {
            startDate = sdf.parse("2020-02-16");
            today = sdf.parse("2020-02-17");
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        Query query = new Query();
        Criteria criteria = Criteria.where("createTime").is(date);
        query.addCriteria(criteria);
        //存储前一日全国疫情数据
        DXYNationalData dxyNationalData = null;
        try {
            dxyNationalData = mongoTemplate.findOne(query, DXYNationalData.class);
        } catch (Exception e) {
            log.error("get national data from mongo failed,exception=", e);
            log.info("get data from mongo end with exception");
        }

        if (null != dxyNationalData) {
            //转换mysql对象
            NationalData nationalData = MapStructUtil.INSTANCE.buildDXYNationalData(dxyNationalData);
            nationalData.setCreateTime(c.getTime());
            nationalDataMapper.insert(nationalData);
            log.info("save national data  for Yesterday to mysql end");
        }

        //获取前一日省份疫情数据
        try {
            dxyAreaEntityList = mongoTemplate.find(query, DXYAreaEntity.class);
        } catch (Exception e) {
            log.error("get  data from mongo failed,exception=", e);
            log.info("get  data from mongo end with exception");
            return;
        }

        if (CollectionUtils.isEmpty(dxyAreaEntityList)) {
            log.info("get  data from mongo end,data is empty");
            log.info("get data from mongo end,date=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            return;
        }

        //获取省份信息
        List<ProvinceData> provinceData = provinceDataMapper.selectAll();

        //省份转map，便于城市信息中LocationId的赋值
        Map<String, Integer> provinceMap = provinceData.stream().collect(Collectors.toMap(ProvinceData::getProvinceName, ProvinceData::getLocationId));

        //转成mysql对应对象
        List<AreaData> areaDataList = dxyAreaEntityList.stream().map(dxyAreaEntity -> {
            AreaData areaData = MapStructUtil.INSTANCE.buildAreaData(dxyAreaEntity);
            areaData.setLocationId(provinceMap.get(areaData.getProvinceName()));
            areaData.setUpdateTime(c.getTime());
            return areaData;
        }).collect(Collectors.toList());


        //取出城市疫情数据
        List<CityData> cityDataList = new ArrayList<>();
        for (DXYAreaEntity dxyAreaEntity : dxyAreaEntityList) {
            if (!CollectionUtils.isEmpty(dxyAreaEntity.getCities())) {
                for (DXYAreaCityEntity city : dxyAreaEntity.getCities()) {
                    CityData cityData = MapStructUtil.INSTANCE.buildCityData(city);
                    cityData.setParentLocationId(provinceMap.get(dxyAreaEntity.getProvinceName()));
                    cityData.setCreateTime(c.getTime());
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

       /* //检查前一日是否有数据更新，如果没有则取出上一天的数据再次存储为当天数据;
        //2020/03/03 更新：去除非中国数据的存储

        //根据省份信息判断
        List<ProvinceData> provinceDataListForCheckDouble = provinceData.stream().filter(provinceData1 -> {
            Boolean flag = true;
            for (AreaData areaData : areaDataList) {
                if (areaData.getProvinceShortName().equals(provinceData1.getProvinceShortName())
                        || !provinceData1.getCountry().equals("中国")) {
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
        calendar.setTime(c.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        //获取前一日省份数据
        List<AreaData> areaDataListForYesterday = areaDataMapper.selectDataByUpdate(provinceDataListForCheckDouble, sdf.format(calendar.getTime()));
        List<CityData> cityDataForYesterday = new ArrayList<>();
        //前一日数据也为空，则代表爬虫未获取到,那就全部初始化为0吧
        if (CollectionUtils.isEmpty(areaDataListForYesterday)) {
            for (ProvinceData data : provinceDataListForCheckDouble) {
                AreaData areaData = new AreaData();
                BeanUtils.copyProperties(data, areaData);
                areaData.setId(UUID.randomUUID().toString());
                areaData.setConfirmedCount("-");
                areaData.setCuredCount("-");
                areaData.setDeadCount("-");
                areaData.setSuspectedCount("-");
                areaData.setUpdateTime(c.getTime());
                areaDataListForYesterday.add(areaData);
            }
        } else {
            //获取前一日城市数据
            cityDataForYesterday = cityDataMapper.selectDataByLocationIdAndcreateTime(areaDataListForYesterday, calendar.getTime());
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
        }*/

        log.info("get data from mongo end,date=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

}
