package in.nerd_is.android_showcase.hitokoto.net;

import retrofit2.Retrofit;

/**
 * Created by Xuqiang ZHENG on 2016/9/18.
 */
public class HitokotoRetrofit {

    private static Retrofit mRetrofit;

    static {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(HitokotoUrl.BASE_URL)
                .build();
    }

    private HitokotoRetrofit() {}
}
