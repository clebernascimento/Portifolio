package com.cleber.financeiro;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    //shared pref mode
    int PRIVATE_MODE = 0;

    //shared preferences file name
    private static final String PREF_NAME = "android -  Bem-Vindo";

    private static final String LANÇAMENTO_DA_PRIMEIRA_VEZ = "lançando pela primeira vez";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(LANÇAMENTO_DA_PRIMEIRA_VEZ, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(LANÇAMENTO_DA_PRIMEIRA_VEZ, true);
    }

}