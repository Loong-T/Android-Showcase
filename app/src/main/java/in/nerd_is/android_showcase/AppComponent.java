package in.nerd_is.android_showcase;

import javax.inject.Singleton;

import dagger.Component;
import in.nerd_is.android_showcase.common.di.activity.ActivityBindingModule;
import in.nerd_is.android_showcase.hitokoto.HitokotoModule;

/**
 * Created by Xuqiang ZHENG on 2016/9/20.
 */
@Singleton
@Component(modules = { AppModule.class, ActivityBindingModule.class, HitokotoModule.class })
public interface AppComponent {
    void inject(ThisApplication application);
}
