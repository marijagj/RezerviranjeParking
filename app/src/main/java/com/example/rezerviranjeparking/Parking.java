package com.example.rezerviranjeparking;

public class Parking {
    private int id;
    private String parking_name;
    private String city_name;
    private String free;
    private Float lat;
    private Float lon;
    public Parking(int id, String parking_name, String city_name, String free,Float lat,Float lon) {
        this.id = id;
        this.parking_name = parking_name;
        this.city_name = city_name;
        this.free = free;
        this.lat=lat;
        this.lon=lon;

    }
    public int getId () {
        return id;
    }
    public void setId ( int id){
        this.id = id;
    }
    public String getParkingName () {
        return parking_name;
    }
    public void setParkingName (String parking_name){
        this.parking_name = parking_name;
    }
    public String getCityName () {
        return city_name;
    }
    public void setCityName (String city_name){
        this.city_name = city_name;
    }
    public String getFree () {
        return free;
    }
    public void setFree (String free){
        this.free = free;
    }
    public Float getLat () {
        return lat;
    }
    public void setLat (Float lat){
        this.lat = lat;
    }
    public Float getLon () {
        return lon;
    }
    public void setLon (Float lon){
        this.lon = lon;
    }
}