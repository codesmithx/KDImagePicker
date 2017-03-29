package com.kissdevs.imagepicker.features.common;

import com.kissdevs.imagepicker.model.Picker_Folder;
import com.kissdevs.imagepicker.model.Picker_Image;

import java.util.List;

public interface ImageLoaderListener {
    void onImageLoaded(List<Picker_Image> images, List<Picker_Folder> folders);
    void onFailed(Throwable throwable);
}
