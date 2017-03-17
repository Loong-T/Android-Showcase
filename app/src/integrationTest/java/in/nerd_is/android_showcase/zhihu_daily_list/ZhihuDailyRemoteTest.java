package in.nerd_is.android_showcase.zhihu_daily_list;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import in.nerd_is.android_showcase.common.lib_support.retrofit.RetrofitModule;
import in.nerd_is.android_showcase.zhihu_daily.model.repository.ZhihuDailyDataSource;
import in.nerd_is.android_showcase.zhihu_daily.model.repository.remote.ZhihuDailyRemoteRepository;
import rx.observers.TestSubscriber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.IsNot.not;

/**
 * @author Xuqiang ZHENG on 2016/10/9.
 */
public class ZhihuDailyRemoteTest {

    private static ZhihuDailyDataSource dataSource;

    @BeforeClass
    public static void setupDataSource() {
        dataSource = new ZhihuDailyRemoteRepository(
                new RetrofitModule().provideZhihuDailyRetrofit());
    }

    @Test
    public void getLatestNews_allRight_resultNotNullAndDateConverted() {
        TestSubscriber<List<?>> testSubscriber = new TestSubscriber<>();
        dataSource.getLatestNews().subscribe(testSubscriber);

        testSubscriber.assertCompleted();
        testSubscriber.assertNoErrors();

        List<List<?>> events = testSubscriber.getOnNextEvents();
        assertThat("results not empty", events, not(empty()));

        List<?> list = events.get(0);
        assertThat("has stories", list, not(empty()));
        assertThat("date converted correctly", list.get(0), notNullValue());
    }
}
