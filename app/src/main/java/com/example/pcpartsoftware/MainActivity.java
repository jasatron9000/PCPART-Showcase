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

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //vars
    DrawerLayout drawerLayout;
    NavigationView navView;
    Button hamburger;
    RecyclerView recyclerView;
    //Button imageButton;

    ArrayList<MainModel> mainModels;
    MainAdapter mainAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign and initialise variables
        hamburger = (Button) findViewById(R.id.button);
        drawerLayout =  findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.nav_bar);
        recyclerView = findViewById(R.id.recycler_view);
        mainModels = new ArrayList<>();
        //imageButton = (Button) findViewById(R.id.text_viewer);

        //Create int array for recycler
        Integer[] imageNums = {R.drawable.geforce, R.drawable.gigabyte, R.drawable.i9,
                R.drawable.radeon, R.drawable.threadripper, R.drawable.titanrtx};

        //Create string array
        String[] imageNames = {"NVIDIA GeForce", "NVIDIA GIGABYTE", "Intel Core i9",
                "AMD Radeon", "AMD ThreadRipper", "Titan RTX"};

        //initialise ArrayList
        for (int i = 0; i < imageNums.length; i++){
            MainModel model = new MainModel(imageNums[i], imageNames[i]);
            mainModels.add(model);
        }

        // Horizontal Layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Initialise MainAdapter
        mainAdapter = new MainAdapter(MainActivity.this, mainModels);

        //Set MainAdapter to recyclerView
        recyclerView.setAdapter(mainAdapter);

        //set image buttons (testing)



        navView.bringToFront();

        hamburger.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View hamburger) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });



        navView.setNavigationItemSelectedListener(this);

        //
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return true;
    }


}
