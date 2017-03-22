package in.nerd_is.android_showcase.zhihu_daily_detail;

import dagger.Module;
import dagger.Provides;

/**
 * @author Xuqiang ZHENG on 2017/3/21.
 */
@Module
public class ZhihuDailyDetailActivityModule {
    @Provides
    ZhihuDailyDetailPresenter providePresenter() {
        return new ZhihuDailyDetailPresenter();
    }
}
