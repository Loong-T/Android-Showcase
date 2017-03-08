package in.nerd_is.android_showcase.zhihu_daily.model.repository.remote;

import in.nerd_is.android_showcase.zhihu_daily.model.LatestNews;
import in.nerd_is.android_showcase.zhihu_daily.model.repository.ZhihuDailyDataSource;
import rx.Observable;

/**
 * @author Xuqiang ZHENG on 2016/10/9.
 */
public class ZhihuDailyRemoteRepository implements ZhihuDailyDataSource {

    private final ZhihuDailyApi api;

    public ZhihuDailyRemoteRepository(ZhihuDailyApi zhihuDailyApi) {
        api = zhihuDailyApi;
    }

    @Override
    public Observable<LatestNews> getLatestNews() {
        return api.getLatestNews();
    }
}
