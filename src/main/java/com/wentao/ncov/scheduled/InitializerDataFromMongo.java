package com.wentao.ncov.scheduled;

import com.wentao.ncov.entity.mongo.DXYAreaEntity;
import com.wentao.ncov.entity.mysql.AreaData;
import com.wentao.ncov.mappers.AreaDataMapper;
import com.wentao.ncov.util.MapStructUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
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
public class InitializerDataFromMongo {

    private static final Logger log = LoggerFactory.getLogger(InitializerDataFromMongo.class);

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private AreaDataMapper areaDataMapper;

    @Scheduled(cron = "0 10 0 * * *")
    public void scheduledMethod() {
        log.info("get data from mongo start.....");
        List<DXYAreaEntity> dxyAreaEntityList = new ArrayList<>();
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DAY_OF_MONTH, -4);
        Date startDate = c.getTime();
        /*startDate.setHours(23);
        startDate.setMinutes(50);
        startDate.setSeconds(0);*/

        today.setHours(0);
        today.setMinutes(0);
        today.setSeconds(0);

        Query query = new Query();
        Criteria criteria = Criteria.where("updateTime").gte(startDate.getTime()).lte(today.getTime());
        query.addCriteria(criteria);
        try {
            dxyAreaEntityList = mongoTemplate.find(query, DXYAreaEntity.class);
        } catch (Exception e) {
            log.error("get data from mongo failed,exception=", e);
            log.info("get data from mongo end");
            return;
        }

        if (CollectionUtils.isEmpty(dxyAreaEntityList)) {
            log.info("get data from mongo end,data is empty");
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
        //存入mysql
        try {
            Integer resultCount = areaDataMapper.insertAll(areaDataList);
            log.info("save data to mysql end,count=", resultCount);
        } catch (Exception e) {
            log.error("save data to mysql error,e=", e);
        }

        log.info("get data from mongo end,date=" + today);
    }

}
