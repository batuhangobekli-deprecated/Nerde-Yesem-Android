package com.example.neyesem.api;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.neyesem.utils.NeyesemSharedPreferences;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NeyesemAPIClient {

    @NonNull
    public static Retrofit getClientWithApi() {
        HttpLoggingInterceptor headerLogging = new HttpLoggingInterceptor();
        HttpLoggingInterceptor bodyLogging = new HttpLoggingInterceptor();
        headerLogging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        bodyLogging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(bodyLogging)
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15,TimeUnit.SECONDS)
                .addInterceptor(
                        chain -> {
                            Request.Builder request = chain.request().newBuilder();
                            if (!TextUtils.isEmpty(NeyesemSharedPreferences.UserKey))
                                request = request.addHeader("user-key",  NeyesemSharedPreferences.UserKey);
                            request = request.method(chain.request().method(), chain.request().body());
                            return chain.proceed(request.build());

                        }).build();


        return new Retrofit.Builder()
                .client(client)
                .baseUrl(NeyesemSharedPreferences.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
