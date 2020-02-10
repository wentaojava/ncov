package com.wentao.ncov.entity.mongo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 省份加城市ncov数据
 *
 * @author wentao
 * @time 2020年02月09日
 * @copyright Gods bless me,code never with bug.
 */
@Document("DXYArea")
@Data
public class DXYAreaEntity {
    private String id;
    private String provinceName;
    private String provinceShortName;
    private String country;
    private String comment;
    private Integer confirmedCount;
    private Integer suspectedCount;
    private Integer curedCount;
    private Integer deadCount;
    private Integer locationId;
    private Long updateTime;
    private List<DXYAreaCityEntity> cities;
}
