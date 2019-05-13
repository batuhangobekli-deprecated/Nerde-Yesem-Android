package com.example.neyesem.view.nearbyrestaurant.detail;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.example.neyesem.api.ApiInterface;
import com.example.neyesem.api.NeyesemAPIClient;
import com.example.neyesem.model.restaurant_detail.RestaurantDetail;
import com.example.neyesem.utils.CustomCallBack;

import retrofit2.Call;
import retrofit2.Response;

public class RestaurantDetailPresenter {

    private ApiInterface apiInterface = NeyesemAPIClient.getClientWithApi().create(ApiInterface.class);
    private RestaurantDetailView restaurantDetailView;

    public RestaurantDetailPresenter(RestaurantDetailView restaurantDetailView) {
        this.restaurantDetailView = restaurantDetailView;
    }

    public void getBlogDetail(AppCompatActivity activity, ViewGroup container, String restaurantId) {
        apiInterface.getRestaurantDetail(restaurantId).enqueue(new CustomCallBack<RestaurantDetail>(activity,container,restaurantDetailView,CustomCallBack.Type.Layout) {
            @Override
            public void onResponse(@NonNull Call<RestaurantDetail> call, @NonNull Response<RestaurantDetail> response) {
                super.onResponse(call, response);
                if(response.isSuccessful())
                    restaurantDetailView.onGetRestaurantDetail(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<RestaurantDetail> call, @NonNull Throwable t) {
                super.onFailure(call, t);
            }
        });
    }
}
