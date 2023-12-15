package net.cavitos.android.customer.app.layout;

import static java.util.Objects.nonNull;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PhotoForm extends BaseForm {

    protected static final int CAMERA_PERMISSION = 205;
    protected static final int CAMERA_SERVICE_CODE = 301;

    protected String photoPath;

    protected void takePhoto(final Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) ==
                    PackageManager.PERMISSION_GRANTED) {

                takePhoto();

                return;

            }

            final var permissions = new String[] { Manifest.permission.CAMERA };
            ActivityCompat.requestPermissions(this, permissions, CAMERA_PERMISSION);

            return;
        }

        takePhoto();

    }

    protected void verifyPermissions(@NonNull String[] permissions, @NonNull int[] grantResults) {

        if (permissions.length > 0 && PackageManager.PERMISSION_GRANTED == grantResults[0]) {

            takePhoto();
            return;
        }

        Toast.makeText(this, "Necesita conceder permiso para acceder a la camara",
                        Toast.LENGTH_LONG)
                .show();
    }

    // -----------------------------------------------------------------------------------------

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
        return File.createTempFile(fileName, ".jpg", imagePath);
    }
}
