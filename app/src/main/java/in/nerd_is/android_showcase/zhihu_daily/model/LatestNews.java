package in.nerd_is.android_showcase.zhihu_daily.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by loong on 2016/10/7.
 */
public class LatestNews extends News {
    @Json(name = "top_stories") public List<Story> topStories;
}
