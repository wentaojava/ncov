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
    private String country;
    private String province;
    private String city;
    /**
     * 确诊人数
     */
    private String sure_cnt;
    /**
     * 疑似人数
     */
    private String like_cnt;
    /**
     * 死亡人数
     */
    private String die_cnt;
    /**
     * 治愈人数
     */
    private String cure_cnt;
    /**
     * 新增确诊数
     */
    private String sure_new_cnt;
    private String sure_nzd_cnt;
}

