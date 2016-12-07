package in.nerd_is.android_showcase.common.di.activity;

import android.app.Activity;

public interface HasActivitySubcomponentBuilders {
    ActivityComponentBuilder get(Class<? extends Activity> activityClass);
}