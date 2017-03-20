package in.nerd_is.android_showcase.main;

import com.github.javafaker.Faker;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import in.nerd_is.android_showcase.hitokoto.model.Hitokoto;
import in.nerd_is.android_showcase.hitokoto.usecase.GetHitokoto;
import in.nerd_is.android_showcase.utils.DateUtils;
import io.reactivex.observers.DisposableSingleObserver;

import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;

/**
 * @author Xuqiang ZHENG on 2016/10/7.
 */
public class MainPresenterTest {

    private MainPresenter presenter;

    @Mock
    private MainContract.View view;

    @Mock
    private GetHitokoto getHitokoto;

    @Captor
    private ArgumentCaptor<DisposableSingleObserver<Hitokoto>> observerCaptor;

    private static Faker FAKER = new Faker(Locale.getDefault());

    @Before
    public void setupPresenter() {
        MockitoAnnotations.initMocks(this);
        presenter = new MainPresenter(getHitokoto);
        presenter.setView(view);
    }

    @Test
    public void loadHitokotoAndShow_allRight_hitokotoShowed() {
        Hitokoto hitokoto = createFakerHitokoto();

        presenter.loadHitokoto();

        verify(getHitokoto).execute(isNull(), observerCaptor.capture());
        observerCaptor.getValue().onSuccess(hitokoto);

        verify(view).showHitokoto(hitokoto);
    }

    private static Hitokoto createFakerHitokoto() {
        return Hitokoto.FACTORY.creator.create(
                FAKER.idNumber().valid(),
                FAKER.idNumber().ssnValid(),
                FAKER.hacker().noun(),
                FAKER.lorem().sentence(),
                FAKER.name().name(),
                FAKER.book().title(),
                DateUtils.fromDate(FAKER.date().past(42, TimeUnit.DAYS))
        );
    }
}
