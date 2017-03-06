package in.nerd_is.android_showcase.zhihu_daily.entity;

import com.google.auto.value.AutoValue;

import org.threeten.bp.LocalDate;

/**
 * @author Xuqiang ZHENG on 2016/12/5.
 */
@AutoValue
public abstract class Date {
    public abstract LocalDate date();

    public static Date create(LocalDate localDate) {
        return new AutoValue_Date(localDate);
    }
}
