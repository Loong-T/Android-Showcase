package in.nerd_is.android_showcase.hitokoto.net;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by Xuqiang ZHENG on 2016/10/9.
 */
public interface HitokotoRetrofit {
    Retrofit INSTANCE = new Retrofit.Builder()
            .baseUrl(HitokotoUrl.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();
}
