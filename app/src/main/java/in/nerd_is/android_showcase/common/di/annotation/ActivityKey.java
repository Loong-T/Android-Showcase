package in.nerd_is.android_showcase.common.di.annotation;

import android.app.Activity;

import dagger.MapKey;

/**
 * Created by Xuqiang ZHENG on 2016/10/25.
 */
@MapKey
public @interface ActivityKey {
    Class<? extends Activity> value();
}
