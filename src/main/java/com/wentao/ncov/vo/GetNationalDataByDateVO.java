package com.wentao.ncov.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wentao
 * @time 2020年03月07日
 * @copyright Gods bless me,code never with bug.
 */
@Data
public class GetNationalDataByDateVO implements Serializable {
    private static final long serialVersionUID = 4322226153907486582L;

    private String confirmedCount;

    private String suspectedCount;


    private String curedCount;


    private String deadCount;

}
