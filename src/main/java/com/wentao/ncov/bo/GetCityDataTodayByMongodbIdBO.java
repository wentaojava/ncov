package com.wentao.ncov.bo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author wentao
 * @time 2020年02月14日
 * @copyright Gods bless me,code never with bug.
 */
@Data
public class GetCityDataTodayByMongodbIdBO implements Serializable {
    private static final long serialVersionUID = -43845060402179761L;


    @NotNull(message = "mongodb对应id不可为空")
    private String id;
}
