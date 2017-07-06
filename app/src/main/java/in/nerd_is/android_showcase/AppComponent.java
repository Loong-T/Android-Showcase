package in.nerd_is.android_showcase;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * @author Xuqiang ZHENG on 2016/9/20.
 */
@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        ActivityBuilderModule.class,
})
public interface AppComponent {
    void inject(ThisApplication application);
}
