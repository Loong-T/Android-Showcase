package in.nerd_is.android_showcase.zhihu_daily_list.net;

import in.nerd_is.android_showcase.common.net.RetrofitUtils;
import in.nerd_is.android_showcase.zhihu_daily_list.entity.LatestNews;
import retrofit2.http.GET;
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
        @GET(ZhihuDailyUrl.LATEST)
        Observable<LatestNews> getLatestNews();
    }
}
