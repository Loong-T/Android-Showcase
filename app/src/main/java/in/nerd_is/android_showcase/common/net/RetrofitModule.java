package in.nerd_is.android_showcase.common.net;

import com.squareup.moshi.Moshi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.nerd_is.android_showcase.common.di.annotation.ActivityScope;
import in.nerd_is.android_showcase.hitokoto.net.HitokotoUrl;
import in.nerd_is.android_showcase.zhihu_daily.moshi.LocalDateAdapter;
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
    private RxJavaCallAdapterFactory rxJavaCallAdapterFactory;

    public RetrofitModule() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
    }

    @Provides @Named(TAG_HITOKOTO)
    public Retrofit provideHitokotoRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(HitokotoUrl.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
    }

    @Provides @Named(TAG_ZHIHU_DAILY)
    public Retrofit provideZhihuDailyRetrofit() {
        Moshi moshi = new Moshi.Builder().add(new LocalDateAdapter()).build();
        return new Retrofit.Builder()
                .baseUrl(ZhihuDailyUrl.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
    }
}
