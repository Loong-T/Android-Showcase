package in.nerd_is.android_showcase.common.lib_support.auto;

import android.os.Parcel;

import com.ryanharter.auto.value.parcel.TypeAdapter;

import org.threeten.bp.LocalDate;

import in.nerd_is.android_showcase.utils.DateUtils;

/**
 * @author Xuqiang ZHENG on 2017/3/22.
 */
public class LocalDateParcelAdapter implements TypeAdapter<LocalDate> {
    @Override
    public LocalDate fromParcel(Parcel in) {
        return DateUtils.fromMillis(in.readLong()).toLocalDate();
    }

    @Override
    public void toParcel(LocalDate value, Parcel dest) {
        dest.writeLong(DateUtils.toMillis(value));
    }
}
