package in.nerd_is.android_showcase.zhihu_daily_list.repository;

import in.nerd_is.android_showcase.zhihu_daily_list.entity.LatestNews;
import rx.Observable;

/**
 * Created by Xuqiang ZHENG on 2016/10/9.
 */
public interface ZhihuDailyDataSource {
    Observable<LatestNews> getLatestNews();
}
