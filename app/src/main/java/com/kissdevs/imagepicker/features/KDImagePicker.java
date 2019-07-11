package com.kissdevs.imagepicker.features;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.kissdevs.imagepicker.model.Picker_Image;

import java.util.ArrayList;
import java.util.List;

public abstract class KDImagePicker {

    public static final String EXTRA_SELECTED_IMAGES = "selectedImages";
    public static final String EXTRA_LIMIT = "limit";
    public static final String EXTRA_SHOW_CAMERA = "showCamera";
    public static final String EXTRA_MODE = "mode";
    public static final String EXTRA_FOLDER_MODE = "folderMode";
    public static final String EXTRA_FOLDER_TITLE = "folderTitle";
    public static final String EXTRA_IMAGE_TITLE = "imageTitle";
    public static final String EXTRA_IMAGE_DIRECTORY = "imageDirectory";
    public static final String EXTRA_RETURN_AFTER_FIRST = "returnAfterFirst";

    public static final int MAX_LIMIT = 99;

    public static final int MODE_SINGLE = 1;
    public static final int MODE_MULTIPLE = 2;

    private Feat_ImagePickerConfig config;

    public abstract void start(int requestCode);

    public static class Feat_ImagePickerWithActivity extends KDImagePicker {

        private Activity activity;

        public Feat_ImagePickerWithActivity(Activity activity) {
            this.activity = activity;
            init(activity);
        }

        @Override
        public void start(int requestCode) {
            Intent intent = getIntent(activity);
            activity.startActivityForResult(intent, requestCode);
        }
    }

    public static class Feat_ImagePickerWithFragment extends KDImagePicker {

        private Fragment fragment;

        public Feat_ImagePickerWithFragment(Fragment fragment) {
            this.fragment = fragment;
            init(fragment.getActivity());
        }

        @Override
        public void start(int requestCode) {
            Intent intent = getIntent(fragment.getActivity());
            fragment.startActivityForResult(intent, requestCode);
        }
    }

    /* --------------------------------------------------- */
    /* > Stater */
    /* --------------------------------------------------- */

    public void init(Context context) {
        config = new Feat_ImagePickerConfig(context);
    }

    public static Feat_ImagePickerWithActivity create(Activity activity) {
        return new Feat_ImagePickerWithActivity(activity);
    }

    public static Feat_ImagePickerWithFragment create(Fragment fragment) {
        return new Feat_ImagePickerWithFragment(fragment);
    }

    /* --------------------------------------------------- */
    /* > Builder */
    /* --------------------------------------------------- */

    public KDImagePicker single() {
        config.setMode(KDImagePicker.MODE_SINGLE);
        return this;
    }

    public KDImagePicker multi() {
        config.setMode(KDImagePicker.MODE_MULTIPLE);
        return this;
    }

    public KDImagePicker returnAfterFirst(boolean returnAfterFirst) {
        config.setReturnAfterFirst(returnAfterFirst);
        return this;
    }

    public KDImagePicker limit(int count) {
        config.setLimit(count);
        return this;
    }

    public KDImagePicker showCamera(boolean show) {
        config.setShowCamera(show);
        return this;
    }

    public KDImagePicker folderTitle(String title) {
        config.setFolderTitle(title);
        return this;
    }

    public KDImagePicker imageTitle(String title) {
        config.setImageTitle(title);
        return this;
    }

    public KDImagePicker origin(ArrayList<Picker_Image> images) {
        config.setSelectedImages(images);
        return this;
    }

    public KDImagePicker folderMode(boolean folderMode) {
        config.setFolderMode(folderMode);
        return this;
    }

    //"Camera" is set as default
//    public KDImagePicker imageDirectory(String directory) {
//        config.setImageDirectory(directory);
//        return this;
//    }

    public Intent getIntent(Context context) {
        Intent intent = new Intent(context, Activity_ImagePicker.class);
        intent.putExtra(Feat_ImagePickerConfig.class.getSimpleName(), config);
        return intent;
    }

    /* --------------------------------------------------- */
    /* > Helper */
    /* --------------------------------------------------- */

    public static List<Picker_Image> getImages(Intent intent) {
        if (intent == null) {
            return null;
        }
        return intent.getParcelableArrayListExtra(KDImagePicker.EXTRA_SELECTED_IMAGES);
    }
}
