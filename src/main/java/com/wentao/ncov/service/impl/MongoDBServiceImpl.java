package com.wentao.ncov.service.impl;


import com.wentao.ncov.entity.mongo.DXYAreaEntity;
import com.wentao.ncov.entity.mysql.ProvinceData;
import com.wentao.ncov.service.MongoDBService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 根据省份获取数据
     *
     * @param provinceData
     * @return
     * @throws
     * @author wentao
     * @time 2020年02月13日
     * Gods bless me,code never with bug.
     */
    @Override
    public Map<String, DXYAreaEntity> getDataToday(List<ProvinceData> provinceData) {
        Map<String, DXYAreaEntity> dxyAreaEntityMap = new HashMap<>(provinceData.size());
        for (ProvinceData province : provinceData) {
            if (!StringUtils.isEmpty(province.getCountry()) && province.getCountry().equals("中国")) {
                Query query = new Query();
                Criteria criteria = Criteria.where("provinceShortName").is(province.getProvinceShortName());
                query.addCriteria(criteria);
                query.with(Sort.by(Sort.Order.desc("updateTime")));
                DXYAreaEntity dxyAreaEntity = null;
                try {
                    dxyAreaEntity = mongoTemplate.findOne(query, DXYAreaEntity.class);
                } catch (Exception e) {
                    log.error("get data for " + province.getProvinceShortName() + " from mongoDB error,e=", e);
                }
                dxyAreaEntityMap.put(province.getProvinceShortName(), dxyAreaEntity);
            }
        }
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
        Criteria criteria = Criteria.where("_id").is(id);
        query.addCriteria(criteria);
        DXYAreaEntity dxyAreaEntity = null;
        try {
            dxyAreaEntity = mongoTemplate.findOne(query, DXYAreaEntity.class);
        } catch (Exception e) {
            log.error("get data for (_id=" + id + ") from mongoDB error,e=", e);
        }
        return dxyAreaEntity;
    }
}
