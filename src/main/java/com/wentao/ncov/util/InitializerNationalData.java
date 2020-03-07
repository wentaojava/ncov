package com.wentao.ncov.util;


import com.wentao.ncov.entity.mongo.DXYNationalData;
import com.wentao.ncov.entity.mysql.AreaData;
import com.wentao.ncov.entity.mysql.NationalData;
import com.wentao.ncov.mappers.AreaDataMapper;
import com.wentao.ncov.mappers.NationalDataMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 存储省份信息
 *
 * @author wentao
 * @time 2020年02月11日
 * @copyright Gods bless me,code never with bug.
 */
@Transactional(rollbackFor = RuntimeException.class)
@Component
public class InitializerNationalData {
    private static final Logger log = LoggerFactory.getLogger(InitializerNationalData.class);

    @Resource
    private MongoTemplate mongoTemplate;
    @Resource
    private NationalDataMapper nationalDataMapper;
    @Resource
    private AreaDataMapper areaDataMapper;


    public void makeDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        try {
            startDate = sdf.parse("2020-02-08");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Query query = new Query();
        Criteria criteria = Criteria.where("createTime").is(startDate);
        query.addCriteria(criteria);
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
            nationalData.setCreateTime(startDate);
            nationalDataMapper.insert(nationalData);
            log.info("save national data  for Yesterday to mysql end");
        } else {
            List<AreaData> list = areaDataMapper.selectByUpdateTime("2020-02-08", "中国");
            if (!CollectionUtils.isEmpty(list)) {
                Integer confirmedCount = 0;
                Integer suspectedCount = 0;
                Integer curedCount = 0;
                Integer deadCount = 0;
                for (AreaData areaData : list) {
                    if (!areaData.getConfirmedCount().equals("-") || !StringUtils.isEmpty(areaData.getConfirmedCount())) {
                        confirmedCount += Integer.valueOf(areaData.getConfirmedCount());
                    }
                    if (!areaData.getSuspectedCount().equals("-") || !StringUtils.isEmpty(areaData.getSuspectedCount())) {
                        suspectedCount += Integer.valueOf(areaData.getSuspectedCount());
                    }
                    if (!areaData.getCuredCount().equals("-") || !StringUtils.isEmpty(areaData.getCuredCount())) {
                        curedCount += Integer.valueOf(areaData.getCuredCount());
                    }
                    if (!areaData.getDeadCount().equals("-") || !StringUtils.isEmpty(areaData.getDeadCount())) {
                        deadCount += Integer.valueOf(areaData.getDeadCount());
                    }
                }
                NationalData nationalData = new NationalData();
                nationalData.setConfirmedCount(confirmedCount.toString());
                nationalData.setConfirmedCountIncr("-");
                nationalData.setSuspectedCount(suspectedCount.toString());
                nationalData.setSuspectedCountIncr("-");
                nationalData.setCuredCount(curedCount.toString());
                nationalData.setCuredCountIncr("-");
                nationalData.setDeadCount(deadCount.toString());
                nationalData.setDeadCountIncr("-");
                nationalDataMapper.insert(nationalData);
            }


        }
    }
}
