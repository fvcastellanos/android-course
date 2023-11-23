package net.cavitos.android.product.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import net.cavitos.android.product.app.layout.BaseLayout;
import net.cavitos.android.product.app.layout.ProductLayout;

public class MainActivity extends BaseLayout {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final var btnAddProduct = findViewById(R.id.btnOpenProductForm);

        btnAddProduct.setOnClickListener((view -> displayLayout(this, ProductLayout.class)));
    }
}
