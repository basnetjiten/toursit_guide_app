package com.eversoft.tourist_facilitator.data.serverEntity.hotel.data;

public class Rating {

    private long id;

    private int value;

    public Rating() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
