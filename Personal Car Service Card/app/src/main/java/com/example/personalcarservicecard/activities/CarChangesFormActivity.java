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
import com.example.personalcarservicecard.utilClasses.CarChanges;
import com.example.personalcarservicecard.utilClasses.RevisionType;

import java.util.ArrayList;
import java.util.List;

public class CarChangesFormActivity extends AppCompatActivity {
    private static final int ADD_CAR_CHANGE_REQUEST_CODE= 200;

    //declarare componente vizuale
    private Button btnAdd;
    private ListView lvCarChange;
    private List<CarChanges> carChanges = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_changes_form);

        initComponents();
        addLvChangesAdapter();
        btnAdd.setOnClickListener(addCarChangesClickEvent());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_CAR_CHANGE_REQUEST_CODE && resultCode == RESULT_OK && data !=null) {
            //preluare revizii din intent
            CarChanges carCh = (CarChanges) data.getSerializableExtra(CarChangesActivity.CARCHANGES_KEY);
            if (carCh != null) {
                Toast.makeText(getApplicationContext(),
                        getString(R.string.car_change_added_message, carCh.toString()),
                        Toast.LENGTH_LONG).show();
                carChanges.add(carCh);
                ArrayAdapter adapter = (ArrayAdapter) lvCarChange.getAdapter();
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void initComponents(){
       btnAdd = findViewById(R.id.btn_add_ACCF);
        lvCarChange = findViewById(R.id.lv_car_changes_form);

    }

   private View.OnClickListener addCarChangesClickEvent(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CarChangesActivity.class);
                startActivityForResult(intent, ADD_CAR_CHANGE_REQUEST_CODE);
            }
        };
   }


    private void addLvChangesAdapter() {
        //initializare ArrayAdapter de tip CarChanges pentru adaugare pe ListView
        ArrayAdapter<CarChanges> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1, carChanges);
        lvCarChange.setAdapter(adapter);
    }
}