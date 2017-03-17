package in.nerd_is.android_showcase.hitokoto.model.repository.remote;

import in.nerd_is.android_showcase.hitokoto.model.Hitokoto;
import in.nerd_is.android_showcase.hitokoto.model.repository.HitokotoDataSource;
import rx.Observable;

/**
 * @author Xuqiang ZHENG on 2016/9/18.
 */
public class HitokotoRemoteRepository implements HitokotoDataSource {

    private final HitokotoApi api;

    public HitokotoRemoteRepository(HitokotoApi hitokotoApi) {
        api = hitokotoApi;
    }

    @Override
    public Observable<Hitokoto> getHitokoto() {
        return api.getHitokoto();
    }

    @Override
    public Observable<Long> saveHitokoto(Hitokoto hitokoto) {
        throw new UnsupportedOperationException("save function is not support on remote");
    }
}
