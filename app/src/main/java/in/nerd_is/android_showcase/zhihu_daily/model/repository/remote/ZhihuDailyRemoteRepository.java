package in.nerd_is.android_showcase.zhihu_daily.model.repository.remote;

import com.annimon.stream.Stream;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import in.nerd_is.android_showcase.common.lib_support.retrofit.RetrofitUtils;
import in.nerd_is.android_showcase.zhihu_daily.model.Date;
import in.nerd_is.android_showcase.zhihu_daily.model.LatestNews;
import in.nerd_is.android_showcase.zhihu_daily.model.News;
import in.nerd_is.android_showcase.zhihu_daily.model.Story;
import in.nerd_is.android_showcase.zhihu_daily.model.repository.ZhihuDailyDataSource;
import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static in.nerd_is.android_showcase.common.Constant.TAG_ZHIHU_DAILY;
import static in.nerd_is.android_showcase.zhihu_daily.Constant.NEWS_DATE_FORMATTER;
import static in.nerd_is.android_showcase.zhihu_daily.model.repository.remote.ZhihuDailyUrl.BEFORE;
import static in.nerd_is.android_showcase.zhihu_daily.model.repository.remote.ZhihuDailyUrl.DATE_PLACE_HOLDER;
import static in.nerd_is.android_showcase.zhihu_daily.model.repository.remote.ZhihuDailyUrl.LATEST;

/**
 * @author Xuqiang ZHENG on 2016/10/9.
 */
public class ZhihuDailyRemoteRepository implements ZhihuDailyDataSource {

    private final Api api;

    public ZhihuDailyRemoteRepository(@Named(TAG_ZHIHU_DAILY) Retrofit zhihuDailyRetrofit) {
        api = RetrofitUtils.create(zhihuDailyRetrofit, Api.class);
    }

    @Override
    public Single<List<?>> getLatestNews() {
        return api.getLatestNews()
                .map(latestNews -> {
                    List<Object> list = new ArrayList<>(latestNews.stories.size() + 1);
                    LocalDate date = latestNews.date;

                    list.add(Date.create(date));
                    for (Story story : latestNews.stories) {
                        story.setDate(date);
                        list.add(story);
                    }
                    return list;
                });
    }

    @Override
    public Single<List<?>> getNewsBefore(Date date) {
        return api.getNewsBefore(NEWS_DATE_FORMATTER.format(date.date()))
                .map(news -> {
                    List<Object> list = new ArrayList<>(news.stories.size() + 1);
                    list.add(Date.create(news.date));
                    Stream.of(news.stories).forEach(list::add);
                    return list;
                });
    }

    private interface Api {
        @GET(LATEST)
        Single<LatestNews> getLatestNews();

        @GET(BEFORE)
        Single<News> getNewsBefore(@Path(DATE_PLACE_HOLDER) String date);
    }
}
