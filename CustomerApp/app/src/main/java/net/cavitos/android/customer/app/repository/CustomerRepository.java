package net.cavitos.android.customer.app.repository;

import static java.lang.String.format;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;

import net.cavitos.android.customer.app.domain.Customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
                        cursor.getString(3),
                        cursor.getString(4)
                );

                customerList.add(customer);
            }

            return customerList;

        } catch (Exception exception) {

            return Collections.emptyList();
        }
    }

    public Optional<Customer> findById(final int id) {

        final var readableDatabase = sqLiteOpenHelper.getReadableDatabase();

        final var query = """
                    select *
                    from %s
                    where id = %s
                    limit 1
                """;

        try (final var cursor = readableDatabase.rawQuery(format(query, CUSTOMER_TABLE, id), null)) {

            if (cursor.moveToNext()) {

                final var customer = new Customer(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );

                return Optional.of(customer);
            }


        } catch (Exception exception) {

            // do something
        }

        return Optional.empty();
    }

    public boolean update(final int id,
                          final String name,
                          final String country,
                          final String company,
                          final String photoPath) {

        final var writableDatabase = sqLiteOpenHelper.getWritableDatabase();

        final var query = """
                    update %s
                    set
                      name = '%s',
                      country = '%s',
                      company = '%s',
                      photo_path = '%s'
                    where id = %s
                """;

        final var updateQuery = format(query, CUSTOMER_TABLE, name, country, company, photoPath, id);

        try {

            writableDatabase.execSQL(updateQuery, new Object[] {});
            return true;

        } catch (Exception exception) {

            return false;
        }

    }

    public boolean delete(final int id) {

        final var writableDatabase = sqLiteOpenHelper.getWritableDatabase();

        final var query = """
                    delete from %s
                    where id = %s
                """;

        final var deleteQuery = format(query, CUSTOMER_TABLE, id);

        try {

            writableDatabase.execSQL(deleteQuery, new Object[] {});
            return true;

        } catch (Exception exception) {

            return false;
        }
    }
}
