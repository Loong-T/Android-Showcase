package in.nerd_is.android_showcase.hitokoto.model.repository;

import in.nerd_is.android_showcase.hitokoto.model.Hitokoto;
import io.reactivex.Single;

/**
 * @author Xuqiang ZHENG on 2016/9/18.
 */
public interface HitokotoDataSource {
    Single<Hitokoto> getHitokoto();

    Single<Long> saveHitokoto(Hitokoto hitokoto);
}
