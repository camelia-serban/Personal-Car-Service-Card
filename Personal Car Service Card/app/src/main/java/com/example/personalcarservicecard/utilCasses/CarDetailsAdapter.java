package com.example.personalcarservicecard.utilClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.personalcarservicecard.R;

import java.util.List;

public class CarDetailsAdapter extends ArrayAdapter<CarDetails> {
    public static final String CAR_DETAILS_KEY = "CARDETAILSK";

    private Context context;
    private List<CarDetails> carDetails1;
    private LayoutInflater inflater;
    private int resource;

    public CarDetailsAdapter(@NonNull Context context, int resource, @NonNull List<CarDetails> objects,
                             LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.carDetails1 = objects;
        this.inflater = inflater;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource , parent, false);
        CarDetails carDetails = carDetails1.get(position);
        if (carDetails != null) {
            addCarBrand(view, carDetails.getBrand());
            addCarModel(view, carDetails.getModel());
            addFabricationYear(view, carDetails.getYear());
        }
        return view;
    }

    private void addCarBrand(View view, String brand) {
        TextView textView = view.findViewById(R.id.tv_row_car_brand);
        populateTextViewContent(textView, brand);
    }

    private void addCarModel(View view, String tietAddModel) {
        TextView textView = view.findViewById(R.id.tv_row_car_model);
        populateTextViewContent(textView, tietAddModel);
    }

    private void addFabricationYear(View view, int addFabYear) {
        TextView textView = view.findViewById(R.id.tv_row_fabrication_year);
        populateTextViewContent(textView, String.valueOf(addFabYear));
    }

    private void populateTextViewContent(TextView  textView, String value) {
        if (value != null && !value.trim().isEmpty()) {
            textView.setText(value);
        } else {
            textView.setText(R.string.no_content);
        }
    }
}
