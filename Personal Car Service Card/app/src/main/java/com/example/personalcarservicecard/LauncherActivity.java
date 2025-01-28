package com.example.personalcarservicecard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.personalcarservicecard.activities.CarChangesFormActivity;
import com.example.personalcarservicecard.activities.CarDetailsFormActivity;
import com.example.personalcarservicecard.activities.DetailsCarActivity;
import com.example.personalcarservicecard.activities.RevisionsActivity;
import com.example.personalcarservicecard.activities.RevisionsFormActivity;
import com.example.personalcarservicecard.activities.ServiceReviewActivity;
import com.example.personalcarservicecard.activities.ServiceReviewFormActivity;
import com.example.personalcarservicecard.utilClasses.Review;

public class LauncherActivity extends AppCompatActivity {
    public static final int PROFILE_REQUEST_CODE = 300;

    private Button btnMyCar;
    private Button btnCarRevisions;
    private Button btnCarChangesH;
    private Button btnServiceReview;

    private Button btnAddProfile;
    private TextView tvMsg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        btnMyCar = findViewById(R.id.btn_my_car);
        btnMyCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open the Personal Data Activity
                Intent intent = new Intent(getApplicationContext(), CarDetailsFormActivity.class);
                startActivity(intent);
            }
        });
        btnCarRevisions = findViewById(R.id.btn_car_revisions);
        btnCarRevisions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open the Service Details Activity
                Intent intent1 = new Intent(getApplicationContext(), RevisionsFormActivity.class);
                startActivity(intent1);
            }
        });

        btnCarChangesH = findViewById(R.id.btn_car_changes_history);
        btnCarChangesH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open the Car Solved Issues Activity
                Intent intent2 = new Intent(getApplicationContext(), CarChangesFormActivity.class);
                startActivity(intent2);
            }
        });

        btnServiceReview = findViewById(R.id.btn_service_review);
        btnServiceReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open the Car Details Activity
                Intent intent3 = new Intent(getApplicationContext(), ServiceReviewFormActivity.class);
                startActivity(intent3);
            }
        });


        btnAddProfile = findViewById(R.id.btn_profile);
        btnAddProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open the Car Details Activity
                Intent intent4 = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent4);
            }
        });

        tvMsg = findViewById(R.id.tv_main_message);
        btnAddProfile.setOnClickListener(profileEventListener());
        displayMessage();
    }

    private void displayMessage() {
        //deschidere fisier de preferinte in memorie
        SharedPreferences preferences = getSharedPreferences(ProfileActivity.SHARED_PREF_FILE_NAME, MODE_PRIVATE);
        //citire valori din fisiere de preferinte
        String name = preferences.getString(ProfileActivity.NAME, "");
        if (name != null && !name.isEmpty()) {
            tvMsg.setText(getString(R.string.main_welcome_message, name));
        }
    }

    private View.OnClickListener profileEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivityForResult(intent, PROFILE_REQUEST_CODE);
            }
        };
    }

}