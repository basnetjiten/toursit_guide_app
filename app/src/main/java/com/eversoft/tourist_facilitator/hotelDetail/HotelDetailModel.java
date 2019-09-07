package com.eversoft.tourist_facilitator.hotelDetail;

import android.graphics.Bitmap;

import com.eversoft.tourist_facilitator.data.NetworkServiceImpl;
import com.eversoft.tourist_facilitator.data.entity.LoginData;
import com.eversoft.tourist_facilitator.data.interfaces.NetworkService;
import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.ReservationRequest;
import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.ReservationResponse;
import com.eversoft.tourist_facilitator.hotelDetail.interfaces.HotelDetailModelOps;
import com.eversoft.tourist_facilitator.hotelDetail.interfaces.HotelDetailPresenterOps;
import com.eversoft.tourist_facilitator.hotelList.interfaces.ReservationRequestResult;
import com.eversoft.tourist_facilitator.login.interfaces.getPictureResult;



class HotelDetailModel implements HotelDetailModelOps {
    private final HotelDetailPresenterOps hoteDetailPresenter;

    //todo remove:, replace with callback
    private final NetworkService networkService;

    public HotelDetailModel(HotelDetailPresenterOps hotelDetailPresenterOps, NetworkServiceImpl networkService) {
        this.hoteDetailPresenter = hotelDetailPresenterOps;
        this.networkService = networkService;
    }

    @Override
    public void reserveRoom(final ReservationRequest reservationRequest, LoginData loginData) {
        networkService.reservationRequest(reservationRequest, loginData, new ReservationRequestResult() {
            @Override
            public void reservationRequestFailure(String s) {
                hoteDetailPresenter.reservationRequestFailure(s);
            }

            @Override
            public void reservationRequestResult(ReservationResponse reservationResponse) {
                hoteDetailPresenter.reservationRequestResult(reservationResponse);
            }

            @Override
            public void reservationRequestReject(ReservationResponse reservationResponse) {
                hoteDetailPresenter.reservationRequestReject(reservationResponse);
            }
        });
    }

    @Override
    public void getPicture(String picturePath) {
        networkService.getPicture(picturePath, new getPictureResult(){
            @Override
            public void getPictureResultOk(Bitmap bitmap) {
                hoteDetailPresenter.getPictureResultOk( bitmap);
            }

            @Override
            public void getPictureResultNOk() {
                hoteDetailPresenter.getPictureResultNOk();
            }
        });
    }
}
