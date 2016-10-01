package in.nerd_is.android_showcase.hitokoto.net;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by Xuqiang ZHENG on 2016/9/18.
 */
public class HitokotoRetrofit {

    private static Retrofit mRetrofit;

    static {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(HitokotoUrl.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private HitokotoRetrofit() {}

    public static <T> T create(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }
}
