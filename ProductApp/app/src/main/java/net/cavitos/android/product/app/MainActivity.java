package net.cavitos.android.product.app;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import net.cavitos.android.product.app.adapter.ProductViewAdapter;
import net.cavitos.android.product.app.layout.BaseLayout;
import net.cavitos.android.product.app.layout.ProductLayout;
import net.cavitos.android.product.app.repository.ProductRepository;

public class MainActivity extends BaseLayout {

    private final ProductRepository productRepository;

    public MainActivity() {

        this.productRepository = new ProductRepository(this);
    }

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final var btnAddProduct = findViewById(R.id.btnOpenProductForm);

        btnAddProduct.setOnClickListener((view -> displayLayout(this, ProductLayout.class)));

        recyclerView = findViewById(R.id.rvProductListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final var products = productRepository.getProducts();
        final var productAdapter = new ProductViewAdapter(products);

        recyclerView.setAdapter(productAdapter);
    }
}
