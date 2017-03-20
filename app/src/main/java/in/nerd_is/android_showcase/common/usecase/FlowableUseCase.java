package in.nerd_is.android_showcase.common.usecase;

import android.support.annotation.Nullable;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @author Xuqiang ZHENG on 2017/3/18.
 */
public abstract class FlowableUseCase<P, R>
        extends UseCase<P, Flowable<R>, DisposableSubscriber<R>> {

    public FlowableUseCase(Scheduler workerScheduler, Scheduler responseScheduler) {
        super(workerScheduler, responseScheduler);
    }

    @Override
    public void execute(@Nullable P param, DisposableSubscriber<R> subscriber) {
        disposables.add(
                buildPublisher(param)
                        .subscribeOn(workerScheduler)
                        .observeOn(responseScheduler)
                        .subscribeWith(subscriber)
        );
    }
}
