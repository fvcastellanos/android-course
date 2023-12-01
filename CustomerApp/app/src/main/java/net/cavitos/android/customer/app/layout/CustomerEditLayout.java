package net.cavitos.android.customer.app.layout;

import android.os.Bundle;
import android.widget.EditText;

import net.cavitos.android.customer.app.MainActivity;
import net.cavitos.android.customer.app.R;
import net.cavitos.android.customer.app.repository.CustomerRepository;
import net.cavitos.android.customer.app.repository.DBConnection;

public class CustomerEditLayout extends BaseForm {

    private final CustomerRepository customerRepository;

    public CustomerEditLayout() {

        this.customerRepository = new CustomerRepository(new DBConnection(this));
    }

    private EditText edCustomerName;
    private EditText edCustomerCountry;
    private EditText edCustomerCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit_layout);

        final var customerId = getCustomerId(savedInstanceState);

        edCustomerName = findViewById(R.id.edCustomerEditName);
        edCustomerCountry = findViewById(R.id.edCustomerEditCountry);
        edCustomerCompany = findViewById(R.id.edCustomerEditCompany);

        final var btnBack = findViewById(R.id.btnCustomerEditBack);
        btnBack.setOnClickListener(view -> displayLayout(this, MainActivity.class));

        final var btnSave = findViewById(R.id.btnCustomerEditSave);
        btnSave.setOnClickListener(view -> {

            customerRepository.update(
                    customerId,
                    edCustomerName.getText().toString(),
                    edCustomerCountry.getText().toString(),
                    edCustomerCompany.getText().toString()
            );

            displayLayout(this, MainActivity.class);
        });

        loadCustomerData(customerId);
    }

    // ------------------------------------------------------------------------------------

    private void loadCustomerData(final int id) {

        final var customerHolder = customerRepository.findById(id);

        customerHolder.ifPresent(customer -> {

            edCustomerName.setText(customer.getName());
            edCustomerCountry.setText(customer.getCountry());
            edCustomerCompany.setText(customer.getCompany());
        });
    }

}