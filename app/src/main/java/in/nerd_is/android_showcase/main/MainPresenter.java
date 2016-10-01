package in.nerd_is.android_showcase.main;

import in.nerd_is.android_showcase.hitokoto.entity.Hitokoto;
import in.nerd_is.android_showcase.hitokoto.usecase.GetHitokoto;
import rx.Subscriber;

/**
 * Created by Xuqiang ZHENG on 2016/9/20.
 */
public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private GetHitokoto getHitokoto;

    public MainPresenter(MainContract.View view, GetHitokoto getHitokoto) {
        this.view = view;
        this.getHitokoto = getHitokoto;
    }

    @Override
    public void loadHitokoto() {
        getHitokoto.execute(null, new HitokotoSubscriber());
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
