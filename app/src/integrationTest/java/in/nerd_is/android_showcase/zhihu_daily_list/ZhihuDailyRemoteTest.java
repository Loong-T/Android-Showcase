package in.nerd_is.android_showcase.zhihu_daily_list;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import in.nerd_is.android_showcase.common.lib_support.retrofit.RetrofitModule;
import in.nerd_is.android_showcase.zhihu_daily.model.Date;
import in.nerd_is.android_showcase.zhihu_daily.model.repository.ZhihuDailyDataSource;
import in.nerd_is.android_showcase.zhihu_daily.model.repository.remote.ZhihuDailyRemoteRepository;
import io.reactivex.observers.TestObserver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

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
        TestObserver<List<?>> testObserver = dataSource.getLatestNews().test();

        testObserver.assertSubscribed()
                .assertNoErrors()
                .assertComplete();

        assertThat("has one result", testObserver.valueCount(), equalTo(1));
        List<?> result = testObserver.values().get(0);
        assertThat("result not null", result, notNullValue());
        assertThat("result not empty", result, not(empty()));

        Optional<Date> dateOptional = Stream.of(result).select(Date.class).findFirst();
        assertThat("has date item", dateOptional.isPresent(), is(true));
    }
}
