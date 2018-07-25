package com.example.poto.playmymusic;

public class CategoryModel {
    private String name;
    private int idimg;
    private int color;

    public CategoryModel(String name, int idimg,int color){
        this.name=name;
        this.idimg=idimg;
        this.color=color;
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
}
