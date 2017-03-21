package in.nerd_is.android_showcase.common.usecase;

import android.support.annotation.Nullable;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;

/**
 * @author Xuqiang ZHENG on 2017/3/18.
 */
public abstract class ObservableUseCase<P, R>
        extends UseCase<P, Observable<R>, DisposableObserver<R>> {

    public ObservableUseCase(Scheduler workerScheduler, Scheduler responseScheduler) {
        super(workerScheduler, responseScheduler);
    }

    @Override
    public void execute(@Nullable P param, DisposableObserver<R> observer) {
        DisposableObserver disposableObserver = buildPublisher(param)
                .subscribeOn(workerScheduler)
                .observeOn(responseScheduler)
                .subscribeWith(observer);
        disposables.add(disposableObserver);
    }
}
