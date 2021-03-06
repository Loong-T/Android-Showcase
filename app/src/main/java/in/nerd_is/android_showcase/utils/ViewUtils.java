package in.nerd_is.android_showcase.utils;

import android.support.annotation.IdRes;
import android.view.View;

/**
 * @author Xuqiang ZHENG on 2016/10/1.
 */
public final class ViewUtils {
    private ViewUtils() {}

    @SuppressWarnings("unchecked")
    public static <T extends View> T find(View view, @IdRes int resId) {
        return (T) view.findViewById(resId);
    }
}
