package in.nerd_is.android_showcase.zhihu_daily.model.repository;

import java.util.List;

import in.nerd_is.android_showcase.zhihu_daily.model.Date;
import in.nerd_is.android_showcase.zhihu_daily.model.StoryDetail;
import io.reactivex.Single;

/**
 * @author Xuqiang ZHENG on 2016/10/9.
 */
public interface ZhihuDailyDataSource {
    Single<List<?>> getLatestNews();

    Single<List<?>> getNewsBefore(Date date);

    Single<StoryDetail> getNewsDetail(long id);
}
