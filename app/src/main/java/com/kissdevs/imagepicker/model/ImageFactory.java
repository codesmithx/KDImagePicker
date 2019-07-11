package com.kissdevs.imagepicker.model;

import com.kissdevs.imagepicker.helper.ImagePickerUtils;

import java.util.ArrayList;
import java.util.List;

public class ImageFactory {
    
    public static List<Picker_Image> singleListFromPath(String path) {
        List<Picker_Image> images = new ArrayList<>();
        images.add(new Picker_Image(0, ImagePickerUtils.getNameFromFilePath(path), path, true));
        return images;
    }
}
