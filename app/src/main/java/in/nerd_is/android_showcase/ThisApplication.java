package in.nerd_is.android_showcase;

import android.app.Activity;
import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.jakewharton.threetenabp.AndroidThreeTen;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import in.nerd_is.android_showcase.common.Configuration;
import in.nerd_is.android_showcase.common.lib_support.paperparcel.LocalDateParcelAdapter;
import in.nerd_is.android_showcase.common.lib_support.retrofit.RetrofitModule;
import paperparcel.Adapter;
import paperparcel.ProcessorConfig;

/**
 * @author Xuqiang ZHENG on 2016/9/20.
 */
@ProcessorConfig(adapters = @Adapter(LocalDateParcelAdapter.class))
public class ThisApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

    public static ThisApplication INSTANCE;

    public Configuration configuration;

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;

        DebugOnly.initStetho(this);
        AndroidThreeTen.init(this);

        DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .retrofitModule(new RetrofitModule())
                .build()
                .inject(this);

        configuration = Configuration.getInstance();
        setNightModeFromConfiguration();
    }

    public void setNightModeFromConfiguration() {
        if (configuration.isNightMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }
}
