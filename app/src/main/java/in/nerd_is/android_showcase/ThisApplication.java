package in.nerd_is.android_showcase;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.github.piasy.biv.BigImageViewer;
import com.github.piasy.biv.loader.glide.GlideImageLoader;
import com.jakewharton.threetenabp.AndroidThreeTen;

import in.nerd_is.android_showcase.common.Configuration;
import in.nerd_is.android_showcase.common.lib_support.retrofit.RetrofitModule;

/**
 * @author Xuqiang ZHENG on 2016/9/20.
 */
public class ThisApplication extends Application {

    public static ThisApplication INSTANCE;

    public AppComponent appComponent;
    public Configuration configuration;

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;

        DebugOnly.initStetho(this);
        AndroidThreeTen.init(this);
        BigImageViewer.initialize(GlideImageLoader.with(this));

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .retrofitModule(new RetrofitModule())
                .build();
        appComponent.inject(this);

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
}
