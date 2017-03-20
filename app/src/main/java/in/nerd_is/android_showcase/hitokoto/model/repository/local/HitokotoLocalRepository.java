package in.nerd_is.android_showcase.hitokoto.model.repository.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import in.nerd_is.android_showcase.hitokoto.model.Hitokoto;
import in.nerd_is.android_showcase.hitokoto.model.repository.HitokotoDataSource;
import io.reactivex.Single;

import static in.nerd_is.android_showcase.hitokoto.model.Hitokoto.COUNT_MAPPER;
import static in.nerd_is.android_showcase.hitokoto.model.Hitokoto.FACTORY;
import static in.nerd_is.android_showcase.hitokoto.model.Hitokoto.Insert_hitokoto;
import static in.nerd_is.android_showcase.hitokoto.model.Hitokoto.SELECT_RANDOM_MAPPER;

/**
 * @author Xuqiang ZHENG on 2017/3/8.
 */
public class HitokotoLocalRepository implements HitokotoDataSource {

    private final SQLiteDatabase db;

    public HitokotoLocalRepository(HitokotoDbHelper dbHelper) {
        db = dbHelper.getWritableDatabase();
    }

    @Override
    public Single<Hitokoto> getHitokoto() {
        Cursor cursor = db.rawQuery(FACTORY.count().statement, FACTORY.count().args);
        long count = COUNT_MAPPER.map(cursor);
        cursor.close();

        if (count <= 0) {
            return Single.never();
        }

        return Single.fromCallable(() -> db.rawQuery(FACTORY.select_random().statement,
                FACTORY.select_random().args))
                .map(c -> {
                    Hitokoto hitokoto = SELECT_RANDOM_MAPPER.map(c);
                    c.close();
                    return hitokoto;
                });
    }

    @Override
    public Single<Long> saveHitokoto(Hitokoto hitokoto) {
        Insert_hitokoto insertHitokoto = new Insert_hitokoto(db, FACTORY);
        insertHitokoto.bind(
                hitokoto.id(),
                hitokoto.uid(),
                hitokoto.catname(),
                hitokoto.text(),
                hitokoto.author(),
                hitokoto.source(),
                hitokoto.date()
        );
        return Single.just(insertHitokoto.program.executeInsert());
    }
}
