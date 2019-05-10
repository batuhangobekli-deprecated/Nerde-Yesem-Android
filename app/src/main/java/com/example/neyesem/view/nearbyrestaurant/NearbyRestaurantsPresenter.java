package com.example.neyesem.view.nearbyrestaurant;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.example.neyesem.api.ApiInterface;
import com.example.neyesem.api.NeyesemAPIClient;
import com.example.neyesem.model.nearby_restaurants.GeocodeResponse;
import com.example.neyesem.utils.CustomCallBack;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class NearbyRestaurantsPresenter {
    private ApiInterface apiInterface = NeyesemAPIClient.getClientWithApi().create(ApiInterface.class);
    private NearbyRestaurantsView nearbyRestaurantsView;

    public NearbyRestaurantsPresenter(NearbyRestaurantsView nearbyRestaurantsView) {
        this.nearbyRestaurantsView = nearbyRestaurantsView;
    }
    public void getBlogDetail(AppCompatActivity activity, ViewGroup container, double latitude, double longitude) {
        apiInterface.getNearbyRestaurants(latitude,longitude).enqueue(new CustomCallBack<GeocodeResponse>(activity,container,nearbyRestaurantsView,CustomCallBack.Type.Layout) {
            @Override
            public void onResponse(@NonNull Call<GeocodeResponse> call, @NonNull Response<GeocodeResponse> response) {
                super.onResponse(call, response);
                if(response.isSuccessful())
                    nearbyRestaurantsView.onGetNearbyRestaurants(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<GeocodeResponse> call, @NonNull Throwable t) {
                super.onFailure(call, t);
            }
        });
    }


}
