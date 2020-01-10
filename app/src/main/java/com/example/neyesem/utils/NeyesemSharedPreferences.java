package com.example.neyesem.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class NeyesemSharedPreferences {
    public static String BaseUrl = "https://developers.zomato.com/api/v2.1/";
    public static String UserKey = "9ca46a91642ef8d445d61f49325b6a05";


    public static void initSharedPreference(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("NeyesemDB", Context.MODE_PRIVATE);
        BaseUrl = sharedPreferences.getString("BaseUrl", BaseUrl);
        UserKey = sharedPreferences.getString("UserKey", UserKey);
    }
}

