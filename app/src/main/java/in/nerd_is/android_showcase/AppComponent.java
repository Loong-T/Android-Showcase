package in.nerd_is.android_showcase;

import javax.inject.Singleton;

import dagger.Component;
import in.nerd_is.android_showcase.common.lib_support.retrofit.RetrofitModule;
import in.nerd_is.android_showcase.main.MainActivityComponent;

/**
 * @author Xuqiang ZHENG on 2016/9/20.
 */
@Singleton
@Component(modules = {
        AppModule.class,
        RetrofitModule.class,
})
public interface AppComponent {
    MainActivityComponent.Builder mainComponentBuilder();

    void inject(ThisApplication application);
}
