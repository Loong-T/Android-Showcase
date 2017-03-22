package in.nerd_is.android_showcase.common.lib_support.sqldelight;

import android.support.annotation.NonNull;

import com.squareup.sqldelight.ColumnAdapter;

import java.util.Arrays;
import java.util.List;

import in.nerd_is.android_showcase.utils.CommonUtils;

/**
 * @author Xuqiang ZHENG on 2017/3/5.
 */
public class UrlListAdapter implements ColumnAdapter<List<String>, String> {

    private static final String SEPARATOR = ",";

    @NonNull
    @Override
    public List<String> decode(String databaseValue) {
        return Arrays.asList(databaseValue.split(SEPARATOR));
    }

    @Override
    public String encode(@NonNull List<String> value) {
        return CommonUtils.mkString(value, SEPARATOR);
    }
}
