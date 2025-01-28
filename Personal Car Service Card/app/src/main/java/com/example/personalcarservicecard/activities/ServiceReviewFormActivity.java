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
import com.example.personalcarservicecard.utilClasses.Review;
import com.example.personalcarservicecard.utilClasses.RevisionType;

import java.util.ArrayList;
import java.util.List;

public class ServiceReviewFormActivity extends AppCompatActivity {
    private static final int ADD_SERVICE_REVIEW_REQUEST_CODE = 200;

    private Button btnAdd;
    private ListView lvReviews;
    private List<Review> reviews = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_review_form);

        initComponents();
        addLvReviewsAdapter();
        btnAdd.setOnClickListener(addReviewClickEvent());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_SERVICE_REVIEW_REQUEST_CODE && resultCode == RESULT_OK && data !=null){
            //preluare recenzii din intent
            Review review = (Review) data.getSerializableExtra(ServiceReviewActivity.REVIEW_KEY);
            if(review != null){
                Toast.makeText(getApplicationContext(),
                        getString(R.string.reviews_added_msg, review.toString()), Toast.LENGTH_LONG).show();
                reviews.add(review);
                ArrayAdapter adapter = (ArrayAdapter) lvReviews.getAdapter();
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void initComponents(){
        btnAdd = findViewById(R.id.btn_add_review);
        lvReviews = findViewById(R.id.lv_service_review);
    }

    private View.OnClickListener addReviewClickEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //deschidere activitate
                Intent intent = new Intent(getApplicationContext(), ServiceReviewActivity.class);
                startActivityForResult(intent, ADD_SERVICE_REVIEW_REQUEST_CODE);
            }
        };
    }

    private void addLvReviewsAdapter() {
        //initializare ArrayAdapter de tip Review pentru adaugare pe ListView
        ArrayAdapter<Review> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1, reviews);
        lvReviews.setAdapter(adapter);
    }


}