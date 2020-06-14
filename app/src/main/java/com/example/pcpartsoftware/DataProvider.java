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

        initialiseProducts(context, R.array.cpu_list, img1, "CPU");
        initialiseProducts(context, R.array.gpu_list, img1, "GPU");
        initialiseProducts(context, R.array.ram_list, img1, "RAM");

    }

    public ClientDatabase getcDB() {
        return cDB;
    }

    public Catalogue getCat() {
        return cat;
    }

    private void initialiseProducts(Context context, int cData, int[] cImg, String Category){
        //Retrieve the data
        Resources res = context.getResources();
        TypedArray data = res.obtainTypedArray(cData);


        for(int i = 0; i < data.length(); i++){
            //Current Array
            int currID = data.getResourceId(i, 0);

            if(currID > 0){
                String[] current = context.getResources().getStringArray(data.getResourceId(i, 0));

                this.cat.addToList(current[0], current[1], Category, Double.parseDouble(current[2]),
                        cImg, current[3], current[4]);

                Log.i("DATA LOAD", Category + ", " + current[0]);

            }
            else{
                Log.i("DATA LOAD", "Failed To Load");
            }
        }

        data.recycle();
    }

}
