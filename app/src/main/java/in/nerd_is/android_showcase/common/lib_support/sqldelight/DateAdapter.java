package in.nerd_is.android_showcase.common.lib_support.sqldelight;

import android.support.annotation.NonNull;

import com.squareup.sqldelight.ColumnAdapter;

import org.threeten.bp.LocalDateTime;

import in.nerd_is.android_showcase.utils.DateUtils;

/**
 * @author Xuqiang ZHENG on 2017/3/5.
 */
public class DateAdapter implements ColumnAdapter<LocalDateTime, Long> {
    @NonNull
    @Override
    public LocalDateTime decode(Long millis) {
        return DateUtils.fromMillis(millis);
    }

    @Override
    public Long encode(@NonNull LocalDateTime dateTime) {
        return DateUtils.toMillis(dateTime);
    }
}
