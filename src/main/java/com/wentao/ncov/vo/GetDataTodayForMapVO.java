package com.wentao.ncov.vo;

import com.wentao.ncov.entity.mysql.AreaData;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author wentao
 * @time 2020年02月13日
 * @copyright Gods bless me,code never with bug.
 */
@Data
public class GetDataTodayForMapVO implements Serializable {
    private static final long serialVersionUID = 5267820775123989782L;
    /**
     * 省份数据集合
     */
    private List<AreaData> areaDataList;
}
