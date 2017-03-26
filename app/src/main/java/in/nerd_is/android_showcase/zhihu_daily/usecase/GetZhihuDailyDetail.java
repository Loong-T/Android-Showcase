package in.nerd_is.android_showcase.zhihu_daily.usecase;

import javax.inject.Inject;
import javax.inject.Named;

import in.nerd_is.android_showcase.common.usecase.SingleUseCase;
import in.nerd_is.android_showcase.zhihu_daily.model.StoryDetail;
import in.nerd_is.android_showcase.zhihu_daily.model.repository.ZhihuDailyDataSource;
import io.reactivex.Scheduler;
import io.reactivex.Single;

import static in.nerd_is.android_showcase.common.Constant.TAG_IO;
import static in.nerd_is.android_showcase.common.Constant.TAG_MAIN;

/**
 * @author Xuqiang ZHENG on 2017/3/21.
 */
public class GetZhihuDailyDetail extends SingleUseCase<Long, StoryDetail> {

    private final ZhihuDailyDataSource dataSource;

    @Inject
    public GetZhihuDailyDetail(ZhihuDailyDataSource dataSource,
                               @Named(TAG_IO) Scheduler workerScheduler,
                               @Named(TAG_MAIN) Scheduler responseScheduler) {
        super(workerScheduler, responseScheduler);
        this.dataSource = dataSource;
    }

    @Override
    protected Single<StoryDetail> buildPublisher(Long param) {
        return dataSource.getNewsDetail(param);
    }
}
