package com.example.neyesem.view.nearbyrestaurant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.neyesem.BuildConfig;
import com.example.neyesem.R;
import com.example.neyesem.model.nearby_restaurants.GeocodeResponse;
import com.example.neyesem.services.LocationService;
import com.example.neyesem.shared.BaseFragment;
import com.google.android.gms.location.LocationResult;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Response;

public class NearbyRestaurantsFragment extends BaseFragment implements NearbyRestaurantsView{
    private View parentView;
    private RelativeLayout container;
    private NearbyRestaurantsPresenter presenter = new NearbyRestaurantsPresenter(this);
    private Location lastLocation;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (parentView == null) {
            parentView = inflater.inflate(R.layout.fragment_nearbyrestaurants, container, false);
            initViews();
            configurelocationPermission();
            getNearbyRestaurants();
        }
        return parentView;
    }
    private void getNearbyRestaurants(){
        presenter.getBlogDetail((AppCompatActivity) getActivity(),container,40.0,-72.12);
    }
    private void initViews(){
        container = parentView.findViewById(R.id.relativelayout_container);
    }
    private void configurelocationPermission(){
        AndPermission.with(this)
                .runtime()
                .permission(Permission.Group.LOCATION)
                .onGranted(permissions -> {
                    startLocationService();
                })
                .onDenied(permissions -> {
                    new AlertDialog.Builder(getContext())
                            .setTitle("Ne Yesem")
                            .setMessage(R.string.location_on_access_denied)
                            .setPositiveButton("Tamam", (dialog, which) -> {
                                if (AndPermission.hasAlwaysDeniedPermission(getContext(),"")) {
                                    getContext().startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + BuildConfig.APPLICATION_ID)));
                                }
                            })
                            .setCancelable(false).show();

                })
                .start();
    }
    private void startLocationService() {
        EventBus.getDefault().register(this);
        getActivity().startService(new Intent(getContext(), LocationService.class));
    }

    private void stopLocationService() {
        EventBus.getDefault().unregister(this);
        getActivity().stopService(new Intent(getContext(), LocationService.class));
    }


    @SuppressLint("MissingPermission")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLocationCallback(LocationResult locationResult) {
        Toast.makeText(getContext(), locationResult.getLastLocation().toString(), Toast.LENGTH_SHORT).show();
        this.lastLocation = locationResult.getLastLocation();
        stopLocationService();
    }


    @Override
    public void onGetNearbyRestaurants(GeocodeResponse response) {

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
    public static NearbyRestaurantsFragment newInstance(){
        return new NearbyRestaurantsFragment();
    }
}
