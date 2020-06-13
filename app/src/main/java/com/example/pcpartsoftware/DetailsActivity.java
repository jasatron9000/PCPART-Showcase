package com.example.pcpartsoftware;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<DetailsModel> detailModels;
    DetailsAdapter detailsAdapter;
    ImageButton arrow;
    BottomNavigationView detNav;
    TextView targetText;

    Product currentProd;

    String over;
    String desc;
    String spec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        arrow = findViewById(R.id.arrow_details);
        recyclerView = findViewById(R.id.recycler_view_details);
        detailModels = new ArrayList<>();
        Intent currentIntent = getIntent();
        currentProd = currentIntent.getParcelableExtra("PROD");

        targetText = findViewById(R.id.details_text);


        detNav = findViewById(R.id.nav_details);
        detNav.setOnNavigationItemSelectedListener(detNavListen);

        //Create img array for recycler
        int[] imageNumsDet = currentProd.getProductImg();
        over = currentProd.getOverview();
        desc = currentProd.getDescr();
        spec = currentProd.getSpecs();
        String name = currentProd.getProductName();
        String price = currentProd.getProductPrice();
        //float rating = currentIntent.getFloatExtra("RTG");

        //initialise ArrayList
        for (int i = 0; i < imageNumsDet.length; i++){
            DetailsModel model = new DetailsModel(imageNumsDet[i]);
            detailModels.add(model);
        }

        // Horizontal Layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(DetailsActivity.this,
                LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Initialise MainAdapter
        detailsAdapter = new DetailsAdapter(DetailsActivity.this, detailModels);

        //Set MainAdapter to recyclerView
        recyclerView.setAdapter(detailsAdapter);

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch(getIntent().getStringExtra("KEY")){
                    case "Main":
                        Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                        intent.putExtra("KEY", "Details");
                        startActivity(intent);
                        break;

                    case "List":
                        Intent intent1 = new Intent(DetailsActivity.this, ListActivity.class);
                        intent1.putExtra("KEY", "Details");
                        startActivity(intent1);
                        break;
                }
                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });




    }

    private BottomNavigationView.OnNavigationItemSelectedListener detNavListen =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    switch(menuItem.getItemId()){
                        case R.id.nav_overview_det:
                            targetText.setText(over);
                            break;
                        case R.id.nav_specs_det:
                            targetText.setText(spec);
                            break;
                        case R.id.nav_desc_det:
                            targetText.setText(desc);
                            break;
                    }
                    return true;
                }
            };

}