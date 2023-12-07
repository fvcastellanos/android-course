package net.cavitos.android.photo.app;

import static java.util.Objects.nonNull;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_PERMISSION = 205;
    private static final int CAMERA_SERVICE_CODE = 301;

    private ImageView imgPhotoView;

    private String photoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgPhotoView = findViewById(R.id.imgPhotoView);

        final var btnOpenCameraService = findViewById(R.id.btnOpenCameraService);
        btnOpenCameraService.setOnClickListener(view -> {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
                        PackageManager.PERMISSION_GRANTED) {

                    takePhoto();

                    return;

                }

                final var permissions = new String[]{ Manifest.permission.CAMERA };
                ActivityCompat.requestPermissions(this, permissions, CAMERA_PERMISSION);

                return;
            }

            takePhoto();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (CAMERA_SERVICE_CODE == requestCode && Activity.RESULT_OK == resultCode) {

            imgPhotoView.setImageURI(Uri.parse(photoPath));
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

    private void takePhoto() {

        final var intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        final var componentName = intent.resolveActivity(getPackageManager());

        if (nonNull(intent.resolveActivity(getPackageManager()))) {

            try {

                final var file = buildFile();
                final var photoUri = FileProvider.getUriForFile(this,
                        "net.cavitos.android.photo.app", file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);

                startActivityForResult(intent, CAMERA_SERVICE_CODE);

            } catch (IOException exception) {

                throw new RuntimeException(exception);
            }
        }
    }

    private File buildFile() throws IOException{

        final var fileName = UUID.randomUUID().toString();

        final var imagePath = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        final var tempFile = File.createTempFile(fileName, ".jpg", imagePath);

        photoPath = tempFile.getAbsolutePath();

        return tempFile;
    }
}