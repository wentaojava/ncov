package com.wentao.ncov.vo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author wentao
 * @time 2020年02月13日
 * @copyright Gods bless me,code never with bug.
 */
@Document("DXYArea")
@Data
public class GetProvinceVO implements Serializable {
    private static final long serialVersionUID = -207841913285614795L;

    /**
     * mongoDB中的主键Id
     */
    private String id;
    private String provinceName;
}
