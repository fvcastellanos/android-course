package net.cavitos.android.customer.app.layout;

import android.content.ContentValues;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import net.cavitos.android.customer.app.MainActivity;
import net.cavitos.android.customer.app.R;
import net.cavitos.android.customer.app.repository.CustomerRepository;
import net.cavitos.android.customer.app.repository.DBConnection;

import org.apache.commons.lang3.StringUtils;

public class CustomerForm extends BaseForm {

    private final CustomerRepository customerRepository;

    private EditText edCustomerName;

    private EditText edCustomerCountry;

    private EditText edCustomerCompany;

    private ImageButton btnSaveCustomer;

    public CustomerForm() {

        final var dbConnection = new DBConnection(this);
        this.customerRepository = new CustomerRepository(dbConnection);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_form);

        edCustomerName = findViewById(R.id.edCustomerName);
        edCustomerCountry = findViewById(R.id.edCustomerCountry);
        edCustomerCompany = findViewById(R.id.edCustomerCompany);

        btnSaveCustomer = findViewById(R.id.btnSaveCustomer);

        btnSaveCustomer.setOnClickListener(view -> {

            if (validate()) {

                final var contentValues = new ContentValues();
                contentValues.put("name", edCustomerName.getText().toString());
                contentValues.put("country", edCustomerCountry.getText().toString());
                contentValues.put("company", edCustomerCompany.getText().toString());

                customerRepository.add(contentValues);

                Toast.makeText(this, "Cliente ingresado", Toast.LENGTH_LONG)
                        .show();

                displayLayout(this, MainActivity.class);

                return;
            }

            Toast.makeText(this, "Debe ingresar todos los valores", Toast.LENGTH_LONG)
                    .show();

        });

        final var btnCustomerFormBack = findViewById(R.id.btnCustomerFormBack);

        btnCustomerFormBack.setOnClickListener((view -> displayLayout(this, MainActivity.class)));
    }

    private boolean validate() {

        final var name = edCustomerName.getText().toString();
        final var country = edCustomerCountry.getText().toString();
        final var company = edCustomerCompany.getText().toString();

        return StringUtils.isNotBlank(name) && StringUtils.isNotBlank(company) &&
                StringUtils.isNotBlank(country);
    }
}