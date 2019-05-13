package com.example.neyesem.view.nearbyrestaurant.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Priority;
import com.example.neyesem.R;
import com.example.neyesem.model.restaurant_detail.RestaurantDetail;
import com.example.neyesem.shared.BaseFragment;
import com.example.neyesem.utils.NeyesemExtensions;

import retrofit2.Response;

public class RestaurantDetailFragment extends BaseFragment implements RestaurantDetailView {
    private View parentView;
    private TextView restaurantNameTextView;
    private TextView cuisineTextView;
    private TextView votesTextView;
    private TextView priceTextView;
    private ImageView thumbnailImageView;
    private RestaurantDetailPresenter presenter;
    private RelativeLayout container;
    private int restaurantId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (parentView == null) {
            parentView = inflater.inflate(R.layout.fragment_restaurantdetail, container, false);
            presenter =  new RestaurantDetailPresenter(this);
            initViews();
            getRestaurantDetails();
        }
        return parentView;
    }
    private void initViews(){
        restaurantNameTextView = parentView.findViewById(R.id.textview_restaurantdetail_name);
        cuisineTextView = parentView.findViewById(R.id.textview_restaurantdetail_cuisine);
        votesTextView = parentView.findViewById(R.id.textview_restaurantdetail_votes);
        priceTextView = parentView.findViewById(R.id.textview_restaurantdetail_price);
        container = parentView.findViewById(R.id.relativelayout_restaurantdetail_container);
        thumbnailImageView = parentView.findViewById(R.id.imageview_restaurantdetail_thumbnail);
    }

    private void getRestaurantDetails(){
        presenter.getRestaurantDetail((AppCompatActivity) getActivity(),container,restaurantId);
    }

    public static RestaurantDetailFragment newInstance(int restaurantId){
        RestaurantDetailFragment restaurantDetail = new RestaurantDetailFragment();
        restaurantDetail.restaurantId = restaurantId;
        return restaurantDetail;
    }

    @Override
    public void onGetRestaurantDetail(RestaurantDetail restaurantDetail) {
        restaurantNameTextView.setText(restaurantDetail.getName());
        cuisineTextView.setText(restaurantDetail.getCuisines());
        votesTextView.setText(restaurantDetail.getUserRating().getVotes());
        priceTextView.setText(restaurantDetail.getPriceRange());
        NeyesemExtensions.loadImage(thumbnailImageView, restaurantDetail.getThumb(), Priority.HIGH, false, R.color.black);

    }

    @Override
    public void onConfirmDialog() {

    }

    @Override
    public void onRetryLayout() {

    }

    @Override
    public void onUserError(Response serverResponse) {

    }
}
