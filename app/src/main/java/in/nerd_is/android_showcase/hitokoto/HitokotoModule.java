package in.nerd_is.android_showcase.hitokoto;

import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import in.nerd_is.android_showcase.common.di.annotation.ActivityScope;
import in.nerd_is.android_showcase.hitokoto.model.repository.HitokotoDataSource;
import in.nerd_is.android_showcase.hitokoto.model.repository.local.HitokotoDbHelper;
import in.nerd_is.android_showcase.hitokoto.model.repository.local.HitokotoLocalRepository;
import in.nerd_is.android_showcase.hitokoto.model.repository.remote.HitokotoRemoteRepository;
import retrofit2.Retrofit;

import static in.nerd_is.android_showcase.common.Constant.TAG_HITOKOTO;
import static in.nerd_is.android_showcase.common.Constant.TAG_LOCAL;
import static in.nerd_is.android_showcase.common.Constant.TAG_REMOTE;

/**
 * @author Xuqiang ZHENG on 2016/10/1.
 */
@Module
public class HitokotoModule {
    @Provides @ActivityScope @Named(TAG_REMOTE)
    public static HitokotoDataSource provideRemoteDataSource(
            @Named(TAG_HITOKOTO) Retrofit hitokotoRetrofit) {
        return new HitokotoRemoteRepository(hitokotoRetrofit);
    }

    @Provides @ActivityScope @Named(TAG_LOCAL)
    public static HitokotoDataSource provideLocalDataSource(Context context) {
        return new HitokotoLocalRepository(new HitokotoDbHelper(context));
    }
}
