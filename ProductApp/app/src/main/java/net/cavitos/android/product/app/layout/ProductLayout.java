package net.cavitos.android.product.app.layout;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import android.content.ContentValues;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import net.cavitos.android.product.app.MainActivity;
import net.cavitos.android.product.app.R;
import net.cavitos.android.product.app.domain.Product;
import net.cavitos.android.product.app.repository.ProductRepository;

public class ProductLayout extends BaseLayout {

    private final ProductRepository productRepository;

    private EditText edProductName;

    private EditText edProductPrice;

    private EditText edProductQuantity;

    private EditText edProductTotal;

    public ProductLayout() {

        this.productRepository = new ProductRepository(this);
    }

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

                final var total = product.getPrice() * product.getQuantity();

                edProductTotal.setText(Double.toString(total));

                return;
            }

            Toast.makeText(this, "Debe ingresar los valores", Toast.LENGTH_LONG)
                    .show();

        });

        final var btnExit = findViewById(R.id.btnExitFromProducts);

        btnExit.setOnClickListener(view -> displayLayout(this, MainActivity.class));

        final var btnSaveProduct = findViewById(R.id.btnSaveProduct);

        btnSaveProduct.setOnClickListener(view -> {

            if (validate()) {

                productRepository.add(getValues());

                Toast.makeText(this, "Producto ingresado!", Toast.LENGTH_LONG)
                        .show();

                return;
            }

            Toast.makeText(this, "Debe ingresar los valores", Toast.LENGTH_LONG)
                    .show();
        });
    }

    private Product getValues() {

        final var name = edProductName.getText()
                .toString();

        final var priceValue = edProductPrice.getText()
                .toString();

        final var quantityValue = edProductQuantity.getText()
                .toString();

        return new Product(
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