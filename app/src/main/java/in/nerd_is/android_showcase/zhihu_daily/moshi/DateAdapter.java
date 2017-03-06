package in.nerd_is.android_showcase.zhihu_daily.moshi;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

/**
 * @author Xuqiang ZHENG on 2016/12/4.
 */
public class DateAdapter {
    @ToJson String toJson(LocalDate date) {
        return date.format(DateTimeFormatter.BASIC_ISO_DATE);
    }

    @FromJson LocalDate fromJson(String json) {
        return DateTimeFormatter.BASIC_ISO_DATE.parse(json).query(LocalDate::from);
    }
}
