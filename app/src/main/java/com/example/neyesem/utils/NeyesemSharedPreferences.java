package com.example.neyesem.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class NeyesemSharedPreferences {
    public static String BaseUrl = "https://developers.zomato.com/api/v2.1/";
    public static String UserKey = "";


    public static void initSharedPreference(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("NeyesemDB",Context.MODE_PRIVATE);
        BaseUrl = sharedPreferences.getString("BaseUrl",BaseUrl);
        UserKey = sharedPreferences.getString("UserKey", UserKey);
    }

    @SuppressLint("ApplySharedPref")
    public static void setLogin(Context context, String Token){
        SharedPreferences sharedPreferences = context.getSharedPreferences("NeyesemDB",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("UserKey",Token);
        editor.apply();
        initSharedPreference(context);
    }
}

