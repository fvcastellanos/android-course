package net.cavitos.android.customer.app.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBConnection extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DATABASE_ID = "customerInventoryWithPhoto.db";
    private static final String CUSTOMER_TABLE = "customer";

    public DBConnection(@Nullable Context context) {
        super(context, DATABASE_ID, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final var createCustomerTable = """
                CREATE TABLE %s (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    country TEXT NOT NULL,
                    company TEXT NOT NULL,
                    photo_path TEXT
                )
                """;

        sqLiteDatabase.execSQL(String.format(createCustomerTable, CUSTOMER_TABLE));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        // For changes in DB schema, like migrations
    }
}
