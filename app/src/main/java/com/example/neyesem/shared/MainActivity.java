package com.example.neyesem.shared;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.neyesem.R;
import com.example.neyesem.services.LocationService;
import com.example.neyesem.utils.NeyesemExtensions;
import com.example.neyesem.utils.PermissionHandler;
import com.example.neyesem.utils.PermissionHandlerListener;
import com.google.android.gms.location.LocationResult;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements PermissionHandlerListener {
    private boolean permissionProcess = false;
    private PermissionHandler permissionHandler;
    private Location lastLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permissionHandler = new PermissionHandler(this);
    }

    @Override
    public void onResume() {
        startServices();
        super.onResume();
    }

    @Override
    public void onPause() {
        stopServices();
        super.onPause();
    }

    @Override
    public void OnGrantPermission(int RequestIdentifier) {
        startLocationService();
        permissionProcess = false;
    }
    private void startLocationService() {
        EventBus.getDefault().register(this);
        startService(new Intent(this, LocationService.class));
    }
    private void stopLocationService() {
        EventBus.getDefault().unregister(this);
        stopService(new Intent(this, LocationService.class));
    }

    @SuppressLint("MissingPermission")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLocationCallback(LocationResult locationResult) {
        //listSofraAdapter.setLocation(locationResult);
        this.lastLocation = locationResult.getLastLocation();
        stopLocationService();
    }
    private void startServices(){
        if (permissionHandler.checkPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            if(!NeyesemExtensions.isServiceRunning(getApplicationContext(),LocationService.class)){
                startLocationService();
            }
        }
        else if(!permissionProcess){
            permissionProcess = true;
            permissionHandler.requestPermission(Manifest.permission.ACCESS_FINE_LOCATION,getResources().getString(R.string.location_on_access_denied),1,this);
        }
    }
    public void stopServices() {
        if (NeyesemExtensions.isServiceRunning(getApplicationContext(), LocationService.class)) {
            stopLocationService();
        }
    }


}
