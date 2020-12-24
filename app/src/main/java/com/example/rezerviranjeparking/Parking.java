package com.example.rezerviranjeparking;

public class Parking {
    private int id;
    private String parking_name;
    private String city_name;
    private String free;
    private String taken;
    public Parking(int id, String parking_name, String city_name, String free, String taken) {
        this.id = id;
        this.parking_name = parking_name;
        this.city_name = city_name;
        this.free = free;
        this.taken = taken;
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
        public String getTaken () {
            return taken;
        }
        public void setTaken (String taken){
            this.taken = taken;
        }
    }