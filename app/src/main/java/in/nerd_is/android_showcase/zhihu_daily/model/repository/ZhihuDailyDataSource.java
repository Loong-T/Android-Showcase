package in.nerd_is.android_showcase.zhihu_daily.model.repository;

import java.util.List;

import in.nerd_is.android_showcase.zhihu_daily.model.Date;
import rx.Observable;

/**
 * @author Xuqiang ZHENG on 2016/10/9.
 */
public interface ZhihuDailyDataSource {
    Observable<List<?>> getLatestNews();

    Observable<List<?>> getNewsBefore(Date date);
}
