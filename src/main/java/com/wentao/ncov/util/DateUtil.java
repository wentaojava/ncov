package com.wentao.ncov.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 日期格式化
 *
 * @author wentao
 * @time 2020年03月07日
 * @copyright Gods bless me,code never with bug.
 */
public final class DateUtil {

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);

    public static Date ignoreDateTime(Date date) {
        try {
            return sdf.parse(sdf.format(date));
        } catch (ParseException e) {
            return date;
        }

    }
}
