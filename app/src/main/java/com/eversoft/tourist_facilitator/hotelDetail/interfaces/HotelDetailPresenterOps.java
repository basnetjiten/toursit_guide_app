package com.eversoft.tourist_facilitator.hotelDetail.interfaces;

import android.graphics.Bitmap;

import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.ReservationResponse;



public interface HotelDetailPresenterOps {
    void onStartup();

    void reservation(long roomId);

    void confirmReservation();

    void rejectReservation();

    void reservationRequestReject(ReservationResponse reservationResponse);

    void reservationRequestResult(ReservationResponse reservationRequest);

    void reservationRequestFailure(String s);

    void ReservationSuccessDialogDisappear();

    void showReservationRequested();

    void getPictureResultOk(Bitmap bitmap);

    void getPictureResultNOk();
}
