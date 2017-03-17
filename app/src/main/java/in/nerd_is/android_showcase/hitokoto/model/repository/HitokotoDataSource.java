package in.nerd_is.android_showcase.hitokoto.model.repository;

import in.nerd_is.android_showcase.hitokoto.model.Hitokoto;
import rx.Observable;

/**
 * @author Xuqiang ZHENG on 2016/9/18.
 */
public interface HitokotoDataSource {
    Observable<Hitokoto> getHitokoto();

    Observable<Long> saveHitokoto(Hitokoto hitokoto);
}
