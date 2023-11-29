package net.cavitos.android.product.app.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import net.cavitos.android.product.app.R;

public class ProductView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
    }
}