package in.nerd_is.android_showcase.main;

import in.nerd_is.android_showcase.hitokoto.model.Hitokoto;
import in.nerd_is.android_showcase.hitokoto.usecase.GetHitokoto;
import rx.Subscriber;

/**
 * @author Xuqiang ZHENG on 2016/9/20.
 */
public class MainPresenter implements MainContract.UserActionListener {

    private MainContract.View view;
    private GetHitokoto getHitokoto;

    public MainPresenter(GetHitokoto getHitokoto) {
        this.getHitokoto = getHitokoto;
    }

    @Override
    public void setView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void loadHitokoto() {
        getHitokoto.execute(null, view.lifecycleTransformer(), new HitokotoSubscriber());
    }

    private class HitokotoSubscriber extends Subscriber<Hitokoto> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            view.snackbar(e.getLocalizedMessage());
        }

        @Override
        public void onNext(Hitokoto hitokoto) {
            view.showHitokoto(hitokoto);
        }
    }
}
