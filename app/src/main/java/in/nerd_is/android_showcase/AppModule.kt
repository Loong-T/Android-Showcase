package `in`.nerd_is.android_showcase

import `in`.nerd_is.android_showcase.common.Constant.TAG_IO
import `in`.nerd_is.android_showcase.common.Constant.TAG_MAIN
import `in`.nerd_is.android_showcase.common.ViewModelModule
import `in`.nerd_is.android_showcase.common.lib_support.retrofit.RetrofitModule
import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

/**
 * @author Xuqiang ZHENG on 2016/9/20.
 */
@Module(includes = arrayOf(
        ViewModelModule::class,
        RetrofitModule::class
) )
class AppModule(private val thisApplication: ThisApplication) {

    @Provides
    @Singleton
    fun provideAppContext(): Context {
        return thisApplication
    }

    @Provides
    @Singleton
    fun provideThisApplication(): ThisApplication {
        return thisApplication
    }

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "db").build()
    }

    @Provides
    @Named(TAG_IO)
    @Singleton
    fun provideIoScheduler(): Scheduler {
        return Schedulers.io()
    }

    @Provides
    @Named(TAG_MAIN)
    @Singleton
    fun provideMainScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}
