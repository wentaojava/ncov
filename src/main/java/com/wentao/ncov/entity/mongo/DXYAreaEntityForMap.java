package com.wentao.ncov.entity.mongo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * 省份加城市ncov数据
 *
 * @author wentao
 * @time 2020年02月09日
 * @copyright Gods bless me,code never with bug.
 */
@Document("DXYArea")
@Data
public class DXYAreaEntityForMap implements Serializable {
    private static final long serialVersionUID = 3480473307532305846L;
    private String id;
    private String provinceName;
    private String provinceShortName;
    private String confirmedCount;
    private String newConfirmedCount;
    private String suspectedCount;
    private String curedCount;
    private String deadCount;
    private String sureNzdCnt;
    private String createTime;
}
