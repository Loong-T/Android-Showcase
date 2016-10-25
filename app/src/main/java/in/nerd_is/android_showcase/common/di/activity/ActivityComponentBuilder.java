package in.nerd_is.android_showcase.common.di.activity;

/**
 * Created by Xuqiang ZHENG on 2016/10/24.
 */
public interface ActivityComponentBuilder<M extends ActivityModule, C extends ActivityComponent> {
    ActivityComponentBuilder<M, C> activityModule(M module);
    C build();
}
