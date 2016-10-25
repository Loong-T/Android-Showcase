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
    private Observable.Transformer transformer;

    public MainModule(MainActivity activity,
                      Observable.Transformer transformer) {
        super(activity);
        this.view = activity;
        this.transformer = transformer;
    }

    @Provides @ActivityScope
    MainContract.View provideView() {
        return view;
    }

    @Provides @ActivityScope
    MainPresenter providePresenter(GetHitokoto getHitokoto) {
        return new MainPresenter(view, getHitokoto);
    }

    @Provides @ActivityScope
    Observable.Transformer provideTransformer() {
        return transformer;
    }
}
