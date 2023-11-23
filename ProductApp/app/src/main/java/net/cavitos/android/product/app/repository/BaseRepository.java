package net.cavitos.android.product.app.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public abstract class BaseRepository extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DATABASE_ID = "inventory.db";

    public BaseRepository(@Nullable Context context) {
        super(context, DATABASE_ID, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final var dbSchema = """
                CREATE TABLE product (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    price DOUBLE NOT NULL,
                    quantity DOUBLE NOT NULL    
                )
                """;

        sqLiteDatabase.execSQL(dbSchema);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        // For changes in DB schema, like migrations
    }

}
