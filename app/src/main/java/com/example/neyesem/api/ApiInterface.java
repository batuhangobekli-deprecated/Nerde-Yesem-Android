package com.example.neyesem.api;

import com.example.neyesem.model.categories.CategoriesResponse;
import com.example.neyesem.model.nearby_restaurants.GeocodeResponse;
import com.example.neyesem.model.nearby_restaurants.Restaurant;
import com.example.neyesem.model.restaurant_detail.RestaurantDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("geocode")
    Call<GeocodeResponse> getNearbyRestaurants(@Query("lat") double latitude, @Query("lon") double longitude);

    @GET("restaurant")
    Call<RestaurantDetail> getRestaurantDetail(@Query("res_id") int id);

    @GET("categories")
    Call<CategoriesResponse> getCategories();

}
