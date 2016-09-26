package in.nerd_is.android_showcase.common;

import android.support.annotation.StringRes;

/**
 * Created by Xuqiang ZHENG on 2016/9/18.
 */

public interface BaseContract {
    interface View {
        void toast(CharSequence text);
        void toast(@StringRes int resId);

        void snackbar(CharSequence text);
        void snackbar(@StringRes int resId);
    }

    interface Presenter {

    }
}
