package in.nerd_is.android_showcase.hitokoto.usecase;

import javax.inject.Inject;
import javax.inject.Named;

import in.nerd_is.android_showcase.common.usecase.UseCase;
import in.nerd_is.android_showcase.hitokoto.repository.HitokotoDataSource;
import rx.Observable;
import rx.Scheduler;

import static in.nerd_is.android_showcase.common.Constant.TAG_IO;
import static in.nerd_is.android_showcase.common.Constant.TAG_MAIN;

/**
 * @author Xuqiang ZHENG on 2016/9/20.
 */
public class GetHitokoto extends UseCase<Void> {

    private HitokotoDataSource dataSource;

    @Inject
    public GetHitokoto(HitokotoDataSource dataSource,
                       @Named(TAG_IO) Scheduler backgroundScheduler,
                       @Named(TAG_MAIN) Scheduler responseScheduler) {
        super(backgroundScheduler, responseScheduler);
        this.dataSource = dataSource;
    }

    @Override
    protected Observable buildUseCaseObservable(Void param) {
        return /*dataSource.getHitokoto();*/ null;
    }
}
