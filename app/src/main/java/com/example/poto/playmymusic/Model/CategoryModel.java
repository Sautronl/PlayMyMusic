package com.example.poto.playmymusic.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class CategoryModel implements Parcelable{
    private String category;
    private int idImageCategory;
    private int couleurtexte;

    public CategoryModel(String category, int idImageCategory,int couleurtexte){
        this.category=category;
        this.idImageCategory=idImageCategory;
        this.couleurtexte=couleurtexte;
    }

    public String getName() {
        return category;
    }

    public void setName(String name) {
        this.category = name;
    }
    public int getIdimg() {
        return idImageCategory;
    }

    public void setIdimg(int idimg) {
        this.idImageCategory = idimg;
    }

    public int getCouleurtexte() {
        return couleurtexte;
    }

    public void setCouleurtexte(int couleurtexte) {
        this.couleurtexte = couleurtexte;
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

    protected CategoryModel(Parcel in) {
        category = in.readString();
        idImageCategory = in.readInt();
        couleurtexte = in.readInt();
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(category);
        dest.writeInt(idImageCategory);
        dest.writeInt(couleurtexte);
    }
}
