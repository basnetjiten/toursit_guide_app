package com.eversoft.tourist_facilitator.reservation.interfaces;

import com.eversoft.tourist_facilitator.data.entity.LoginData;
import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.RateRequest;



public interface ReservationModelOps {
    void downloadUserReservation(LoginData loginData);

    void rateRequest(LoginData loginData, RateRequest rateRequest);
}
