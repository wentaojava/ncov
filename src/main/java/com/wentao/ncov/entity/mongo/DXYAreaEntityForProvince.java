package com.wentao.ncov.entity.mongo;

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
public class DXYAreaEntityForProvince implements Serializable {

    private static final long serialVersionUID = 8228361647729742582L;
    /**
     * mongoDB中的主键Id
     */
    private String id;
    private String provinceName;
}
