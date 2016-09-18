package in.nerd_is.android_showcase.common.usecase;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public abstract class UseCase<T> {

    private Scheduler mBackgroundScheduler;
    private Scheduler mResponseScheduler;
    private Observable.Transformer mLifecycleTransformer;
    private Subscription subscription = Subscriptions.empty();

    public UseCase(@NonNull Scheduler backgroundScheduler,
                   @NonNull Scheduler responseScheduler,
                   @NonNull Observable.Transformer lifecycleTransformer) {
        mBackgroundScheduler = backgroundScheduler;
        mResponseScheduler = responseScheduler;
        mLifecycleTransformer = lifecycleTransformer;
    }

    protected abstract Observable buildUseCaseObservable(T param);

    @SuppressWarnings("unchecked")
    public void execute(T param, Subscriber subscriber) {
        subscription = buildUseCaseObservable(param)
                .subscribeOn(mBackgroundScheduler)
                .observeOn(mResponseScheduler)
                .compose(mLifecycleTransformer)
                .subscribe(subscriber);
    }

    public void  unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
