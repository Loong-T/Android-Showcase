package in.nerd_is.android_showcase.common.net;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.nerd_is.android_showcase.common.di.annotation.ActivityScope;
import in.nerd_is.android_showcase.hitokoto.net.HitokotoUrl;
import in.nerd_is.android_showcase.zhihu_daily.net.ZhihuDailyUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static in.nerd_is.android_showcase.common.Constant.TAG_HITOKOTO;
import static in.nerd_is.android_showcase.common.Constant.TAG_ZHIHU_DAILY;

/**
 * Created by Xuqiang ZHENG on 2016/11/25.
 */
@Module
public class RetrofitModule {

    private final OkHttpClient okHttpClient;
    private MoshiConverterFactory moshiConverterFactory;
    private RxJavaCallAdapterFactory rxJavaCallAdapterFactory;

    public RetrofitModule() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        moshiConverterFactory = MoshiConverterFactory.create();

        rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
    }

    @Provides @Named(TAG_HITOKOTO)
    public Retrofit provideHitokotoRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(HitokotoUrl.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(moshiConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
    }

    @Provides @Named(TAG_ZHIHU_DAILY)
    public Retrofit provideZhihuDailyRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(ZhihuDailyUrl.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(moshiConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
    }
}
