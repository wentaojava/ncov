package com.wentao.ncov.bo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wentao
 * @time 2020年03月07日
 * @copyright Gods bless me,code never with bug.
 */
@Data
public class GetNationalDataByDateBO implements Serializable {
    private static final long serialVersionUID = 1428681781335843639L;

    @NotNull(message = "请选择开始日期")
    private Date startDate;
    @NotNull(message = "请选择结束日期")
    private Date endDate;
}
