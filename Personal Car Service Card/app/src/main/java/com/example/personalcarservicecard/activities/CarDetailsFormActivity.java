package com.example.personalcarservicecard.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.personalcarservicecard.R;
import com.example.personalcarservicecard.utilClasses.CarDetails;
import com.example.personalcarservicecard.utilClasses.CarDetailsAdapter;

import java.util.ArrayList;
import java.util.List;

public class CarDetailsFormActivity extends AppCompatActivity {
    private static final int ADD_CAR_DETAILS_REQUEST_CODE = 210;

    private Button Addbtn;
    private ListView lvCarDetails;
    private List<CarDetails> carDetails1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details_form);

        initComponents();
        //carDetails1.add(new CarDetails("BMW", "Seria5", 2010));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_CAR_DETAILS_REQUEST_CODE
                && resultCode == RESULT_OK && data != null){
            CarDetails carDet = (CarDetails) data.getSerializableExtra(DetailsCarActivity.CARDETAILS_KEY);
            if(carDet != null){
                Toast.makeText(getApplicationContext(),
                        getString(R.string.car_details_msg, carDet.toString()), Toast.LENGTH_LONG).show();
                carDetails1.add(carDet);
                notifyAdapter();
            }
        }
    }


    private void initComponents(){
        Addbtn = findViewById(R.id.btn_add_ACDF);
        lvCarDetails = findViewById(R.id.lv_car_details_form);
        addLvCarDetailsAdapter();
        Addbtn.setOnClickListener(addCarDetailsClickEvent());
    }

    private View.OnClickListener addCarDetailsClickEvent(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DetailsCarActivity.class);
                //startActivity(intent);
                startActivityForResult(intent, ADD_CAR_DETAILS_REQUEST_CODE);
            }
        };
    }

    private void addLvCarDetailsAdapter() {
        //initializare ArrayAdapter de tip CarDetails pentru adaugare pe ListView
        CarDetailsAdapter adapter = new CarDetailsAdapter(getApplicationContext(),
                R.layout.lv_car_details_adapter, carDetails1, getLayoutInflater());
        lvCarDetails.setAdapter(adapter);
    }

    private void notifyAdapter() {
        ArrayAdapter adapter = (ArrayAdapter) lvCarDetails.getAdapter();
        adapter.notifyDataSetChanged();
    }
}