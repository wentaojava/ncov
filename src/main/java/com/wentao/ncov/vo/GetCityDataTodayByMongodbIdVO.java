package com.wentao.ncov.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wentao
 * @time 2020年02月14日
 * @copyright Gods bless me,code never with bug.
 */
@Data
public class GetCityDataTodayByMongodbIdVO implements Serializable {
    private static final long serialVersionUID = 5519350605133920465L;

    private String cityName;
    private Integer confirmedCount;
    private Integer suspectedCount;
    private Integer curedCount;
    private Integer deadCount;
    private Integer locationId;
}
