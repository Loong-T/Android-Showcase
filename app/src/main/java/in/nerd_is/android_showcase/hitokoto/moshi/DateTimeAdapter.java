package in.nerd_is.android_showcase.hitokoto.moshi;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

/**
 * @author Xuqiang ZHENG on 2017/3/6.
 */
public class DateTimeAdapter {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy.MM.dd kk:mm:ss");

    @ToJson String toJson(LocalDateTime dateTime) {
        return FORMATTER.format(dateTime);
    }

    @FromJson LocalDateTime fromJson(String json) {
        return FORMATTER.parse(json).query(LocalDateTime::from);
    }
}
