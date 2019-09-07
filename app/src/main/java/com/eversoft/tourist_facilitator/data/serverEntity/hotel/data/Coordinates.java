package com.eversoft.tourist_facilitator.data.serverEntity.hotel.data;

public class Coordinates {

    private long id;

    private long latitude;
    private long longitude;

    public Coordinates(long latitude, long longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Coordinates() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}


