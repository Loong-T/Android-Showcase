package in.nerd_is.android_showcase.hitokoto;

import com.squareup.sqlbrite.BriteDatabase;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import in.nerd_is.android_showcase.common.di.annotation.ActivityScope;
import in.nerd_is.android_showcase.hitokoto.model.repository.HitokotoDataSource;
import in.nerd_is.android_showcase.hitokoto.model.repository.local.HitokotoLocalRepository;
import in.nerd_is.android_showcase.hitokoto.model.repository.remote.HitokotoApi;
import in.nerd_is.android_showcase.hitokoto.model.repository.remote.HitokotoRemoteRepository;

import static in.nerd_is.android_showcase.common.Constant.TAG_HITOKOTO;
import static in.nerd_is.android_showcase.common.Constant.TAG_LOCAL;
import static in.nerd_is.android_showcase.common.Constant.TAG_REMOTE;

/**
 * @author Xuqiang ZHENG on 2016/10/1.
 */
@Module
public class HitokotoModule {
    @Provides @ActivityScope @Named(TAG_REMOTE)
    public static HitokotoDataSource provideRemoteDataSource(HitokotoApi hitokotoApi) {
        return new HitokotoRemoteRepository(hitokotoApi);
    }

    @Provides @ActivityScope @Named(TAG_LOCAL)
    public static HitokotoDataSource provideLocalDataSource(
            @Named(TAG_HITOKOTO) BriteDatabase database) {
        return new HitokotoLocalRepository(database);
    }
}
