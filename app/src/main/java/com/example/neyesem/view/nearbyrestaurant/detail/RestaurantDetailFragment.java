package com.example.neyesem.view.nearbyrestaurant.detail;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.neyesem.R;
import com.example.neyesem.model.restaurant_detail.RestaurantDetail;
import com.example.neyesem.shared.BaseFragment;
import com.example.neyesem.utils.NeyesemExtensions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Response;

public class RestaurantDetailFragment extends BaseFragment implements RestaurantDetailView , OnMapReadyCallback {
    private View parentView;
    private TextView restaurantNameTextView;
    private TextView cuisineTextView;
    private TextView votesTextView;
    private TextView priceTextView;
    private ImageView thumbnailImageView;
    private RestaurantDetailPresenter presenter;
    private TextView phoneNumberTextView;
    private RelativeLayout container;
    private FragmentManager fragmentManager;
    private SupportMapFragment mapFragment;
    private GoogleMap mGoogleMap;
    private LatLng location;
    private Marker marker;
    private String photo;
    private TextView ratingTextView;
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
        phoneNumberTextView = parentView.findViewById(R.id.textview_restaurantdetail_phone);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment_restaurantdetail_map);
        ratingTextView = parentView.findViewById(R.id.textview_restaurantdetail_rating);
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
        priceTextView.setText(restaurantDetail.getPriceRange()+ "/5");
        ratingTextView.setText(restaurantDetail.getUserRating().getAggregateRating());
        if (restaurantDetail.getPhoneNumbers() != null)
            phoneNumberTextView.setText(restaurantDetail.getPhoneNumbers());
        else
            phoneNumberTextView.setText("Telefon numarası yok");

        NeyesemExtensions.loadImage(thumbnailImageView, restaurantDetail.getThumb(), Priority.HIGH, false, R.color.black);
        photo = restaurantDetail.getThumb();
        location = restaurantDetail.getLatlong();
        mapFragment.getMapAsync(this);
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

    @Override
    public String getTitle() {
        return "Restoran";
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(location).zoom(15.0f).build()));
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(location);
        marker = mGoogleMap.addMarker(markerOptions);
        MultiTransformation<Bitmap> multi = new MultiTransformation<>(new CenterCrop(), new CircleCrop());
        RequestOptions requestOptions = new RequestOptions().priority(Priority.IMMEDIATE).override(100).transform(multi);
        Glide.with(getContext()).asBitmap().load(photo).apply(requestOptions)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        marker.setIcon(BitmapDescriptorFactory.fromBitmap(resource));
                    }
                });
    }
}
