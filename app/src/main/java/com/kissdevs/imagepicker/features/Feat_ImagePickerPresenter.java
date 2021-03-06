package com.kissdevs.imagepicker.features;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.kissdevs.imagepicker.R;
import com.kissdevs.imagepicker.features.camera.CameraModule;
import com.kissdevs.imagepicker.features.camera.DefaultCameraModule;
import com.kissdevs.imagepicker.features.camera.OnImageReadyListener;
import com.kissdevs.imagepicker.features.common.BasePresenter;
import com.kissdevs.imagepicker.features.common.ImageLoaderListener;
import com.kissdevs.imagepicker.model.Picker_Folder;
import com.kissdevs.imagepicker.model.Picker_Image;

import java.io.File;
import java.util.List;

public class Feat_ImagePickerPresenter extends BasePresenter<Feat_View_ImagePicker> {

    private Feat_ImageLoader imageLoader;
    private CameraModule cameraModule = new DefaultCameraModule();
    private Handler handler = new Handler(Looper.getMainLooper());

    public Feat_ImagePickerPresenter(Feat_ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public void abortLoad() {
        imageLoader.abortLoadImages();
    }

    public void loadImages(boolean isFolderMode) {
        if (!isViewAttached()) return;

        getView().showLoading(true);
        imageLoader.loadDeviceImages(isFolderMode, new ImageLoaderListener() {
            @Override
            public void onImageLoaded(final List<Picker_Image> images, final List<Picker_Folder> folders) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (isViewAttached()) {
                            getView().showFetchCompleted(images, folders);

                            if (folders != null) {
                                if (folders.isEmpty()) {
                                    getView().showEmpty();
                                } else {
                                    getView().showLoading(false);
                                }
                            } else {
                                if (images.isEmpty()) {
                                    getView().showEmpty();
                                } else {
                                    getView().showLoading(false);
                                }
                            }
                        }
                    }
                });
            }

            @Override
            public void onFailed(final Throwable throwable) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (isViewAttached()) {
                            getView().showError(throwable);
                        }
                    }
                });
            }
        });
    }

    public void onDoneSelectImages(List<Picker_Image> selectedImages) {
        if (selectedImages != null && selectedImages.size() > 0) {

            /** Scan selected images which not existed */
            for (int i = 0; i < selectedImages.size(); i++) {
                Picker_Image image = selectedImages.get(i);
                File file = new File(image.getPath());
                if (!file.exists()) {
                    selectedImages.remove(i);
                    i--;
                }
            }
            getView().finishPickImages(selectedImages);
        }
    }

    public void captureImage(Activity activity, Feat_ImagePickerConfig config, int requestCode) {
        Context context = activity.getApplicationContext();
        Intent intent = cameraModule.getCameraIntent(activity, config);
        if (intent == null) {
            Toast.makeText(context, context.getString(R.string.ef_error_create_image_file), Toast.LENGTH_LONG).show();
            return;
        }
        activity.startActivityForResult(intent, requestCode);
    }

    public void finishCaptureImage(Context context, Intent data, final Feat_ImagePickerConfig config) {
        cameraModule.getImage(context, data, new OnImageReadyListener() {
            @Override
            public void onImageReady(List<Picker_Image> images) {
                if (config.isReturnAfterFirst()) {
                    getView().finishPickImages(images);
                } else {
                    getView().showCapturedImage();
                }
            }
        });
    }
}
