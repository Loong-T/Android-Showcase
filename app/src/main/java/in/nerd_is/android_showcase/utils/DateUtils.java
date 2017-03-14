package in.nerd_is.android_showcase.utils;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;

import java.util.Date;

/**
 * @author Xuqiang ZHENG on 2017/3/5.
 */
public final class DateUtils {
    private DateUtils() {}

    public static LocalDateTime fromMillis(long millis) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault());
    }

    public static long toMillis(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static LocalDateTime fromDate(Date date) {
        return LocalDateTime.ofInstant(
                Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
    }
}
