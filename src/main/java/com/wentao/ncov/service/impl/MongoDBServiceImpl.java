package com.wentao.ncov.service.impl;

import com.wentao.ncov.entity.mongo.DXYAreaEntity;
import com.wentao.ncov.entity.mysql.ProvinceData;
import com.wentao.ncov.service.MongoDBService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    public List<DXYAreaEntity> getDataToday(List<ProvinceData> provinceData) {
       /* List<DXYAreaEntity> dxyAreaEntityList = new ArrayList<>();
        Query query = new Query();
        List<Criteria> criteriaList=new ArrayList<>();
        for (ProvinceData provinceDatum : provinceData) {
            criteriaList.add(new Criteria().andOperator(
                    Criteria.where("country").is(provinceDatum.getCountry()),
                    Criteria.where("updateTime").is(provinceDatum.getCountry())));
        }
        Criteria criteria = Criteria.where("updateTime").orOperator();
        query.addCriteria(criteria);
        try {
            dxyAreaEntityList = mongoTemplate.find(query, DXYAreaEntity.class);
        } catch (Exception e) {
            log.error("get  data from mongo failed,exception=", e);
            log.info("get  data from mongo end");
            return;
        }*/
        return null;
    }
}
