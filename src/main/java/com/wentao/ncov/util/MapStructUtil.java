package com.wentao.ncov.util;

import com.wentao.ncov.entity.mongo.DXYAreaCityEntity;
import com.wentao.ncov.entity.mongo.DXYAreaEntity;
import com.wentao.ncov.entity.mysql.AreaData;
import com.wentao.ncov.entity.mysql.CityData;
import com.wentao.ncov.vo.GetCityDataTodayByMongodbIdVO;
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

    @Mapping(target = "updateTime", ignore = true)
    AreaData buildAreaData(DXYAreaEntity dxyAreaEntity);

    CityData buildCityData(DXYAreaCityEntity dxyAreaCityEntity);

    GetCityDataTodayByMongodbIdVO buildCityDataForVO(DXYAreaCityEntity dxyAreaCityEntity);

    @Mapping(target = "cityName", source = "provinceShortName")
    GetCityDataTodayByMongodbIdVO buildCityDataForVO(DXYAreaEntity dxyAreaEntity);
}
