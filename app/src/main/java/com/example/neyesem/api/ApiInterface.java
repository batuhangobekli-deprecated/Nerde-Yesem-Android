package com.example.neyesem.api;

import com.example.neyesem.model.nearby_restaurants.GeocodeResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("geocode")
    Call<GeocodeResponse> getNearbyRestaurants(@Query("lat") double latitude, @Query("lon") double longitude);



}
