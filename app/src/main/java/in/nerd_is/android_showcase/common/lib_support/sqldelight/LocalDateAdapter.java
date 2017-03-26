package in.nerd_is.android_showcase.common.lib_support.sqldelight;

import android.support.annotation.NonNull;

import com.squareup.sqldelight.ColumnAdapter;

import org.threeten.bp.LocalDate;

import in.nerd_is.android_showcase.utils.DateUtils;

/**
 * @author Xuqiang ZHENG on 2017/3/5.
 */
public class LocalDateAdapter implements ColumnAdapter<LocalDate, Long> {
    @NonNull
    @Override
    public LocalDate decode(Long millis) {
        return DateUtils.fromMillis(millis).toLocalDate();
    }

    @Override
    public Long encode(@NonNull LocalDate dateTime) {
        return DateUtils.toMillis(dateTime);
    }
}
