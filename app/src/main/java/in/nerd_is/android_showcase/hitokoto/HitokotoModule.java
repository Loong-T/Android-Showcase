package in.nerd_is.android_showcase.hitokoto;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.nerd_is.android_showcase.hitokoto.net.HitokotoApi;
import in.nerd_is.android_showcase.hitokoto.repository.HitokotoDataSource;
import in.nerd_is.android_showcase.hitokoto.repository.HitokotoRemoteRepository;

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
