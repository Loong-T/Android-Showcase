package in.nerd_is.android_showcase.zhihu_daily.model.repository.remote;

/**
 * @author Xuqiang ZHENG on 2016/10/7.
 */
public interface ZhihuDailyUrl {
    String BASE_URL = "https://news-at.zhihu.com/api/4/news/";

    String DATE_PLACE_HOLDER = "date";

    String ID_PLACE_HOLDER = "id";

    String LATEST = "latest";

    String BEFORE = "before/{" + DATE_PLACE_HOLDER + "}";

    String DETAIL = "{" + ID_PLACE_HOLDER + "}";
}
