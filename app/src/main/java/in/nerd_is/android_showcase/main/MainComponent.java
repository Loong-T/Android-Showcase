package in.nerd_is.android_showcase.main;

import dagger.Component;
import in.nerd_is.android_showcase.ApplicationModule;

/**
 * Created by Xuqiang ZHENG on 2016/9/20.
 */
@Component(dependencies = { MainModule.class, ApplicationModule.class })
public class MainComponent {
}
