package in.nerd_is.android_showcase.hitokoto.net;

import javax.inject.Inject;
import javax.inject.Named;

import in.nerd_is.android_showcase.common.net.RetrofitUtils;
import in.nerd_is.android_showcase.hitokoto.entity.Hitokoto;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import rx.Observable;

import static in.nerd_is.android_showcase.common.Constant.TAG_HITOKOTO;

/**
 * Created by Xuqiang ZHENG on 2016/9/19.
 */
public class HitokotoApi {

    private final Api api;

    @Inject
    public HitokotoApi(@Named(TAG_HITOKOTO) Retrofit hitokotoRetrofit) {
        api = RetrofitUtils.create(hitokotoRetrofit, Api.class);
    }

    public Observable<Hitokoto> getHitokoto() {
        return api.getHitokoto();
    }

    private interface Api {
        @GET(HitokotoUrl.ROOT)
        Observable<Hitokoto> getHitokoto();
    }
}
