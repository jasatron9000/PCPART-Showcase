package com.example.pcpartsoftware;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListActivityAdapter extends RecyclerView.Adapter<ListActivityAdapter.ListActivityViewHolder> {
    private ArrayList<Product> productList;

    public static class ListActivityViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgView;
        private TextView prodName;
        private RatingBar prodRating;
        private TextView prodRatingTxt;
        private TextView prodCost;

        public ListActivityViewHolder(@NonNull final View itemView) {
            super(itemView);
            this.imgView = itemView.findViewById(R.id.list_item_image);
            this.prodName = itemView.findViewById(R.id.list_item_name);
            this.prodRating = itemView.findViewById(R.id.list_item_rating);
            this.prodRatingTxt = itemView.findViewById(R.id.list_item_ratingNum);
            this.prodCost = itemView.findViewById(R.id.list_item_cost);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
    }

    public ListActivityAdapter(ArrayList<Product> productList){
        this.productList = productList;
    }

    @NonNull
    @Override
    public ListActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contextView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_activity_row, parent, false);
        return new ListActivityViewHolder(contextView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListActivityViewHolder holder, int position) {
        final Product currentProd = productList.get(position);

        holder.imgView.setImageResource(currentProd.getProductImg());
        holder.prodName.setText(currentProd.getProductName());
        holder.prodRating.setRating(currentProd.getProductRating());
        holder.prodRatingTxt.setText(String.valueOf(currentProd.getProductRating()));
        holder.prodCost.setText(currentProd.getProductPrice());


    }


    @Override
    public int getItemCount() {
        return productList.size();
    }
}
