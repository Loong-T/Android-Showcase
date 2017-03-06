package in.nerd_is.android_showcase.common.net;

import com.squareup.moshi.Moshi;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import in.nerd_is.android_showcase.BuildConfig;
import in.nerd_is.android_showcase.common.moshi.MyMoshiAdapterFactory;
import in.nerd_is.android_showcase.hitokoto.moshi.DateTimeAdapter;
import in.nerd_is.android_showcase.hitokoto.net.HitokotoUrl;
import in.nerd_is.android_showcase.zhihu_daily.moshi.DateAdapter;
import in.nerd_is.android_showcase.zhihu_daily.net.ZhihuDailyUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static in.nerd_is.android_showcase.common.Constant.TAG_HITOKOTO;
import static in.nerd_is.android_showcase.common.Constant.TAG_ZHIHU_DAILY;

/**
 * @author Xuqiang ZHENG on 2016/11/25.
 */
@Module
public class RetrofitModule {

    private final OkHttpClient okHttpClient;
    private final RxJavaCallAdapterFactory rxJavaCallAdapterFactory;
    private final Moshi.Builder moshiBuilder;

    public RetrofitModule() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

        moshiBuilder = new Moshi.Builder().add(MyMoshiAdapterFactory.create());
    }

    @Provides @Named(TAG_HITOKOTO)
    public Retrofit provideHitokotoRetrofit() {
        Moshi moshi = moshiBuilder.add(new DateTimeAdapter()).build();

        return new Retrofit.Builder()
                .baseUrl(HitokotoUrl.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
    }

    @Provides @Named(TAG_ZHIHU_DAILY)
    public Retrofit provideZhihuDailyRetrofit() {
        Moshi moshi = moshiBuilder.add(new DateAdapter()).build();

        return new Retrofit.Builder()
                .baseUrl(ZhihuDailyUrl.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
    }
}
