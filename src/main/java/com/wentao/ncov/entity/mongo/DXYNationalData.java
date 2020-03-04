package com.wentao.ncov.entity.mongo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 每日全国疫情数据
 *
 * @author wentao
 * @time 2020年03月03日
 * @copyright Gods bless me,code never with bug.
 */
@Document("DXYNationalData")
@Data
public class DXYNationalData {
    private String id;

    private String confirmedCount;

    private String confirmedCountIncr;

    private String suspectedCount;

    private String suspectedCountIncr;

    private String curedCount;

    private String curedCountIncr;

    private String deadCount;

    private String deadCountIncr;

    private String createTime;

}
