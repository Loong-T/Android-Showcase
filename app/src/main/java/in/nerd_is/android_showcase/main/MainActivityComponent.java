package in.nerd_is.android_showcase.main;

import dagger.Subcomponent;
import in.nerd_is.android_showcase.common.di.annotation.ActivityScope;
import in.nerd_is.android_showcase.hitokoto.HitokotoModule;
import in.nerd_is.android_showcase.zhihu_daily.ZhihuDailyModule;
import in.nerd_is.android_showcase.zhihu_daily_list.ZhihuDailyListFragment;

/**
 * @author Xuqiang ZHENG on 2016/9/20.
 */
@ActivityScope
@Subcomponent(modules = {
        MainActivityModule.class,
        HitokotoModule.class,
        ZhihuDailyModule.class,
})
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(ZhihuDailyListFragment zhihuDailyListFragment);

    @Subcomponent.Builder
    interface Builder {
        Builder mainModule(MainActivityModule mainModule);

        Builder hitokotoModule(HitokotoModule hitokotoModule);

        MainActivityComponent build();
    }
}