package com.wentao.ncov.entity.mongo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wentao
 * @time 2020年02月09日
 * @copyright Gods bless me,code never with bug.
 */
@Data
public class DXYAreaCityEntity implements Serializable {

    private static final long serialVersionUID = 6141561610662862392L;
    private String cityName;
    private Integer currentConfirmedCount;
    private Integer confirmedCount;
    private Integer suspectedCount;
    private Integer curedCount;
    private Integer deadCount;
    private Integer locationId;
}

