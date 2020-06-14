package com.example.pcpartsoftware;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    //Assign variables
    RecyclerView recyclerView;

    ArrayList<DetailsModel> detailModels;
    DetailsAdapter detailsAdapter;
    ImageButton arrow;
    BottomNavigationView detNav;
    TextView targetText;
    TextView titleText;
    TextView priceText;
    RatingBar rb;
    TextView rating;
    TextView gst;

    Product currentProd;

    String desc;
    String spec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //Buttons, text and images
        arrow = findViewById(R.id.arrow_details);
        recyclerView = findViewById(R.id.recycler_view_details);
        detailModels = new ArrayList<>();

        //get parsed product
        final Intent currentIntent = getIntent();
        currentProd = currentIntent.getParcelableExtra("PROD");

        //set text and ratings
        targetText = findViewById(R.id.details_text);
        titleText = findViewById(R.id.details_title);
        priceText = findViewById(R.id.details_pricetxt);
        rb = findViewById(R.id.rb_details);
        rating = findViewById(R.id.usrrtg_details);
        gst = findViewById(R.id.gst_detail);


        //get bottom navigation bar to listen for click
        detNav = findViewById(R.id.nav_details);
        detNav.setOnNavigationItemSelectedListener(detNavListen);

        //Create img array for recycler
        int[] imageNumsDet = currentProd.getProductImg();
        desc = currentProd.getDescr();
        spec = currentProd.getSpecs();
        String name = currentProd.getProductName();
        String price = currentProd.getProductPrice();

        //Set initial vals for text and rating
        titleText.setText(currentProd.getProductName());
        rb.setRating(currentProd.getProductRating());
        priceText.setText(currentProd.getProductPrice());
        rating.setText("User Rating: " + currentProd.getProductRating() + "\n\n\n\n\n\n\n\n\n");
        rb.setVisibility(View.VISIBLE);
        priceText.setVisibility(View.VISIBLE);
        rating.setVisibility(View.VISIBLE);
        gst.setVisibility(View.VISIBLE);
        targetText.setVisibility(View.INVISIBLE);

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

                switch(currentIntent.getStringExtra("KEY")){
                    //go to main
                    case "Main":
                        Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                        intent.putExtra("KEY", "Details");
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        break;
                    //go to list
                    case "List":
                        Intent intent1 = new Intent(DetailsActivity.this, ListActivity.class);
                        intent1.putExtra("SORT BY","ALL");
                        startActivity(intent1);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        break;


                }

            }
        });




    }

    //function for the bottom navigation view
    private BottomNavigationView.OnNavigationItemSelectedListener detNavListen =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @RequiresApi(api = Build.VERSION_CODES.Q)
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    switch(menuItem.getItemId()){
                        //case for when Overview is clicked
                        case R.id.nav_overview_det:
                            gst.setVisibility(View.VISIBLE);
                            rb.setVisibility(View.VISIBLE);
                            priceText.setVisibility(View.VISIBLE);
                            rating.setVisibility(View.VISIBLE);
                            targetText.setVisibility(View.INVISIBLE);
                            targetText.setText("");
                            titleText.setText(currentProd.getProductName());
                            rb.setRating(currentProd.getProductRating());
                            priceText.setText(currentProd.getProductPrice());
                            rating.setText("User Rating: " + currentProd.getProductRating() + "\n\n\n\n\n\n\n\n\n");

                            break;

                        //case for when specs is clicked
                        case R.id.nav_specs_det:
                            gst.setVisibility(View.INVISIBLE);
                            rb.setVisibility(View.INVISIBLE);
                            rb.setNumStars(0);
                            priceText.setVisibility(View.INVISIBLE);
                            rating.setVisibility(View.INVISIBLE);
                            rating.setText(" ");
                            priceText.setGravity(View.TEXT_ALIGNMENT_TEXT_START);
                            targetText.setVisibility(View.VISIBLE);
                            targetText.setText(spec);
                            titleText.setText("Specifications");
                            break;

                        //case for when description is clicked
                        case R.id.nav_desc_det:
                            gst.setVisibility(View.INVISIBLE);
                            rb.setVisibility(View.INVISIBLE);
                            rb.setNumStars(0);
                            rating.setVisibility(View.INVISIBLE);
                            rating.setText(" ");
                            priceText.setVisibility(View.INVISIBLE);
                            priceText.setGravity(View.TEXT_ALIGNMENT_TEXT_START);
                            targetText.setVisibility(View.VISIBLE);
                            targetText.setText(desc);
                            titleText.setText("Description");
                            break;
                    }
                    return true;
                }
            };

}