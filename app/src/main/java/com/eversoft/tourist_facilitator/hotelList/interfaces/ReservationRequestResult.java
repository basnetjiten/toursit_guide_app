package com.eversoft.tourist_facilitator.hotelList.interfaces;

import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.ReservationResponse;



public interface ReservationRequestResult {
    void reservationRequestFailure(String s);

    void reservationRequestResult(ReservationResponse reservationResponse);

    void reservationRequestReject(ReservationResponse reservationResponse);
}
