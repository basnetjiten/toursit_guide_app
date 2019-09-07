package com.eversoft.tourist_facilitator.data.serverEntity.endpoint;

import com.eversoft.tourist_facilitator.data.serverEntity.hotel.HotelData;
import com.eversoft.tourist_facilitator.data.serverEntity.hotel.data.ResultStatus;

import java.util.ArrayList;


public class HotelOffer {

    ResultStatus status;

    private ArrayList<HotelData> hotelData = new ArrayList<>();

    public HotelOffer() {
    }

    public HotelOffer(ResultStatus status) {
        this.status = status;
    }

    public ResultStatus getStatus() {
        return status;
    }

    public void setStatus(ResultStatus status) {
        this.status = status;
    }

    public ArrayList<HotelData> getHotelData() {
        return hotelData;
    }

    public void setHotelData(ArrayList<HotelData> hotelData) {
        this.hotelData = hotelData;
    }

    @Override
    public String toString() {
        return "HotelOffer{" +
                "status=" + status +
                ", hotelData=" + hotelData +
                '}';
    }

    public void addHotelData(HotelData hotelData) {
        this.hotelData.add(hotelData);
    }
}
