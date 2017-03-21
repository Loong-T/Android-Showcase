package in.nerd_is.android_showcase.hitokoto.model.repository.remote;

import javax.inject.Named;

import in.nerd_is.android_showcase.common.lib_support.retrofit.RetrofitUtils;
import in.nerd_is.android_showcase.hitokoto.model.Hitokoto;
import in.nerd_is.android_showcase.hitokoto.model.repository.HitokotoDataSource;
import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.http.GET;

import static in.nerd_is.android_showcase.common.Constant.TAG_HITOKOTO;

/**
 * @author Xuqiang ZHENG on 2016/9/18.
 */
public class HitokotoRemoteRepository implements HitokotoDataSource {

    private final Api api;

    public HitokotoRemoteRepository(@Named(TAG_HITOKOTO) Retrofit hitokotoRetrofit) {
        api = RetrofitUtils.create(hitokotoRetrofit, Api.class);
    }

    @Override
    public Single<Hitokoto> getHitokoto() {
        return api.getHitokoto();
    }

    @Override
    public Single<Long> saveHitokoto(Hitokoto hitokoto) {
        throw new UnsupportedOperationException("save function is not support on remote");
    }

    private interface Api {
        @GET(HitokotoUrl.ROOT)
        Single<Hitokoto> getHitokoto();
    }
}
