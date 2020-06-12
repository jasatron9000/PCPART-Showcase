package com.example.pcpartsoftware;

import android.os.Bundle;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private ArrayList<String> categories = generateCategories();
    private ListCategoryAdapter catAdapter;
    private ListActivityAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        //Initialising the Spinner for the drop down menu to sort
        Spinner spinnerCat = findViewById(R.id.list_activity_cat);

        catAdapter = new ListCategoryAdapter(this, 0, categories);
        spinnerCat.setAdapter(catAdapter);

        //Initialise the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.list_activity_recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        listAdapter = new ListActivityAdapter(DataProvider.getInstance().getCat().getCatalogue());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(listAdapter);
    }

    private ArrayList<String> generateCategories(){
        ArrayList<String> categories = new ArrayList<>();
        categories.add("All");
        categories.add("CPU");
        categories.add("GPU");
        categories.add("RAM");

        return categories;
    }
}
