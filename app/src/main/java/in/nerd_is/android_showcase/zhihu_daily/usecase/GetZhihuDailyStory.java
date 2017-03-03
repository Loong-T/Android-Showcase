package in.nerd_is.android_showcase.zhihu_daily.usecase;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import in.nerd_is.android_showcase.common.usecase.UseCase;
import in.nerd_is.android_showcase.zhihu_daily.entity.Date;
import in.nerd_is.android_showcase.zhihu_daily.repository.ZhihuDailyDataSource;
import rx.Observable;
import rx.Scheduler;

import static in.nerd_is.android_showcase.common.Constant.TAG_IO;
import static in.nerd_is.android_showcase.common.Constant.TAG_MAIN;

/**
 * @author Xuqiang ZHENG on 2016/11/24.
 */
public class GetZhihuDailyStory extends UseCase<Void> {

    private ZhihuDailyDataSource dataSource;

    @Inject
    public GetZhihuDailyStory(ZhihuDailyDataSource dataSource,
                              @Named(TAG_IO) Scheduler backgroundScheduler,
                              @Named(TAG_MAIN) Scheduler responseScheduler) {
        super(backgroundScheduler, responseScheduler);
        this.dataSource = dataSource;
    }

    @Override
    protected Observable buildUseCaseObservable(Void param) {
        return dataSource.getLatestNews()
                .map(news -> {
                    final ArrayList<Object> list = new ArrayList<>(news.stories.size() + 1);
                    list.add(new Date(news.date));
                    list.addAll(news.stories);
                    return list;
                });
    }
}
