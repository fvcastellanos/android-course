package net.cavitos.android.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

public class MainActivity extends AppCompatActivity {

    private EditText edName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edName = findViewById(R.id.edName);
    }

    public void showValue(final View view) {

        final var name = edName.getText()
                        .toString();

        String message = "Ingrese un nombre, por favor";

        if (!StringUtils.isBlank(name)) {

            message = String.format("Bienvenido: %s", name);
        }

        Toast.makeText(this, message, Toast.LENGTH_LONG)
                .show();
    }

    public void nextLayout(final View view) {

        final var intent = new Intent(this, SecondScreen.class);
        startActivity(intent);
    }

    public void redirectToPlusOperation(final View view) {

        final var intent = new Intent(this, PlusOperationActivity.class);
        startActivity(intent);
    }
}
