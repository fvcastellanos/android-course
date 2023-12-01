package net.cavitos.android.product.app.layout;

import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import net.cavitos.android.product.app.MainActivity;
import net.cavitos.android.product.app.R;
import net.cavitos.android.product.app.domain.Product;
import net.cavitos.android.product.app.repository.ProductRepository;

public class ProductEditLayout extends BaseLayout {

    private final ProductRepository productRepository;

    public ProductEditLayout() {

        this.productRepository = new ProductRepository(this);
    }

    private EditText edProductName;
    private EditText edProductQuantity;
    private EditText edProductPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_edit_layout);

        final var productId = getProductId(savedInstanceState);

        edProductName = findViewById(R.id.edProductEditName);
        edProductQuantity = findViewById(R.id.edProductEditQuantity);
        edProductPrice = findViewById(R.id.edProductEditPrice);

        final var btnBack = findViewById(R.id.btnProductEditBack);
        btnBack.setOnClickListener(view -> displayLayout(this, MainActivity.class));

        final var btnSave = findViewById(R.id.btnProductEditSave);
        btnSave.setOnClickListener(view -> {

            final var product = new Product(
                    productId,
                    edProductName.getText().toString(),
                    Double.parseDouble(edProductQuantity.getText().toString()),
                    Double.parseDouble(edProductPrice.getText().toString())
            );

            productRepository.update(productId, product);

            displayLayout(this, MainActivity.class);
        });

        loadProductData(productId);
    }

    // --------------------------------------------------------------------------------

    private void loadProductData(final int id) {

        final var productHolder = productRepository.findById(id);

        productHolder.ifPresent(product -> {

            edProductName.setText(product.getName());
            edProductQuantity.setText(Double.toString(product.getQuantity()));
            edProductPrice.setText(Double.toString(product.getPrice()));
        });
    }

}
