package in.nerd_is.android_showcase.common.usecase;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class UseCase<T> {

    private Scheduler backgroundScheduler;
    private Scheduler responseScheduler;
    private CompositeSubscription subscriptions = new CompositeSubscription();

    public UseCase(Scheduler backgroundScheduler,
                   Scheduler responseScheduler) {
        this.backgroundScheduler = backgroundScheduler;
        this.responseScheduler = responseScheduler;
    }

    protected abstract Observable buildUseCaseObservable(T param);

    @SuppressWarnings("unchecked")
    public void execute(T param,
                        Observable.Transformer lifecycleTransformer,
                        Subscriber subscriber) {
        final Subscription subscription = buildUseCaseObservable(param)
                .subscribeOn(backgroundScheduler)
                .observeOn(responseScheduler)
                .compose(lifecycleTransformer)
                .subscribe(subscriber);

        subscriptions.add(subscription);
    }

    public void  unsubscribe() {
        if (!subscriptions.isUnsubscribed()) {
            subscriptions.unsubscribe();
        }
    }
}