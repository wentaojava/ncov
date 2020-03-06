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
    private String confirmedCount;
    /**
     * 疑似人数
     */
    private String suspectedCount;
    /**
     * 治愈人数
     */
    private String curedCount;
    /**
     * 死亡人数
     */
    private String deadCount;
    /**
     * 确诊人数-比昨日新增
     */
    private String confirmedCountIncr;

    /**
     * 疑似人数-比昨日新增
     */
    private String suspectedCountIncr;

    /**
     * 治愈人数-比昨日新增
     */
    private String curedCountIncr;

    /**
     * 死亡人数-比昨日新增
     */
    private String deadCountIncr;

    /**
     * id
     */
    private String id;
}
