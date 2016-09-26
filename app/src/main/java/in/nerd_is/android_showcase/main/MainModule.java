package in.nerd_is.android_showcase.main;

import dagger.Module;
import dagger.Provides;
import in.nerd_is.android_showcase.common.annotation.di.ActivityScope;

/**
 * Created by Xuqiang ZHENG on 2016/9/20.
 */
@Module
public class MainModule {

    private MainContract.View view;

    public MainModule(MainContract.View view) {
        this.view = view;
    }

    @Provides @ActivityScope
    public MainContract.View provideView() {
        return view;
    }

    @Provides @ActivityScope MainPresenter providePresenter() {
        return new MainPresenter(view);
    }
}
