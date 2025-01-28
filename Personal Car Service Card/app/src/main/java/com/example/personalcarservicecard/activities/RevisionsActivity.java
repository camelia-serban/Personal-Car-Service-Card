package com.example.personalcarservicecard.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.personalcarservicecard.R;
import com.example.personalcarservicecard.utilClasses.DateConverter;
import com.example.personalcarservicecard.utilClasses.RevisionType;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Date;

import static android.widget.ArrayAdapter.createFromResource;

public class RevisionsActivity extends AppCompatActivity {
    public static final String REVISION_KEY = "revision_key";
    private final DateConverter dateConverter = new DateConverter();

    private Spinner spnRevType;
    private TextInputEditText tietServiceName;
    //private DatePicker dpCalendar;
    private TextView tvDate;
    private Button btnAddDate;
    DatePickerDialog.OnDateSetListener setListener;
    private Button btnSave;


    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revisions);

        initComponents();
        //preluarea intentului cu care s-a deschis activitatea
        intent = getIntent();
    }

    private void initComponents() {
        spnRevType = findViewById(R.id.spinner_revisions_type);
        tietServiceName = findViewById(R.id.tiet_add_service_name);
        //dpCalendar = findViewById(R.id.dp_revisions);
        tvDate = findViewById(R.id.tv_revisions_date);
        btnAddDate = findViewById(R.id.btn_add_date_picker);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpDialog = new DatePickerDialog(RevisionsActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener, year, month, day);
                dpDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dpDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = day + "/" + month + "/" + year;
                tvDate.setText(date);
            }
        };

        btnAddDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(RevisionsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                month = month +1;
                                String date = day + "/" + month + "/" +year;
                                btnAddDate.setText(date);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        btnSave = findViewById(R.id.btn_save_AR);
        populateSpnRevisionsType();
        btnSave.setOnClickListener(addSaveClickEvent());

    }



    private View.OnClickListener addSaveClickEvent(){
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(validate()) {
                    RevisionType revisionType = buildRevisionTypeFromWidgets();
                    intent.putExtra(REVISION_KEY, revisionType);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };
    }

    private boolean validate(){
        if (tietServiceName.getText() == null || tietServiceName.getText().toString().trim().length() < 3) {
            //afisare mesaj temporar cu informatia lipsa
            Toast.makeText(getApplicationContext(), R.string.add_invalid_name_error, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


    private RevisionType buildRevisionTypeFromWidgets(){
        String revType = spnRevType.getSelectedItem().toString();
        String serviceName = tietServiceName.getText().toString();
        //Date revDate = dateConverter.fromString(btnAddDate.getText().toString().trim());
        String revDate = btnAddDate.getText().toString().trim();
        return new RevisionType(revType, serviceName, revDate);
    }

    private void populateSpnRevisionsType(){
        ArrayAdapter<CharSequence> adapter = createFromResource(getApplicationContext(),
                R.array.add_revisions_type,
                android.R.layout.simple_spinner_dropdown_item);
        //atasam adapter catre spinner
        spnRevType.setAdapter(adapter);
    }

}