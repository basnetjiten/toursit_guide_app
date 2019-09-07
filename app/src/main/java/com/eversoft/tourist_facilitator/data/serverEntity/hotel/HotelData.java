package com.eversoft.tourist_facilitator.data.serverEntity.hotel;


import com.eversoft.tourist_facilitator.data.serverEntity.room.Room;

import java.util.List;


public class HotelData {

    private HotelDetail hotelDetail;
    private List<Room> roomList;

    public HotelData(HotelDetail hotelDetail, List<Room> roomList) {
        this.hotelDetail = hotelDetail;
        this.roomList = roomList;
    }

    public HotelData() {
    }

    public HotelDetail getHotelDetail() {
        return hotelDetail;
    }

    public void setHotelDetail(HotelDetail hotelDetail) {
        this.hotelDetail = hotelDetail;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    @Override
    public String toString() {
        return "HotelData{" +
                "hotelDetail=" + hotelDetail +
                ", roomList=" + roomList +
                '}';
    }
}
