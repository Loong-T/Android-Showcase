package in.nerd_is.android_showcase.hitokoto;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.nerd_is.android_showcase.hitokoto.model.repository.remote.HitokotoApi;
import in.nerd_is.android_showcase.hitokoto.model.repository.HitokotoDataSource;
import in.nerd_is.android_showcase.hitokoto.model.repository.remote.HitokotoRemoteRepository;

/**
 * Created by Xuqiang ZHENG on 2016/10/1.
 */
@Module
public class HitokotoModule {
    @Provides @Singleton
    public static HitokotoDataSource provideGetHitokoto(HitokotoApi hitokotoApi) {
        return new HitokotoRemoteRepository(hitokotoApi);
    }
}
