package net.cavitos.android.product.app.layout;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class BaseLayout extends AppCompatActivity {

    protected void displayLayout(final Context context, final Class<?> layoutClass) {

        final var intent = new Intent(context, layoutClass);
        startActivity(intent);
    }
}
