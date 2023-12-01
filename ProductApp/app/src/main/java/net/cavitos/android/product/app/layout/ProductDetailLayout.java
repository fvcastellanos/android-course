package net.cavitos.android.product.app.layout;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import net.cavitos.android.product.app.MainActivity;
import net.cavitos.android.product.app.R;
import net.cavitos.android.product.app.repository.ProductRepository;

public class ProductDetailLayout extends BaseLayout {

    private final ProductRepository productRepository;

    public ProductDetailLayout() {

        this.productRepository = new ProductRepository(this);
    }

    private EditText edProductName;
    private EditText edProductQuantity;
    private EditText edProductPrice;
    private EditText edProductTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail_layout);

        final var productId = getProductId(savedInstanceState);

        final var btnBack = findViewById(R.id.btnProductDetailBack);
        btnBack.setOnClickListener(view -> displayLayout(this, MainActivity.class));

        final var btnEdit = findViewById(R.id.btnProductDetailEdit);
        btnEdit.setOnClickListener(view -> {

            final var intent = new Intent(this, ProductEditLayout.class);
            intent.putExtra("productId", productId);

            startActivity(intent);
        });

        edProductName = findViewById(R.id.edProductDetailName);
        edProductQuantity = findViewById(R.id.edProductDetailQuantity);
        edProductPrice = findViewById(R.id.edProductDetailUnitPrice);
        edProductTotal = findViewById(R.id.edProductDetailTotal);

        loadProductData(productId);
    }

    // ---------------------------------------------------------------------------

    private void loadProductData(final int id) {

        final var productHolder = productRepository.findById(id);

        productHolder.ifPresent(product -> {

            edProductName.setText(product.getName());
            edProductQuantity.setText(Double.toString(product.getQuantity()));
            edProductPrice.setText(Double.toString(product.getPrice()));
            edProductTotal.setText(Double.toString(product.getQuantity() * product.getPrice()));

            edProductPrice.setInputType(InputType.TYPE_NULL);
            edProductTotal.setInputType(InputType.TYPE_NULL);
            edProductQuantity.setInputType(InputType.TYPE_NULL);
            edProductPrice.setInputType(InputType.TYPE_NULL);
        });
    }
}
