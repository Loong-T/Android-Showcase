package in.nerd_is.android_showcase.main;

import dagger.Subcomponent;
import in.nerd_is.android_showcase.common.di.activity.ActivityComponent;
import in.nerd_is.android_showcase.common.di.activity.ActivityComponentBuilder;
import in.nerd_is.android_showcase.common.di.annotation.ActivityScope;

/**
 * Created by Xuqiang ZHENG on 2016/9/20.
 */
@ActivityScope
@Subcomponent(modules = MainModule.class)
public interface MainComponent extends ActivityComponent<MainActivity> {
    @Subcomponent.Builder
    interface Builder extends ActivityComponentBuilder<MainModule, MainComponent> {
    }
}
