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
import com.example.personalcarservicecard.asyncTask.AsyncTaskRunner;
import com.example.personalcarservicecard.asyncTask.Callback;
import com.example.personalcarservicecard.network.HttpManager;
import com.example.personalcarservicecard.utilClasses.RevisionType;
import com.example.personalcarservicecard.utilClasses.RevisionsAdapter;
import com.example.personalcarservicecard.utilClasses.RevisionsJsonParser;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class RevisionsFormActivity extends AppCompatActivity {
    private static final int ADD_REVISION_TYPE_REQUEST_CODE = 200;
    //here the url
    public static final String REVISIONS_URL = "http://www.json-generator.com/api/json/get/cpIyUzayhu?indent=2";

    private FloatingActionButton fabAdd;
    private ListView lvRevisions;
    private  List<RevisionType> revisions = new ArrayList<>();

    private AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revisions_form);

        initComponents();
        getRevisionsFromHttp();
    }

    private void getRevisionsFromHttp(){
        Callable<String> asyncOperation = new HttpManager(REVISIONS_URL);
        Callback<String> mainThreadOperation = receiveRevisionsFromHttp();
        asyncTaskRunner.executeAsync(asyncOperation, mainThreadOperation);
    }

    private Callback<String> receiveRevisionsFromHttp(){
        return new Callback<String>() {
            @Override
            public void runResultOnUiThread(String result) {
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                revisions.addAll(RevisionsJsonParser.fromJson(result));
                notifyAdapter();
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_REVISION_TYPE_REQUEST_CODE && resultCode == RESULT_OK && data !=null){
            //preluare revizii din intent
            RevisionType rev = (RevisionType) data.getSerializableExtra(RevisionsActivity.REVISION_KEY);
            if(rev != null){
                Toast.makeText(getApplicationContext(),
                        getString(R.string.revision_added_message, rev.toString()),
                        Toast.LENGTH_LONG).show();
                revisions.add(rev);
                notifyAdapter();
            }
        }
    }

    private void initComponents(){
        fabAdd = findViewById(R.id.flt_ADD_ARF);
        lvRevisions = findViewById(R.id.lv_revisions_form);
        addLvRevisionAdapter();
        fabAdd.setOnClickListener(addRevisionClickEvent());
    }

    private View.OnClickListener addRevisionClickEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //deschidere activitate
                Intent intent = new Intent(getApplicationContext(), RevisionsActivity.class);
                startActivityForResult(intent, ADD_REVISION_TYPE_REQUEST_CODE);
            }
        };
    }

    private void addLvRevisionAdapter() {
        //initializare ArrayAdapter de tip RevisionType pentru adaugare pe ListView
        RevisionsAdapter adapter = new RevisionsAdapter(getApplicationContext(),
                R.layout.lv_revisions_adapter, revisions, getLayoutInflater());
        lvRevisions.setAdapter(adapter);
    }

    private void notifyAdapter() {
        ArrayAdapter adapter = (ArrayAdapter) lvRevisions.getAdapter();
        adapter.notifyDataSetChanged();
    }
}