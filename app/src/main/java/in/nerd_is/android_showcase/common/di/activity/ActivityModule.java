package in.nerd_is.android_showcase.common.di.activity;

import dagger.Module;
import dagger.Provides;
import in.nerd_is.android_showcase.common.di.annotation.ActivityScope;

@Module
public abstract class ActivityModule<T> {

    protected final T activity;

    public ActivityModule(T activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public T provideActivity() {
        return activity;
    }
}