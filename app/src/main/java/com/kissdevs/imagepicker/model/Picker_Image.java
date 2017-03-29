package com.kissdevs.imagepicker.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Picker_Image implements Parcelable {

    private long id;
    private String name;
    private String path;
    private boolean isSelected;

    public Picker_Image(long id, String name, String path, boolean isSelected) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.isSelected = isSelected;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.path);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
    }

    protected Picker_Image(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.path = in.readString();
        this.isSelected = in.readByte() != 0;
    }

    public static final Creator<Picker_Image> CREATOR = new Creator<Picker_Image>() {
        @Override
        public Picker_Image createFromParcel(Parcel source) {
            return new Picker_Image(source);
        }

        @Override
        public Picker_Image[] newArray(int size) {
            return new Picker_Image[size];
        }
    };
}
