package com.example.neyesem.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.neyesem.BuildConfig;
import com.example.neyesem.R;
import com.example.neyesem.shared.BaseFragment;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

public class NearbyRestaurantsFragment extends BaseFragment {
    private View parentView;

    public static NearbyRestaurantsFragment newInstance(){
        return new NearbyRestaurantsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (parentView == null) {
            parentView = inflater.inflate(R.layout.fragment_nearbyrestaurants, container, false);
            AndPermission.with(this)
                    .runtime()
                    .permission(Permission.Group.LOCATION)
                    .onGranted(permissions -> {
                        // Storage permission are allowed.
                    })
                    .onDenied(permissions -> {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Ne Yesem")
                                .setMessage(R.string.location_on_access_denied)
                                .setPositiveButton("Tamam", (dialog, which) -> {
                                    if (AndPermission.hasAlwaysDeniedPermission(getContext(),"sa")) {
                                        getContext().startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + BuildConfig.APPLICATION_ID)));
                                    }
                                })
                                .setCancelable(false).show();

                    })
                    .start();
        }
        return parentView;
    }
}
