package com.kissdevs.imagepicker.features.camera;

import android.content.Context;
import android.content.Intent;

import com.kissdevs.imagepicker.features.Feat_ImagePickerConfig;

public interface CameraModule {
    Intent getCameraIntent(Context context, Feat_ImagePickerConfig config);

    void getImage(Context context, Intent intent, OnImageReadyListener imageReadyListener);
}
