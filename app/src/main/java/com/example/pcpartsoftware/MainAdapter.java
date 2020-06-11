package com.example.pcpartsoftware;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    ArrayList<MainModel> mainModels;
    Context context;

    public MainAdapter(Context context, ArrayList<MainModel> mainModels){
        this.context = context;
        this.mainModels = mainModels;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Set image to ImageView
        holder.imageView.setImageResource(mainModels.get(position).getImage());

        //make img clickable
        holder.imageView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(context, "It also works!", Toast.LENGTH_SHORT).show();
            }
        });

        //Set image name to TextView
        holder.textView.setText(mainModels.get(position).getImgName());

        //make text clickable
        holder.textView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(context, "It works!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mainModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Initialize vars
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //Assign vars
            imageView = itemView.findViewById(R.id.img_viewer);
            textView = itemView.findViewById(R.id.text_viewer);

        }
    }
}
