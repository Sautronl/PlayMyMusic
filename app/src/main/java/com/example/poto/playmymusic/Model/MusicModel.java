package com.example.poto.playmymusic.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class MusicModel implements Parcelable{

    private String title;
    private int idMusic;
    private int idImageMusic;
    private CategoryModel categoryModel;

    public MusicModel(String title, int idMusic, int idImageMusic, CategoryModel categoryModel) {
        this.title = title;
        this.idMusic = idMusic;
        this.idImageMusic = idImageMusic;
        this.categoryModel = categoryModel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIdMusic() {
        return idMusic;
    }

    public void setIdMusic(int idMusic) {
        this.idMusic = idMusic;
    }

    public int getIdImageMusic() {
        return idImageMusic;
    }

    public void setIdImageMusic(int idImageMusic) {
        this.idImageMusic = idImageMusic;
    }

    public CategoryModel getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(CategoryModel categoryModel) {
        this.categoryModel = categoryModel;
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
        title = in.readString();
        idMusic = in.readInt();
        idImageMusic = in.readInt();
        categoryModel = in.readParcelable(CategoryModel.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(idMusic);
        dest.writeInt(idImageMusic);
        dest.writeParcelable(categoryModel, flags);
    }
}
