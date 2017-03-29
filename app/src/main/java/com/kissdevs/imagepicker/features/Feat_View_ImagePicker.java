package com.kissdevs.imagepicker.features;

import com.kissdevs.imagepicker.features.common.MvpView;
import com.kissdevs.imagepicker.model.Picker_Folder;
import com.kissdevs.imagepicker.model.Picker_Image;

import java.util.List;

public interface Feat_View_ImagePicker extends MvpView {
    void showLoading(boolean isLoading);
    void showFetchCompleted(List<Picker_Image> images, List<Picker_Folder> folders);
    void showError(Throwable throwable);
    void showEmpty();
    void showCapturedImage();
    void finishPickImages(List<Picker_Image> images);
}
