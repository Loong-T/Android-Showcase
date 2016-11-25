package in.nerd_is.android_showcase.zhihu_daily;

import dagger.Module;
import dagger.Provides;
import in.nerd_is.android_showcase.zhihu_daily.net.ZhihuDailyApi;
import in.nerd_is.android_showcase.zhihu_daily.repository.ZhihuDailyDataSource;
import in.nerd_is.android_showcase.zhihu_daily.repository.ZhihuDailyRemoteRepository;

/**
 * Created by Xuqiang ZHENG on 2016/11/24.
 */
@Module
public class ZhihuDailyModule {
    @Provides
    ZhihuDailyDataSource providesZhihuDailyDataSource(ZhihuDailyApi zhihuDailyApi) {
        return new ZhihuDailyRemoteRepository(zhihuDailyApi);
    }
}
