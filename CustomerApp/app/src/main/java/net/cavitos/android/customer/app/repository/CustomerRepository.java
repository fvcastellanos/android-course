package net.cavitos.android.customer.app.repository;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;

public class CustomerRepository {

    private static final String CUSTOMER_TABLE = "customer";

    private final SQLiteOpenHelper sqLiteOpenHelper;

    public CustomerRepository(final SQLiteOpenHelper sqLiteOpenHelper) {

        this.sqLiteOpenHelper = sqLiteOpenHelper;
    }

    public void add(final ContentValues contentValues) {

        final var writableDatabase = sqLiteOpenHelper.getWritableDatabase();

        writableDatabase.insert(CUSTOMER_TABLE, null, contentValues);
    }
}
