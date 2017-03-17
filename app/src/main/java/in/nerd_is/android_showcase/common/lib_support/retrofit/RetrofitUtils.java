package in.nerd_is.android_showcase.common.lib_support.retrofit;

import retrofit2.Retrofit;

/**
 * @author loong on 2016/10/7.
 */
final public class RetrofitUtils {
    public static <T> T create(Retrofit retrofit, Class<T> clazz) {
        return retrofit.create(clazz);
    }
}
