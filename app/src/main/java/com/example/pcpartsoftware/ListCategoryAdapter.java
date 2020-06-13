package com.example.pcpartsoftware;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListCategoryAdapter extends ArrayAdapter<String> {


    public ListCategoryAdapter(@NonNull Context context, int resource, @NonNull List<String> cat) {
        super(context, resource, cat);
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //set convertView to a specific layout
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_selected_view, parent, false);
        }

        //Set the text
        TextView txtView = convertView.findViewById(R.id.spinner_selected);
        RelativeLayout layoutView = convertView.findViewById(R.id.spinner_selected_layout);
        ImageView imgView = convertView.findViewById(R.id.spinner_image);

        txtView.setText("Category: " + getItem(position));
        layoutView.setBackgroundResource(R.drawable.rounded_background);
        imgView.setImageAlpha(255);

        return convertView;

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //set convertView to a specific layout
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_selected_view, parent, false);
        }

        //Set the text
        TextView txtView = convertView.findViewById(R.id.spinner_selected);
        RelativeLayout layoutView = convertView.findViewById(R.id.spinner_selected_layout);
        ImageView imgView = convertView.findViewById(R.id.spinner_image);

        txtView.setText(getItem(position));
        imgView.setImageAlpha(0);
        layoutView.setBackgroundResource(R.color.colorDarkForeGround);

        return convertView;
    }
}
