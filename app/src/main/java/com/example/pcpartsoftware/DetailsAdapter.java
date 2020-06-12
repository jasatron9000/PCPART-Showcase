package com.example.pcpartsoftware;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {

    ArrayList<DetailsModel> detailsModels;
    Context context;

    public DetailsAdapter(Context context, ArrayList<DetailsModel> detailsModels){
        this.context = context;
        this.detailsModels = detailsModels;
    }

    @NonNull
    @Override
    public DetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Create view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_details, parent,
                false);
        return new DetailsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imageView.setImageResource(detailsModels.get(position).getImage());

        //make img clickable
        holder.imageView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(context, "It also works!", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return detailsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Initialize vars
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //Assign vars
            imageView = itemView.findViewById(R.id.img_slider_details);

        }
    }

}
