package in.nerd_is.android_showcase.main;

import dagger.Module;
import dagger.Provides;
import in.nerd_is.android_showcase.common.di.activity.ActivityModule;
import in.nerd_is.android_showcase.common.di.annotation.ActivityScope;
import in.nerd_is.android_showcase.hitokoto.usecase.GetHitokoto;
import rx.Observable;

/**
 * Created by Xuqiang ZHENG on 2016/9/20.
 */
@Module
public class MainModule extends ActivityModule<MainActivity> {

    private MainContract.View view;

    public MainModule(MainActivity activity) {
        super(activity);
        this.view = activity;
    }

    @Provides @ActivityScope
    MainContract.View provideView() {
        return view;
    }

    @Provides @ActivityScope
    MainPresenter providePresenter(GetHitokoto getHitokoto) {
        return new MainPresenter(getHitokoto);
    }
}
