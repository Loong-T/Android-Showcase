package in.nerd_is.android_showcase.main;

import dagger.Subcomponent;
import in.nerd_is.android_showcase.common.di.annotation.ActivityScope;
import in.nerd_is.android_showcase.zhihu_daily_list.ZhihuDailyListFragment;

/**
 * @author Xuqiang ZHENG on 2016/9/20.
 */
@ActivityScope
@Subcomponent(modules = MainModule.class)
public interface MainComponent {

    void inject(MainActivity mainActivity);

    void inject(ZhihuDailyListFragment zhihuDailyListFragment);

    @Subcomponent.Builder
    interface Builder {
        Builder mainModule(MainModule mainModule);

        MainComponent build();
    }
}
