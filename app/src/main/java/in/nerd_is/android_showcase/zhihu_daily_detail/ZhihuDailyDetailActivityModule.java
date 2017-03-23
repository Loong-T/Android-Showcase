package in.nerd_is.android_showcase.zhihu_daily_detail;

import dagger.Module;
import dagger.Provides;
import in.nerd_is.android_showcase.common.di.annotation.ActivityScope;
import in.nerd_is.android_showcase.zhihu_daily.usecase.GetZhihuDailyDetail;

/**
 * @author Xuqiang ZHENG on 2017/3/21.
 */
@Module
public class ZhihuDailyDetailActivityModule {
    @Provides
    @ActivityScope
    ZhihuDailyDetailPresenter providePresenter(GetZhihuDailyDetail getZhihuDailyDetail) {
        return new ZhihuDailyDetailPresenter(getZhihuDailyDetail);
    }
}
