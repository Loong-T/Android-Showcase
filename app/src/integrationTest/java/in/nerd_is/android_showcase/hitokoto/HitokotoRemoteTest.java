package in.nerd_is.android_showcase.hitokoto;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import in.nerd_is.android_showcase.common.net.RetrofitModule;
import in.nerd_is.android_showcase.hitokoto.entity.Hitokoto;
import in.nerd_is.android_showcase.hitokoto.net.HitokotoApi;
import in.nerd_is.android_showcase.hitokoto.repository.HitokotoDataSource;
import in.nerd_is.android_showcase.hitokoto.repository.HitokotoRemoteRepository;
import rx.observers.TestSubscriber;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Xuqiang ZHENG on 2016/9/19.
 */
public class HitokotoRemoteTest {

    private static HitokotoDataSource dataSource;
    private TestSubscriber<Hitokoto> testSubscriber;

    @BeforeClass
    public static void setupDataSource() {
        dataSource = new HitokotoRemoteRepository(
                new HitokotoApi(new RetrofitModule().provideHitokotoRetrofit()));
    }

    @Before
    public void setUp() {
        testSubscriber = new TestSubscriber<>();
    }

    @Test
    public void getHitokoto_resultNotNull() {
        dataSource.getHitokoto()
                .subscribe(testSubscriber);

        testSubscriber.assertCompleted();
        testSubscriber.assertNoErrors();
        List<Hitokoto> events = testSubscriber.getOnNextEvents();

        assertThat("result must not be null", events, notNullValue());
        assertThat("has a result", events.size(), equalTo(1));
        assertThat("result should be Hitokoto type",
                events.get(0), notNullValue(Hitokoto.class));
    }
}
