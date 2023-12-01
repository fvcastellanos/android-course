package net.cavitos.android.customer.app.layout;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.cavitos.android.customer.app.MainActivity;

import java.util.Map;

public abstract class BaseForm extends AppCompatActivity  {

    protected void displayLayout(final Context context, final Class<?> formClass) {

        final var intent = new Intent(context, formClass);
        startActivity(intent);
    }

    protected void displayLayout(final Context context, final Class<?> formClass, final Map<String, String> parameters) {

        final var intent = new Intent(context, formClass);

        if (nonNull(parameters)) {

            parameters.forEach(intent::putExtra);
        }

        startActivity(intent);
    }


    protected int getCustomerId(final Bundle savedInstanceState) {

        if (isNull(savedInstanceState)) {

            final var bundle = getIntent()
                    .getExtras();

            if (nonNull(bundle)) {

                return bundle.getInt("customerId");
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return savedInstanceState.getSerializable("customerId", Integer.class);
        }

        return 0;
    }
}
