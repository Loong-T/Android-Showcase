package in.nerd_is.android_showcase.zhihu_daily.model.repository.remote;

/**
 * @author Xuqiang ZHENG on 2016/10/7.
 */
public interface ZhihuDailyUrl {
    String BASE_URL = "http://news-at.zhihu.com/api/4/news/";

    String DATE_PLACE_HOLDER = "date";

    String LATEST = "latest";

    String BEFORE = "before/{" + DATE_PLACE_HOLDER + "}";
}
