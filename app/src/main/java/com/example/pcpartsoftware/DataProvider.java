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

        String specs = "<b><i>Specs:</i></b>\\n\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t- Memory Size: 8GB\\n\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t-\n" +
                "        Base Clock Speed: 1465MHz\\n\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t- Boost Clock Speed: 1725MHz\\n\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t-\n" +
                "        Max Displays: 6\\n\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t- Length: 270mm\\n\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t- Display Ports: 3";

        String description = "\\n<b><i>Description:</i></b>\\n\\n\n" +
                "\n" +
                "        Great gaming experiences are created by bending the rules.\n" +
                "        The AMD Radeon™ RX 5700 Series GPUs defy conventions with an all-new 7nm process infused with RDNA\n" +
                "        architecture that is specifically tuned for the perfect 1440p gaming experience. More efficient processing\n" +
                "        power handles advanced 3D effects with ease, and 8GB of high-speed GDDR6 VRAM provides ample storage for\n" +
                "        the detailed textures, meshes, and effects of modern games. Industry-leading software and\n" +
                "        frequent driver upgrades push the experience even further, enabling overclocking,\n" +
                "        gameplay recording, and more.\\n\\n";


        String over = "Dear students, \n" +
                "\n" +
                "I am a TA from the CS302 course. I am very sorry that the demo video you uploaded is expired. We didn't download it in time. I am very sorry about it. \n" +
                "\n" +
                "So may I ask would you please kindly upload it again and send me your link with Group Number. I am very sorry about the trouble. I hope you still have it on your computer." +
                " Please let me know if you are unable to find it. We will try to finger it out. " +
                "\n\n\n\n\n\n\n";

        int[] img1 =  {R.drawable.geforce, R.drawable.gigabyte, R.drawable.i9};
        this.cat.addToList("Intel i7", "$400.00", "CPU",
                4.5, img1, specs, description, over);

        int[] img2 =  {R.drawable.gigabyte, R.drawable.i9, R.drawable.radeon};
        this.cat.addToList("AMD Ryzen 7", "$350.00", "CPU",
                4.9, img2, specs, description, over);

        int[] img3 =  {R.drawable.i9, R.drawable.radeon, R.drawable.threadripper};
        this.cat.addToList("AMD Vega", "$420.69", "GPU",
                3.5, img3, specs, description, over);

        int[] img4 =  {R.drawable.radeon, R.drawable.threadripper, R.drawable.titanrtx};
        this.cat.addToList("NVIDIA GTX2080", "$690.42", "GPU",
                5.0, img4, specs, description, over);

        int[] img5 =  {R.drawable.threadripper, R.drawable.titanrtx, R.drawable.geforce};
        this.cat.addToList("Corsair 16GB RAM", "$70.00", "RAM",
                4.5, img5, specs, description, over);

        int[] img6 =  {R.drawable.titanrtx, R.drawable.radeon, R.drawable.i9};
        this.cat.addToList("Corsair 8GB RAM", "$80.00", "RAM",
                3.0, img6, specs, description, over);



    }

    public ClientDatabase getcDB() {
        return cDB;
    }

    public Catalogue getCat() {
        return cat;
    }

}
