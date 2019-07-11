package com.kissdevs.imagepicker.model;

import java.util.ArrayList;

/**
 * Created by boss1088 on 8/22/16.
 */
public class Picker_Folder {

    private String folderName;
    private ArrayList<Picker_Image> images;

    public Picker_Folder(String bucket) {
        folderName = bucket;
        images = new ArrayList<>();
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public ArrayList<Picker_Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Picker_Image> images) {
        this.images = images;
    }
}
