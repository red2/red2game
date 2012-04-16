package com.five.util;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;

public class LocationServices
{
    /**
     * 
     */
    private LocationManager locationManager;
    
    /**
     * 
     */
    private Context context;
    
    /**
     * 提供的位置信息精度差，但速度较GPS定位快
     */
    private String networkProvider = null;
    
    /**
     * 可以提供更加精确的位置信息，但定位速度和质量受到卫星数量和环境情况的影响
     */
    private String gpsProvider = null;
    
    /**
     * Location对象中，包含了可以确定位置的信息，如经度、纬度和速度等
     */
    private Location lastKnownlocation = null;
    
    /**
     * Location包含了可以确定位置的信息，如经度、纬度和速度等
     */
    private Location location = null;
    
    public LocationServices(Context context)
    {
        this.context = context;
        this.locationManager = (LocationManager) this.context.getSystemService(Context.LOCATION_SERVICE);
        this.networkProvider = LocationManager.NETWORK_PROVIDER;
    }
    
    private final LocationListener locationListener = new LocationListener()
    {
        
        @Override
        public void onLocationChanged(Location location)
        {
            // 在设备的位置改变时被调用
        }
        
        @Override
        public void onProviderDisabled(String provider)
        {
            // 在用户启用具有定位功能的硬件时被调用
        }
        
        @Override
        public void onProviderEnabled(String provider)
        {
            // 在用户启用具有定位功能的硬件时被调用
        }
        
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras)
        {
            /*
             * 在提供定位功能的硬件的状态改变时被调用 如从不可获取位置信息状态到可以获取位置信息的状态，反之亦然
             */
            switch (status)
            {
                case LocationProvider.AVAILABLE:
                {
                    Log.v("Status", "AVAILABLE");
                }
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                {
                    Log.v("Status", "OUT_OF_SERVICE");
                }
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                {
                    Log.v("Status", "TEMPORARILY_UNAVAILABLE");
                }
                    break;
            }
        }
        
    };
}
