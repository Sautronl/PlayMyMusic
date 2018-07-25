package com.example.poto.playmymusic;

import android.os.Parcel;
import android.os.Parcelable;

public class CategoryModel implements Parcelable{
    private String name;
    private int idimg;
    private int color;

    public CategoryModel(String name, int idimg,int color){
        this.name=name;
        this.idimg=idimg;
        this.color=color;
    }

    protected CategoryModel(Parcel in) {
        name = in.readString();
        idimg = in.readInt();
        color = in.readInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getIdimg() {
        return idimg;
    }

    public void setIdimg(int idimg) {
        this.idimg = idimg;
    }
    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


    public static final Creator<CategoryModel> CREATOR = new Creator<CategoryModel>() {
        @Override
        public CategoryModel createFromParcel(Parcel in) {
            return new CategoryModel(in);
        }

        @Override
        public CategoryModel[] newArray(int size) {
            return new CategoryModel[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(idimg);
        dest.writeInt(color);
    }
}
