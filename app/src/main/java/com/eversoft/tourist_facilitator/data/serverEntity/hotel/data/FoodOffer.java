package com.eversoft.tourist_facilitator.data.serverEntity.hotel.data;

public class FoodOffer {
    //none,breakfast,lunch,dinner

    //Dining,Vending,Exercise,Recreation,SwimmingPool,Parking
    private long id;

    private String name;

    public FoodOffer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FoodOffer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
