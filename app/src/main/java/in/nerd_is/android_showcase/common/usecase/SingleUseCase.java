package in.nerd_is.android_showcase.common.usecase;

import android.support.annotation.Nullable;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * @author Xuqiang ZHENG on 2017/3/18.
 */
public abstract class SingleUseCase<P, R>
        extends UseCase<P, Single<R>, DisposableSingleObserver<R>> {

    public SingleUseCase(Scheduler workerScheduler, Scheduler responseScheduler) {
        super(workerScheduler, responseScheduler);
    }

    @Override
    public void execute(@Nullable P param, DisposableSingleObserver<R> observer) {
        disposables.add(
                buildPublisher(param)
                        .subscribeOn(workerScheduler)
                        .observeOn(responseScheduler)
                        .subscribeWith(observer)
        );
    }
}
