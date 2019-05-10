package com.example.neyesem.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class NeyesemSharedPreferences {
    public static String BaseUrl = "https://developers.zomato.com/api/v2.1/";
    public static String UserKey = "b32f1315bae0ada454480368f64206d6";


    public static void initSharedPreference(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("NeyesemDB", Context.MODE_PRIVATE);
        BaseUrl = sharedPreferences.getString("BaseUrl", BaseUrl);
        UserKey = sharedPreferences.getString("UserKey", UserKey);
    }
}

