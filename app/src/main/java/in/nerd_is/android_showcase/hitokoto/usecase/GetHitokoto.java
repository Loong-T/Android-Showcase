package in.nerd_is.android_showcase.hitokoto.usecase;

import javax.inject.Inject;
import javax.inject.Named;

import in.nerd_is.android_showcase.common.usecase.SingleUseCase;
import in.nerd_is.android_showcase.hitokoto.model.Hitokoto;
import in.nerd_is.android_showcase.hitokoto.model.repository.HitokotoDataSource;
import io.reactivex.Scheduler;
import io.reactivex.Single;

import static in.nerd_is.android_showcase.common.Constant.TAG_IO;
import static in.nerd_is.android_showcase.common.Constant.TAG_LOCAL;
import static in.nerd_is.android_showcase.common.Constant.TAG_MAIN;
import static in.nerd_is.android_showcase.common.Constant.TAG_REMOTE;

/**
 * @author Xuqiang ZHENG on 2016/9/20.
 */
public class GetHitokoto extends SingleUseCase<Void, Hitokoto> {

    private HitokotoDataSource localDataSource;
    private HitokotoDataSource remoteDataSource;

    @Inject
    public GetHitokoto(@Named(TAG_LOCAL) HitokotoDataSource localSource,
                       @Named(TAG_REMOTE) HitokotoDataSource remoteSource,
                       @Named(TAG_IO) Scheduler backgroundScheduler,
                       @Named(TAG_MAIN) Scheduler responseScheduler) {
        super(backgroundScheduler, responseScheduler);
        localDataSource = localSource;
        remoteDataSource = remoteSource;
    }

    @Override
    protected Single<Hitokoto> buildPublisher(Void param) {
        return remoteDataSource.getHitokoto()
                .doOnSuccess(hitokoto -> localDataSource.saveHitokoto(hitokoto));
    }
}
