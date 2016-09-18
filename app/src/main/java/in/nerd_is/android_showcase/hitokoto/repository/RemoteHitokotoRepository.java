package in.nerd_is.android_showcase.hitokoto.repository;

import in.nerd_is.android_showcase.hitokoto.entity.Hitokoto;
import rx.Observable;

/**
 * Created by Xuqiang ZHENG on 2016/9/18.
 */
public class RemoteHitokotoRepository implements HitokotoDataSource {
    @Override
    public Observable<Hitokoto> getHitokoto() {
        return null;
    }
}
