package in.nerd_is.android_showcase.zhihu_daily;

import dagger.Module;
import dagger.Provides;
import in.nerd_is.android_showcase.zhihu_daily.model.repository.remote.ZhihuDailyApi;
import in.nerd_is.android_showcase.zhihu_daily.model.repository.ZhihuDailyDataSource;
import in.nerd_is.android_showcase.zhihu_daily.model.repository.remote.ZhihuDailyRemoteRepository;

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
