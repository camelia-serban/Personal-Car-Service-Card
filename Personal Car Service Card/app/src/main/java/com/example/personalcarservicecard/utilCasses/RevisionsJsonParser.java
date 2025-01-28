package com.example.personalcarservicecard.utilClasses;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RevisionsJsonParser {
    public static final String revisionType = "revisionType";
    public static final String serviceName = "serviceName";
    public static final String date = "dateTime";

    public static List<RevisionType> fromJson(String json){
        try{
            JSONArray array = new JSONArray(json);
            return readRevisions(array);
        }catch(JSONException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private static List<RevisionType> readRevisions(JSONArray array) throws JSONException{
        List<RevisionType> revisions = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            RevisionType revision = readRevisions(array.getJSONObject(i));
            revisions.add(revision);
        }
        return revisions;
    }

    private static RevisionType readRevisions(JSONObject object) throws JSONException{
        String revType = object.getString(revisionType);
        String servName = object.getString(serviceName);
        String dateTime = object.getString(date);
        return new RevisionType(revType, servName, dateTime);

    }

}
