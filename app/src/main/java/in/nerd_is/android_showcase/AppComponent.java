package in.nerd_is.android_showcase;

import javax.inject.Singleton;

import dagger.Component;
import in.nerd_is.android_showcase.hitokoto.HitokotoModule;
import in.nerd_is.android_showcase.main.MainComponent;
import in.nerd_is.android_showcase.main.MainModule;

/**
 * Created by Xuqiang ZHENG on 2016/9/20.
 */
@Singleton
@Component(modules = { AppModule.class, HitokotoModule.class })
public interface AppComponent {
    MainComponent.Builder mainComponentBuilder();
}
