package com.wentao.ncov.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wentao
 * @time 2020年02月13日
 * @copyright Gods bless me,code never with bug.
 */
@Data
public class GetProvinceVO implements Serializable {
    private static final long serialVersionUID = -207841913285614795L;

    private Integer id;
    private String provinceName;
    private String provinceShortName;
    private Integer locationId;
    private String country;
}
