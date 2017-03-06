package in.nerd_is.android_showcase.common.moshi;

import com.ryanharter.auto.value.moshi.MoshiAdapterFactory;
import com.squareup.moshi.JsonAdapter;

/**
 * @author Xuqiang ZHENG on 2017/3/5.
 */
@MoshiAdapterFactory
public abstract class MyMoshiAdapterFactory implements JsonAdapter.Factory {
    public static JsonAdapter.Factory create() {
        return new AutoValueMoshi_MyMoshiAdapterFactory();
    }
}
