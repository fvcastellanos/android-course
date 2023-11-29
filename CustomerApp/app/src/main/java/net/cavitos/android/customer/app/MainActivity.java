package net.cavitos.android.customer.app;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.cavitos.android.customer.app.adapter.CustomerViewAdapter;
import net.cavitos.android.customer.app.layout.BaseForm;
import net.cavitos.android.customer.app.layout.CustomerForm;
import net.cavitos.android.customer.app.repository.CustomerRepository;
import net.cavitos.android.customer.app.repository.DBConnection;

public class MainActivity extends BaseForm {

    private final CustomerRepository customerRepository;

    private RecyclerView recyclerView;

    public MainActivity() {

        final var dbConnection = new DBConnection(this);
        this.customerRepository = new CustomerRepository(dbConnection);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final var btnAddCustomer = findViewById(R.id.btnAddCustomer);
        btnAddCustomer.setOnClickListener((view) -> displayLayout(this, CustomerForm.class));

        recyclerView = findViewById(R.id.rvCustomerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final var customers = customerRepository.getCustomers();
        final var customerAdapter = new CustomerViewAdapter(customers);

        recyclerView.setAdapter(customerAdapter);
    }
}
