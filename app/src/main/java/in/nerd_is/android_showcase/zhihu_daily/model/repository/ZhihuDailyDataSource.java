package in.nerd_is.android_showcase.zhihu_daily.model.repository;

import in.nerd_is.android_showcase.zhihu_daily.model.LatestNews;
import rx.Observable;

/**
 * Created by Xuqiang ZHENG on 2016/10/9.
 */
public interface ZhihuDailyDataSource {
    Observable<LatestNews> getLatestNews();
}
