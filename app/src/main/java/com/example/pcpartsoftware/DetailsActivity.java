package com.example.pcpartsoftware;

import android.content.Intent;
import android.os.Bundle;
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

    private Product currentProd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        arrow = findViewById(R.id.arrow_details);
        recyclerView = findViewById(R.id.recycler_view_details);
        detailModels = new ArrayList<>();

        detNav = findViewById(R.id.nav_details);
        detNav.setOnNavigationItemSelectedListener(detNavListen);

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
            public void onClick(View view) {

                switch(getIntent().getStringExtra("KEY")){
                    case "Main":
                        finish();
                        break;

                    case "Login":
                        Intent intent1 = new Intent(DetailsActivity.this, LoginActivity.class);
                        intent1.putExtra("KEY", "Details");
                        startActivity(intent1);
                        break;
                }
            }
        });




    }

    private BottomNavigationView.OnNavigationItemSelectedListener detNavListen =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    TextView targetText = findViewById(R.id.details_text);
                    String overview = "Dear students, \n" +
                            "\n" +
                            "I am a TA from the CS302 course. I am very sorry that the demo video you uploaded is expired. We didn't download it in time. I am very sorry about it. \n" +
                            "\n" +
                            "So may I ask would you please kindly upload it again and send me your link with Group Number. I am very sorry about the trouble. I hope you still have it on your computer." +
                            " Please let me know if you are unable to find it. We will try to finger it out. " +
                            "\n\n\n\n\n\n\n";
                    String specs = "\"Firstly, is it alright to use Lorem Ipsum for our DetailsActivity text?\" No.\n" +
                            "\n" +
                            "\"Secondly, how does the 2min Q&A work? Do we get emailed questions to answer or something?\" If required, we will organize a Zoom session with you. The Q&A doesn't have any mark; " +
                            "it's only to prove the originality of your submission. Just keep checking " +
                            "your email regularly in the coming weeks in case we have concerns about the originality of your code. \n\n\n\n\n\n\n";


                    String descript = "Good morning guys!\n" +
                            "\n" +
                            "We have our last online tutorial today at 1pm today. It's basically a brief exam overview. My office hour will follow right after. I'm pretty sure this is the last interaction I'll be " +
                            "having with you as your tutor so I hope you all do amazing in your exam and " +
                            "have a wonderful rest of the year! Feel free to email me any practice exams you've attempted, I'll be happy to give feedback.\n\n\n\n\n\n\n";

                    switch(menuItem.getItemId()){
                        case R.id.nav_overview_det:
                            targetText.setText(overview);
                            break;
                        case R.id.nav_specs_det:
                            targetText.setText(specs);
                            break;
                        case R.id.nav_desc_det:
                            targetText.setText(descript);
                            break;
                    }
                    return true;
                }
            };

}