package net.cavitos.android.product.app.repository;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.Nullable;

import net.cavitos.android.product.app.domain.Product;

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
}

