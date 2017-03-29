package com.kissdevs.imagepicker.features.camera;

import com.kissdevs.imagepicker.model.Picker_Image;

import java.util.List;

public interface OnImageReadyListener {
    void onImageReady(List<Picker_Image> image);
}
