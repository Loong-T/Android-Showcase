package in.nerd_is.android_showcase.common.usecase;

import android.support.annotation.Nullable;

import io.reactivex.Maybe;
import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableMaybeObserver;

/**
 * @author Xuqiang ZHENG on 2017/3/18.
 */
public abstract class MaybeUseCase<P, R>
        extends UseCase<P, Maybe<R>, DisposableMaybeObserver<R>> {

    public MaybeUseCase(Scheduler workerScheduler, Scheduler responseScheduler) {
        super(workerScheduler, responseScheduler);
    }

    @Override
    public void execute(@Nullable P param, DisposableMaybeObserver<R> observer) {
        disposables.add(
                buildPublisher(param)
                        .subscribeOn(workerScheduler)
                        .observeOn(responseScheduler)
                        .subscribeWith(observer)
        );
    }
}
