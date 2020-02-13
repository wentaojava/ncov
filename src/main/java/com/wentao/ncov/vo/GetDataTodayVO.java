package com.wentao.ncov.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wentao
 * @time 2020年02月13日
 * @copyright Gods bless me,code never with bug.
 */
@Data
public class GetDataTodayVO implements Serializable {
    private static final long serialVersionUID = 5267820775123989782L;

    /**
     * 确诊人数
     */
    private Integer confirmedCount;
    /**
     * 疑似人数
     */
    private Integer suspectedCount;
    /**
     * 治愈人数
     */
    private Integer curedCount;
    /**
     * 死亡人数
     */
    private Integer deadCount;
}
