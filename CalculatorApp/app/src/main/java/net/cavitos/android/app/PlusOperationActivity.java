package net.cavitos.android.app;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.collect.ImmutableList;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class PlusOperationActivity extends AppCompatActivity {

    private EditText edMathGrade;

    private EditText edITGrade;

    private EditText edSystemMaintenanceGrade;

    private EditText edLanguageGrade;

    private EditText edHistoryGrade;

    private EditText edEthicsGrade;

    private TextView edPlus;

    private TextView edMinus;

    private TextView edMultiply;

    private TextView edDivision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus_operation);

        edMathGrade = findViewById(R.id.edMathGrade);
        edITGrade = findViewById(R.id.edITGrade);
        edSystemMaintenanceGrade = findViewById(R.id.edSystemMaintenanceGrade);
        edHistoryGrade = findViewById(R.id.edHistoryGrade);
        edLanguageGrade = findViewById(R.id.edLanguageGrade);
        edEthicsGrade = findViewById(R.id.edEthicsGrade);

        edPlus = findViewById(R.id.edPlus);
        edMinus = findViewById(R.id.edMinus);
        edMultiply = findViewById(R.id.edMultiply);
        edDivision = findViewById(R.id.edDivision);
    }

    public void plusGrades(final View view) {

        if (!validInput()) {

            Toast.makeText(this, "Ingrese todos los valores", Toast.LENGTH_LONG)
                    .show();

            return;
        }

        final var mathGrade = edMathGrade.getText()
                .toString();

        final var systemMaintenanceGrade = edSystemMaintenanceGrade.getText()
                .toString();

        final var itGrade = edITGrade.getText()
                .toString();

        final var languageGrade = edLanguageGrade.getText()
                .toString();

        final var historyGrade = edHistoryGrade.getText()
                .toString();

        final var ethicsGrade = edEthicsGrade.getText()
                .toString();

        final var valueList = ImmutableList.of(
                mathGrade,
                systemMaintenanceGrade,
                itGrade,
                languageGrade,
                historyGrade,
                ethicsGrade
        );

        edPlus.setText(String.format("Suma de notas: %s", plusList(valueList)));
        edMinus.setText(String.format("Resta de notas: %s", minusList(valueList)));
        edMultiply.setText(String.format("Multiplicacion de notas: %s", multiplyList(valueList)));
        edDivision.setText(String.format("Division de notas: %s", divideList(valueList)));
    }

    public void goToMainLayout(final View view) {

        final var intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // --------------------------------------------------------------------------------------

    public double plusList(final List<String> valueList) {

        return valueList.stream()
                .mapToDouble(Double::parseDouble)
                .sum();
    }

    public double minusList(final List<String> valueList) {

        return valueList.stream()
                .mapToDouble(Double::parseDouble)
                .reduce((x, y) -> x - y)
                .orElse(0);
    }

    public double multiplyList(final List<String> valueList) {

        return valueList.stream()
                .mapToDouble(Double::parseDouble)
                .reduce((x, y) -> x * y)
                .orElse(0);
    }

    public double divideList(final List<String> valueList) {

        return valueList.stream()
                .mapToDouble(Double::parseDouble)
                .reduce((x, y) -> x / y)
                .orElse(0);
    }

    private boolean validInput() {

        return buildValueList().stream()
                .allMatch(StringUtils::isNotBlank);
    }

    private List<String> buildValueList() {

        final var mathGrade = edMathGrade.getText()
                .toString();

        final var systemMaintenanceGrade = edSystemMaintenanceGrade.getText()
                .toString();

        final var itGrade = edITGrade.getText()
                .toString();

        final var languageGrade = edLanguageGrade.getText()
                .toString();

        final var historyGrade = edHistoryGrade.getText()
                .toString();

        final var ethicsGrade = edEthicsGrade.getText()
                .toString();

        return ImmutableList.of(
                mathGrade,
                systemMaintenanceGrade,
                itGrade,
                languageGrade,
                historyGrade,
                ethicsGrade
        );
    }
}