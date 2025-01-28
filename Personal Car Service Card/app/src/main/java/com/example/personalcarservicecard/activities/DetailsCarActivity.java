package com.example.personalcarservicecard.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.personalcarservicecard.R;
import com.example.personalcarservicecard.utilClasses.CarDetails;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class DetailsCarActivity extends AppCompatActivity {
    public static final String CARDETAILS_KEY = "carDetails_key";


    private TextInputEditText tietAddModel;
    private EditText addFabYear;
    private Spinner spnAddBrand;
    private Button btnSaveADC;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_car);

        initComponents();

        intent = getIntent();
    }

    private void initComponents(){
        tietAddModel = findViewById(R.id.tiet_add_car_model);
        addFabYear =findViewById(R.id.et_add_fabrication_year);
        spnAddBrand =findViewById(R.id.spn_add_car_brand);
        btnSaveADC = findViewById(R.id.btn_save_ADC);
        populateSpnAddBrand();
        btnSaveADC.setOnClickListener(addSaveClickEvent());
    }

    private View.OnClickListener addSaveClickEvent(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {
                    //construire obiect Java cu informatiile din intefata
                    CarDetails cardetails1 = buildCarDetailsFromWidgets();
                    //punere in intent a obiectului creat
                    intent.putExtra(CARDETAILS_KEY, cardetails1);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };
    }


    private boolean validate(){
        //validare camp model
        if(tietAddModel.getText() == null || tietAddModel.getText().toString()
                .trim().length() <2){
            //afisare mesaj temporar cu informatia lipsa
            Toast.makeText(getApplicationContext(),
                    R.string.add_invalid_name_error,
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        //validare camp an fabricatie
        if(addFabYear.getText() == null || Integer.parseInt(addFabYear.getText().toString().trim())<1970
                || Integer.parseInt(addFabYear.getText().toString().trim())>2021){
            Toast.makeText(getApplicationContext(),
                    R.string.add_invalid_year_error,
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        return true;
    }




    private CarDetails buildCarDetailsFromWidgets() {
        //preluam ce este selectat in spinner
        String brand = spnAddBrand.getSelectedItem().toString();
        //extragem modelul
        String model = tietAddModel.getText().toString();
        //extragem anul
        int year = Integer.parseInt(addFabYear.getText().toString().trim());
        return new CarDetails(brand, model, year);
    }


    private void populateSpnAddBrand(){
        //creare adaptor care asigura convertirea unei colectii de string-uri
        //la o colectie de view(TextView)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.
                createFromResource(getApplicationContext(),
                        R.array.add_car_brand,
                        android.R.layout.simple_spinner_dropdown_item);
        //atasam adapter-ul catre spinner
        spnAddBrand.setAdapter(adapter);
    }

}