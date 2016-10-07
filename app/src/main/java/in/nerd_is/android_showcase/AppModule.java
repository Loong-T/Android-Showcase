package in.nerd_is.android_showcase;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.nerd_is.android_showcase.main.MainComponent;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static in.nerd_is.android_showcase.common.Constant.TAG_IO;
import static in.nerd_is.android_showcase.common.Constant.TAG_MAIN;

/**
 * Created by Xuqiang ZHENG on 2016/9/20.
 */
@Module
public class AppModule {

    private ThisApplication thisApplication;

    public AppModule(ThisApplication thisApplication) {
        this.thisApplication = thisApplication;
    }

    @Provides @Singleton
    public ThisApplication provideApplication() {
        return thisApplication;
    }

    @Provides @Named(TAG_IO) @Singleton
    public Scheduler provideIoScheduler() {
        return Schedulers.io();
    }

    @Provides @Named(TAG_MAIN) @Singleton
    public Scheduler provideMainScheduler() {
        return AndroidSchedulers.mainThread();
    }
}