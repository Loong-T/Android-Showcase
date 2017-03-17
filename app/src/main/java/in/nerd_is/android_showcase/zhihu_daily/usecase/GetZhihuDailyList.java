package in.nerd_is.android_showcase.zhihu_daily.usecase;

import android.support.annotation.Nullable;

import javax.inject.Inject;
import javax.inject.Named;

import in.nerd_is.android_showcase.common.usecase.UseCase;
import in.nerd_is.android_showcase.zhihu_daily.model.Date;
import in.nerd_is.android_showcase.zhihu_daily.model.repository.ZhihuDailyDataSource;
import rx.Observable;
import rx.Scheduler;

import static in.nerd_is.android_showcase.common.Constant.TAG_IO;
import static in.nerd_is.android_showcase.common.Constant.TAG_MAIN;

/**
 * @author Xuqiang ZHENG on 2016/11/24.
 */
public class GetZhihuDailyList extends UseCase<Date> {

    private ZhihuDailyDataSource dataSource;

    @Inject
    public GetZhihuDailyList(ZhihuDailyDataSource dataSource,
                             @Named(TAG_IO) Scheduler backgroundScheduler,
                             @Named(TAG_MAIN) Scheduler responseScheduler) {
        super(backgroundScheduler, responseScheduler);
        this.dataSource = dataSource;
    }

    @Override
    protected Observable buildUseCaseObservable(@Nullable Date param) {
        if (param == null) {
            return dataSource.getLatestNews();
        }

        return dataSource.getNewsBefore(param);
    }
}
