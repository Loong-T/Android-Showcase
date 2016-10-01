package in.nerd_is.android_showcase;

import android.app.Application;

/**
 * Created by Xuqiang ZHENG on 2016/9/20.
 */
public class ThisApplication extends Application {

    public static ThisApplication INSTANCE;
    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
