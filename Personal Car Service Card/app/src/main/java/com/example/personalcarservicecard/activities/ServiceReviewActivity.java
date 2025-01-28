package com.example.personalcarservicecard.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.personalcarservicecard.R;
import com.example.personalcarservicecard.utilClasses.Review;
import com.google.android.material.textfield.TextInputEditText;

public class ServiceReviewActivity extends AppCompatActivity {
    public static final String REVIEW_KEY = "review_key";

    private TextInputEditText tietAddServiceName;
    private RatingBar rbStars;
    private TextView rateCount;
    private Button btnSave;


    float rateValue;
    String temp;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_review);

        initComponents();
        btnSave.setOnClickListener(addSaveClickEvent());
        intent = getIntent();
    }

    private void initComponents() {
        tietAddServiceName = findViewById(R.id.tiet_add_service_name);
        rbStars = findViewById(R.id.feedback_ratingBar);
        btnSave = findViewById(R.id.btn_save_feedback);
        rateCount = findViewById(R.id.tv_rating_bar);

        rbStars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rateValue = ratingBar.getRating();

               if(rateValue <= 1 && rateValue >0)
                   rateCount.setText("1 star rating");
               else if(rateValue <=2 && rateValue >1)
                   rateCount.setText("2 stars rating");
               else if(rateValue <=3 && rateValue >2)
                   rateCount.setText("3 stars rating");
               else if(rateValue <=4 && rateValue>3)
                   rateCount.setText("4 stars rating");
               else if(rateValue <=5 && rateValue>4)
                   rateCount.setText("5 stars rating");
            }
        });
        temp = rateCount.getText().toString();
        rbStars.setRating(0);
        rateCount.setText("");
       // viewText();
    }

   /* private void viewText() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }*/


    private View.OnClickListener addSaveClickEvent(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(validate()){
                    Review rev = buildServiceReviewFromWidgets();
                    intent.putExtra(REVIEW_KEY, rev);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };
    }



    private boolean validate(){
        if(tietAddServiceName.getText() == null || tietAddServiceName.getText().toString()
                .trim().length() < 3){
            Toast.makeText(getApplicationContext(),
                    R.string.add_invalid_name_error,
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        return true;
    }


    private Review buildServiceReviewFromWidgets() {
        String name = tietAddServiceName.getText().toString();
        //String msg = String.valueOf(myRating);
       String msg = rateCount.getText().toString();
        return new Review(name, msg);
    }
}