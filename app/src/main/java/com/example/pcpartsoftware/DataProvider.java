package com.example.pcpartsoftware;

public class DataProvider {
    private static DataProvider instance_dp = null;
    private ClientDatabase cDB;
    private Catalogue cat;

    private DataProvider(){
        cDB = new ClientDatabase();
        cat = new Catalogue();

        generateUsers();
        generateProducts();
    }

    public static DataProvider getInstance(){
        if (instance_dp == null){
            instance_dp = new DataProvider();
        }

        return instance_dp;
    }

    private void generateUsers(){
        System.out.println("Users Generated \n");

        this.cDB.addNewUser("jbuq319", "admin", "jbuq319@aucklanduni.ac.nz", 0211234567, "Jason Buquiran","6a Gos Dr.");
        this.cDB.addNewUser("jmuk184", "idk", "jbuq319@aucklanduni.ac.nz", 0217654321, "Jacob Mukkada","53 Hat Dr.");
        this.cDB.addNewUser("guest", "password", "guest@gmail.com", 0210000000, "Guest Account","13 Guest Rd.");
    }

    private void generateProducts(){
        System.out.println("Products Generated \n");

        this.cat.addToList("Intel i7", "$400.00", "CPU",
                4.5, R.drawable.geforce);

        this.cat.addToList("AMD Ryzen 7", "$350.00", "CPU",
                4.9, R.drawable.gigabyte);

        this.cat.addToList("AMD Vega", "$420.69", "GPU",
                3.5, R.drawable.i9);

        this.cat.addToList("NVIDIA GTX2080", "$690.42", "GPU",
                5.0, R.drawable.radeon);

        this.cat.addToList("Corsair 16GB RAM", "$70.00", "RAM",
                4.5, R.drawable.threadripper);

        this.cat.addToList("Corsair 8GB RAM", "$80.00", "RAM",
                3.0, R.drawable.titanrtx);

    }

    public ClientDatabase getcDB() {
        return cDB;
    }

    public Catalogue getCat() {
        return cat;
    }
}
