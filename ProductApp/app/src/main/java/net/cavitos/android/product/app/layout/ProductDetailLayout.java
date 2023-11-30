package net.cavitos.android.product.app.layout;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import android.os.Build;
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

        final var btnBack = findViewById(R.id.btnProductDetailBack);
        btnBack.setOnClickListener(view -> displayLayout(this, MainActivity.class));

        edProductName = findViewById(R.id.edProductDetailName);
        edProductQuantity = findViewById(R.id.edProductDetailQuantity);
        edProductPrice = findViewById(R.id.edProductDetailUnitPrice);
        edProductTotal = findViewById(R.id.edProductDetailTotal);

        loadProductData(getProductId(savedInstanceState));
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

    private int getProductId(final Bundle savedInstanceState) {

        if (isNull(savedInstanceState)) {

            final var bundle = getIntent()
                    .getExtras();

            if (nonNull(bundle)) {

                return bundle.getInt("productId");
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return savedInstanceState.getSerializable("customerId", Integer.class);
        }

        return 0;
    }
}
