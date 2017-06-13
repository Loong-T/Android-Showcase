package in.nerd_is.android_showcase.common.lib_support.paperparcel;

import android.os.Parcel;
import android.support.annotation.NonNull;

import org.threeten.bp.LocalDate;

import in.nerd_is.android_showcase.utils.DateUtils;
import paperparcel.TypeAdapter;

/**
 * @author Xuqiang ZHENG on 2017/3/22.
 */
public final class LocalDateParcelAdapter implements TypeAdapter<LocalDate> {

    public static final LocalDateParcelAdapter INSTANCE = new LocalDateParcelAdapter();

    @Override
    public LocalDate readFromParcel(@NonNull Parcel source) {
        return DateUtils.fromMillis(source.readLong()).toLocalDate();
    }

    @Override
    public void writeToParcel(LocalDate value, @NonNull Parcel dest, int flags) {
        dest.writeLong(DateUtils.toMillis(value));
    }
}
