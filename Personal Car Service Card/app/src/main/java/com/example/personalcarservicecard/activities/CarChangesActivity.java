package com.example.personalcarservicecard.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.personalcarservicecard.R;
import com.example.personalcarservicecard.utilClasses.CarChanges;
import com.example.personalcarservicecard.utilClasses.RevisionType;
import com.google.android.material.textfield.TextInputEditText;

public class CarChangesActivity extends AppCompatActivity {
    public static final String CARCHANGES_KEY = "carChanges_key";

    private TextInputEditText tietAddOther;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private CheckBox checkBox5;
    private CheckBox checkBox6;
    private CheckBox checkBox7;

    private Button btnSaveChanges;
    private TextInputEditText tietAddMonth;
    private TextInputEditText tietAddYear;

    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_changes);

        initComponents();
        //preluarea intentului cu care s-a deschis activitatea
        intent = getIntent();
    }


    private void initComponents(){
        tietAddOther = findViewById(R.id.tiet_other_car_changes);
        checkBox1 = findViewById(R.id.cb_headlights);
        checkBox2 = findViewById(R.id.cb_taillights);
        checkBox3 = findViewById(R.id.cb_windscreen);
        checkBox4 = findViewById(R.id.cb_battery);
        checkBox5 = findViewById(R.id.cb_ac_compressor);
        checkBox6 = findViewById(R.id.cb_mirrors);
        checkBox7 = findViewById(R.id.cb_color);
        tietAddMonth = findViewById(R.id.tiet_add_month);
        tietAddYear = findViewById(R.id.tiet_add_year);
        btnSaveChanges = findViewById(R.id.btn_save_ACC);
        btnSaveChanges.setOnClickListener(addSaveClickEvent());
    }

    private View.OnClickListener addSaveClickEvent(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(validate()) {
                    CarChanges carCh = createCarChangesFromViews();
                    intent.putExtra(CARCHANGES_KEY, carCh);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };
    }



    private boolean validate(){
        //validare campul addOther
        /*if(tietAddOther.getText() == null || tietAddOther.getText().toString()
                .trim().length() <2){
            Toast.makeText(getApplicationContext(),
                    R.string.add_invalid_name_error,
                    Toast.LENGTH_LONG).show();
            return false;
        }*/
        //validare campul luna sa fie in intervalul 1-12
        if(tietAddMonth.getText() == null || Integer.parseInt(tietAddMonth.getText().toString().trim())<1
                || Integer.parseInt(tietAddMonth.getText().toString().trim())>12){
            Toast.makeText(getApplicationContext(),
                    R.string.add_invalid_month_error,
                    Toast.LENGTH_LONG).show();
            return false;
        }
        //validare an sa fie in intervalul 2000-2021
        if(tietAddYear.getText() == null || Integer.parseInt(tietAddYear.getText().toString().trim())<2000
                || Integer.parseInt(tietAddYear.getText().toString().trim())>2021){
            Toast.makeText(getApplicationContext(),
                    R.string.add_invalid_year_error,
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if(!checkBox1.isChecked() && !checkBox2.isChecked() && !checkBox3.isChecked() && !checkBox4.isChecked()
                && !checkBox5.isChecked() && !checkBox6.isChecked() && !checkBox7.isChecked()){
            //String other = tietAddOther.getText().toString();
            if(tietAddOther.getText() == null || tietAddOther.getText().toString()
                    .trim().length() <2) /* {
                Toast.makeText(getApplicationContext(),
                        R.string.invalid_change,
                        Toast.LENGTH_LONG).show();
                return false;
            }*/
                Toast.makeText(getApplicationContext(),
                        R.string.invalid_change,
                        Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


    private CarChanges createCarChangesFromViews() {
        String result = new String();
        //result.append("I changed: ");
        if(checkBox1.isChecked()){
           result = checkBox1.getText().toString();
        }
        if(checkBox2.isChecked()){
           result = checkBox2.getText().toString();
        }
        if(checkBox3.isChecked()){
            result = checkBox3.getText().toString();
        }
        if(checkBox4.isChecked()){
           result = checkBox4.getText().toString();
        }
        if(checkBox5.isChecked()){
            result = checkBox5.getText().toString();
        }
        if(checkBox6.isChecked()){
           result = checkBox6.getText().toString();
        }
        if(checkBox7.isChecked()){
            result = checkBox7.getText().toString();
        }
        if(!checkBox1.isChecked() && !checkBox2.isChecked() && !checkBox3.isChecked() && !checkBox4.isChecked()
        && !checkBox5.isChecked() && !checkBox6.isChecked() && !checkBox7.isChecked()){
            //extragem numele
            result = tietAddOther.getText().toString();
        }

        //extragem luna
        int month = Integer.parseInt(tietAddMonth.getText().toString().trim());
        //extragem anul
        int year = Integer.parseInt(tietAddYear.getText().toString().trim());

        return new CarChanges(result, month, year);
    }



}