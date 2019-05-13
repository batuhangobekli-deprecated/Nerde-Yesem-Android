package com.example.neyesem.view.nearbyrestaurant.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.neyesem.R;
import com.example.neyesem.shared.BaseFragment;


public class RestaurantDetailFragment extends BaseFragment {
    private View parentView;
    private TextView restaurantNameTextView;
    private TextView cuisineTextView;
    private TextView openingHoursTextView;
    private TextView priceTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (parentView == null) {
            parentView = inflater.inflate(R.layout.fragment_restaurantdetail, container, false);
            initViews();

        }
        return parentView;
    }
    private void initViews(){
        restaurantNameTextView = parentView.findViewById(R.id.textview_restaurantdetail_name);
        cuisineTextView = parentView.findViewById(R.id.textview_restaurantdetail_cuisine);
        openingHoursTextView = parentView.findViewById(R.id.textview_restaurantdetail_hours);
        priceTextView = parentView.findViewById(R.id.textview_restaurantdetail_price);
    }

    private void getRestaurantDetails(){

    }

    public static RestaurantDetailFragment newInstance(){
        return new RestaurantDetailFragment();
    }

}
