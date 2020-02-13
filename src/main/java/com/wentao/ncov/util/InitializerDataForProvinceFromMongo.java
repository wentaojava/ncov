package com.wentao.ncov.util;


import com.wentao.ncov.entity.mysql.AreaData;
import com.wentao.ncov.entity.mysql.ProvinceData;
import com.wentao.ncov.mappers.AreaDataMapper;
import com.wentao.ncov.mappers.ProvinceDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class InitializerDataForProvinceFromMongo {
    private static final Logger log = LoggerFactory.getLogger(InitializerDataForProvinceFromMongo.class);

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private ProvinceDataMapper provinceDataMapper;
    @Resource
    private AreaDataMapper areaDataMapper;


    public void makeProvince() {
        List<AreaData> areaDataList = areaDataMapper.selectAll();

        List<ProvinceData> provinceData = new ArrayList<>();
        for (AreaData areaData : areaDataList) {
            ProvinceData provinceData1 = new ProvinceData();
            BeanUtils.copyProperties(areaData, provinceData1);

            if (!provinceData.contains(provinceData1)) {
                provinceData1.setCountryType("");
                provinceData1.setCreateTime(new Date());
                provinceData.add(provinceData1);
            }
        }

        for (ProvinceData data : provinceData) {
            provinceDataMapper.insert(data);
        }

    }
}
