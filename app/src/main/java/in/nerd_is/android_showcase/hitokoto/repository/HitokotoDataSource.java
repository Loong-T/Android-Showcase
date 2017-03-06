package in.nerd_is.android_showcase.hitokoto.repository;

import in.nerd_is.android_showcase.hitokoto.entity.Hitokoto;
import rx.Observable;

/**
 * @author Xuqiang ZHENG on 2016/9/18.
 */
public interface HitokotoDataSource {
    Observable<Hitokoto> getHitokoto();
}
