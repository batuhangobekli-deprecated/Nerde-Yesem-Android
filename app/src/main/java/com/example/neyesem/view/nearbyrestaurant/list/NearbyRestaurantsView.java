package com.example.neyesem.view.nearbyrestaurant.list;

import com.example.neyesem.model.nearby_restaurants.GeocodeResponse;
import com.example.neyesem.shared.BaseView;

public interface NearbyRestaurantsView extends BaseView {
    void onGetNearbyRestaurants(GeocodeResponse response);
}
