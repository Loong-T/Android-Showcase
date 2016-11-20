package in.nerd_is.android_showcase.zhihu_daily_list.net;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by loong on 2016/10/7.
 */
public interface ZhihuDailyRetrofit {
        Retrofit INSTANCE = new Retrofit.Builder()
                .baseUrl(ZhihuDailyUrl.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
}
