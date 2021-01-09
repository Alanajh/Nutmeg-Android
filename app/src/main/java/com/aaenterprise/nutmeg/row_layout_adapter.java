package com.aaenterprise.nutmeg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class row_layout_adapter extends ArrayAdapter<String> {
    DatabaseHelper db;
    public row_layout_adapter(@NonNull Context context, String[] values) {
        super(context, R.layout.row_layout_1, values);
        ArrayList arrayList = db.getQuestion();
    }
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater theInflator = LayoutInflater.from(getContext());
        View theView = theInflator.inflate(R.layout.row_layout_1,
                parent, false);
        String tests = getItem(position);
        TextView theTextView = theView.findViewById(R.id.testListView);
        theTextView.setText(tests);
        ImageView theImageView = theView.findViewById(R.id.arrow_image);
        theImageView.setImageResource(R.drawable.arrow);

        return theView;
    }
}
