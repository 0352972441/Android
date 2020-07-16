package com.example.odometerapp.Services;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;

import androidx.core.content.ContextCompat;

import java.util.Random;

public class OdometerServers extends Service implements LocationListener {
    private static double distanceMeter;
    private  LocationListener listener;
    private static Location lastLocation = null;
    private static LocationManager locationManager;
    private  final IBinder binder = new  OdometerBinder();
    public OdometerServers() {
    }

    @Override
    public void onCreate() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            String provider = locationManager.getBestProvider(new Criteria(),true);
            if(provider != null){
                locationManager.requestLocationUpdates(provider,1000,1,this);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class OdometerBinder extends Binder{
        public OdometerServers getDometer(){
            return OdometerServers.this;
        }
    }

    public double getDistance(){
        return distanceMeter /1609.344;
    }

    @Override
    public void onLocationChanged(Location location) {
        if(lastLocation == null){
            lastLocation = location;
        }
        distanceMeter += location.distanceTo(lastLocation);
        lastLocation = location;
    }

    public void onDestroy(){
        super.onDestroy();
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                                                PackageManager.PERMISSION_GRANTED){
            if(lastLocation != null && this != null){
                locationManager.removeUpdates(this);
                  listener = this;
                  listener = null;
                  locationManager = null;
            }
        }
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
