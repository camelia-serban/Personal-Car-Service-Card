package com.example.personalcarservicecard.utilClasses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConverter {
    private static final String FORMAT_DATE = "dd/MM/yyyy";
    private final SimpleDateFormat formatter;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public DateConverter() {
        //SimpleDateFormat utilizata pentru realizarea conversiilor de la String la Date si invers
        formatter = new SimpleDateFormat(FORMAT_DATE, Locale.US);
    }

    public Date fromString(String value) {
        try {
            return formatter.parse(value);
        } catch (ParseException e) {
            return null;
        }
    }

    public String toString(Date value) {
        if (value == null) {
            return null;
        }
        return formatter.format(value);
    }
}
