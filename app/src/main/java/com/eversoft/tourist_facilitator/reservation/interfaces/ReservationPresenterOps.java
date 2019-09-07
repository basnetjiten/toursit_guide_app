package com.eversoft.tourist_facilitator.reservation.interfaces;

import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.RateRequest;
import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.ReservationData;
import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.UserReservationResponse;




public interface ReservationPresenterOps {
    void onStartup();

    void userReservationNotAvailable();

    void userReservationResult(UserReservationResponse userReservationResponse);

    void reservationRequestFailure(String s);

    void listItemSelect(ReservationData item);

    void showFilterRequested();

    void rateConfirm(RateRequest rateRequest);

    void rateResultOk();

    void rateResultNok(String s);
}
