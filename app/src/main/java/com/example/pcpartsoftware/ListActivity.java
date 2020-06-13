package com.example.pcpartsoftware;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private ArrayList<String> categories = generateCategories();
    private ListCategoryAdapter catAdapter;
    private ListActivityRecyclerHandler rHandler;

    private Button menuButton;
    private EditText searchBar;

    private int currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        //+===================================================================+
        //|                                                                   |
        //|               INITIALISATION FOR THE LIST ACTIVITY                |
        //|                                                                   |
        //+===================================================================+

        //Initialising the Spinner for the drop down menu to sort
        Spinner spinnerCat = findViewById(R.id.list_activity_cat);

        catAdapter = new ListCategoryAdapter(this, 0, categories);
        spinnerCat.setAdapter(catAdapter);

        //Initialise the RecyclerView
        ArrayList<Product> prodList = DataProvider.getInstance().getCat().getCatalogue();
        this.rHandler = new ListActivityRecyclerHandler(this.findViewById(android.R.id.content),
                R.id.list_activity_recyclerView, this, prodList);


        //Initialise the EditText functionality
        this.searchBar = findViewById(R.id.list_activity_text);

        //+===================================================================+
        //|                                                                   |
        //|               EVENT HANDLING FOR THE LIST ACTIVITY                |
        //|                                                                   |
        //+===================================================================+

        //Handling the events for the changing the category sort
        spinnerCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = categories.get(position);
                Toast.makeText(getBaseContext(), "Sorted by: " + selected, Toast.LENGTH_SHORT).show();

                ArrayList<Product> sortedList = DataProvider.getInstance().getCat().returnListByCategory(selected);
                rHandler.updatedRecycler(new ArrayList<Product>(sortedList));
                currentPos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Event Handling for the Search Bar
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String finalVal = searchBar.getText().toString();
                ArrayList<Product> sortedList = DataProvider.getInstance().getCat().returnListByName(finalVal);

                //Populate the RecyclerView with the sorted data in the Spinner if nothing is written
                if(finalVal.equals("")){
                    rHandler.updatedRecycler(new ArrayList<Product>(DataProvider.getInstance().getCat().returnListByCategory(categories.get(currentPos))));
                }
                else{
                    rHandler.updatedRecycler(new ArrayList<Product>(sortedList));
                }

                Log.i("TEST", finalVal);
            }
        });

        //+===================================================================+
        //|                                                                   |
        //|               INTENT HANDLER FOR THE LIST ACTIVITY                |
        //|                                                                   |
        //+===================================================================+


    }

    private ArrayList<String> generateCategories(){
        ArrayList<String> categories = new ArrayList<>();
        categories.add("ALL");
        categories.add("CPU");
        categories.add("GPU");
        categories.add("RAM");

        return categories;
    }

}
