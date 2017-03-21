package in.nerd_is.android_showcase.common.usecase;

import android.support.annotation.Nullable;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;


/**
 * @author Xuqiang ZHENG on 2016/9/20.
 */
public abstract class UseCase<R, P, O> {

    protected Scheduler workerScheduler;
    protected Scheduler responseScheduler;

    protected CompositeDisposable disposables = new CompositeDisposable();

    public UseCase(Scheduler workerScheduler,
                             Scheduler responseScheduler) {
        this.workerScheduler = workerScheduler;
        this.responseScheduler = responseScheduler;
    }

    protected abstract P buildPublisher(R param);

    public abstract void execute(@Nullable R param, O observer);

    public void cancel() {
        disposables.clear();
    }
}