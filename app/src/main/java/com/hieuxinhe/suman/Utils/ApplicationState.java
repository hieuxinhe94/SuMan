package com.hieuxinhe.suman.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.hieuxinhe.suman.Constants.LocateLanguageConstants;

import java.util.Locale;

public class ApplicationState {
    public static Locale LOCALE = Locale.ENGLISH;


    private static ApplicationState INSTANCE;
    private static Context mCtx;

    // example key
    private static final String KEY_INSTALL_TIME = "KEY_INSTALL_TIME";
    private static final String KEY_APPLICATION = "pref.demo.example";
    private static final String KEY_USERNAME = "keyusername";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String SETUP_LANGUAGE = "Lan";


    public ApplicationState(Context context) {
        mCtx = context;
    }
    public static synchronized ApplicationState getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ApplicationState(context);
        }
        return INSTANCE;
    }



    public void setFirstTimeLaunch(boolean isFirstTime) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(IS_FIRST_TIME_LAUNCH, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(IS_FIRST_TIME_LAUNCH, false);
        editor.apply();
    }

    public boolean isFirstTimeLaunch() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(IS_FIRST_TIME_LAUNCH, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setLanguageSetup(String lan) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SETUP_LANGUAGE, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(SETUP_LANGUAGE, lan).apply();
    }
    public String getLanguageSetup() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SETUP_LANGUAGE, Context.MODE_PRIVATE);
        return sharedPreferences.getString(SETUP_LANGUAGE, "");
    }

    public boolean isSystemSupportedLanguageSpeech() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SETUP_LANGUAGE, Context.MODE_PRIVATE);
        String lan =  sharedPreferences.getString(SETUP_LANGUAGE, null);
        for (String item: LocateLanguageConstants.data) {
            if (item.equals(lan)) return true;
        }
        return false;
    }
}
