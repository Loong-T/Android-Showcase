package `in`.nerd_is.android_showcase.hitokoto.model.repository.local

import `in`.nerd_is.android_showcase.AppDatabase
import `in`.nerd_is.android_showcase.hitokoto.model.Hitokoto
import `in`.nerd_is.android_showcase.hitokoto.model.repository.HitokotoDataSource
import io.reactivex.Single

/**
 * @author Xuqiang ZHENG on 2017/3/8.
 */
class HitokotoLocalRepository(db: AppDatabase) : HitokotoDataSource {

    private val dao = db.hitokotoDao()

    override fun getHitokoto(): Single<Hitokoto> {
        return dao.selectRandomHitokoto().singleOrError()
    }

    override fun saveHitokoto(hitokoto: Hitokoto): Single<Long> {
        return Single.just(dao.insertHitokoto(hitokoto))
    }
}
