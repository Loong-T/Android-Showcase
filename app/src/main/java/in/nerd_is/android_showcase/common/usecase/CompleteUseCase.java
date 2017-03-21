package in.nerd_is.android_showcase.common.usecase;

import android.support.annotation.Nullable;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableCompletableObserver;

/**
 * @author Xuqiang ZHENG on 2017/3/18.
 */
public abstract class CompleteUseCase<P>
        extends UseCase<P, Completable, DisposableCompletableObserver> {

    public CompleteUseCase(Scheduler workerScheduler, Scheduler responseScheduler) {
        super(workerScheduler, responseScheduler);
    }

    @Override
    public void execute(@Nullable P param, DisposableCompletableObserver observer) {
        disposables.add(
                buildPublisher(param)
                        .subscribeOn(workerScheduler)
                        .observeOn(responseScheduler)
                        .subscribeWith(observer)
        );
    }
}
