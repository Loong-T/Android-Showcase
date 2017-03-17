package in.nerd_is.android_showcase.zhihu_daily;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import in.nerd_is.android_showcase.zhihu_daily.model.repository.ZhihuDailyDataSource;
import in.nerd_is.android_showcase.zhihu_daily.model.repository.remote.ZhihuDailyRemoteRepository;
import retrofit2.Retrofit;

import static in.nerd_is.android_showcase.common.Constant.TAG_ZHIHU_DAILY;

/**
 * @author Xuqiang ZHENG on 2016/11/24.
 */
@Module
public class ZhihuDailyModule {
    @Provides
    ZhihuDailyDataSource providesZhihuDailyDataSource(
            @Named(TAG_ZHIHU_DAILY) Retrofit zhihuDailyRetrofit) {
        return new ZhihuDailyRemoteRepository(zhihuDailyRetrofit);
    }
}
