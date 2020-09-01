package com.akdeveloper.bootcamapplication.models;

public class Devlops {

    private float longitude;
    private float latitude;
    private String locationTitle;
    private String locationAddress;
    private String locationImgUrl;
    final String DRAWABLE = "drawable/";

    public Devlops(float latitude, float longitude, String locationTitle, String locationAddress, String locationImgUrl) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.locationTitle = locationTitle;
        this.locationAddress = locationAddress;
        this.locationImgUrl = locationImgUrl;
    }


    public String getImgUrl() {
        return DRAWABLE + locationImgUrl;
    }
    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public String getLocationTitle() {
        return locationTitle;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public String getLocationImgUrl() {
        return locationImgUrl;
    }
}
