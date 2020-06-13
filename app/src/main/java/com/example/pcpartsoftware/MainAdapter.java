package com.example.pcpartsoftware;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
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

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    ArrayList<MainModel> mainModels;
    Context context;
    ArrayList<Product> products;

    public MainAdapter(Context context, ArrayList<MainModel> mainModels, ArrayList<Product> products){
        this.context = context;
        this.mainModels = mainModels;
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Create view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //Set image to ImageView
        holder.imageView.setImageResource(mainModels.get(position).getImage());

        //final Product product = mainModels.get(position).getProduct();
        //final int pos = position;

        //make img clickable
        holder.imageView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //creating a new intent with current context and next activity class args
                Intent intent = new Intent(context, DetailsActivity.class);
                //adding extra string to identify prev activity in next activity
                intent.putExtra("KEY", "Main");
                intent.putExtra("PROD", products.get(position));
                //start the next activity
                context.startActivity(intent);


            }
        });

        //Set image name to TextView
        holder.textView.setText(mainModels.get(position).getImgName());

        //make text clickable
        holder.textView.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {

                //creating a new intent with current context and next activity class args
                Intent intent = new Intent(context, DetailsActivity.class);
                //adding extra string to identify prev activity in next activity
                intent.putExtra("KEY", "Main");
                intent.putExtra("PROD", products.get(position));
                //start the next activity
                context.startActivity(intent);
            }
        });

        //Set ratings
        holder.ratingView.setRating((float) mainModels.get(position).getRatingBar());

        //Set Pricing
        holder.priceView.setText(mainModels.get(position).getPrices());
    }

    @Override
    public int getItemCount() {
        return mainModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Initialize vars
        ImageView imageView;
        TextView textView;
        RatingBar ratingView;
        TextView priceView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //Assign vars
            imageView = itemView.findViewById(R.id.img_viewer);
            textView = itemView.findViewById(R.id.text_viewer);
            ratingView = itemView.findViewById(R.id.scroller_rb);
            priceView = itemView.findViewById(R.id.scroller_price);

        }
    }
}
