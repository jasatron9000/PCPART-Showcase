package com.example.pcpartsoftware;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<DetailsModel> detailModels;
    DetailsAdapter detailsAdapter;
    ImageButton arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        arrow = findViewById(R.id.arrow_details);

        recyclerView = findViewById(R.id.recycler_view_details);
        detailModels = new ArrayList<>();

        //Create img array for recycler
        Integer[] imageNumsDet = {R.drawable.geforce, R.drawable.gigabyte, R.drawable.i9,
                R.drawable.radeon, R.drawable.threadripper, R.drawable.titanrtx};

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
            public void onClick(View v) {

                switch(getIntent().getStringExtra("KEY")){
                    case "Main":
                        Intent intent = new Intent(DetailsActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });

    }

}