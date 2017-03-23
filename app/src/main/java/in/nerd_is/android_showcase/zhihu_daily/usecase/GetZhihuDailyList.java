package in.nerd_is.android_showcase.zhihu_daily.usecase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import in.nerd_is.android_showcase.common.usecase.SingleUseCase;
import in.nerd_is.android_showcase.zhihu_daily.model.Date;
import in.nerd_is.android_showcase.zhihu_daily.model.repository.ZhihuDailyDataSource;
import io.reactivex.Scheduler;
import io.reactivex.Single;

import static in.nerd_is.android_showcase.common.Constant.TAG_IO;
import static in.nerd_is.android_showcase.common.Constant.TAG_MAIN;

/**
 * @author Xuqiang ZHENG on 2016/11/24.
 */
public class GetZhihuDailyList extends SingleUseCase<Date, List<?>> {

    private final ZhihuDailyDataSource dataSource;

    @Inject
    public GetZhihuDailyList(ZhihuDailyDataSource dataSource,
                             @Named(TAG_IO) Scheduler backgroundScheduler,
                             @Named(TAG_MAIN) Scheduler responseScheduler) {
        super(backgroundScheduler, responseScheduler);
        this.dataSource = dataSource;
    }

    @Override
    protected Single<List<?>> buildPublisher(Date param) {
        if (param == null) {
            return dataSource.getLatestNews();
        }

        return dataSource.getNewsBefore(param);
    }
}
