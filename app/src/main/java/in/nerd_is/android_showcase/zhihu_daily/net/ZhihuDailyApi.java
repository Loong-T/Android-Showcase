package in.nerd_is.android_showcase.zhihu_daily.net;

import javax.inject.Inject;
import javax.inject.Named;

import in.nerd_is.android_showcase.common.net.RetrofitUtils;
import in.nerd_is.android_showcase.zhihu_daily.entity.LatestNews;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import rx.Observable;

import static in.nerd_is.android_showcase.common.Constant.TAG_ZHIHU_DAILY;

/**
 * Created by loong on 2016/10/7.
 */
public class ZhihuDailyApi {

    private final Api api;

    @Inject
    public ZhihuDailyApi(@Named(TAG_ZHIHU_DAILY) Retrofit zhihuDailyRetrofit) {
        api = RetrofitUtils.create(zhihuDailyRetrofit, Api.class);
    }

    public Observable<LatestNews> getLatestNews() {
        return api.getLatestNews();
    }

    private interface Api {
        @GET(ZhihuDailyUrl.LATEST)
        Observable<LatestNews> getLatestNews();
    }
}
