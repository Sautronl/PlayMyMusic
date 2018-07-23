package com.example.poto.playmymusic;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

public class MusicModel implements Parcelable{
    private String category;
    private String title;
    private int id;
    private int imageId;

    public MusicModel(String category, String title, int id, int imageId) {
        this.category = category;
        this.title = title;
        this.id = id;
        this.imageId = imageId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }


    public static final Creator<MusicModel> CREATOR = new Creator<MusicModel>() {
        @Override
        public MusicModel createFromParcel(Parcel in) {
            return new MusicModel(in);
        }

        @Override
        public MusicModel[] newArray(int size) {
            return new MusicModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    protected MusicModel(Parcel in) {
        category = in.readString();
        title = in.readString();
        id = in.readInt();
        imageId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(category);
        dest.writeString(title);
        dest.writeInt(id);
        dest.writeInt(imageId);
    }
}
