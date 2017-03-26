package in.nerd_is.android_showcase.zhihu_daily.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.ryanharter.auto.value.parcel.ParcelAdapter;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.threeten.bp.LocalDate;

import in.nerd_is.android_showcase.common.lib_support.auto.LocalDateParcelAdapter;
import in.nerd_is.android_showcase.common.lib_support.sqldelight.LocalDateAdapter;
import in.nerd_is.android_showcase.common.lib_support.sqldelight.UrlListAdapter;

/**
 * @author Xuqiang ZHENG on 2016/10/7.
 */
@AutoValue
public abstract class Story implements StoryModel, Parcelable {

    private LocalDate date;

    @Override @ParcelAdapter(LocalDateParcelAdapter.class)
    public LocalDate date() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // for sqldelight
    private static final LocalDateAdapter DATE_ADAPTER = new LocalDateAdapter();
    private static final UrlListAdapter LIST_ADAPTER = new UrlListAdapter();

    @SuppressWarnings("StaticInitializerReferencesSubClass")
    public static final Factory<Story> FACTORY = new Factory<>(
            (id, type, title, image, images, read, date) -> {
                AutoValue_Story story = new AutoValue_Story(id, type, title, image, images, read);
                story.setDate(date);
                return story;
            }, LIST_ADAPTER, DATE_ADAPTER);

    public static JsonAdapter<Story> jsonAdapter(Moshi moshi) {
        return new AutoValue_Story.MoshiJsonAdapter(moshi);
    }
}
