package com.wentao.ncov.util;

import com.wentao.ncov.entity.mongo.DXYAreaCityEntity;
import com.wentao.ncov.entity.mongo.DXYAreaEntity;
import com.wentao.ncov.entity.mongo.DXYAreaEntityForMap;
import com.wentao.ncov.entity.mongo.DXYNationalData;
import com.wentao.ncov.entity.mysql.AreaData;
import com.wentao.ncov.entity.mysql.CityData;
import com.wentao.ncov.entity.mysql.NationalData;
import com.wentao.ncov.vo.GetCityDataTodayByMongodbIdVO;
import com.wentao.ncov.vo.GetDataTodayVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author wentao
 * @time 2020年02月10日
 * @copyright Gods bless me,code never with bug.
 */
@Mapper(componentModel = "spring")
public interface MapStructUtil {
    MapStructUtil INSTANCE = Mappers.getMapper(MapStructUtil.class);


    AreaData buildAreaData(DXYAreaEntityForMap dxyAreaEntity);
    AreaData buildAreaData(DXYAreaEntity dxyAreaEntity);

    @Mapping(target = "cityName", source = "city")
    @Mapping(source = "sure_cnt", target = "confirmedCount")
    @Mapping(source = "like_cnt", target = "suspectedCount")
    @Mapping(source = "die_cnt", target = "deadCount")
    @Mapping(source = "cure_cnt", target = "curedCount")
    CityData buildCityData(DXYAreaCityEntity dxyAreaCityEntity);

    @Mapping(target = "cityName", source = "city")
    @Mapping(source = "sure_cnt", target = "confirmedCount")
    @Mapping(source = "like_cnt", target = "suspectedCount")
    @Mapping(source = "die_cnt", target = "deadCount")
    @Mapping(source = "cure_cnt", target = "curedCount")
    GetCityDataTodayByMongodbIdVO buildCityDataForVO(DXYAreaCityEntity dxyAreaCityEntity);

    @Mapping(target = "cityName", source = "provinceShortName")
    GetCityDataTodayByMongodbIdVO buildCityDataForVO(DXYAreaEntity dxyAreaEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    NationalData buildDXYNationalData(DXYNationalData dxyNationalData);

    @Mapping(target = "id", ignore = true)
    GetDataTodayVO buildDXYNationalDataToGetDataTodayVO(DXYNationalData dxyNationalData);
}
