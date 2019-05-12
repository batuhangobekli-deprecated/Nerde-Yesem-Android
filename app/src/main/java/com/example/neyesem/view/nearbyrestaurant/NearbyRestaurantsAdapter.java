package com.example.neyesem.view.nearbyrestaurant;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Priority;
import com.example.neyesem.R;
import com.example.neyesem.api.NeyesemAPIClient;
import com.example.neyesem.model.nearby_restaurants.Restaurant;
import com.example.neyesem.utils.NeyesemExtensions;

import java.util.ArrayList;
import java.util.List;

public class NearbyRestaurantsAdapter extends RecyclerView.Adapter<NearbyRestaurantsAdapter.ViewHolder> {

    private List<Restaurant> restaurantList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nearbyrestaurant, parent, false);
        return new NearbyRestaurantsAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);
        holder.ratingTextView.setText(restaurant.getUserRating().toString());
        holder.cuisineTextView.setText(restaurant.getCuisines());
        holder.restaurantNameTextView.setText(restaurant.getName());
        NeyesemExtensions.loadImage(holder.thumbnailImageView, restaurant.getThumb(), Priority.HIGH, false, R.drawable.common_google_signin_btn_icon_dark_focused);
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView ratingTextView;
        TextView cuisineTextView;
        TextView restaurantNameTextView;
        ImageView thumbnailImageView;

        public ViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.card_nearbyrestaurant);
            ratingTextView = view.findViewById(R.id.textview_itemnearbyrestaurant_rating);
            cuisineTextView = view.findViewById(R.id.textview_itemnearbyrestaurant_cuisine);
            restaurantNameTextView = view.findViewById(R.id.textview_itemnearbyrestaurant_name);
            thumbnailImageView = view.findViewById(R.id.imageview_itemnearbyrestaurant_thumbnail);

        }
    }
}
