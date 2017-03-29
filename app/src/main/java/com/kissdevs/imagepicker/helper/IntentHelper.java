package com.kissdevs.imagepicker.helper;

import android.content.Context;
import android.content.Intent;

import com.kissdevs.imagepicker.features.Feat_ImagePickerConfig;
import com.kissdevs.imagepicker.model.Picker_Image;

import static com.kissdevs.imagepicker.features.KDImagePicker.EXTRA_FOLDER_MODE;
import static com.kissdevs.imagepicker.features.KDImagePicker.EXTRA_FOLDER_TITLE;
import static com.kissdevs.imagepicker.features.KDImagePicker.EXTRA_IMAGE_DIRECTORY;
import static com.kissdevs.imagepicker.features.KDImagePicker.EXTRA_IMAGE_TITLE;
import static com.kissdevs.imagepicker.features.KDImagePicker.EXTRA_LIMIT;
import static com.kissdevs.imagepicker.features.KDImagePicker.EXTRA_MODE;
import static com.kissdevs.imagepicker.features.KDImagePicker.EXTRA_RETURN_AFTER_FIRST;
import static com.kissdevs.imagepicker.features.KDImagePicker.EXTRA_SELECTED_IMAGES;
import static com.kissdevs.imagepicker.features.KDImagePicker.EXTRA_SHOW_CAMERA;
import static com.kissdevs.imagepicker.features.KDImagePicker.MAX_LIMIT;
import static com.kissdevs.imagepicker.features.KDImagePicker.MODE_MULTIPLE;

public class IntentHelper {

    public static Feat_ImagePickerConfig makeConfigFromIntent(Context context, Intent intent) {
        Feat_ImagePickerConfig config = new Feat_ImagePickerConfig(context);
        config.setMode(intent.getIntExtra(EXTRA_MODE, MODE_MULTIPLE));
        config.setLimit(intent.getIntExtra(EXTRA_LIMIT, MAX_LIMIT));
        config.setShowCamera(intent.getBooleanExtra(EXTRA_SHOW_CAMERA, true));
        config.setFolderTitle(intent.getStringExtra(EXTRA_FOLDER_TITLE));
        config.setImageTitle(intent.getStringExtra(EXTRA_IMAGE_TITLE));
        config.setSelectedImages(intent.<Picker_Image>getParcelableArrayListExtra(EXTRA_SELECTED_IMAGES));
        config.setFolderMode(intent.getBooleanExtra(EXTRA_FOLDER_MODE, true));
        config.setImageDirectory(intent.getStringExtra(EXTRA_IMAGE_DIRECTORY));
        config.setReturnAfterFirst(intent.getBooleanExtra(EXTRA_RETURN_AFTER_FIRST, false));
        return config;
    }
}
