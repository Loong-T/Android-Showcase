package in.nerd_is.android_showcase.hitokoto.repository;

import in.nerd_is.android_showcase.hitokoto.entity.Hitokoto;
import in.nerd_is.android_showcase.hitokoto.net.HitokotoApi;
import rx.Observable;

/**
 * Created by Xuqiang ZHENG on 2016/9/18.
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
}
