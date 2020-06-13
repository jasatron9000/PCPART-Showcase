package com.example.pcpartsoftware;


import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {

    private String productName;
    private String productPrice;
    private static int count = 0;
    private int productID;
    private int[] productImg;
    private String productCategory;
    private float productRating;
    private String overview;
    private String specs;
    private String descr;


    public Product(String productName, String productPrice, String productCategory,
                   double productRating, int[] productImg, String specs,
                   String descr, String overview) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        this.productRating = (float)productRating;
        this.productImg = productImg;
        this.overview = overview;
        this.specs = specs;
        this.descr = descr;
        this.productID = this.count;

        count++;

    }

    protected Product(Parcel in) {
        productName = in.readString();
        productPrice = in.readString();
        productID = in.readInt();
        productImg = in.createIntArray();
        productCategory = in.readString();
        productRating = in.readFloat();
        overview = in.readString();
        specs = in.readString();
        descr = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

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

    public int[] getProductImg() {
        return productImg;
    }

    public String getDescr() {
        return descr;
    }

    public String getOverview() {
        return overview;
    }

    public String getSpecs() {
        return specs;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productName);
        dest.writeString(productPrice);
        dest.writeInt(productID);
        dest.writeIntArray(productImg);
        dest.writeString(productCategory);
        dest.writeFloat(productRating);
        dest.writeString(overview);
        dest.writeString(specs);
        dest.writeString(descr);
    }
}