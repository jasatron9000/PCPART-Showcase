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
            getCat().shuffleCatalogue();
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

        int[] imgCPU =  {R.drawable.cpu_1, R.drawable.cpu_2, R.drawable.cpu_3};
        int[] imgGPU =  {R.drawable.gpu_1, R.drawable.gpu_2, R.drawable.gpu_3};
        int[] imgRAM =  {R.drawable.ram_1, R.drawable.ram_2, R.drawable.ram_3};
        Resources res = context.getResources();

        initialiseProducts(context, R.array.cpu_list, imgCPU, "CPU");
        initialiseProducts(context, R.array.gpu_list, imgGPU, "GPU");
        initialiseProducts(context, R.array.ram_list, imgRAM, "RAM");

    }

    public ClientDatabase getcDB() {
        return cDB;
    }

    public Catalogue getCat() {
        return cat;
    }

    private void initialiseProducts(Context context, int cData, int[] cImg, String Category){
        //Retrieve the data from the XML files
        Resources res = context.getResources();
        TypedArray data = res.obtainTypedArray(cData);

        //Loop through the length of the data
        for(int i = 0; i < data.length(); i++){
            //Current Array
            int currDataID = data.getResourceId(i, 0);

            //Check if IDs are detected and if they do not exist a warning message is displayed
            if(currDataID > 0){

                //The information stored in the index of the array
                String[] currentD = context.getResources().getStringArray(data.getResourceId(i, 0));

                //Load all the information to the category class
                this.cat.addToList(currentD[0], currentD[1], Category, Double.parseDouble(currentD[2]),
                        cImg, currentD[3], currentD[4]);

                Log.i("DATA LOAD", Category + ", " + currentD[0]);

            }
            else{
                Log.i("DATA LOAD", "Failed To Load");
            }
        }

        //Garbage Collection
        data.recycle();
    }
}
