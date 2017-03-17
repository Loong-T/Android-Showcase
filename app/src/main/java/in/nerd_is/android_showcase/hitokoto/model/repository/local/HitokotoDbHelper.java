package in.nerd_is.android_showcase.hitokoto.model.repository.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import in.nerd_is.android_showcase.hitokoto.model.Hitokoto;

/**
 * @author Xuqiang ZHENG on 2017/3/8.
 */
public class HitokotoDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public HitokotoDbHelper(Context context) {
        super(context, Hitokoto.TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Hitokoto.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // empty
    }
}
