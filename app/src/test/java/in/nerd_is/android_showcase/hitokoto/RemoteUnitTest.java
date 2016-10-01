package in.nerd_is.android_showcase.hitokoto;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import in.nerd_is.android_showcase.hitokoto.entity.Hitokoto;
import in.nerd_is.android_showcase.hitokoto.repository.HitokotoDataSource;
import in.nerd_is.android_showcase.hitokoto.repository.HitokotoRemoteRepository;
import rx.observers.TestSubscriber;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Xuqiang ZHENG on 2016/9/19.
 */
public class RemoteUnitTest {

    private static HitokotoDataSource remoteDataSource;
    private TestSubscriber<Hitokoto> testSubscriber;

    @BeforeClass
    public static void setUpClass() {
        remoteDataSource = new HitokotoRemoteRepository();
    }

    @Before
    public void setUp() {
        testSubscriber = new TestSubscriber<>();
    }

    @Test
    public void getHitokoto_notNullResult() {
        remoteDataSource.getHitokoto()
                .subscribe(testSubscriber);

        testSubscriber.assertCompleted();
        testSubscriber.assertNoErrors();
        List<Hitokoto> events = testSubscriber.getOnNextEvents();

        assertThat(events, notNullValue());
        assertThat(events.size(), equalTo(1));
        assertThat(events.get(0), notNullValue(Hitokoto.class));
    }
}
