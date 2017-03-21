package in.nerd_is.android_showcase;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static in.nerd_is.android_showcase.common.Constant.TAG_IO;
import static in.nerd_is.android_showcase.common.Constant.TAG_MAIN;

/**
 * @author Xuqiang ZHENG on 2016/9/20.
 */
@Module
public class AppModule {

    private ThisApplication thisApplication;

    public AppModule(ThisApplication thisApplication) {
        this.thisApplication = thisApplication;
    }

    @Provides @Singleton
    public Context provideApplicationContext() {
        return thisApplication;
    }

    @Provides @Named(TAG_IO) @Singleton
    public static Scheduler provideIoScheduler() {
        return Schedulers.io();
    }

    @Provides @Named(TAG_MAIN) @Singleton
    public static Scheduler provideMainScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
