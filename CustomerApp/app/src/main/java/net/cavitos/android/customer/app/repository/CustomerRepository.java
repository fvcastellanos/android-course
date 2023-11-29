package net.cavitos.android.customer.app.repository;

import static java.lang.String.format;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;

import net.cavitos.android.customer.app.domain.Customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public List<Customer> getCustomers() {

        final var readableDatabase = sqLiteOpenHelper.getReadableDatabase();

        final var query = """
                    select *
                    from %s
                    order by id desc
                """;

        try (final var cursor = readableDatabase.rawQuery(format(query, CUSTOMER_TABLE), null)) {

            final var customerList = new ArrayList<Customer>();

            while (cursor.moveToNext()) {

                final var customer = new Customer(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                );

                customerList.add(customer);
            }

            return customerList;

        } catch (Exception exception) {

            return Collections.emptyList();
        }
    }
}
