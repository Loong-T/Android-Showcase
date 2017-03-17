package in.nerd_is.android_showcase;

import android.content.Context;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;

/**
 * @author Xuqiang ZHENG on 2017/3/14.
 */
public class DebugOnly {
    public static void initStetho(Context context) {
        Stetho.initializeWithDefaults(context);
    }

    public static OkHttpClient.Builder addStethoInterceptor(OkHttpClient.Builder builder) {
        return builder.addNetworkInterceptor(new StethoInterceptor());
    }
}
