package in.nerd_is.android_showcase.hitokoto;

import org.junit.BeforeClass;
import org.junit.Test;

import in.nerd_is.android_showcase.common.lib_support.retrofit.RetrofitModule;
import in.nerd_is.android_showcase.hitokoto.model.Hitokoto;
import in.nerd_is.android_showcase.hitokoto.model.repository.HitokotoDataSource;
import in.nerd_is.android_showcase.hitokoto.model.repository.remote.HitokotoRemoteRepository;
import io.reactivex.observers.TestObserver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author Xuqiang ZHENG on 2016/9/19.
 */
public class HitokotoRemoteTest {

    private static HitokotoDataSource dataSource;

    @BeforeClass
    public static void setupDataSource() {
        dataSource = new HitokotoRemoteRepository(
                new RetrofitModule().provideHitokotoRetrofit());
    }

    @Test
    public void getHitokotoFromRemote_allRight_hasOneResultAndNotNull() {
         TestObserver<Hitokoto> testObserver = dataSource.getHitokoto().test();

        testObserver.assertSubscribed()
                .assertNoErrors()
                .assertComplete();

        assertThat("has one result", testObserver.valueCount(), equalTo(1));

        Hitokoto result = testObserver.values().get(0);
        assertThat("result not null", result, notNullValue());
    }
}
