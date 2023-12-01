package net.cavitos.android.product.app.layout;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class BaseLayout extends AppCompatActivity {

    protected void displayLayout(final Context context, final Class<?> layoutClass) {

        final var intent = new Intent(context, layoutClass);
        startActivity(intent);
    }

    protected int getProductId(final Bundle savedInstanceState) {

        if (isNull(savedInstanceState)) {

            final var bundle = getIntent()
                    .getExtras();

            if (nonNull(bundle)) {

                return bundle.getInt("productId");
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return savedInstanceState.getSerializable("customerId", Integer.class);
        }

        return 0;
    }
}
