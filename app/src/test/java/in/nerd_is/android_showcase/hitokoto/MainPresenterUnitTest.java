package in.nerd_is.android_showcase.hitokoto;

import org.mockito.Mock;

import in.nerd_is.android_showcase.main.MainContract;
import in.nerd_is.android_showcase.main.MainPresenter;

/**
 * @author loong on 2016/10/7.
 */
public class MainPresenterUnitTest {

    private MainPresenter presenter;

    @Mock
    private MainContract.View view;

//    @Mock
//    private GetHitokoto getHitokoto;
//
//    @Captor
//    private ArgumentCaptor<Subscriber<Hitokoto>> subscriberCaptor;

//    private static Hitokoto HITOKOTO;
//    static {
//        HITOKOTO = new Hitokoto();
//        HITOKOTO.id = "test-id";
//        HITOKOTO.type = "test";
//        HITOKOTO.from = "me";
//        HITOKOTO.creator = "me";
//        HITOKOTO.createdAt = System.currentTimeMillis();
//        HITOKOTO.hitokoto = "a test hitokoto";
//    }
//
//    @Before
//    public void setupPresenter() {
//        MockitoAnnotations.initMocks(this);
//        presenter = new MainPresenter(getHitokoto);
//    }
//
//    @Test
//    public void loadHitokoto_showHitokoto() {
//        presenter.loadHitokoto();
//
//        verify(getHitokoto).execute(isNull(), view.lifecycleTransformer(), subscriberCaptor.capture());
//        subscriberCaptor.getValue().onNext(HITOKOTO);
//
//        verify(view).showHitokoto(HITOKOTO);
//    }
}
