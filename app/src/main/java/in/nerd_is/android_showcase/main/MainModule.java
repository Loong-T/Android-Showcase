package in.nerd_is.android_showcase.main;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Xuqiang ZHENG on 2016/9/20.
 */
@Module
public class MainModule {

    private MainContract.View view;

    public MainModule(MainContract.View view) {
        this.view = view;
    }

    @Provides
    public MainContract.View provideVeiw() {
        return view;
    }
}
