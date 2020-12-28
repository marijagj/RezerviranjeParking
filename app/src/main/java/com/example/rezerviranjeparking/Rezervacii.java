package com.example.rezerviranjeparking;

public class Rezervacii {
    private int id;
    private String username;
    private String parking_name;
    private String city_name;
    private String date;
    private String time;
    private Float lat;
    private Float lon;
    public Rezervacii(int id, String username, String parking_name,String city_name, String date, String time,Float lat,Float lon) {
        this.id = id;
        this.username=username;
        this.parking_name = parking_name;
        this.city_name = city_name;
        this.date = date;
        this.time = time;
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
    public String getDate () {
        return date;
    }
    public void setDate (String date){
        this.date = date;
    }
    public String getTime () {
        return time;
    }
    public void setTime (String time){
        this.time = time;
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
