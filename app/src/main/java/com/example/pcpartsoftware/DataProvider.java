package com.example.pcpartsoftware;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Log;

public class DataProvider {
    private static DataProvider instance_dp = null;
    private static Boolean hasDataCreated;
    private ClientDatabase cDB;
    private Catalogue cat;

    private DataProvider(){
        cDB = new ClientDatabase();
        cat = new Catalogue();
        hasDataCreated = false;
    }

    public static DataProvider getInstance(){
        if (instance_dp == null){
            instance_dp = new DataProvider();
        }


        return instance_dp;
    }

    public void generateItems(Context context){
        if(!hasDataCreated){
            Log.i("DATA", "The data is being generated");
            generateUsers();
            generateProducts(context);
        }
        else{
            Log.i("DATA", "The data has already been created");
        }

        hasDataCreated = true;
    }

    private void generateUsers(){
        System.out.println("Users Generated \n");

        this.cDB.addNewUser("jbuq319", "admin", "jbuq319@aucklanduni.ac.nz", "0211234567", "Jason Buquiran","6a Gos Dr.");
        this.cDB.addNewUser("jmuk184", "idk", "jbuq319@aucklanduni.ac.nz", "0217654321", "Jacob Mukkada","53 Hat Dr.");
        this.cDB.addNewUser("guest", "password", "guest@gmail.com", "0210000000", "Guest Account","13 Guest Rd.");
    }

    private void generateProducts(Context context){
        System.out.println("Products Generated \n");

        int[] img1 =  {R.drawable.geforce, R.drawable.gigabyte, R.drawable.i9};
        int[] img2 =  {R.drawable.gigabyte, R.drawable.i9, R.drawable.radeon};
        Resources res = context.getResources();

        initialiseProducts(context, R.array.cpu_list, R.array.cpu_imgs, "CPU");
        initialiseProducts(context, R.array.gpu_list, R.array.cpu_imgs, "GPU");
        initialiseProducts(context, R.array.ram_list, R.array.cpu_imgs, "RAM");

    }

    public ClientDatabase getcDB() {
        return cDB;
    }

    public Catalogue getCat() {
        return cat;
    }

    private void initialiseProducts(Context context, int cData, int cImg, String Category){
        //Retrieve the data from the XML files
        Resources res = context.getResources();
        TypedArray data = res.obtainTypedArray(cData);
        TypedArray imgs = res.obtainTypedArray(cImg);

        //Loop through the length of the data
        for(int i = 0; i < data.length(); i++){
            //Current Array
            int currDataID = data.getResourceId(i, 0);
            int currImgID = data.getResourceId(i, 0);

            //Check if IDs are detected and if they do not exist a warning message is displayed
            if(currDataID > 0 && currImgID > 0){

                //The information stored in the index of the array
                String[] currentD = context.getResources().getStringArray(data.getResourceId(i, 0));
                TypedArray currentImgArray = res.obtainTypedArray(currImgID);
                int[] currentI = new int[3];

                //Loops through the three image IDs and if the Id cannot be detected a placeholder
                //image is used
                for(int j = 0; j < currentImgArray.length(); j++){
                    int indexedImage = currentImgArray.getResourceId(i, 0);

                    if (indexedImage > 0){
                        currentI[i] = indexedImage;
                    }
                    else{
                        currentI[i] = R.drawable.geforce;
                    }
                }

                //Load all the information to the category class
                this.cat.addToList(currentD[0], currentD[1], Category, Double.parseDouble(currentD[2]),
                        currentI, currentD[3], currentD[4]);

                Log.i("DATA LOAD", Category + ", " + currentD[0]);
                currentImgArray.recycle();

            }
            else{
                Log.i("DATA LOAD", "Failed To Load");
            }
        }

        //Garbage Collection
        data.recycle();
        imgs.recycle();
    }
}
