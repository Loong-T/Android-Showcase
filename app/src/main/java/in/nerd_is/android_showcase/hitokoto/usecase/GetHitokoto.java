package in.nerd_is.android_showcase.hitokoto.usecase;

import javax.inject.Inject;
import javax.inject.Named;

import in.nerd_is.android_showcase.common.usecase.UseCase;
import in.nerd_is.android_showcase.hitokoto.model.repository.HitokotoDataSource;
import rx.Observable;
import rx.Scheduler;

import static in.nerd_is.android_showcase.common.Constant.TAG_IO;
import static in.nerd_is.android_showcase.common.Constant.TAG_LOCAL;
import static in.nerd_is.android_showcase.common.Constant.TAG_MAIN;
import static in.nerd_is.android_showcase.common.Constant.TAG_REMOTE;

/**
 * @author Xuqiang ZHENG on 2016/9/20.
 */
public class GetHitokoto extends UseCase<Void> {

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
    protected Observable buildUseCaseObservable(Void param) {
        return remoteDataSource.getHitokoto()
                .doOnNext(hitokoto -> localDataSource.saveHitokoto(hitokoto));
    }
}
