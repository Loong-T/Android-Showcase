package in.nerd_is.android_showcase.common.lib_support.sqlbrite;

import android.content.Context;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.nerd_is.android_showcase.hitokoto.model.repository.local.HitokotoDbHelper;
import rx.Scheduler;

import static in.nerd_is.android_showcase.common.Constant.TAG_HITOKOTO;
import static in.nerd_is.android_showcase.common.Constant.TAG_IO;

/**
 * @author Xuqiang ZHENG on 2017/3/10.
 */
@Module
public class BriteModule {
    @Provides @Singleton @Named(TAG_HITOKOTO)
    public BriteDatabase provideHitokotoDatabase(Context context,
                                                 @Named(TAG_IO) Scheduler scheduler) {
        SqlBrite sqlBrite = new SqlBrite.Builder().build();
        return sqlBrite.wrapDatabaseHelper(new HitokotoDbHelper(context), scheduler);
    }
}
