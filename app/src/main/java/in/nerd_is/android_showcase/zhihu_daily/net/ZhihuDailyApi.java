package in.nerd_is.android_showcase.zhihu_daily.net;

import in.nerd_is.android_showcase.common.net.RetrofitUtils;
import in.nerd_is.android_showcase.zhihu_daily.entity.LatestNews;
import rx.Observable;

/**
 * Created by loong on 2016/10/7.
 */
public class ZhihuDailyApi {

    private Api api = RetrofitUtils.create(ZhihuDailyRetrofit.INSTANCE, Api.class);

    public Observable<LatestNews> getLatestNews() {
        return api.getLatestNews();
    }

    private interface Api {
        Observable<LatestNews> getLatestNews();
    }
}
