package in.nerd_is.android_showcase.main;

import dagger.Subcomponent;
import in.nerd_is.android_showcase.common.annotation.di.ActivityScope;

/**
 * Created by Xuqiang ZHENG on 2016/9/20.
 */
@ActivityScope
@Subcomponent(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity activity);

    @Subcomponent.Builder
    interface Builder {
        Builder mainModule(MainModule mainModule);

        MainComponent build();
    }
}
