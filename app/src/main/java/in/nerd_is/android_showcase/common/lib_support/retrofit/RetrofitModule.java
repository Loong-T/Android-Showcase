package in.nerd_is.android_showcase.common.lib_support.retrofit;

import com.squareup.moshi.Moshi;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import in.nerd_is.android_showcase.DebugOnly;
import in.nerd_is.android_showcase.common.lib_support.moshi.MyMoshiAdapterFactory;
import in.nerd_is.android_showcase.hitokoto.model.repository.remote.HitokotoUrl;
import in.nerd_is.android_showcase.hitokoto.moshi.DateTimeAdapter;
import in.nerd_is.android_showcase.zhihu_daily.model.repository.remote.ZhihuDailyUrl;
import in.nerd_is.android_showcase.zhihu_daily.moshi.DateAdapter;
import okhttp3.OkHttpClient;
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
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpClient = DebugOnly.addStethoInterceptor(okHttpBuilder).build();

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
