package in.nerd_is.android_showcase.hitokoto.entity;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.sqldelight.RowMapper;

import in.nerd_is.android_showcase.common.sqldelight.DateAdapter;

/**
 * @author Xuqiang ZHENG on 2017/3/5.
 */
@AutoValue
public abstract class Hitokoto implements HitokotoModel {

    private static final DateAdapter DATE_ADAPTER = new DateAdapter();

    public static final Factory<Hitokoto> FACTORY = new Factory<>(
            AutoValue_Hitokoto::new, DATE_ADAPTER);

    public static final RowMapper<Hitokoto> SELECT_BY_ID_MAPPER = FACTORY.select_by_idMapper();

    public static JsonAdapter<Hitokoto> jsonAdapter(Moshi moshi) {
        return new AutoValue_Hitokoto.MoshiJsonAdapter(moshi);
    }
}
