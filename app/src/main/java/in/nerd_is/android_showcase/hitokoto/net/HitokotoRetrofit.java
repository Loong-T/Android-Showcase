package in.nerd_is.android_showcase.hitokoto.net;

import in.nerd_is.android_showcase.common.net.BaseRetrofit;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by Xuqiang ZHENG on 2016/9/18.
 */
public class HitokotoRetrofit extends BaseRetrofit {

    private HitokotoRetrofit() {}

    @Override
    protected void buildRetrofit() {
        RETROFIT = new Retrofit.Builder()
                .baseUrl(HitokotoUrl.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
