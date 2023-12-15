package net.cavitos.android.customer.app.layout;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import net.cavitos.android.customer.app.MainActivity;
import net.cavitos.android.customer.app.R;
import net.cavitos.android.customer.app.repository.CustomerRepository;
import net.cavitos.android.customer.app.repository.DBConnection;

public class CustomerEditLayout extends PhotoForm {

    private final CustomerRepository customerRepository;

    public CustomerEditLayout() {

        this.customerRepository = new CustomerRepository(new DBConnection(this));
    }

    private EditText edCustomerName;
    private EditText edCustomerCountry;
    private EditText edCustomerCompany;

    private ImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit_layout);

        final var customerId = getCustomerId(savedInstanceState);

        edCustomerName = findViewById(R.id.edCustomerEditName);
        edCustomerCountry = findViewById(R.id.edCustomerEditCountry);
        edCustomerCompany = findViewById(R.id.edCustomerEditCompany);
        imgPhoto = findViewById(R.id.imgCustomerEditView);

        final var btnBack = findViewById(R.id.btnCustomerEditBack);
        btnBack.setOnClickListener(view -> displayLayout(this, MainActivity.class));

        final var btnSave = findViewById(R.id.btnCustomerEditSave);
        btnSave.setOnClickListener(view -> {

            customerRepository.update(
                    customerId,
                    edCustomerName.getText().toString(),
                    edCustomerCountry.getText().toString(),
                    edCustomerCompany.getText().toString(),
                    photoPath
            );

            displayLayout(this, MainActivity.class);
        });

        final var btnPhoto = findViewById(R.id.btnCustomerEditPhoto);
        btnPhoto.setOnClickListener(view -> takePhoto(this));

        loadCustomerData(customerId);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (CAMERA_SERVICE_CODE == requestCode && Activity.RESULT_OK == resultCode) {

            imgPhoto.setImageURI(Uri.parse(photoPath));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        verifyPermissions(permissions, grantResults);
    }

    // ------------------------------------------------------------------------------------

    private void loadCustomerData(final int id) {

        final var customerHolder = customerRepository.findById(id);

        customerHolder.ifPresent(customer -> {

            photoPath = customer.getPhotoPath();
            edCustomerName.setText(customer.getName());
            edCustomerCountry.setText(customer.getCountry());
            edCustomerCompany.setText(customer.getCompany());
            imgPhoto.setImageURI(Uri.parse(photoPath));
        });
    }
}