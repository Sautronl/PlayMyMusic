package com.example.poto.playmymusic;

import android.graphics.drawable.Drawable;

public class CategoryModel {
    private String name;
    private int idimg;

    public CategoryModel(String name, int idimg){
        this.name=name;
        this.idimg=idimg;
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
}
