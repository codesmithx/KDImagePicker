package com.kissdevs.imagepicker.features.camera;

import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;

import com.kissdevs.imagepicker.BuildConfig;
import com.kissdevs.imagepicker.features.Feat_ImagePickerConfig;
import com.kissdevs.imagepicker.helper.ImagePickerUtils;
import com.kissdevs.imagepicker.model.ImageFactory;

import java.io.File;
import java.io.Serializable;

public class DefaultCameraModule implements CameraModule, Serializable {

    private static final String TAG = "KDImagePicker";
    protected String currentImagePath;

    @Override
    public Intent getCameraIntent(Context context, Feat_ImagePickerConfig config) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File imageFile = ImagePickerUtils.createImageFile(config.getImageDirectory());
        if (imageFile != null) {
            Context appContext = context.getApplicationContext();

            String providerAuthority = context.getPackageName() + ".provider";

            Log.w(TAG, "Use file provider: " + providerAuthority);
            Uri uri = FileProvider.getUriForFile(appContext, providerAuthority, imageFile);

            currentImagePath = "file:" + imageFile.getAbsolutePath();
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

            ImagePickerUtils.grantAppPermission(context, intent, uri);

            return intent;
        }
        return null;
    }

    @Override
    public void getImage(final Context context, Intent intent, final OnImageReadyListener imageReadyListener) {
        if (imageReadyListener == null) {
            throw new IllegalStateException("OnImageReadyListener must not be null");
        }

        if (currentImagePath == null) {
            imageReadyListener.onImageReady(null);
            return;
        }

        final Uri imageUri = Uri.parse(currentImagePath);
        if (imageUri != null) {
            MediaScannerConnection.scanFile(context.getApplicationContext(),
                    new String[]{imageUri.getPath()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        @Override
                        public void onScanCompleted(String path, Uri uri) {
                            Log.v("KDImagePicker", "File " + path + " was scanned successfully: " + uri);

                            if (path == null) {
                                path = currentImagePath;
                            }
                            imageReadyListener.onImageReady(ImageFactory.singleListFromPath(path));
                            ImagePickerUtils.revokeAppPermission(context, imageUri);
                        }
                    });
        }
    }

}
