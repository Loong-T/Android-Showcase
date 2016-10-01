package in.nerd_is.android_showcase.main;

import dagger.Module;
import dagger.Provides;
import in.nerd_is.android_showcase.common.annotation.di.ActivityScope;
import in.nerd_is.android_showcase.hitokoto.usecase.GetHitokoto;
import rx.Observable;

/**
 * Created by Xuqiang ZHENG on 2016/9/20.
 */
@Module
public class MainModule {

    private MainContract.View view;
    private Observable.Transformer transformer;

    public MainModule(MainContract.View view,
                      Observable.Transformer transformer) {
        this.view = view;
        this.transformer = transformer;
    }

    @Provides @ActivityScope
    public MainContract.View provideView() {
        return view;
    }

    @Provides @ActivityScope MainPresenter providePresenter(GetHitokoto getHitokoto) {
        return new MainPresenter(view, getHitokoto);
    }

    @Provides @ActivityScope
    public Observable.Transformer provideTransformer() {
        return transformer;
    }
}
