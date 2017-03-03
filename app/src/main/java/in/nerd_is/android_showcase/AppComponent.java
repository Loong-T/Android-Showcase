package in.nerd_is.android_showcase;

import javax.inject.Singleton;

import dagger.Component;
import in.nerd_is.android_showcase.common.net.RetrofitModule;
import in.nerd_is.android_showcase.hitokoto.HitokotoModule;
import in.nerd_is.android_showcase.main.MainComponent;
import in.nerd_is.android_showcase.main.MainModule;

/**
 * @author Xuqiang ZHENG on 2016/9/20.
 */
@Singleton
@Component(modules = {
        AppModule.class,
        RetrofitModule.class,
        HitokotoModule.class
})
public interface AppComponent {
    MainComponent.Builder mainComponentBuilder();

    void inject(ThisApplication application);
}
