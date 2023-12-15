package net.cavitos.android.customer.app.layout;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static java.util.Objects.nonNull;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import net.cavitos.android.customer.app.MainActivity;
import net.cavitos.android.customer.app.R;
import net.cavitos.android.customer.app.repository.CustomerRepository;
import net.cavitos.android.customer.app.repository.DBConnection;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class CustomerForm extends BaseForm {

    private static final int CAMERA_PERMISSION = 205;
    private static final int CAMERA_SERVICE_CODE = 301;

    private final CustomerRepository customerRepository;

    private EditText edCustomerName;

    private EditText edCustomerCountry;

    private EditText edCustomerCompany;

    private ImageView imgCustomerForm;

    private String photoPath;

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

        imgCustomerForm = findViewById(R.id.imgFormCustomer);

        final var btnSaveCustomer = findViewById(R.id.btnSaveCustomer);

        btnSaveCustomer.setOnClickListener(view -> {

            if (validate()) {

                final var contentValues = new ContentValues();
                contentValues.put("name", edCustomerName.getText().toString());
                contentValues.put("country", edCustomerCountry.getText().toString());
                contentValues.put("company", edCustomerCompany.getText().toString());

                var path = "";
                if (isNotBlank(photoPath)) {

                    path = photoPath;
                }

                contentValues.put("photo_path", path);

                customerRepository.add(contentValues);

                photoPath = "";

                Toast.makeText(this, "Cliente ingresado", Toast.LENGTH_LONG)
                        .show();

                displayLayout(this, MainActivity.class);

                return;
            }

            Toast.makeText(this, "Debe ingresar todos los valores", Toast.LENGTH_LONG)
                    .show();

        });

        final var btnPhoto = findViewById(R.id.btnFormCustomerPhoto);

        btnPhoto.setOnClickListener(view -> {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
                        PackageManager.PERMISSION_GRANTED) {

                    takePhoto();

                    return;

                }

                final var permissions = new String[] { Manifest.permission.CAMERA };
                ActivityCompat.requestPermissions(this, permissions, CAMERA_PERMISSION);

                return;
            }

            takePhoto();
        });

        final var btnCustomerFormBack = findViewById(R.id.btnCustomerFormBack);

        btnCustomerFormBack.setOnClickListener((view -> displayLayout(this, MainActivity.class)));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (CAMERA_SERVICE_CODE == requestCode && Activity.RESULT_OK == resultCode) {

            imgCustomerForm.setImageURI(Uri.parse(photoPath));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (permissions.length > 0 && PackageManager.PERMISSION_GRANTED == grantResults[0]) {

            takePhoto();
            return;
        }

        Toast.makeText(this, "Necesita conceder permiso para acceder a la camara", Toast.LENGTH_LONG)
                .show();
    }

    // -----------------------------------------------------------------------------------------

    private boolean validate() {

        final var name = edCustomerName.getText().toString();
        final var country = edCustomerCountry.getText().toString();
        final var company = edCustomerCompany.getText().toString();

        return isNotBlank(name) && isNotBlank(company) &&
                isNotBlank(country);
    }

    private void takePhoto() {

        final var intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        final var componentName = intent.resolveActivity(getPackageManager());

        if (nonNull(componentName)) {

            try {

                final var file = buildFile();
                final var photoUri = FileProvider.getUriForFile(this,
                        "net.cavitos.android.customer.app", file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);

                photoPath = file.getAbsolutePath();

                startActivityForResult(intent, CAMERA_SERVICE_CODE);

            } catch (IOException exception) {

                throw new RuntimeException(exception);
            }
        }
    }

    private File buildFile() throws IOException {

        final var fileName = UUID.randomUUID().toString();

        final var imagePath = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        final var tempFile = File.createTempFile(fileName, ".jpg", imagePath);

        return tempFile;
    }
}