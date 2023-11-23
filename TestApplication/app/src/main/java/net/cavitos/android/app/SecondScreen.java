package net.cavitos.android.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

public class SecondScreen extends AppCompatActivity {

    private EditText edLastName;

    private EditText edAge;

    private EditText edBirthArea;

    private EditText edBirthYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);

        edLastName = findViewById(R.id.edLastName);
        edBirthArea = findViewById(R.id.edBirthArea);
        edBirthYear = findViewById(R.id.edBirthYear);
        edAge = findViewById(R.id.edAge);
    }

    public void displayInformation(final View view) {

        final var lastName = edLastName.getText()
                        .toString();

        final var age = edAge.getText()
                        .toString();

        final var birthArea = edBirthArea.getText()
                .toString();

        final var birthYear = edBirthYear.getText()
                .toString();

        String message = "Ingrese todos los valores, por favor";

        if ((StringUtils.isNotBlank(lastName)) && (StringUtils.isNotBlank(age))
                && (StringUtils.isNotBlank(birthArea)) && (StringUtils.isNotBlank(birthYear))) {

            message = String.format("Soy %s y tengo %s, naci en: %s en el año: %s", lastName, age,
                    birthArea, birthYear);
        }

        Toast.makeText(this, message, Toast.LENGTH_LONG)
                .show();
    }

    public void priorLayout(final View view) {

        final var intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
