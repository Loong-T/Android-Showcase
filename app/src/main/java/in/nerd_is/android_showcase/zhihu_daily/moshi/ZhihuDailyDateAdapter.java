package in.nerd_is.android_showcase.zhihu_daily.moshi;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import org.threeten.bp.LocalDate;

import static in.nerd_is.android_showcase.zhihu_daily.Constant.NEWS_DATE_FORMATTER;

/**
 * @author Xuqiang ZHENG on 2016/12/4.
 */
public class ZhihuDailyDateAdapter {
    @ToJson String toJson(LocalDate date) {
        return date.format(NEWS_DATE_FORMATTER);
    }

    @FromJson LocalDate fromJson(String json) {
        return NEWS_DATE_FORMATTER.parse(json).query(LocalDate::from);
    }
}
