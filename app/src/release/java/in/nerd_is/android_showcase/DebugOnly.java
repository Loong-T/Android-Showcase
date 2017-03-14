package in.nerd_is.android_showcase;

import android.content.Context;

import com.facebook.stetho.Stetho;

/**
 * @author Xuqiang ZHENG on 2017/3/14.
 */
public class DebugOnly {
    public static void init(Context context) {
        // empty
    }

    public static OkHttpClient.Builder addStethoInterceptor(OkHttpClient.Builder builder) {
        // do nothing
        return builder;
    }
}
