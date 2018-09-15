package com.priyambad.triggersms;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.location.LocationListener;
import android.location.LocationManager;
import android.content.Context;
import android.support.v7.app.AlertDialog;
/**
 * Created by priyambad on 24-Apr-17.
 */
public class GPSTracker extends Service implements LocationListener {

    public final Context context;
    boolean isGPSEnabled=false;
    boolean isNetworkEnabled=false;
    boolean canGetLocation=false;
    Location location;
    double longitude,latitude;
    protected LocationManager locationManager;
    public static final long min_dist=10;
    public static final long min_time=30000;

    public GPSTracker(Context context){
        this.context=context;
        getLocation();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

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
    public Location getLocation(){
        try{
            locationManager=(LocationManager)context.getSystemService(LOCATION_SERVICE);
            isGPSEnabled=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnabled=locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if(!isGPSEnabled && !isNetworkEnabled){

            }
            else{
                this.canGetLocation=true;
                if(isNetworkEnabled){
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,min_time,min_dist,this);
                    if(locationManager!=null){
                        location=locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
                        if(location!=null){
                            latitude=location.getLatitude();
                            longitude=location.getLongitude();
                        }
                    }
                }
                if(isGPSEnabled){
                    if(location!=null){
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,min_time,min_dist,this);
                        if(locationManager!=null){
                            location=locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
                            if(location!=null){
                                latitude=location.getLatitude();
                                longitude=location.getLongitude();
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return location;
    }
    public double getLatitude(){
        if(location!=null){
            latitude=location.getLatitude();
        }
        return latitude;
    }
    public double getLongitude(){
        if(location!=null){
            longitude=location.getLongitude();
        }
        return longitude;
    }
    public boolean isCanGetLocation(){
        return this.canGetLocation;
    }
}
