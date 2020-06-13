package com.example.pcpartsoftware;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListActivityRecyclerHandler {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ListActivityAdapter listAdapter;
    private ArrayList<Product> currentList;

    public ListActivityRecyclerHandler(View v, int recyclerID, Context context, ArrayList<Product> productList){
        this.recyclerView = v.findViewById(recyclerID);
        recyclerView.setHasFixedSize(true);
        this.layoutManager = new LinearLayoutManager(context);
        this.currentList = new ArrayList<>(productList);
        this.listAdapter = new ListActivityAdapter(currentList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(listAdapter);

    }

    //Updated the contents based on a listener
    public void updatedRecycler(ArrayList<Product> newProdList){
        while(currentList.size() != 0){
            currentList.remove(0);
        }
        this.currentList.addAll(newProdList);

        this.listAdapter.notifyDataSetChanged();
    }

    public ListActivityAdapter getAdapter(){
        return listAdapter;
    }
}
