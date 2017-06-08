package `in`.nerd_is.android_showcase

import `in`.nerd_is.android_showcase.DebugOnly.addStethoInterceptor
import android.content.Context

import com.facebook.stetho.Stetho
import okhttp3.OkHttpClient

/**
 * @author Xuqiang ZHENG on 2017/3/14.
 */
object DebugOnly {
    @JvmStatic fun init(context: Context) {
        // empty
    }

    fun OkHttpClient.Builder.addStethoInterceptor(): OkHttpClient.Builder {
        // do nothing
        return this
    }

    fun OkHttpClient.Builder.addLoggingInterceptor(): OkHttpClient.Builder {
        // do nothing
        return this
    }
}
