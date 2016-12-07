package in.nerd_is.android_showcase.common.di.activity;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import in.nerd_is.android_showcase.common.di.annotation.ActivityKey;
import in.nerd_is.android_showcase.main.MainActivity;
import in.nerd_is.android_showcase.main.MainComponent;

@Module(subcomponents = {
        MainComponent.class,
})
public abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    public abstract ActivityComponentBuilder mainActivityComponentBuilder(MainComponent.Builder builder);
}