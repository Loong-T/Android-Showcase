package in.nerd_is.android_showcase;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

import in.nerd_is.android_showcase.common.lib_support.retrofit.RetrofitModule;
import in.nerd_is.android_showcase.common.lib_support.sqlbrite.BriteModule;

/**
 * @author Xuqiang ZHENG on 2016/9/20.
 */
public class ThisApplication extends Application {

    public static ThisApplication INSTANCE;

    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;

        DebugOnly.initStetho(this);

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .retrofitModule(new RetrofitModule())
                .briteModule(new BriteModule())
                .build();
        appComponent.inject(this);

        AndroidThreeTen.init(this);
    }
}
