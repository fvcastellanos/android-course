package net.cavitos.android.customer.app.layout;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import com.google.common.collect.ImmutableMap;

import net.cavitos.android.customer.app.MainActivity;
import net.cavitos.android.customer.app.R;
import net.cavitos.android.customer.app.repository.CustomerRepository;
import net.cavitos.android.customer.app.repository.DBConnection;

public class CustomerDetailLayout extends BaseForm {

    private final CustomerRepository customerRepository;

    private EditText edCustomerName;
    private EditText edCustomerCountry;
    private EditText edCustomerCompany;

    public CustomerDetailLayout() {

        final var dbConnection = new DBConnection(this);
        this.customerRepository = new CustomerRepository(dbConnection);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail_layout);

        final var customerId = getCustomerId(savedInstanceState);

        final var btnBack = findViewById(R.id.btnCustomerDetailBack);
        btnBack.setOnClickListener(view -> displayLayout(this, MainActivity.class));

        final var btnEdit = findViewById(R.id.btnCustomerDetailEdit);

        btnEdit.setOnClickListener(view -> {

            final var intent = new Intent(this, CustomerEditLayout.class);
            intent.putExtra("customerId", customerId);

            startActivity(intent);
        });

        edCustomerName = findViewById(R.id.edCustomerDetailName);
        edCustomerCountry = findViewById(R.id.edCustomerDetailCountry);
        edCustomerCompany = findViewById(R.id.edCustomerDetailCompany);

        loadCustomerData(customerId);
    }

    // ------------------------------------------------------------------------------------

    private void loadCustomerData(final int id) {

        final var customerHolder = customerRepository.findById(id);

        customerHolder.ifPresent(customer -> {

            edCustomerName.setText(customer.getName());
            edCustomerCountry.setText(customer.getCountry());
            edCustomerCompany.setText(customer.getCompany());

            edCustomerName.setInputType(InputType.TYPE_NULL);
            edCustomerCompany.setInputType(InputType.TYPE_NULL);
            edCustomerCountry.setInputType(InputType.TYPE_NULL);
        });
    }
}