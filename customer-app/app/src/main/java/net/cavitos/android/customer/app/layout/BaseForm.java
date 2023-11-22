package net.cavitos.android.customer.app.layout;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import net.cavitos.android.customer.app.MainActivity;

public abstract class BaseForm extends AppCompatActivity  {

    protected void displayLayout(final Context context, final Class<?> formClass) {

        final var intent = new Intent(context, formClass);
        startActivity(intent);
    }
}
