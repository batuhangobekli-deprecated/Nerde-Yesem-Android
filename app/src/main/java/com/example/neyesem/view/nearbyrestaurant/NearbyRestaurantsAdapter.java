package com.example.neyesem.view.nearbyrestaurant;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Priority;
import com.example.neyesem.R;
import com.example.neyesem.model.nearby_restaurants.NearbyRestaurant;
import com.example.neyesem.model.nearby_restaurants.Restaurant;
import com.example.neyesem.utils.NeyesemExtensions;
import com.google.android.gms.location.LocationResult;

import java.util.ArrayList;
import java.util.List;

public class NearbyRestaurantsAdapter extends RecyclerView.Adapter<NearbyRestaurantsAdapter.ViewHolder> {
    private Location locationResult;
    private List<NearbyRestaurant> restaurantList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nearbyrestaurant, parent, false);
        return new NearbyRestaurantsAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position).getRestaurant();
        holder.ratingTextView.setText(restaurant.getUserRating().getAggregateRating());
        String cuisineString = restaurant.getCuisines().split(",")[0];
        holder.cuisineTextView.setText(cuisineString);
        holder.distanceTextView.setText("sa");
        holder.restaurantNameTextView.setText(restaurant.getName());
        NeyesemExtensions.loadImage(holder.thumbnailImageView, restaurant.getThumb(), Priority.HIGH, false, R.color.black);
        if(locationResult == null)
            holder.distanceView.setVisibility(View.GONE);
        else{
            holder.distanceView.setVisibility(View.VISIBLE);
            String distance = NeyesemExtensions.getDistance(restaurant.getLocation().getLatitude(),restaurant.getLocation().getLongitude(),locationResult.getLatitude(),locationResult.getLongitude());
            holder.distanceTextView.setText(distance);
        }
    }
    public void setList(List<NearbyRestaurant> restaurants) {
        this.restaurantList.clear();
        this.restaurantList.addAll(restaurants);
        notifyDataSetChanged();
    }
    public void setLocation(LocationResult locResult) {
        this.locationResult = locResult.getLastLocation();
        notifyDataSetChanged();
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
        TextView distanceTextView;
        RelativeLayout distanceView;

        public ViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.card_nearbyrestaurant);
            ratingTextView = view.findViewById(R.id.textview_itemnearbyrestaurant_rating);
            cuisineTextView = view.findViewById(R.id.textview_itemnearbyrestaurant_cuisine);
            restaurantNameTextView = view.findViewById(R.id.textview_itemnearbyrestaurant_name);
            thumbnailImageView = view.findViewById(R.id.imageview_itemnearbyrestaurant_thumbnail);
            distanceTextView = view.findViewById(R.id.textview_itemnearbyrestaurant_distance);
            distanceView = view.findViewById(R.id.reletivelayout_nearbyrestaurant_distance);

        }
    }
}
