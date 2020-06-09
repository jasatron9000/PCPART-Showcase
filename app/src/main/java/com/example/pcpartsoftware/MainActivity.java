package com.example.pcpartsoftware;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    //vars
    DrawerLayout drawerLayout;
    NavigationView navView;
    //Button hamburger;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_bar);
        toolbar = findViewById(R.id.toolbar);
        //hamburger = findViewById(R.id.button);

        //Toolbar
        setSupportActionBar(toolbar);

        //Navigation drawer menu
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }
}
