package com.example.pcpartsoftware;

public class MainModel {

    Integer image;
    String imgName;
    float ratingBar;
    String prices;

    //constructor
    public MainModel(Integer image, String imgName, float ratingBar, String prices){
        this.image = image;
        this.imgName = imgName;
        this.ratingBar = ratingBar;
        this.prices = prices;
    }

    //Getters:

    public Integer getImage() {
        return image;
    }

    public String getImgName() {
        return imgName;
    }

    public double getRatingBar() {
        return ratingBar;
    }

    public String getPrices() {
        return prices;
    }

}
