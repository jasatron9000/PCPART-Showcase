package com.example.pcpartsoftware;


public class Product {

    private String productName;
    private String productPrice;
    private static int count = 0;
    private int productID;
    private Integer productImg;
    private String productCategory;
    private float productRating;
    private String specs;
    private String text;


    public Product(String productName, String productPrice, String productCategory,
                   double productRating, Integer productImg) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        this.productRating = (float)productRating;
        this.productImg = productImg;
        this.productID = this.count;
        count++;

    }

    public float getProductRating() {
        return productRating;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public Integer getProductImg() {
        return productImg;
    }
}