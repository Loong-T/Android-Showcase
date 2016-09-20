package in.nerd_is.android_showcase.main;

/**
 * Created by Xuqiang ZHENG on 2016/9/20.
 */
public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void loadHitokoto() {

    }
}
