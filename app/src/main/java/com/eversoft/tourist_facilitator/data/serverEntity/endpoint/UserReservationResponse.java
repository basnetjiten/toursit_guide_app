package com.eversoft.tourist_facilitator.data.serverEntity.endpoint;


import com.eversoft.tourist_facilitator.data.serverEntity.hotel.data.ResultStatus;

import java.util.ArrayList;



public class UserReservationResponse {

    ResultStatus status;

    ArrayList<ReservationData> reservationDataArrayList;

    public UserReservationResponse() {

    }

    public ArrayList<ReservationData> getReservationDataArrayList() {
        return reservationDataArrayList;
    }

    public void setReservationDataArrayList(ArrayList<ReservationData> reservationDataArrayList) {
        this.reservationDataArrayList = reservationDataArrayList;
    }

    public ResultStatus getStatus() {
        return status;
    }

    public void setStatus(ResultStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserReservationResponse{" +
                "status=" + status +
                ", reservationDataArrayList=" + reservationDataArrayList +
                '}';
    }
}
