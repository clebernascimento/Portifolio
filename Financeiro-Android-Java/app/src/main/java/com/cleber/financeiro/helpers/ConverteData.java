package com.cleber.financeiro.helpers;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConverteData {

    public static Calendar textoData(String stringData){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(simpleDateFormat.parse(stringData));
        }catch (ParseException e){
            Log.d("Finaceiro", e.getMessage());
        }
        return calendar;
    }
}
