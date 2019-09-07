package com.eversoft.tourist_facilitator.reservation.interfaces;

import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.UserReservationResponse;



public interface UserReservationResult {

    void userReservationNotAvailable();

    void userReservationResult(UserReservationResponse userReservationResponse);

    void reservationRequestFailure(String s);
}
