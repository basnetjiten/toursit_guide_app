package com.eversoft.tourist_facilitator.data.serverEntity.room;


import java.util.List;


public class Room {
    private long id;

    private String name;
    private String description;
    private int size;
    private float price;

    private List<RoomFacilities> roomFacilities;

    public Room() {
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<RoomFacilities> getRoomFacilities() {
        return roomFacilities;
    }

    public void setRoomFacilities(List<RoomFacilities> roomFacilities) {
        this.roomFacilities = roomFacilities;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", size=" + size +
                ", price=" + price +
                ", roomFacilities=" + roomFacilities +
                '}';
    }
}
