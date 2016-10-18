package in.nerd_is.android_showcase.zhihu_daily;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import in.nerd_is.android_showcase.zhihu_daily.entity.LatestNews;
import in.nerd_is.android_showcase.zhihu_daily.repository.ZhihuDailyDataSource;
import in.nerd_is.android_showcase.zhihu_daily.repository.ZhihuDailyRemoteRepository;
import rx.observers.TestSubscriber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by Xuqiang ZHENG on 2016/10/9.
 */
public class ZhihuDailyRemoteTest {

    private static ZhihuDailyDataSource dataSource;

    @BeforeClass
    public static void setupDataSource() {
        dataSource = new ZhihuDailyRemoteRepository();
    }

    @Test
    public void getLatestNews_resultNotNull() {
        TestSubscriber<LatestNews> testSubscriber = new TestSubscriber<>();
        dataSource.getLatestNews()
                .subscribe(testSubscriber);

        testSubscriber.assertCompleted();
        testSubscriber.assertNoErrors();

        List<LatestNews> events = testSubscriber.getOnNextEvents();
        assertThat("results not empty", events, not(empty()));

        LatestNews latestNews = events.get(0);
        assertThat("has stories", latestNews.stories, not(empty()));
        assertThat("has top stories", latestNews.topStories, not(empty()));
    }
}
