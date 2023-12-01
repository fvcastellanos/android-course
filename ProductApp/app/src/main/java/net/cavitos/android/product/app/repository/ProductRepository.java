package net.cavitos.android.product.app.repository;

import static java.lang.String.format;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.Nullable;

import net.cavitos.android.product.app.domain.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProductRepository extends BaseRepository {

    private static final String PRODUCT_TABLE = "product";

    public ProductRepository(@Nullable Context context) {
        super(context);
    }

    public void add(final Product product) {

        final var writableDatabase = getWritableDatabase();

        final var contentValue = new ContentValues();
        contentValue.put("name", product.getName());
        contentValue.put("price", product.getPrice());
        contentValue.put("quantity", product.getQuantity());

        writableDatabase.insert(PRODUCT_TABLE, null, contentValue);
    }

    public List<Product> getProducts() {

        final var readableDatabase = getReadableDatabase();

        final var query = """
                    select *
                    from %s
                    order by id desc
                """;

        try (final var cursor = readableDatabase.rawQuery(format(query, PRODUCT_TABLE), null)) {

            final var productList = new ArrayList<Product>();

            while (cursor.moveToNext()) {

                final var product = new Product(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getDouble(2),
                        cursor.getDouble(3)
                );

                productList.add(product);
            }

            return productList;

        } catch (Exception exception) {

            return Collections.emptyList();
        }
    }

    public Optional<Product> findById(final int id) {

        final var readableDatabase = getReadableDatabase();

        final var query = """
                    select *
                    from %s
                    where id = %s
                    limit 1
                """;

        try (final var cursor = readableDatabase.rawQuery(format(query, PRODUCT_TABLE, id), null)) {

            if (cursor.moveToNext()) {

                final var product = new Product(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getDouble(2),
                        cursor.getDouble(3)
                );

                return Optional.of(product);
            }


        } catch (Exception exception) {

        }

        return Optional.empty();
    }

    public void update(final int id, final Product product) {

        final var writableDatabase = getWritableDatabase();

        final var queryLayout = """
                    update %s
                    set
                      name = '%s',
                      quantity = %s,
                      price = %s
                    where id = %s                      
                    """;

        final var query = format(queryLayout, PRODUCT_TABLE, product.getName(),
                product.getQuantity(), product.getPrice(), id);

        try {

            writableDatabase.execSQL(query);

        } catch (final Exception exception) {

            exception.printStackTrace();

            // do something
        }
    }
}

