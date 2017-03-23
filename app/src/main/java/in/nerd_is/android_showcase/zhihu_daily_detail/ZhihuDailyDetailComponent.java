package in.nerd_is.android_showcase.zhihu_daily_detail;

import dagger.Subcomponent;
import in.nerd_is.android_showcase.common.di.annotation.ActivityScope;
import in.nerd_is.android_showcase.zhihu_daily.ZhihuDailyModule;

/**
 * @author Xuqiang ZHENG on 2017/3/21.
 */
@ActivityScope
@Subcomponent(modules = {
        ZhihuDailyDetailActivityModule.class,
        ZhihuDailyModule.class
})
public interface ZhihuDailyDetailComponent {
    void inject(ZhihuDailyDetailActivity activity);
    
    @Subcomponent.Builder
    interface Builder {
        Builder detailActivityModule(ZhihuDailyDetailActivityModule module);

        ZhihuDailyDetailComponent build();
    }
}
