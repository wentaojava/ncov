package com.wentao.ncov.bo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author wentao
 * @time 2020年02月13日
 * @copyright Gods bless me,code never with bug.
 */
@Data
public class GetCityByLocationIdBO implements Serializable {
    private static final long serialVersionUID = 7268355300479613137L;

    @NotNull(message = "省份id不可为空")
    private Long locationId;
}
