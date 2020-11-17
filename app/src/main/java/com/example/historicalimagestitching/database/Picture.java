// https://dzone.com/articles/create-a-database-android-application-in-android-s


package com.example.historicalimagestitching.database;

import android.graphics.Bitmap;

public class Picture {

    private int ID;
    private String title;
    private String locationName;
    private double latitude;
    private double longitude;
    private byte[] image;


    public Picture(){}

    public Picture(String title, String locationName,
                   double latitude, double longitude, byte[] image) {
        this.ID = ID;
        this.title = title;
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.image = image;
    }


    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public String getLocationName() {
        return locationName;
    }

    public double getLatitude() {
        return latitude;
    }

    public byte[] getImage(){
        return image;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setImage(byte[] image){
        this.image = image;
    }
}
