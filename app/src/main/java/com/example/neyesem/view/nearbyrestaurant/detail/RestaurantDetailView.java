package com.example.neyesem.view.nearbyrestaurant.detail;

import com.example.neyesem.model.restaurant_detail.RestaurantDetail;
import com.example.neyesem.shared.BaseView;


public interface RestaurantDetailView extends BaseView {
    void onGetRestaurantDetail(RestaurantDetail response);
}
