package in.nerd_is.android_showcase.hitokoto.net;

import in.nerd_is.android_showcase.hitokoto.entity.Hitokoto;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Xuqiang ZHENG on 2016/9/19.
 */
public class HitokotoApi {

    private Api api = HitokotoRetrofit.create(Api.class);

    public Observable<Hitokoto> getHitokoto() {
        return api.getHitokoto();
    }

    private interface Api {
        @GET(HitokotoUrl.ROOT)
        Observable<Hitokoto> getHitokoto();
    }
}
