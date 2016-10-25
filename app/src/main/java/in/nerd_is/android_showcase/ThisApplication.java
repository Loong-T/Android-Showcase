package in.nerd_is.android_showcase;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.Map;

import javax.inject.Inject;

import in.nerd_is.android_showcase.common.di.activity.ActivityComponentBuilder;
import in.nerd_is.android_showcase.common.di.activity.HasActivitySubcomponentBuilders;
import in.nerd_is.android_showcase.hitokoto.HitokotoModule;

/**
 * Created by Xuqiang ZHENG on 2016/9/20.
 */
public class ThisApplication extends Application implements HasActivitySubcomponentBuilders {

    public static ThisApplication INSTANCE;

    @Inject
    Map<Class<? extends Activity>, ActivityComponentBuilder> activityComponentBuilders;

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .hitokotoModule(new HitokotoModule())
                .build();

        appComponent.inject(this);
    }

    @Override
    public ActivityComponentBuilder get(Class<? extends Activity> activityClass) {
        return activityComponentBuilders.get(activityClass);
    }
}
