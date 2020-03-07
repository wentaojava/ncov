package com.wentao.ncov.service.impl;


import com.wentao.ncov.entity.mongo.DXYAreaEntity;
import com.wentao.ncov.entity.mongo.DXYAreaEntityForMap;
import com.wentao.ncov.entity.mongo.DXYNationalData;
import com.wentao.ncov.service.MongoDBService;
import com.wentao.ncov.vo.GetProvinceVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author wentao
 * @time 2020年02月13日
 * @copyright Gods bless me,code never with bug.
 */
@Slf4j
@Service
public class MongoDBServiceImpl implements MongoDBService {
    @Resource
    private MongoTemplate mongoTemplate;


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
    @Override
    public Map<String, DXYAreaEntityForMap> getDataTodayForProvince() {
        //2020221更新python值存储当日最新数据，因此此处查询mongoDB时去除循环条件
        List<DXYAreaEntityForMap> dxyAreaEntityList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        Query query = new Query();
        Criteria criteria = Criteria.where("createTime").is(date);
        query.addCriteria(criteria);
        try {
            dxyAreaEntityList = mongoTemplate.find(query, DXYAreaEntityForMap.class);
        } catch (Exception e) {
            log.error("get data size：" + dxyAreaEntityList.size() + " from mongoDB error,e=", e);
        }
        Map<String, DXYAreaEntityForMap> dxyAreaEntityMap = dxyAreaEntityList.stream().collect(
                Collectors.toMap(DXYAreaEntityForMap::getProvinceShortName, Function.identity(), (a, b) -> b));
        return dxyAreaEntityMap;
    }


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
    @Override
    public DXYAreaEntity getDataTodayByMongodbId(String id) {
        Query query = new Query();
        Criteria criteria = Criteria.where("id").is(id);
        query.addCriteria(criteria);
        DXYAreaEntity dxyAreaEntity = null;
        try {
            dxyAreaEntity = mongoTemplate.findOne(query, DXYAreaEntity.class);
        } catch (Exception e) {
            log.error("get data for (id=" + id + ") from mongoDB error,e=", e);
        }
        return dxyAreaEntity;
    }

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
    @Override
    public DXYNationalData getNationalDataToday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        Query query = new Query();
        Criteria criteria = Criteria.where("createTime").is(date);
        query.addCriteria(criteria);
        DXYNationalData dxyNationalData = null;
        try {
            dxyNationalData = mongoTemplate.findOne(query, DXYNationalData.class);
        } catch (Exception e) {
            log.error("get data for (date=" + date + ") from mongoDB error,e=", e);
        }
        return dxyNationalData;
    }

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
    @Override
    public List<GetProvinceVO> getTodayProvince() {
        List<GetProvinceVO> dxyAreaEntityList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        Query query = new Query();
        Criteria criteria = Criteria.where("createTime").is(date);
        query.addCriteria(criteria);
        try {
            dxyAreaEntityList = mongoTemplate.find(query, GetProvinceVO.class);
        } catch (Exception e) {
            log.error("get data size：" + dxyAreaEntityList.size() + " from mongoDB error,e=", e);
        }
        return dxyAreaEntityList;
    }
}
