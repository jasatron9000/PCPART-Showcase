package com.example.pcpartsoftware;

import android.util.Log;

import java.util.ArrayList;

public class Catalogue {

    private ArrayList<Product> catalogue;


    public Catalogue() {
        this.catalogue = new ArrayList<>();
    }

    public void addToList(String productName, String productPrice, String productCategory,
                          double productRating, Integer productImg){

        this.catalogue.add(new Product(productName, productPrice, productCategory,
        productRating, productImg));

    }

    public ArrayList<Product> getCatalogue() {
        return catalogue;
    }

    public Product returnProduct(int ID){
        for(int i =  0; i < this.catalogue.size(); i++){
            if(this.catalogue.get(i).getProductID() == ID){
                return catalogue.get(i);
            }
        }

        return null;
    }

    public ArrayList<Product> returnListByCategory(String category){
        ArrayList<Product> list = new ArrayList<>();
        for(int i =  0; i < this.catalogue.size(); i++){
            if(this.catalogue.get(i).getProductCategory().equals(category)){
                list.add(catalogue.get(i));
            }
        }

        Log.i("SELECTION", "CAT FIRED" + ", " + category);


        if(list.size() > 0){
            return list;
        }
        else{
            return this.catalogue;
        }

    }

    public ArrayList<Product> returnListByName(String name){
        ArrayList<Product> list = new ArrayList<>();
        for(int i =  0; i < this.catalogue.size(); i++){
            if(this.catalogue.get(i).getProductName().toLowerCase().replace(" ","")
                    .contains(name.toLowerCase().replace(" ",""))){
                list.add(catalogue.get(i));
            }
        }

        return list;

    }

    public ArrayList<Product> getListbyRating(int length){
        ArrayList<Product> unsorted = new ArrayList<>(this.catalogue);
        ArrayList<Product> sorted = new ArrayList<Product>();
        ArrayList<Product> finalOut = new ArrayList<Product>();

        while(unsorted.size() != 0){
            float largestRating = 0;
            int largestIndex = 0;

            for (int i = 0; i < unsorted.size(); i++){
                if (largestRating < unsorted.get(i).getProductRating()){
                    largestIndex = i;
                    largestRating = unsorted.get(i).getProductRating();
                }
            }

            sorted.add(unsorted.get(largestIndex));
            unsorted.remove(largestIndex);
        }

        if (length > 0 && length <= sorted.size()){
            for (int i = 0; i < length; i++){
                finalOut.add(sorted.get(i));
            }
        }
        else{
            finalOut = sorted;
        }

        return finalOut;
    }


}
