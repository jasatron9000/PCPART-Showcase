package com.example.pcpartsoftware;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //vars
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private Button hamburger;
    private RecyclerView recyclerView;
    private TextView clientName;
    private TextView clientUsername;
    private ImageButton searchButton;

    private ArrayList<MainModel> mainModels;
    private MainAdapter mainAdapter;
    private ArrayList<Product> topPicks;

    private ListActivityRecyclerHandler gpuSelection;
    private ListActivityRecyclerHandler cpuSelection;
    private ListActivityRecyclerHandler ramSelection;

    private DataProvider dp = DataProvider.getInstance();
    private String valueIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Retrieve Intent
        Intent currentIntent = getIntent();
        valueIntent = currentIntent.getStringExtra("LoginIntent");

        // Assign and initialise variables
        hamburger = (Button) findViewById(R.id.button);
        drawerLayout =  findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.nav_bar);
        recyclerView = findViewById(R.id.recycler_view);
        mainModels = new ArrayList<>();
        searchButton = findViewById(R.id.search_btn);

        //Create arraylists for the MainModel
        ArrayList<Integer> imageNumsList = new ArrayList<>();
        ArrayList<String> imageNamesList = new ArrayList<>();
        ArrayList<Float> ratingsList = new ArrayList<>();
        ArrayList<String> priceList = new ArrayList<>();

        topPicks = new ArrayList<>(DataProvider.getInstance().getCat().getListbyRating(5));
        intialiseOtherSelection();

        //add values to the arraylists
        for(int i = 0; i < topPicks.size(); i++){

            imageNumsList.add(topPicks.get(i).getProductImg()[0]);
            imageNamesList.add(topPicks.get(i).getProductName());
            ratingsList.add(topPicks.get(i).getProductRating());
            priceList.add(topPicks.get(i).getProductPrice());

        }



        //initialise ArrayList
        for (int i = 0; i < imageNumsList.size(); i++){
            MainModel model = new MainModel(imageNumsList.get(i), imageNamesList.get(i),
                    ratingsList.get(i), priceList.get(i));
            mainModels.add(model);
        }


        // Horizontal Layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Initialise MainAdapter
        mainAdapter = new MainAdapter(MainActivity.this, mainModels, topPicks);

        //Set MainAdapter to recyclerView
        recyclerView.setAdapter(mainAdapter);

        //set search button to go to list
        searchButton.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMainActivity("SORT BY","ALL");
            }
        });

        //bring navView to front of screen
        navView.bringToFront();

        // set hamburger button to open navigation drawer when clicked
        hamburger.setOnClickListener(new Button.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View hamburger) {
                drawerLayout.openDrawer(GravityCompat.START);
                clientName = (TextView) findViewById(R.id.propername_menu);
                clientUsername = (TextView) findViewById(R.id.username_menu);
                if(dp.getcDB().getCurrentClient() != null){
                    clientName.setText(dp.getcDB().getCurrentClient().getName());
                    clientUsername.setText("@" + dp.getcDB().getCurrentClient().getUsername());
                }
                else{
                    clientName.setText("No_Name");
                    clientUsername.setText("@" + "No Username");
                }
            }
        });


        //set navView to 'listen for clicks in drawer'
        navView.setNavigationItemSelectedListener(this);

        //
    }

    //Code to stop the back button from exiting the app on press when the nav drawer is open
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            if(valueIntent == null){
                super.onBackPressed();
            }
        }
    }

    //Code for navigation to different activities through the drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        // Switch Statement to change Activities.
        // KEY is just there cos that's how it works
        switch(menuItem.getItemId()){
            case R.id.nav_logout:
                Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                intent1.putExtra("KEY", "Main");
                startActivity(intent1);
                break;
            case R.id.nav_gpu:
                gotoMainActivity("SORT BY","GPU");
                break;
            case R.id.nav_cpu:
                gotoMainActivity("SORT BY","CPU");
                break;
            case R.id.nav_ram:
                gotoMainActivity("SORT BY","RAM");
                break;
            case R.id.nav_profile:
                Intent intentProfile = new Intent (MainActivity.this, AccountActivity.class);
                startActivity(intentProfile);

        }
        return true;
    }

    //Code to get GPU, CPU and RAM selections for the items by category
    private void intialiseOtherSelection(){
        String[] category = new String[]{"CPU", "GPU", "RAM"};
        ArrayList<ArrayList<Product>> listOfProductArray = new ArrayList<>();

        for (String cat : category){
            ArrayList<Product> limited = new ArrayList<>();
            limited.add(dp.getCat().returnListByCategory(cat).get(0));
            limited.add(dp.getCat().returnListByCategory(cat).get(1));
            listOfProductArray.add(limited);
        }
        View v = this.findViewById(android.R.id.content);

        cpuSelection = new ListActivityRecyclerHandler(v, R.id.cpu_select,
                this, listOfProductArray.get(0));

        gpuSelection = new ListActivityRecyclerHandler(v, R.id.gpu_select,
                this, listOfProductArray.get(1));

        ramSelection = new ListActivityRecyclerHandler(v, R.id.ram_select,
                this, listOfProductArray.get(2));


    }

    //function for ease-of-use when navigating to main
    private void gotoMainActivity(String key, String code){
        //creating a new intent with current context and next activity class args
        Intent intentRAM = new Intent(MainActivity.this, ListActivity.class);

        //adding extra string to identify prev activity in next activity
        intentRAM.putExtra(key, code);

        //start the next activity
        startActivity(intentRAM);

        //Override the Animation Enter/Leave
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


}
