package in.nerd_is.android_showcase.main;

import in.nerd_is.android_showcase.hitokoto.model.Hitokoto;
import in.nerd_is.android_showcase.hitokoto.usecase.GetHitokoto;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * @author Xuqiang ZHENG on 2016/9/20.
 */
public class MainPresenter implements MainContract.Presenter {

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
        getHitokoto.execute(null, new DisposableSingleObserver<Hitokoto>() {
            @Override
            public void onSuccess(Hitokoto hitokoto) {
                view.showHitokoto(hitokoto);
            }

            @Override
            public void onError(Throwable e) {
                view.showError(e);
            }
        });
    }

    @Override
    public void cancelTask() {
        getHitokoto.cancel();
    }
}
