package in.nerd_is.android_showcase.hitokoto;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import in.nerd_is.android_showcase.common.lib_support.retrofit.RetrofitModule;
import in.nerd_is.android_showcase.hitokoto.model.Hitokoto;
import in.nerd_is.android_showcase.hitokoto.model.repository.remote.HitokotoApi;
import in.nerd_is.android_showcase.hitokoto.model.repository.HitokotoDataSource;
import in.nerd_is.android_showcase.hitokoto.model.repository.remote.HitokotoRemoteRepository;
import rx.observers.TestSubscriber;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Xuqiang ZHENG on 2016/9/19.
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
        dataSource.getHitokoto().subscribe(testSubscriber);

        testSubscriber.assertCompleted();
        testSubscriber.assertNoErrors();
        List<Hitokoto> events = testSubscriber.getOnNextEvents();

        assertThat("result must not be null", events, notNullValue());
        assertThat("has a result", events.size(), equalTo(1));
        assertThat("result should be Hitokoto type",
                events.get(0), notNullValue(Hitokoto.class));
    }
}
