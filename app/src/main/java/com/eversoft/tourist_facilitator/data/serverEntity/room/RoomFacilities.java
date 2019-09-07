package com.eversoft.tourist_facilitator.data.serverEntity.room;


public class RoomFacilities {
    //KitchenFacilities,Television,InternetAccess,Hairdryer,Towels

    private long id;

    private String name;

    public RoomFacilities() {
    }

    public RoomFacilities(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RoomFacilities{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
