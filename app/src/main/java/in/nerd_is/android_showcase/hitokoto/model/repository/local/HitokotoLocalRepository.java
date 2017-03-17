package in.nerd_is.android_showcase.hitokoto.model.repository.local;

import android.database.Cursor;

import com.squareup.sqlbrite.BriteDatabase;

import in.nerd_is.android_showcase.hitokoto.model.Hitokoto;
import in.nerd_is.android_showcase.hitokoto.model.repository.HitokotoDataSource;
import rx.Observable;

import static in.nerd_is.android_showcase.hitokoto.model.Hitokoto.*;

/**
 * @author Xuqiang ZHENG on 2017/3/8.
 */
public class HitokotoLocalRepository implements HitokotoDataSource {

    private final BriteDatabase db;

    public HitokotoLocalRepository(BriteDatabase db) {
        this.db = db;
    }

    @Override
    public Observable<Hitokoto> getHitokoto() {
        Cursor cursor = db.query(TABLE_NAME, FACTORY.count().statement);
        long count = COUNT_MAPPER.map(cursor);
        cursor.close();

        if (count <= 0) {
            return Observable.empty();
        }

        return db.createQuery(TABLE_NAME, FACTORY.select_random().statement)
                .mapToOne(SELECT_RANDOM_MAPPER::map);
    }

    @Override
    public Observable<Long> saveHitokoto(Hitokoto hitokoto) {
        Insert_hitokoto insertHitokoto = new Insert_hitokoto(db.getWritableDatabase(), FACTORY);
        insertHitokoto.bind(
                hitokoto.id(),
                hitokoto.uid(),
                hitokoto.catname(),
                hitokoto.text(),
                hitokoto.author(),
                hitokoto.source(),
                hitokoto.date()
        );
        return Observable.just(insertHitokoto.program.executeInsert());
    }
}
