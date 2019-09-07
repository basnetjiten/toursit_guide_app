package com.eversoft.tourist_facilitator.reservation.interfaces;

import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.UserReservationResponse;
import com.eversoft.tourist_facilitator.data.serverEntity.hotel.data.ResultStatus;



public interface RateRequestResult {
    void rateResultOk();
    void rateResultNok(String s);
}
