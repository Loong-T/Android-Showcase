package in.nerd_is.android_showcase.zhihu_daily.repository;

import in.nerd_is.android_showcase.zhihu_daily.entity.LatestNews;
import in.nerd_is.android_showcase.zhihu_daily.net.ZhihuDailyApi;
import rx.Observable;

/**
 * Created by Xuqiang ZHENG on 2016/10/9.
 */
public class ZhihuDailyRemoteRepository implements ZhihuDailyDataSource {

    private ZhihuDailyApi api = new ZhihuDailyApi();

    @Override
    public Observable<LatestNews> getLatestNews() {
        return api.getLatestNews();
    }
}
