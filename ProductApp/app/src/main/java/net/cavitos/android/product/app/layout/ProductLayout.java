package net.cavitos.android.product.app.layout;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import net.cavitos.android.product.app.R;
import net.cavitos.android.product.app.domain.ProductRecord;

public class ProductLayout extends BaseLayout {

    private EditText edProductName;

    private EditText edProductPrice;

    private EditText edProductQuantity;

    private EditText edProductTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_layout);

        edProductName = findViewById(R.id.edProductName);
        edProductPrice = findViewById(R.id.edProductPrice);
        edProductQuantity = findViewById(R.id.edProductQuantity);
        edProductTotal = findViewById(R.id.edProductTotal);

        final var btnTotal = findViewById(R.id.btnProductTotal);

        btnTotal.setOnClickListener(view -> {

            if (validate()) {

                final var product = getValues();

                final var total = product.price() * product.quantity();

                edProductTotal.setText(Double.toString(total));

                return;
            }

            Toast.makeText(this, "Debe ingresar los valores", Toast.LENGTH_LONG)
                    .show();

        });
    }

    private ProductRecord getValues() {

        final var name = edProductName.getText()
                .toString();

        final var priceValue = edProductPrice.getText()
                .toString();

        final var quantityValue = edProductQuantity.getText()
                .toString();

        return new ProductRecord(
                name,
                Double.parseDouble(priceValue),
                Double.parseDouble(quantityValue)
        );
    }

    private boolean validate() {

        final var name = edProductName.getText()
                .toString();

        final var priceValue = edProductPrice.getText()
                .toString();

        final var quantityValue = edProductQuantity.getText()
                .toString();

        return isNotBlank(name) && isNotBlank(priceValue) && isNotBlank(quantityValue);
    }
}