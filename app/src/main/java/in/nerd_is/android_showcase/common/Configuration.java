package in.nerd_is.android_showcase.common;

import android.content.Context;
import android.content.SharedPreferences;

import in.nerd_is.android_showcase.ThisApplication;

/**
 * @author Xuqiang ZHENG on 2017/3/27.
 */
public class Configuration {

    // do not change these constants
    public static final String NIGHT_MODE = "night_mode";

    private static final String SP_NAME_CONFIGURATION = "configuration";

    private static Configuration INSTANCE;

    private final SharedPreferences sp;

    public static Configuration getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Configuration(ThisApplication.INSTANCE);
        }
        return INSTANCE;
    }

    private Configuration(Context appContext) {
        sp = appContext.getSharedPreferences(SP_NAME_CONFIGURATION, Context.MODE_PRIVATE);
    }

    public void setNightMode(boolean nightMode) {
        sp.edit().putBoolean(NIGHT_MODE, nightMode).apply();
    }

    public boolean isNightMode() {
        return sp.getBoolean(NIGHT_MODE, false);
    }
}
