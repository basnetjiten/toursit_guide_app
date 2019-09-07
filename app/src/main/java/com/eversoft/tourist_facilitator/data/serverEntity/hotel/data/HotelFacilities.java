package com.eversoft.tourist_facilitator.data.serverEntity.hotel.data;


public class HotelFacilities {
    //Dining,Vending,Exercise,Recreation,SwimmingPool,Parking
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public HotelFacilities() {
    }

    public HotelFacilities(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HotelFacilities{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
