package net.cavitos.android.customer.app;

import android.os.Bundle;

import net.cavitos.android.customer.app.layout.BaseForm;
import net.cavitos.android.customer.app.layout.CustomerForm;

public class MainActivity extends BaseForm {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final var btnAddCustomer = findViewById(R.id.btnAddCustomer);
        btnAddCustomer.setOnClickListener((view) -> displayLayout(this, CustomerForm.class));
    }
}
