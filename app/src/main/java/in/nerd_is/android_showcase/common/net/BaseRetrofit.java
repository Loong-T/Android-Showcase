package in.nerd_is.android_showcase.common.net;

import retrofit2.Retrofit;

/**
 * Created by loong on 2016/10/7.
 */
public abstract class BaseRetrofit {
    protected static Retrofit RETROFIT;

    protected BaseRetrofit() {
        buildRetrofit();
    }

    protected abstract void buildRetrofit();

    public static <T> T create(Class<T> clazz) {
        return RETROFIT.create(clazz);
    }
}
