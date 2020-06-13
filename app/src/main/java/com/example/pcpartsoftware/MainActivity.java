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

    private ArrayList<MainModel> mainModels;
    private MainAdapter mainAdapter;
    private ArrayList<Product> topPicks;

    private Client currentClient;
    private DataProvider dp = DataProvider.getInstance();

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




        topPicks = new ArrayList<>(DataProvider.getInstance().getCat().getListbyRating(5));

        //Log.i("LOOOK AT MEE ", String.valueOf(DataProvider.getInstance().getCat().getCatalogue().size()));

        Integer[] imageNums = {topPicks.get(0).getProductImg()[0], topPicks.get(1).getProductImg()[0],
                topPicks.get(2).getProductImg()[0], topPicks.get(3).getProductImg()[0],
                topPicks.get(4).getProductImg()[0]};


        String[] imageNames = {topPicks.get(0).getProductName(), topPicks.get(1).getProductName(),
                topPicks.get(2).getProductName(), topPicks.get(3).getProductName(),
                topPicks.get(4).getProductName()};


        Float[] ratings = {topPicks.get(0).getProductRating(), topPicks.get(1).getProductRating(),
                topPicks.get(2).getProductRating(), topPicks.get(3).getProductRating(),
                topPicks.get(4).getProductRating()};


        String[] pricing = {topPicks.get(0).getProductPrice(), topPicks.get(1).getProductPrice(),
                topPicks.get(2).getProductPrice(), topPicks.get(3).getProductPrice(),
                topPicks.get(4).getProductPrice()};

        //initialise ArrayList
        for (int i = 0; i < imageNums.length; i++){
            MainModel model = new MainModel(imageNums[i], imageNames[i], ratings[i], pricing[i]);
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


        //bring navView to front of screen
        navView.bringToFront();

        // set hamburger button to open navigation drawer when clicked
        hamburger.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View hamburger) {
                drawerLayout.openDrawer(GravityCompat.START);
                clientName = (TextView) findViewById(R.id.username_menu);
                clientName.setText(dp.getcDB().getCurrentClient().getName());
            }
        });


        //set navView to 'listen for clicks in drawer'
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

        // Switch Statement to change Activities.
        // KEY is just there cos that's how it works
        switch(menuItem.getItemId()){
            case R.id.nav_cpu:
                //creating a new intent with current context and next activity class args
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                //adding extra string to identify prev activity in next activity
                intent.putExtra("KEY", "Main");
                //start the next activity
                startActivity(intent);
                break;
            case R.id.nav_logout:
                Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                intent1.putExtra("KEY", "Main");
                startActivity(intent1);
                break;
        }
        return true;
    }


}
