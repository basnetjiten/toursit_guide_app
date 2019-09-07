package com.eversoft.tourist_facilitator.hotelDetail.interfaces;

import com.eversoft.tourist_facilitator.data.entity.LoginData;
import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.ReservationRequest;


public interface HotelDetailModelOps {
    void reserveRoom(ReservationRequest reservationRequest, LoginData loginData);

    void getPicture(String picturePath);
}
