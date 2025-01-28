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

import java.util.Date;
import java.util.List;

public class RevisionsAdapter extends ArrayAdapter<RevisionType> {
    private Context context;
    private int resource;
    private List<RevisionType> revisionTypes;
    private LayoutInflater inflater;

    DateConverter dateConverter;


    public RevisionsAdapter(@NonNull Context context, int resource, @NonNull List<RevisionType> objects  ,LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.revisionTypes = objects;
        this.inflater = inflater;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        RevisionType revisionType =revisionTypes.get(position);
        if( revisionType != null){
            addRevisionType(view, revisionType.getRevisionType());
            addServiceName(view, revisionType.getServiceName());
            addDateTime(view, revisionType.getDateTime());
        }
        return view;
    }

    private void addRevisionType(View view, String revisionType) {
        TextView textView = view.findViewById(R.id.tv_row_revision_type);
        populateTextViewContent(textView, revisionType);
    }

    private void addServiceName(View view, String serviceName) {
        TextView textView = view.findViewById(R.id.tv_row_service_name);
        populateTextViewContent(textView, serviceName);
    }


    private void addDateTime(View view, String dateTime) {
        TextView textView = view.findViewById(R.id.tv_row_date);
        String dateView = String.valueOf(dateTime);
      //  String dateView = context.getString(R.string.lv_row_view_date_number_format, dateTime);
        populateTextViewContent(textView, dateView);
    }

    private void populateTextViewContent(TextView textView, String value) {
        if (value != null && !value.trim().isEmpty()) {
            textView.setText(value);
        } else {
            textView.setText(R.string.main_new_revision_type_added_message);
        }
    }

}
