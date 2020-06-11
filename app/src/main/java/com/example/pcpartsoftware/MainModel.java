package com.example.pcpartsoftware;

public class MainModel {

    Integer image;
    String imgName;

    public MainModel(Integer image, String imgName){
        this.image = image;
        this.imgName = imgName;
    }

    public Integer getImage() {
        return image;
    }

    public String getImgName() {
        return imgName;
    }

}
