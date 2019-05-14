package com.example.neyesem.view.categories;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.example.neyesem.api.ApiInterface;
import com.example.neyesem.api.NeyesemAPIClient;
import com.example.neyesem.model.categories.CategoriesResponse;
import com.example.neyesem.utils.CustomCallBack;

import retrofit2.Call;
import retrofit2.Response;

public class CategoriesPresenter {
    private ApiInterface apiInterface = NeyesemAPIClient.getClientWithApi().create(ApiInterface.class);
    private CategoriesView categoriesView;

    public CategoriesPresenter(CategoriesView categoriesView) {
        this.categoriesView = categoriesView;
    }

    public void getCategories(AppCompatActivity activity, ViewGroup container) {
        apiInterface.getCategories().enqueue(new CustomCallBack<CategoriesResponse>(activity,container,categoriesView,CustomCallBack.Type.Layout) {
            @Override
            public void onResponse(@NonNull Call<CategoriesResponse> call, @NonNull Response<CategoriesResponse> response) {
                super.onResponse(call, response);
                if(response.isSuccessful())
                    categoriesView.onGetCategories(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<CategoriesResponse> call, @NonNull Throwable t) {
                super.onFailure(call, t);
            }
        });
    }
}
